import * as React from "react";
import { Collapse, Dialog, Button } from "element-react";
import {
  getAttachedDoc,
  deleteAttachedDoc,
  getAttachedArticles,
  deleteAttachedArticle,
  getMentionedFiles,
} from "../../../api/attachedLaw";
import { useState } from "react";
import DocItem from "../editLaw/DocItem";
import ArticleItem from "../editLaw/ArticleItem";
import { convertArticleToTreeData, getParentsOfArticleTree } from "../editLaw/DocItem";

export default function AttachedLaws() {
  //   addRelatedDoc("MmM5MDlmZGQ2NzhiZjE3OTAxNjc4YmY2NGYyODAzOWQ%3D");

  const [relatedDocs, setRelatedDocs] = useState([]);
  const [dialogVisible, setDialogVisible] = useState(false);
  const [chosenDocId, setChosenDocId] = useState("");
  const [relatedArticles, setRelatedArticles] = useState([]);
  const [isDeletingDoc, setIsDeletingDoc] = useState(true);
  const [chosenArticleId, setChosenArticleId] = useState("");

  React.useEffect(() => {
    const fetchData = async () => {
      const attachedDocs = await getAttachedDoc();
      setRelatedDocs(attachedDocs);
      const attachedArticles = await getAttachedArticles();
      var newArticles = [];
      for (var article of attachedArticles) {
        var newArticle = convertArticleToTreeData(article, true);
        newArticle = getParentsOfArticleTree(newArticle);
        newArticles.push(newArticle);
      }
      setRelatedArticles(newArticles);
    };
    fetchData();
  }, []);

  async function deleteDocItem(docId) {
    setDialogVisible(false);
    setRelatedDocs(relatedDocs.filter((doc) => doc.id != docId));
    await deleteAttachedDoc(docId);
  }

  async function deleteArticleItem(articleId) {
    setDialogVisible(false);
    setRelatedArticles(relatedArticles.filter((article) => article.id != articleId));
    await deleteAttachedArticle(articleId);
  }

  const docs = relatedDocs.map((doc) => (
    <DocItem
      relatedDoc={doc}
      key={doc.id}
      deleteItem={() => {
        setIsDeletingDoc(true);
        setChosenDocId(doc.id);
        setDialogVisible(true);
      }}
    ></DocItem>
  ));

  const articles = relatedArticles.map((article) => (
    <ArticleItem
      realtedArticle={article}
      key={article.id}
      deleteItem={() => {
        setIsDeletingDoc(false);
        setChosenArticleId(article.id);
        setDialogVisible(true);
      }}
    ></ArticleItem>
  ));

  const activeName = "relatedDocs";
  return (
    <div>
      <div>
        <Button
          style={{ marginTop: 15, marginBottom: 15 }}
          onClick={async () => {
            await getMentionedFiles();
            var newAttachedDocs=await getAttachedDoc();
            setRelatedDocs(newAttachedDocs);
          }}
        >
          解析法律
        </Button>
      </div>
      <Collapse value={["relatedDocs", "relatedArticles"]}>
        <Collapse.Item title="相关法律" name="relatedDocs">
          {docs}
        </Collapse.Item>
        <Collapse.Item title="相关条款" name="relatedArticles">
          {articles}
        </Collapse.Item>
      </Collapse>

      <Dialog
        title="提示"
        size="tiny"
        visible={dialogVisible}
        onCancel={() => setDialogVisible(false)}
        lockScroll={false}
      >
        <Dialog.Body>
          <span>确定删除该法条吗？</span>
        </Dialog.Body>
        <Dialog.Footer className="dialog-footer">
          <Button size="mini" onClick={() => setDialogVisible(false)}>
            取消
          </Button>
          <Button
            size="mini"
            type="primary"
            onClick={() => (isDeletingDoc ? deleteDocItem(chosenDocId) : deleteArticleItem(chosenArticleId))}
          >
            确定
          </Button>
        </Dialog.Footer>
      </Dialog>
    </div>
  );
}
