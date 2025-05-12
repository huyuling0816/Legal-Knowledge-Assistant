import * as React from "react";
import { useState } from "react";
import { Tree, Card, Button } from "element-react";
require("./ArticleDetailStyle.css");
import { addAttachedArticle } from "../../../api/attachedLaw";

export default class ArticleDetail extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      article: props.article,
      options: {
        children: "children",
        label: "seq",
      },
    };
  }

  addToWord(data) {
    return Word.run(async (context) => {
      //在前面插入
      const doc = context.document;
      const originalRange = doc.getSelection();
      var content=data.content;
      content = content.replaceAll("<font color='red'>", "");
      content = content.replaceAll("</font>", "");
      var textToInsert=data.parent+" "+data.seq+"\n"+content;
      originalRange.insertText(textToInsert, Word.InsertLocation.after);
      //加载并等待text属性完成
      originalRange.load("text");
      await context.sync();
      await addAttachedArticle(data.articleId);
    });
    // console.log('hello')
  }

  renderContent(nodeModel, data, store) {
    return (
      <span>
        {data.children ? (
          <span>
            <Button
              icon="edit"
              size="mini"
              style={{ borderStyle: "none" }}
              onClick={(event) => {
                event.stopPropagation();
                this.addToWord(data);
              }}
            ></Button>
            <span style={{ fontWeight: "bold", marginRight: 10 }}>{data.seq}</span>
            <span dangerouslySetInnerHTML={{ __html: data.content.split("（")[0] }}></span>
            {/* <span
              style={{
                display:"inline-flex",
                overflow: "hidden",
                whiteSpace: "nowrap",
                textOverflow: "ellipsis",
                width: "70%",
              }}
            >
              {data.content}
            </span> */}
          </span>
        ) : (
          <span>
            <Button
              icon="edit"
              size="mini"
              style={{ color: "gray", borderStyle: "none" }}
              onClick={(event) => {
                event.stopPropagation();
                this.addToWord(data);
              }}
            ></Button>
            <span style={{ fontWeight: "bold", marginRight: 10 }}>{data.seq}</span>
            <span dangerouslySetInnerHTML={{ __html: data.content }}></span>
          </span>
        )}
      </span>
    );
  }

  render() {
    return (
      <div>
        <div className="tree">
          <Tree
            data={this.state.article.children}
            options={this.state.options}
            renderContent={(...args) => this.renderContent(...args)}
            defaultExpandAll={true}
            style={{
              borderStyle: "none",
              lineHeight: 1,
            }}
          />
        </div>
      </div>
    );
  }
}
