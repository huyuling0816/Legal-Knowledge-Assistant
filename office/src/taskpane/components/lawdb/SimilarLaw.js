import * as React from "react";
import PropTypes from "prop-types";
import { Button } from "element-react";
import { useNavigate } from "react-router-dom";
export default function SimilarLaw(props) {
  const lawDoc = props.lawDoc;
  const navigate = useNavigate();
  const seeDetail = () => {
    navigate(`/lawdb/${lawDoc.id}`);
  };
  return (
    <div>
      <Button type={"text"} onClick={seeDetail}>
        {lawDoc.title}
      </Button>
    </div>
  )
}
SimilarLaw.propTypes = {
  lawDoc: PropTypes.object,
}