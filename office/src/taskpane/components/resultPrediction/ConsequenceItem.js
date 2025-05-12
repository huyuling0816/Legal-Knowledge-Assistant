import * as React from "react";
import { useState, useMemo } from "react";
import ArticleItem from "../editLaw/ArticleItem";

export default function ConsequenceItem({ consequence, relatedArticle }) {
  return (
    <div style={{ marginBottom: 25, marginLeft: 15, marginRight:15 }}>
      <div style={{marginBottom: 10}}>
        <i className="el-icon-information" style={{color: "gray", marginRight:5}}></i>
        <span style={{ fontSize: "15px" }}>{consequence}</span>
      </div>
      {/* <div>
        <hr style={{color: "lightgray"}}></hr>
      </div> */}
      <div>
        <ArticleItem realtedArticle={relatedArticle}></ArticleItem>
      </div>
    </div>
  );
}
