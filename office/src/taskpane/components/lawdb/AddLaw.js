import * as React from "react";
import { useEffect, useState } from "react";
import { Button } from "element-react";
import { Link } from "react-router-dom";
import AddLawItem from "./AddLawItem";
import { chinese2Status } from "../../../utils/consts";
import { createByFile, createByText } from "../../../api/law";
import { useSelector } from "react-redux";
import { useNavigate } from "react-router-dom";
import { dateFormatter } from "../../../utils/dateUtil";

/* global global, Office, self, window */
export default function AddLaw() {
  const user = useSelector((state) => state.user.user);
  const navigate = useNavigate();
  /*
  const [form, setForm] = useState({
    // title: "",
    // office: "",
    // status: "",
    // type: "",
    // publish: null,
    // expiry: null,
    // text: ""
  });
   */
  const [forms, setForms] = useState([]);
  const [count, setCount] = useState(0);
  // const [fileList, setFileList] = useState([]);
  useEffect(() => {
    const firstForm = {
      id: count
    };
    setForms((prevForms) => [...prevForms, firstForm]);
    setCount(count + 1);
  }, []);
  const addForm = () => {
    const newForm = {
      id: count
    };
    setForms((prevForms) => [...prevForms, newForm]);
    setCount(count + 1);
  };
  const deleteForm = (id) => {
    const newForms = forms.filter((form) => form.id !== id);
    setForms(newForms);
  };
  const handleChange = (id, name, value) => {
    const target = forms.find((form) => form.id === id);
    if (target) {
      const updatedForm = { ...target, [name]: value };
      setForms(forms.map((form) => (form.id === id ? updatedForm : form)));
    }
  };
  const onSubmit = async () => {
    let submitForms = [...forms];
    submitForms = submitForms.map(async (item) => {
      item.publish = dateFormatter(item.publish);
      item.expiry = dateFormatter(item.expiry);
      item.status = chinese2Status[item.status];
      if (item.file === undefined || item.file === "") {
        // 文本上传
        // await createByText(submitForms, user.id);
        item.docCategory = item.type;
        await createByText(item, user.id);
      } else {
        // doc上传
        let formdata = new window.FormData();
        formdata.append("id", "");
        formdata.append("title", item.title);
        formdata.append("office", item.office);
        formdata.append("publish", item.publish);
        formdata.append("expiry", item.expiry);
        formdata.append("status", item.status);
        formdata.append("type", item.type);
        formdata.append("docCategory", item.type);
        formdata.append("fileType", item.title.substring(item.title.indexOf(".")));
        formdata.append("docFile", item.file);
        formdata.append("userId", user.id);
        await createByFile(formdata);
      }
      navigate("/myupload");
      return item;
    });
  };

  function isFirstForm(id) {
    return id === forms[0].id;
  }

  return (
    <div>
      <h1 style={{ marginLeft: "5px" }}>导入法律</h1>
      {forms.map((form, index) => (
        <AddLawItem
          key={index}
          id={form.id}
          form={form}
          handleChange={handleChange}
          addForm={addForm}
          deleteForm={deleteForm}
          isFirstForm={isFirstForm}
        />
      ))}
      <div style={{ textAlign: "right", marginTop: "10px" }}>
        <Button type="primary" onClick={onSubmit}>
          确定
        </Button>
        <Button>
          <Link to={"/lawdb"} style={{ textDecoration: "none", color: "black" }}>
            取消
          </Link>
        </Button>
      </div>
    </div>
  );
}
