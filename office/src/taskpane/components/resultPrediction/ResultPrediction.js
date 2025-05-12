import * as React from "react";
import { useState, useMemo } from "react";
import { Input, Button } from "element-react";
import ConsequenceItem from "./ConsequenceItem";
import { getLogic } from "../../../api/logic";
import { convertArticleToTreeData } from "../editLaw/DocItem";

export default function ResultPrediction() {
  const [selectedText, setSelectedText] = useState("");
  const [cnt, setCnt] = useState(0);
  const [logic, setLogic] = useState([]);

  var consequence = "This is a consequence.";
  var relatedArticle = {
    id: "123456",
    content: "articleContent",
    docTitle: "docTitle",
    seq: "articleSeq",
    docId: "docId",
  };

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

  async function getConsequenceAndArticle() {
    var logicVos = await getLogic(selectedText);
    for (var vo of logicVos) {
      vo.articleTree = convertArticleToTreeData(vo.articleVo);
    }
    console.log(logicVos);
    setLogic(logicVos);
  }

  const consequences = logic.map((logic) => (
    <ConsequenceItem
      consequence={logic.consequence}
      relatedArticle={logic.articleTree}
      key={logic.id}
    ></ConsequenceItem>
  ));

  return (
    <div>
      <div style={{ display: "inline-flex", alignItems: "center", width: "100%" }}>
        <Input
          type="textarea"
          autosize={{ minRows: 2 }}
          placeholder="请输入内容"
          style={{
            width: "70%",
            margin: 15,
          }}
          value={selectedText}
          onChange={(e) => {
            setSelectedText(e);
          }}
        ></Input>
        <Button type="primary" onClick={getConsequenceAndArticle}>
          获取法律后件
        </Button>
      </div>
      <div>
        {/* <ConsequenceItem consequence={consequence} relatedArticle={relatedArticle}></ConsequenceItem>
        <ConsequenceItem consequence={consequence} relatedArticle={relatedArticle}></ConsequenceItem> */}
        {consequences}
      </div>
    </div>
  );
}
