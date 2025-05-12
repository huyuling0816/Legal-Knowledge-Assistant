import * as React from "react";
import { useState } from "react";
import { Button, Tag, Card, Dialog } from "element-react";
import { getSimpleDoc, getSimpleArticleByDocId, getFullArticlesByDocId } from "../../../api/law.js";
import ArticleItem from "./ArticleItem.js";
import DocDetail from "./ArticleDetail.js";
import { addAttachedDoc } from "../../../api/attachedLaw.js";

/* global Word, require, Office, console */
export default function DocItem({ relatedDoc, deleteItem }) {
  const [dialogVisible, setDialogVisible] = useState(false);
  const [articles, setArticles] = useState([]);
  let docTitle = relatedDoc.title.replaceAll("<font color='red'>", "");
  docTitle = docTitle.replaceAll("</font>", "");
  async function addToWord() {
    return Word.run(async (context) => {
      //在前面插入
      const doc = context.document;
      const originalRange = doc.getSelection();
      let content = relatedDoc.title;
      content = content.replaceAll("<font color='red'>", "");
      content = content.replaceAll("</font>", "");
      originalRange.insertText(`《${content}》`, Word.InsertLocation.after);
      //加载并等待text属性完成
      originalRange.load("text");
      await context.sync();
      await addAttachedDoc(relatedDoc.id);
    });
  }

  async function seeDetail() {
    setDialogVisible(true);
    // var docId=relatedDoc.id.replaceAll('%','%%');
    // console.log(docId);
    var docId = relatedDoc.id.replaceAll("%", "%25");
    let response = await getFullArticlesByDocId(docId);
    let rawArticleData = response.data;

    // var rawArticle=rawArticleData[0];
    // var articleTree=convertArticleToTreeData(rawArticle);
    // articleTree=getParentsOfArticleTree(articleTree);
    // console.log(articleTree);

    var articleTrees = [];
    for (var article of rawArticleData) {
      var articleTreeData = convertArticleToTreeData(article, false);
      articleTreeData = getParentsOfArticleTree(articleTreeData);
      articleTrees.push(articleTreeData);
    }

    // for(var i=0;i<10;i++){
    //   console.log(articleTrees[i]);
    // }

    // let response = await getSimpleArticleByDocId(relatedDoc.id);
    // let articles=response.data
    //假数据
    setArticles(articleTrees);
  }
  function deleteArticleItem(articleId) {
    setArticles(articles.filter((article) => article.id != articleId));
  }
  const articleList = articles.map((article) => (
    <ArticleItem
      realtedArticle={article}
      key={article.id}
      deleteItem={() => deleteArticleItem(article.id)}
    ></ArticleItem>
  ));

  return (
    <div>
      <Card
        className="box-card"
        // header={
        //   <div>
        //     <span style={{ margin: "2px" }} dangerouslySetInnerHTML={{ __html: relatedDoc.title }}></span>
        //   </div>
        // }
        style={{
          marginBottom: 15,
          marginLeft: 15,
          marginRight: 15,
        }}
      >
        <div style={{ margin: "0 auto" }}>
          <span style={{ margin: "2px" }} dangerouslySetInnerHTML={{ __html: relatedDoc.title }}></span>
          <span style={{ float: "right" }}>
            <Button.Group>
              <Button icon="search" size="small" style={{ color: "gray" }} onClick={seeDetail}></Button>
              <Button icon="edit" size="small" style={{ color: "gray" }} onClick={addToWord}></Button>
              <Button icon="close" size="small" style={{ color: "gray" }} onClick={deleteItem}></Button>
            </Button.Group>

            {/* <Button style={{ margin: "2px" }} onClick={seeDetail} type="success" plain={true}>
              查看
            </Button> */}
            {/* <Button style={{ margin: "2px" }} onClick={addToWord} type="success" plain={true}>
              添加
            </Button>
            <Button style={{ margin: "2px" }} onClick={deleteItem} type="danger" plain={true}>
              移除
            </Button> */}
          </span>
        </div>
        {/* <div style={{ float: "right" }}>
          <Button style={{ margin: "2px" }} onClick={seeDetail} type="success" plain={true}>
            查看
          </Button>
          <Button style={{ margin: "2px" }} onClick={addToWord} type="success" plain={true}>
            添加
          </Button>
          <Button style={{ margin: "2px" }} onClick={deleteItem} type="danger" plain={true}>
            移除
          </Button>
        </div> */}
      </Card>
      <Dialog
        title={docTitle}
        size="full"
        visible={dialogVisible}
        onCancel={() => setDialogVisible(false)}
        lockScroll={false}
      >
        <Dialog.Body>{articleList}</Dialog.Body>
        <Dialog.Footer className="dialog-footer">
          <Button onClick={() => setDialogVisible(false)}>取消</Button>
          <Button type="primary" onClick={() => setDialogVisible(false)}>
            确定
          </Button>
        </Dialog.Footer>
      </Dialog>
    </div>
  );
}

