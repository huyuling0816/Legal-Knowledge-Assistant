import * as React from "react";
import { Button, DatePicker, Form, Input } from "element-react";
import { useState } from "react";
import { Link, useNavigate, useParams } from "react-router-dom";
import "../../../mock/law";

/* global document, Office, module, require */
import { useDispatch } from "react-redux";
import {
  setItems,
  setParas,
  setSects,
  setSubParas,
  setArticles,
  setChaps,
  setParts,
  setDoc,
  setAppendixes
} from "../../../store/lawSlice";
import Detail from "../lawdetail/Detail";
import {
  getRelatedLaws,
  getSimpleAppendixByDocId,
  getSimpleArticleByDocId,
  getSimpleChapByDocId,
  getSimpleDoc, getSimpleItemByDocId, getSimpleParaByDocId,
  getSimplePartByDocId,
  getSimpleSectByDocId, getSimpleSubParaByDocId
} from "../../../api/law";
import { statusDict } from "../../../utils/consts";
import * as util from "util";
import SimilarLaw from "./SimilarLaw";

export default function LawInfo() {
  let lawId = useParams()["lawId"].replace("=", "%3D");
  const dispatch = useDispatch();
  const [lawDoc, setLawDoc] = useState({});
  const navigate = useNavigate();
  const [lawParts, setLawParts] = useState([]);
  const [lawChaps, setLawChaps] = useState([]);
  const [lawSects, setLawSects] = useState([]);
  const [lawArticles, setLawArticles] = useState([]);
  const [lawParas, setLawParas] = useState([]);
  const [lawSubParas, setLawSubParas] = useState([]);
  const [lawItems, setLawItems] = useState([]);
  const [lawAppendixes, setLawAppendixes] = useState([]);
  const [relatedLaws, setRelatedLaws] = useState([]);
  const look = () => {
    let url = lawDoc.docURL;
    if (url.includes("html")) {
      url = "https://flk.npc.gov.cn/detail2.html?" + lawId;
      window.location.href = url;
    } else {
      window.location.href = url;
    }
  };
  React.useEffect(async () => {
    document.documentElement.scrollTop = document.body.scrollTop = 0;
    let res = await getRelatedLaws(lawId);
    setRelatedLaws(res.data);
    console.log(res.data);
    res = await getSimpleDoc(lawId);
    setLawDoc(res.data);
    dispatch(setDoc(res.data));
    res = await getSimplePartByDocId(lawId);
    setLawParts(res.data);
    dispatch(setParts(res.data));
    res = await getSimpleChapByDocId(lawId);
    setLawChaps(res.data);
    dispatch(setChaps(res.data));
    res = await getSimpleSectByDocId(lawId);
    setLawSects(res.data);
    dispatch(setSects(res.data));
    res = await getSimpleArticleByDocId(lawId);
    setLawArticles(res.data);
    dispatch(setArticles(res.data));
    res = await getSimpleParaByDocId(lawId);
    setLawParas(res.data);
    dispatch(setParas(res.data));
    res = await getSimpleSubParaByDocId(lawId);
    setLawSubParas(res.data);
    dispatch(setSubParas(res.data));
    res = await getSimpleItemByDocId(lawId);
    setLawItems(res.data);
    dispatch(setItems(res.data));
    res = await getSimpleAppendixByDocId(lawId);
    setLawAppendixes(res.data);
    dispatch(setArticles(res.data));
  }, [lawId]);
  return (
    <div>
      <h1 style={{ marginLeft: "10px" }}>{lawDoc.title}</h1>
      <Form model={lawDoc} labelWidth="80">
        <Form.Item label="制定机关">
          <Input value={lawDoc.office}></Input>
        </Form.Item>
        <Form.Item label="当前状态">
          <Input value={statusDict[lawDoc.status]}></Input>
        </Form.Item>
        <Form.Item label="类型">
          <Input value={lawDoc.docCategory}></Input>
        </Form.Item>
        <Form.Item label="公布日期">
          <DatePicker value={new Date(lawDoc.publish)} />
        </Form.Item>
        <Form.Item label="生效/失效日期">
          <DatePicker value={new Date(lawDoc.expiry)} />
        </Form.Item>
        <Form.Item label="章头">
          <Input value={lawDoc.docText} type="textarea" autosize={{ minRows: 5, maxRows: 10 }}></Input>
        </Form.Item>
      </Form>
      <div style={{ display: "flex", justifyContent: "center", alignItems: "center" }}>
        <Button type="text" onClick={look}>
          查看原始文件
        </Button>
      </div>
      <Detail />
      <h1>相关法律推荐</h1>
      <div>
        {relatedLaws.map((relatedLaw) => (
          <SimilarLaw lawDoc={relatedLaw} key={relatedLaw.id} />
        ))}
      </div>
    </div>
  );
}
