import * as React from "react";
import Item from "./Item";
import PropTypes from "prop-types";

export default function SubPara(props) {
  return (
    <div>
      {props.lawSubParas.map((subPara, index) => (
        <div key={index}>
          <div>{subPara.subparagraphName}</div>
          {subPara.items && subPara.items.length > 0 && <Item lawItems={subPara.items} />}
        </div>
      ))}
    </div>
  );
}

SubPara.propTypes = {
  lawSubParas: PropTypes.array
};