function convertItemToTreeData(itemData) {
  var item = {
    id: itemData.id,
    seq: itemData.itemSeq,
    content: itemData.itemName,
  };
  return item;
}

function convertSubparaToTreeData(subparaData) {
  var subparagraph = {
    id: subparaData.id,
    seq: subparaData.subparagraphSeq,
    content: subparaData.subparagraphName,
  };
  if (subparaData.items != null && subparaData.items.length != 0) {
    subparagraph.children = [];
    for (var i = 0; i < subparaData.items.length; i++) {
      var item = convertItemToTreeData(subparaData.items[i]);
      // item.parent = subparaData.subparagraphSeq;
      subparagraph.children.push(item);
    }
  }
  return subparagraph;
}

function convertParagraphToTreeData(paraData) {
  var paragraph = {
    id: paraData.id,
    seq: paraData.paragraphSeq,
    content: paraData.paragraphName,
  };
  if (paraData.subparagraphs != null && paraData.subparagraphs.length != 0) {
    paragraph.children = [];
    for (var i = 0; i < paraData.subparagraphs.length; i++) {
      var subparaData = convertSubparaToTreeData(paraData.subparagraphs[i]);
      paragraph.children.push(subparaData);
    }
  }
  return paragraph;
}

export function convertArticleToTreeData(articleData, allowOnlyOneParagraph) {
  // console.log(articleData);
  var article = {
    id: articleData.id,
    seq: articleData.articleSeq,
    content: articleData.articleName,
    docId: articleData.docId,
  };
  if (articleData.docTitle) {
    article.docTitle = articleData.docTitle;
  }
  if (articleData.paragraphs != null && articleData.paragraphs.length != 0) {
    if (
      articleData.paragraphs.length == 1 &&
      (articleData.paragraphs[0].subparagraphs == null || articleData.paragraphs[0].subparagraphs.length == 0) &&
      !allowOnlyOneParagraph
    ) {
    } else {
      article.children = [];
      for (var i = 0; i < articleData.paragraphs.length; i++) {
        var paragraphData = convertParagraphToTreeData(articleData.paragraphs[i]);
        paragraphData.articleId = articleData.id;
        if (paragraphData.subparagraphs != null) {
          for (var subpara of paragraphData.subparagraphs) {
            subpara.articleId = articleData.id;
            if (subpara.items != null) {
              for (var item of subpara.items) {
                item.articleId = articleData.id;
              }
            }
          }
        }
        article.children.push(paragraphData);
      }
    }
  }
  return article;
}

export function getParentsOfArticleTree(article) {
  if (article.children) {
    for (var para of article.children) {
      if (para.children) {
        for (var subpara of para.children) {
          if (subpara.children) {
            for (var item of subpara.children) {
              item.parent = article.seq + " " + para.seq + " " + subpara.seq;
            }
          }
          subpara.parent = article.seq + " " + para.seq;
        }
      }
      para.parent = article.seq;
    }
  }
  return article;
}
