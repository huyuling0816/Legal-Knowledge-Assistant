import * as React from "react";
import { Card, Table } from "element-react";
import PropTypes from "prop-types";
import { useEffect } from "react";

export default function Item(props) {
  return (
    <div>
      <Card className="box-card">
        {props.lawItems.map((item, index) => (
          <div key={index} className="text item">
            {item.itemName}
          </div>
        ))}
      </Card>
    </div>
  );
}

Item.propTypes = {
  lawItems: PropTypes.array
};
