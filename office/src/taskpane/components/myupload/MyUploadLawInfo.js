import { Button, DatePicker, Form, Input, Select, Card, Message } from "element-react";
import Detail from "../lawdetail/Detail";
import * as React from "react";
import { Link, useNavigate, useParams } from "react-router-dom";
import { useDispatch, useSelector } from "react-redux";
import { useState } from "react";
import axios from "axios";
import {
  setAppendixes,
  setArticles,
  setChaps,
  setDoc,
  setItems,
  setParas,
  setParts,
  setSects,
  setSubParas
} from "../../../store/lawSlice";
import {
  getSimpleAppendixByDocId,
  getSimpleArticleByDocId,
  getSimpleChapByDocId,
  getSimpleDoc, getSimpleItemByDocId, getSimpleParaByDocId,
  getSimplePartByDocId,
  getSimpleSectByDocId, getSimpleSubParaByDocId,
  updateLaw
} from "../../../api/law";
import { chinese2Status, statusDict } from "../../../utils/consts";
/* global document, Office, module, require */
export default function MyUploadLawInfo() {
  const user = useSelector((state) => state.user.user);
  const lawId = useParams()["lawId"].replace("=", "%3D");
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const [lawDoc, setLawDoc] = useState({});
  const [lawParts, setLawParts] = useState([]);
  const [lawChaps, setLawChaps] = useState([]);
  const [lawSects, setLawSects] = useState([]);
  const [lawArticles, setLawArticles] = useState([]);
  const [lawParas, setLawParas] = useState([]);
  const [lawSubParas, setLawSubParas] = useState([]);
  const [lawItems, setLawItems] = useState([]);
  const [lawAppendixes, setLawAppendixes] = useState([]);
  const handleChange = (name, value) => {
    setLawDoc((preState) => ({
      ...preState,
      [name]: value
    }));
  };
  const onSubmit = async () => {
    let temp = lawDoc;
    temp.status = chinese2Status[temp.status];
    console.log(temp);
    await updateLaw(temp, user.id);
    navigate("/myupload");
    Message.success("修改成功！");
  };
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
    let res = await getSimpleDoc(lawId);
    let temp = res.data;
    temp.status = statusDict[temp.status];
    setLawDoc(temp);
    dispatch(setDoc(temp));
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
  }, []);
  return (
    <div>
      <Card className="box-card" style={{ marginTop: "10px" }}>
        {/*<h1 style={{ marginLeft: "10px" }}>{lawDoc.title}</h1>*/}
        <Form model={lawDoc} labelWidth="80">
          <Form.Item label="标题">
            <Input value={lawDoc.title} onChange={(value) => handleChange("title", value)}></Input>
          </Form.Item>
          <Form.Item label="制定机关">
            <Input value={lawDoc.office} onChange={(value) => handleChange("office", value)}></Input>
          </Form.Item>
          <Form.Item label="当前状态">
            <Select value={lawDoc.status} placeholder="请选择状态" onChange={(value) => handleChange("status", value)}>
              <Select.Option label="尚未生效" value="尚未生效"></Select.Option>
              <Select.Option label="生效" value="生效"></Select.Option>
              <Select.Option label="已修改" value="已修改"></Select.Option>
              <Select.Option label="已废止" value="已废止"></Select.Option>
            </Select>
          </Form.Item>
          <Form.Item label="类型">
            <Select
              value={lawDoc.docCategory}
              placeholder="请选择类型"
              onChange={(value) => handleChange("type", value)}
            >
              <Select.Option label="宪法" value="宪法"></Select.Option>
              <Select.Option label="法律" value="法律"></Select.Option>
              <Select.Option label="行政法规" value="行政法规"></Select.Option>
              <Select.Option label="监察法规" value="监察法规"></Select.Option>
              <Select.Option label="司法解释" value="司法解释"></Select.Option>
              <Select.Option label="地方性法规" value="地方性法规"></Select.Option>
            </Select>
          </Form.Item>
          <Form.Item label="公布日期">
            <DatePicker
              value={new Date(lawDoc.publish)}
              placeholder="发布日期"
              onChange={(value) => handleChange("publish", value)}
            />
          </Form.Item>
          <Form.Item label="生效日期">
            <DatePicker
              value={new Date(lawDoc.expiry)}
              placeholder="生效日期"
              onChange={(value) => handleChange("expiry", value)}
            />
          </Form.Item>
          <div style={{ textAlign: "right", marginTop: "10px" }}>
            <Button type="primary" onClick={onSubmit}>
              确定
            </Button>
            <Button>
              <Link to={"/myupload"} style={{ textDecoration: "none", color: "black" }}>
                取消
              </Link>
            </Button>
          </div>
          <Form.Item label="章头" style={{ marginTop: "10px" }}>
            <Input
              value={lawDoc.docText}
              type="textarea"
              autosize={{ minRows: 5, maxRows: 10 }}
              readOnly={true}
            ></Input>
          </Form.Item>
        </Form>
        <div style={{ display: "flex", justifyContent: "center", alignItems: "center" }}>
          <Button type="text" onClick={look}>查看原始文件</Button>
        </div>
        <Detail />
      </Card>
    </div>
  );
}
