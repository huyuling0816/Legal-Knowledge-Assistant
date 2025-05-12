import * as React from "react";
import { Card, Tag } from "element-react";
import { useEffect, useState } from "react";
import Item from "./Item";
import SubPara from "./SubPara";
import PropTypes from "prop-types";
// props: lawParas
export default function Para(props) {
  const getSimpleName = (paraName) => {
    if (paraName.indexOf("（") >= 0) return paraName.substring(0, paraName.indexOf("（"));
    else return paraName;
  };
  return (
    <div>
      {props.lawParas.map((para, index) => (
        <Card key={index} className="box-card">
          <div>
            <div>
              <Tag type="gray">{para.paragraphSeq}</Tag>
              {" " + getSimpleName(para.paragraphName)}
            </div>
            {para.subParas.length > 0 ? (
              <Card>
                <SubPara lawSubParas={para.subParas} />
              </Card>
            ) : para.items.length > 0 ? (
              <Card>
                <Item lawItems={para.items} />
              </Card>
            ) : null}
          </div>
        </Card>
      ))}
    </div>
  );
}
Para.propTypes = {
  lawParas: PropTypes.array
};
