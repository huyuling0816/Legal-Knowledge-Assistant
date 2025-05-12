import * as React from "react";
import { useState } from "react";
import { Button, Tag } from "element-react";
import {
  getInterpretationArticleList,
  getKeywordCompletion,
  getRelatedArticleList,
  getDocTitleCompletion,
} from "../../api/note.js";
/* global Word, require, Office, console */
export default function Header({ title, logo, message }) {
  const [prompt, setPrompt] = useState("aaaa");
  const [prompt2, setPrompt2] = useState("...");
  const [cnt, setCnt] = useState(1);
  const [selectedText, setSelectedText] = useState("");
  //读取选中项目
  function getSelectedData() {
    Office.context.document.getSelectedDataAsync(Office.CoercionType.Text, function (asyncResult) {
      if (asyncResult.status == Office.AsyncResultStatus.Failed) {
        setPrompt2("Action failed. Error: " + asyncResult.error.message);
      } else {
        setPrompt2("Selected data: " + asyncResult.value);
      }
    });
  }
  //设置选中项目
  function setSelectedData() {
    Office.context.document.setSelectedDataAsync("Hello Worald!", function (asyncResult) {
      if (asyncResult.status == Office.AsyncResultStatus.Failed) {
        setPrompt(asyncResult.error.message);
      }
      setPrompt(asyncResult.status);
    });
  }

  async function sendRequest() {
    const param = "abb";
    var res = await getDocTitleCompletion(param);

    return Word.run(async (context) => {
      const doc = context.document;
      const originalRange = doc.getSelection();
      originalRange.insertText(res.data[0].title, Word.InsertLocation.before);
      originalRange.load("text");
      await context.sync();
    });
  }

  return (
    <section className="ms-welcome__header ms-bgColor-neutralLighter ms-u-fadeIn500">
      {/*<img width="90" height="90" src={logo} alt={title} title={title} />*/}
      <h1 className="ms-fontSize-su ms-fontWeight-light ms-fontColor-neutralPrimary">{prompt}</h1>
      <Tag type="warning">{selectedText}</Tag>
      <Button onClick={setSelectedData}>设置</Button>
      <Button onClick={getSelectedData}>读取2</Button>
      <Button type="primary" onClick={sendRequest}>
        发送
      </Button>
    </section>
  );
}
