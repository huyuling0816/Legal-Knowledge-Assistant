import * as React from "react";
import PropTypes from "prop-types";
import { Tag } from "element-react";
import { Col, Collapse } from "antd";
import Sect from "./Sect";
import Article from "./Article";
import { useEffect } from "react";

export default function Chap(props) {
  const { Panel } = Collapse;
  return (
    <div>
      <Collapse defaultActiveKey={[]}>
        {props.lawChaps.map((chap, index) => (
          <Panel
            key={index}
            header={
              <div>
                <Tag type={"success"}>{chap.chapterSeq}</Tag>
                {" " + chap.chapterName}
              </div>
            }
          >
            <div>
              {chap.sects && chap.sects.length > 0 && <Sect lawSects={chap.sects} />}
              {chap.articles && chap.articles.length > 0 && <Article lawArticles={chap.articles} />}
            </div>
          </Panel>
        ))}
      </Collapse>
    </div>
  );
}

Chap.propTypes = {
  lawChaps: PropTypes.array
};
