import * as React from "react";
import PropTypes from "prop-types";
import { Tag } from "element-react";
import { Collapse } from "antd";
import Chap from "./Chap";
import Sect from "./Sect";
import Article from "./Article";

export default function Part(props) {
  const { Panel } = Collapse;
  return (
    <div>
      <Collapse defaultActiveKey={[0]}>
        {props.lawParts.map((part, index) => (
          <Panel
            key={index}
            header={
              <div>
                <Tag type={"success"}>{part.partSeq}</Tag>
                {" " + part.partName}
              </div>
            }
          >
            <div>
              {part.chaps && part.chaps.length > 0 && <Chap lawChaps={part.chaps} />}
              {part.sects && part.sects.length > 0 && <Sect lawSects={part.sects} />}
              {part.articles && part.articles.length > 0 && <Article lawArticles={part.articles} />}
            </div>
          </Panel>
        ))}
      </Collapse>
    </div>
  );
}

Part.propTypes = {
  lawParts: PropTypes.array
};
