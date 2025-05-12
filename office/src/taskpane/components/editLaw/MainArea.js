import * as React from "react";
import { useState, useMemo } from "react";
import { Button, Tag, Radio, Tabs, Select } from "element-react";
import { getRelatedArticleList } from "../../../api/note.js";
import { queryLaw, getArticlesByContent } from "../../../api/law.js";
import { getQueryLawPayload } from "../../../utils/QueryUtil.js";
import ArticleItem from "./ArticleItem.js";
import DocItem, { convertArticleToTreeData, getParentsOfArticleTree } from "./DocItem.js";
/* global Word, require, Office, console */
export default function MainArea() {
  const [selectedText, setSelectedText] = useState("");
  const [cnt, setCnt] = useState(0);
  const [allRelatedArticles, setAllRelatedArticles] = useState([]);
  const [relatedDocs, setRelatedDocs] = useState([]);
  const [searchByTitle, setSearchByTitle] = useState(true);
  const [DocsFromRelatedArticles, setDocsFromRelatedArticles] = useState([]);
  const [selectorValue, setSelectorValue] = useState("");
  const [selectedRelatedArticles, setSelectedRelatedArticles]=useState([]);

  Office.context.document.addHandlerAsync(Office.EventType.DocumentSelectionChanged, myHandler);

  // Office.context.document.getFilePropertiesAsync(function (asyncResult) {
  //   console.log(asyncResult);
  // });

  async function myHandler(eventArgs) {
    eventArgs.document.getSelectedDataAsync(Office.CoercionType.Text, async function (asyncResult) {
      if (asyncResult.status == Office.AsyncResultStatus.Failed) {
        console.log("Action failed. Error: " + asyncResult.error.message);
      } else {
        if (!(asyncResult.value == "" || asyncResult.value == selectedText)) {
          setSelectedText(asyncResult.value);
          setCnt(cnt + 1);
          console.log("Selected data: " + asyncResult.value);
          await fetchContent(asyncResult.value);
        }
      }
    });
  }
  async function fetchContent(text) {
    console.log("fetchContent:");
    console.log(text);
    if (text == "") {
      return;
    } else {
      // clear();
      // if (searchByTitle === true) {
      //   await fetchRelatedDocs(text);
      // } else {
      //   await fetchIntepretationArticles(text);
      // }
      fetchRelatedDocs(text);
      fetchIntepretationArticles(text);
    }
  }

  //TODO: 假数据
  async function fetchRelatedDocs(text) {
    let param = getQueryLawPayload(text);
    let res = await queryLaw(param);
    // console.log(res.data.data);
    setRelatedDocs(res.data.data);
    return res;
  }
  // function fetchRelatedDocs(text) {
  //   console.log("fetch docs");
  //   var doc = {
  //     id: "docId",
  //     title: "docTitle",
  //     office: "office",
  //     publish: "2024-04-01",
  //     expiry: "2024-04-01",
  //     status: "docStatus",
  //     type: "docType",
  //     suffix: "suffix",
  //     docText: "docText",
  //     articleSum: 1,
  //     divided: 1,
  //     docUrl: "docUrl",
  //     fullContent: "fullContent",
  //   };
  //   var docs = [];
  //   docs.push(doc);
  //   setRelatedDocs(docs);
  //   return docs;
  // }

  //TODO: 此处为假数据
  async function fetchIntepretationArticles(text) {
    let res = await getArticlesByContent(text);
    console.log(res.data);

    // var articles = [];
    // var article = {
    //   id: "id",
    //   // articleName: "articleName",
    //   seq: "articleSeq",
    //   content: "articleContent"
    // };
    // articles.push(article);

    var newArticles = [];
    var docTitles = [];
    for (var article of res.data) {
      if (!docTitles.includes(article.docTitle)) {
        docTitles.push(article.docTitle);
      }
      setDocsFromRelatedArticles(docTitles);

      var newArticle = convertArticleToTreeData(article, true);
      newArticle = getParentsOfArticleTree(newArticle);
      newArticles.push(newArticle);
      console.log(newArticle);
    }

    setAllRelatedArticles(newArticles);
    setSelectedRelatedArticles(newArticles);
    return newArticles;
  }
  // function fetchIntepretationArticles(text) {
  //   console.log("fetch articles");
  //   var articles = [];
  //   var article = {
  //     id: "id",
  //     // articleName: "articleName",
  //     seq: "articleSeq",
  //     content: "articleContent"
  //   };
  //   articles.push(article);
  //   setRelatedArticles(articles);
  //   console.log(articles);
  //   return articles;
  // }

  function clear() {
    setAllRelatedArticles([]);
    setRelatedDocs([]);
  }
  function deleteArticleItem(articleId) {
    setSelectedRelatedArticles(selectedRelatedArticles.filter((article) => article.id != articleId));
  }
  function deleteDocItem(docId) {
    setRelatedDocs(relatedDocs.filter((doc) => doc.id != docId));
  }
  const docs = relatedDocs.map((doc) => (
    <DocItem relatedDoc={doc} key={doc.id} deleteItem={() => deleteDocItem(doc.id)}></DocItem>
  ));

  const articles = selectedRelatedArticles.map((article) => (
    <ArticleItem
      realtedArticle={article}
      key={article.id}
      deleteItem={() => deleteArticleItem(article.id)}
    ></ArticleItem>
  ));

  function onChange(value) {
    const newValue = !searchByTitle;
    setSearchByTitle(newValue);
    // searchByTitle->找docs !searchByTitle->找articles
    // setRelatedArticles([]);
    // setRelatedDocs([]);
    fetchContent(selectedText);
  }

  if (searchByTitle === true) {
    return (
      <section>
        {/* <div onClick={() => fetchContent(selectedText)}>Welcome to lkms</div> */}
        <div>
          <Tabs
            activeName="1"
            onTabClick={(tab) => (tab.props.name == "1" ? setSearchByTitle(true) : setSearchByTitle(false))}
          >
            <Tabs.Pane label="按标题检索" name="1"></Tabs.Pane>
            <Tabs.Pane label="按内容检索" name="2"></Tabs.Pane>
          </Tabs>
        </div>
        {/* <div>
          <Radio value="1" checked={searchByTitle === true} onChange={onChange.bind(this)}>
            按名称检索
          </Radio>
          <Radio value="0" checked={searchByTitle === false} onChange={onChange.bind(this)}>
            按内容检索
          </Radio>
        </div> */}
        <Tag type="success" onClick={clear} style={{ marginBottom: "10px" }}>
          查询法律：
        </Tag>
        <span>{selectedText}</span>
        <br />
        <div>{docs}</div>
      </section>
    );
  } else {
    return (
      <section>
        {/* <div onClick={() => fetchContent(selectedText)}>Welcome to lkms</div> */}
        <div>
          <Tabs
            activeName="1"
            onTabClick={(tab) => (tab.props.name == "1" ? setSearchByTitle(true) : setSearchByTitle(false))}
          >
            <Tabs.Pane label="按标题检索" name="1"></Tabs.Pane>
            <Tabs.Pane label="按内容检索" name="2"></Tabs.Pane>
          </Tabs>
          {/* <Radio value="1" checked={searchByTitle === true} onChange={onChange.bind(this)}>
            按名称检索
          </Radio>
          <Radio value="0" checked={searchByTitle === false} onChange={onChange.bind(this)}>
            按内容检索
          </Radio> */}
        </div>
        <div>
          <Tag type="success" onClick={clear} style={{ marginBottom: "10px" }}>
            查询条款：
          </Tag>
          <span>{selectedText}</span>
          <span>
            <Select
              value={selectorValue}
              placeholder="选择法律"
              style={{ float: "right", marginRight: 15, width: 250 }}
              clearable={true}
              onChange={(value)=>{
                if(value==""){
                  setSelectedRelatedArticles(allRelatedArticles);
                }
                else{
                  setSelectedRelatedArticles(allRelatedArticles.filter((article) => article.docTitle == value));
                }
              }}
            >
              {DocsFromRelatedArticles.map((el) => {
                return <Select.Option key={el} label={el} value={el} style={{maxWidth:250}}/>;
              })}
            </Select>
          </span>
        </div>

        <br />
        {/* <Tag type="primary" style={{ margin: "2px" }}>
      找到{relatedArticles&&relatedArticles.length} 条
    </Tag> */}
        <div>{articles}</div>
      </section>
    );
  }
}
