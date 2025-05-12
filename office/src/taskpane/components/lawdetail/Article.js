import * as React from "react";
import { useStore } from "react-redux";
import { isStrNull } from "../../../utils/stringUtil";
import { useMemo, useState } from "react";
import { Tag } from "element-react";
import { Collapse } from "antd";
import Para from "./Para";
import SubPara from "./SubPara";
import Item from "./Item";
import { paraSort, itemSort } from "../../../utils/sortUtil";
import PropTypes from "prop-types";

export default function Article(props) {
  const store = useStore().getState();
  const [dArticles, setDArticles] = useState(props.lawArticles);
  // const sArticles = store.law.lawArticles;
  // let dArticles = props.lawArticles;
  const sParas = store.law.lawParas;
  const sSubParas = store.law.lawSubParas;
  const sItems = store.law.lawItems;
  const { Panel } = Collapse;
  React.useEffect(() => {
    let articles = dArticles.slice();
    let paras = sParas.slice();
    let subParas = sSubParas.slice();
    for (let i = 0; i < subParas.length; i++) {
      let subPara = { ...subParas[i] };
      subPara.items = [];
      subPara.items = sItems.filter((item) => item.subparagraphId === subPara.id);
      subPara.items.sort(itemSort);
      subParas[i] = subPara;
    }
    for (let i = 0; i < paras.length; i++) {
      let para = { ...paras[i] };
      para.subParas = [];
      para.items = [];
      para.subParas = subParas.filter((item) => item.paragraphId === para.id);
      para.items = sItems.filter((item) => item.paragraphId === para.id && isStrNull(item.subparagraphId));
      para.items.sort(itemSort);
      paras[i] = para;
    }
    for (let i = 0; i < articles.length; i++) {
      let article = { ...articles[i] };
      article.paras = [];
      article.subParas = [];
      article.item = [];
      article.paras = paras.filter((item) => item.articleId === article.id);
      // if (article.paras.length > 0) {
      //   article.paras.sort(paraSort);
      // }
      article.subParas = subParas.filter((item) => item.articleId === article.id && isStrNull(item.paragraphId));
      article.items = sItems.filter(
        (item) => item.articleId === article.id && isStrNull(item.subparagraphId) && isStrNull(item.paragraphId)
      );
      article.items.sort(itemSort);
      articles[i] = article;
    }
    setDArticles(articles);
  }, [sItems]);

  const getArticleSimpleName = (articleName) => {
    if (articleName.indexOf("@") >= 0) return articleName.substring(0, articleName.indexOf("@"));
    else return articleName;
  };

  return (
    <div>
      <Collapse defaultActiveKey={[]}>
        {dArticles.map((article, index) => (
          <Panel
            key={index}
            header={
              <div>
                <Tag type="primary">{article.articleSeq}</Tag>
                {" " + getArticleSimpleName(article.articleName)}
              </div>
            }
          >
            <div>
              {article.paras && article.paras.length > 0 ? (
                <Para lawParas={article.paras} />
              ) : article.subParas && article.subParas.length > 0 ? (
                <SubPara lawSubParas={article.subParas} />
              ) : article.items && article.items.length > 0 ? (
                <Item lawItems={article.items} />
              ) : null}
            </div>
          </Panel>
        ))}
      </Collapse>
    </div>
  );
}

Article.propTypes = {
  lawArticles: PropTypes.array
};

