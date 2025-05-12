import * as React from "react";
import { useState, useMemo } from "react";
import { Button, Tag, Card, Dialog } from "element-react";
import { getSimpleDoc, getFullArticlesByDocId } from "../../../api/law";
import ArticleDetail from "./ArticleDetail";
import { convertArticleToTreeData, getParentsOfArticleTree } from "./DocItem";
import { addAttachedArticle } from "../../../api/attachedLaw";
/* global Word, require, Office, console */
export default function ArticleItem({ realtedArticle, deleteItem }) {
  const [doc, setDoc] = useState({});
  const [dialogVisible, setDialogVisible] = useState(false);
  const [allArticlesInDoc, setAllArticlesInDoc] = useState([]);
  const [docTitle, setDocTitle] = useState("");
  // var allArticlesInDoc = [];
  React.useEffect(() => {
    (async () => {
      //TODO: 此处为假数据
      // getSimpleDoc(realtedArticle.docId).then(res=>{
      //   setDoc(res.data)
      // });
      var doc = {
        id: "docId",
        title: "docTitle",
        office: "office",
        publish: "2024-04-01",
        expiry: "2024-04-01",
        status: "docStatus",
        type: "docType",
        suffix: "suffix",
        docText: "docText",
        articleSum: 1,
        divided: 1,
        docUrl: "docUrl",
        fullContent: "fullContent",
      };
    })();
  }, []);
  // const computedItems = useMemo(() => {
  //   let content = realtedArticle.articleName;
  //   content = content.replaceAll("@Para@", "@");
  //   content = content.replaceAll("@SubP@", "@");
  //   content = content.replaceAll("@Item@", "@");
  //   let filteredContent = content.split("@");
  //   return filteredContent.map((item) => <div dangerouslySetInnerHTML={{ __html: item }}></div>);
  // }, [realtedArticle]);

  function filterContentString() {
    let content = realtedArticle.seq + "\r\n" + realtedArticle.content;
    content = content.replaceAll("@Para@", "\r\n");
    content = content.replaceAll("@SubP@", "\r\n");
    content = content.replaceAll("@Item@", "\r\n");
    content = content.replaceAll("<font color='red'>", "");
    content = content.replaceAll("</font>", "");
    return content;
  }
  async function addToWord() {
    return Word.run(async (context) => {
      //在前面插入
      const doc = context.document;
      const originalRange = doc.getSelection();
      originalRange.insertText(filterContentString(), Word.InsertLocation.after);
      //加载并等待text属性完成
      originalRange.load("text");
      await context.sync();
      await addAttachedArticle(realtedArticle.id);
    });
  }

  function showContent(article) {
    if (!article.children) {
      return <div dangerouslySetInnerHTML={{ __html: article.content }}></div>;
    }
    return <ArticleDetail article={article}></ArticleDetail>;
  }

  async function seeFullDoc() {
    setDialogVisible(true);
    setDocTitle(realtedArticle.docTitle);
    let response = await getFullArticlesByDocId(realtedArticle.docId.replaceAll('%','%25'));
    let rawArticleData = response.data;
    var articleTrees = [];
    for (var article of rawArticleData) {
      var articleTreeData = convertArticleToTreeData(article);
      articleTreeData = getParentsOfArticleTree(articleTreeData);
      articleTrees.push(articleTreeData);
    }
    console.log(articleTrees);
    // setAllArticlesInDoc(
    //   articleTrees.map((article) => {
    //     <ArticleItem realtedArticle={article} key={article.id}></ArticleItem>;
    //   })
    // );
    let allArticles = articleTrees.map((article) => (
      <ArticleItem realtedArticle={article} key={article.id}></ArticleItem>
    ));
    setAllArticlesInDoc(allArticles);

    // console.log(allArticlesInDoc);
  }

  return (
    <div>
      <Card
        className="box-card"
        style={{
          marginBottom: 15,
          marginLeft: 15,
          marginRight: 15,
        }}
        header={
          <div style={{ margin: "0 auto" }}>
            <span>
              {realtedArticle.docTitle ? (
                <div>
                  <span style={{ fontWeight: "bold" }}>{realtedArticle.docTitle}</span>
                  {/* <Button
                    icon="search"
                    size="mini"
                    style={{ borderStyle: "none", color: "gray" }}
                    onClick={seeFullDoc}
                  ></Button> */}
                </div>
              ) : (
                <div></div>
              )}
              <span style={{ margin: "2px", fontWeight: "bold" }}>{realtedArticle.seq}</span>
              {/* <Button icon="edit" size="mini" style={{ borderStyle: "none", color:"gray" }} onClick={addToWord}></Button> */}
            </span>
            <span style={{float:"right"}}>
              <Button.Group>
                <Button icon="search" size="small" style={{ color: "gray" }} onClick={seeFullDoc}></Button>
                <Button icon="edit" size="small" style={{ color: "gray" }} onClick={addToWord}></Button>
                <Button icon="close" size="small" style={{ color: "gray" }} onClick={deleteItem}></Button>
              </Button.Group>
            </span>


            {/* <span style={{ float: "right" }}>
            <Button style={{ margin: "2px" }} onClick={addToWord} type="success" plain={true}>
              添加
            </Button>
            <Button style={{ margin: "2px" }} onClick={deleteItem} type="danger" plain={true}>
              移除
            </Button>
          </span> */}
          </div>
        }
      >
        {/* <div>{computedItems}</div> */}
        {showContent(realtedArticle)}
      </Card>

      <Dialog
        title={docTitle}
        size="full"
        visible={dialogVisible}
        onCancel={() => setDialogVisible(false)}
        lockScroll={false}
      >
        <Dialog.Body>{allArticlesInDoc}</Dialog.Body>
        {/* <Dialog.Footer className="dialog-footer">
          <Button onClick={() => setDialogVisible(false)}>取消</Button>
          <Button type="primary" onClick={() => setDialogVisible(false)}>
            确定
          </Button>
        </Dialog.Footer> */}
      </Dialog>
    </div>
  );
}
