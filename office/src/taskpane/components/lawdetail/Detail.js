import * as React from "react";
import { useState } from "react";
import { useStore } from "react-redux";
import { isStrNull } from "../../../utils/stringUtil";
import Article from "./Article";
import { Typography } from "antd";
import Part from "./Part";
import Chap from "./Chap";
import Sect from "./Sect";
import { articleSort } from "../../../utils/sortUtil";

export default function Detail() {
  const store = useStore().getState();
  const { Text } = Typography;
  const [dParts, setDParts] = useState([]);
  const [dChaps, setDChaps] = useState([]);
  const [dSects, setDSects] = useState([]);
  const [dArticles, setDArticles] = useState([]);
  const sParts = store.law.lawParts;
  const sChaps = store.law.lawChaps;
  const sSects = store.law.lawSects;
  const sArticles = store.law.lawArticles;
  // const sParts = useSelector((state) => state.law.lawParts);
  // const sChaps = useSelector((state) => state.law.lawChaps);
  // const sSects = useSelector((state) => state.law.lawSects);
  // const sArticles = useSelector((state) => state.law.lawArticles);
  React.useEffect(() => {
    // 复制一份 否则chaps和sChaps共享引用 只能读
    let sects = sSects.slice();
    let chaps = sChaps.slice();
    let parts = sParts.slice();
    // 属于sect的article
    for (let i = 0; i < sects.length; i++) {
      let sect = { ...sects[i] };
      if (sect.articles) continue;
      sect.articles = [];
      sect.articles = sArticles.filter((article) => article.sectionId === sect.id);
      // sect.articles.sort(articleSort);
      sects[i] = sect;
    }
    // 属于chap的sect与article
    for (let i = 0; i < chaps.length; i++) {
      let chap = { ...chaps[i] };
      if (chap.sects && chap.articles) continue;
      chap.sects = [];
      chap.sects = sects.filter((sect) => sect.chapterId === chap.id);
      chap.articles = [];
      chap.articles = sArticles.filter((article) => article.chapterId === chap.id && isStrNull(article.sectionId));
      // chap.articles.sort(articleSort);
      chaps[i] = chap;
    }
    // 属于part的chap sect article
    for (let i = 0; i < parts.length; i++) {
      let part = { ...parts[i] };
      if (part.chaps && part.sects && part.articles) continue;
      part.chaps = [];
      part.sects = [];
      part.articles = [];
      part.chaps = chaps.filter((chap) => chap.partId === part.id);
      // console.log(part.chaps)
      part.sects = sects.filter((sect) => isStrNull(sect.chapterId) && sect.partId === part.id);
      part.articles = sArticles.filter((article) => article.partId === part.id && isStrNull(article.chapterId));
      // part.articles.sort(articleSort);
      parts[i] = part;
    }
    // console.log(chaps)
    // console.log(sects)
    // console.log(sArticles)
    setDParts(parts);
    setDChaps(chaps);
    setDSects(sects);
    setDArticles(sArticles);
  }, [sArticles]);
  return (
    <div>
      {dParts && dParts.length > 0 ? (
        <Part lawParts={dParts} />
      ) : dChaps && dChaps.length > 0 ? (
        <Chap lawChaps={dChaps} />
      ) : dSects && dSects.length > 0 ? (
        <Sect lawSects={dSects} />
      ) : (
        <Article lawArticles={dArticles} />
      )}
      <div style={{ display: "flex", justifyContent: "center", alignItems: "center" }}>
        <Text type="warning" style={{ textAlign: "center" }}>
          未显示内容或内容显示有误说明此项目非标准格式法律，请直接查看原始文件！
        </Text>
      </div>
    </div>
  );
}
