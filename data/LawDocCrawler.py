import requests
import os
import time
import pymysql

conn = pymysql.connect(host='127.0.0.1', port=3306, user='root', passwd='', db='law_info_db_test',
                       charset='utf8')
cursor = conn.cursor()
filename = '地方性法规'
doc_category = '地方性法规'


def run(pageNum):
    url = 'https://flk.npc.gov.cn/api/?'
    data = {
        'type': 'dfxfg',
        'searchType': 'title;vague',
        'sortTr': 'f_bbrq_s;desc',
        'gbrqStart': '',
        'gbrqEnd': '',
        'sxrqStart': '',
        'sxrqEnd': '',
        'sort': 'true',
        'page': pageNum,
        'size': '10',
        '_': '1704800783355',
    }
    headers = {
        'User-Agent':
            'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/121.0.0.0 Safari/537.36',
        'Cookie': "_yfx_session_10008821=%7B%22_yfx_firsttime%22%3A%221710293038852%22%2C%22_yfx_lasttime%22%3A%221710293038852%22%2C%22_yfx_visittime%22%3A%221710293038852%22%2C%22_yfx_domidgroup%22%3A%221710293038852%22%2C%22_yfx_domallsize%22%3A%22100%22%2C%22_yfx_cookie%22%3A%2220240313092358855221762711290953%22%7D; wzws_sessionid=gTAyYWFhYaBl/PS6gmZhYjg1ZoA1OC4yMTMuOC4xNzU=; Hm_lvt_54434aa6770b6d9fef104d146430b53b=1711007036,1711011549,1711017300,1711076539; wzws_cid=5101944a44d1dd45b44b63754d17f8fb996c2eee09a3673bb3d8f28941ee34375211f496af585d9a62b5034d9eedb6a746f62120273fd0dd574178cb88fcba11e9cdc2923843eddb86a60d8324d1716d; Hm_lpvt_54434aa6770b6d9fef104d146430b53b=1711417982",
        # 'Accept-Encoding': 'gzip, deflate, br, zstd'
    }
    response = requests.get(url=url, params=data, headers=headers)
    print(response.text)
    data_json = response.json()
    for index in data_json['result']['data']:
        id = index['id']
        title = index['title']
        url = 'https://flk.npc.gov.cn/api/detail'
        data = {
            'id': id
        }
        new_data = requests.post(url=url, data=data, headers=headers).json()
        body = new_data['result']['body']
        for item in body:
            if item['type'] == 'WORD':
                if item['path'] is None:
                    break
                down_load = 'https://wb.flk.npc.gov.cn' + item['path']
                name = item['path'].split('.')[-1]
                res = requests.get(url=down_load, headers=headers)
                content = res.content
                publish = index['publish']
                expiry = index['expiry']
                suffix = item['path'][item['path'].rfind('.') + 1:]
                if publish == '' and expiry == '':
                    sql = "INSERT INTO law_0_doc (title, office, status, type, doc_category, docURL, id, divided, suffix)" \
                          "VALUES (%s, %s, %s, %s, %s, %s, %s, '0', %s)"
                    data = (index['title'], index['office'], index['status'], index['type'], doc_category, item['url'],
                            index['id'], suffix)
                elif publish == '':
                    sql = "INSERT INTO law_0_doc (title, office, expiry, status, type, doc_category, docURL, id, divided, suffix)" \
                          "VALUES (%s, %s, %s, %s, %s, %s, %s, %s, '0', %s)"
                    data = (index['title'], index['office'], expiry, index['status'], index['type'], doc_category,
                            item['url'], index['id'], suffix)
                elif expiry == '':
                    sql = "INSERT INTO law_0_doc (title, office, publish, status, type, doc_category, docURL, id, divided, suffix)" \
                          "VALUES (%s, %s, %s, %s, %s, %s, %s, %s, '0', %s)"
                    data = (index['title'], index['office'], publish, index['status'], index['type'], doc_category,
                            item['url'], index['id'], suffix)
                else:
                    sql = "INSERT INTO law_0_doc (title, office, publish, expiry, status, type, doc_category, docURL, id, divided, suffix)" \
                          "VALUES (%s, %s, %s, %s, %s, %s, %s, %s, %s, '0', %s)"
                    data = (index['title'], index['office'], publish, expiry, index['status'], index['type'],
                            doc_category, item['url'], index['id'], suffix)
                cursor.execute(sql, data)
                conn.commit()
                with open(os.path.join(filename, title + '.' + name), mode='wb') as f:
                    f.write(content)
                print(title, down_load, name)


if not os.path.exists(filename):
    os.mkdir(filename)

for pageNum in range(1, 2146):
    print(pageNum)
    run(pageNum)
    time.sleep(5)
    if pageNum % 5 == 0:
        time.sleep(480)

cursor.close()
conn.close()
