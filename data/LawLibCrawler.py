import re
import uuid
import pymysql
import requests
from bs4 import BeautifulSoup

base_url = 'http://www.law-lib.com/law/lawml.asp?bbdw=%B9%FA%BC%D2%C3%DC%C2%EB%B9%DC%C0%ED%BE%D6'
base_url += '&pages='
# office = '全国人民代表大会'
headers = {
    'User-Agent':
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/121.0.0.0 Safari/537.36',
    # 'Accept-Encoding': 'gzip, deflate, br, zstd'
}
conn = pymysql.connect(host='127.0.0.1', port=3306, user='root', passwd='', db='law_info_db_test',
                       charset='utf8')
cursor = conn.cursor()


def get_basic_info():
    for page in range(1, 51):
        listResponse = requests.get(url=base_url + str(page), headers=headers)
        html = listResponse.content.decode("gbk", errors="ignore")
        bs = BeautifulSoup(html, "html.parser")
        docs = bs.select("body > div.w > div > div.w_l.fl.mainw > div > div.law.mb20 > ul > li")
        for doc in docs:
            doc_info = {}
            for item in doc.children:
                doc_info['title'] = item['title'].strip()
                doc_info['docURL'] = 'http://www.law-lib.com/law/' + item['href']
                doc_info['id'] = 'lib' + str(uuid.uuid4())
                break
            sql = 'select * from law_0_doc where title = %s'
            cursor.execute(sql, doc_info['title'])
            resInDoc = cursor.fetchall()
            # 已经在国家法律法规数据库爬取过
            if len(resInDoc) > 0:
                continue
            sql = 'select * from lib where title = %s'
            cursor.execute(sql, doc_info['title'])
            resInDoc = cursor.fetchall()
            # 已经在法律图书馆爬取过
            if len(resInDoc) > 0:
                continue
            sql = 'insert into lib (id, title, docURL, divided) values (%s, %s, %s, "0")'
            cursor.execute(sql, (doc_info['id'], doc_info['title'], doc_info['docURL']))
            conn.commit()
        print(page)


def traverse_all_docs():
    sql = "select * from lib"
    cursor.execute(sql)
    docs = cursor.fetchall()
    for doc in docs:
        url = doc[11]
        title = doc[0]
        response = requests.get(url=url, headers=headers)
        html = response.content.decode("gbk", errors="ignore")
        # 找不到指定的法规 将记录从数据库删除
        if html == '找不到指定的法规':
            sql = "delete from lib where title = %s"
            cursor.execute(sql, title)
            conn.commit()
            continue
        bs = BeautifulSoup(html, 'html.parser')
        publish = bs.find(string=re.compile(r'【颁布时间】(.*)'))
        expire = bs.find(string=re.compile(r'【失效时间】(.*)'))
        office = bs.find(string=re.compile(r'【颁布单位】(.*)'))
        if publish is not None:
            publish = publish.split('】')[1]
        if expire is not None:
            expire = expire.split('】')[1]
        if office is not None:
            office = office.split('】')[1]
        if publish:
            sql = 'update lib set publish = %s where title = %s'
            cursor.execute(sql, (publish, title))
            conn.commit()
        if expire:
            sql = 'update lib set expiry = %s where title = %s'
            cursor.execute(sql, (expire, title))
            conn.commit()
        if office:
            sql = 'update lib set office = %s where title = %s'
            cursor.execute(sql, (office, title))
            conn.commit()


traverse_all_docs()
conn.close()
