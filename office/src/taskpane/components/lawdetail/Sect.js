import * as React from "react";
import PropTypes from "prop-types";
import { Collapse } from "antd";
import { Tag } from "element-react";
import Article from "./Article";

export default function Sect(props) {
  const { Panel } = Collapse;
  return (
    <div>
      <Collapse defaultActiveKey={[]}>
        {props.lawSects.map((sect, index) => (
          <Panel
            key={index}
            header={
              <div>
                <Tag type={"success"}>{sect.sectionSeq}</Tag>
                {" " + sect.sectionName}
              </div>
            }
          >
            {sect.articles && sect.articles.length > 0 && <Article lawArticles={sect.articles} />}
          </Panel>
        ))}
      </Collapse>
    </div>
  );
}

Sect.propTypes = {
  lawSects: PropTypes.array
};