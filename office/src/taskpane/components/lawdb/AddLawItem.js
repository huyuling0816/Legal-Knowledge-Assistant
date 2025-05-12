import * as React from "react";
import { Button, Card, DatePicker, Form, Input, Message, Select, Upload } from "element-react";
import PropTypes from "prop-types";
import { useState } from "react";
/* global Word, require, Office, console */
export default function AddLawItem(props) {
  const [fileList, setFileList] = useState([]);

  // async function myHandler(eventArgs) {
  //   eventArgs.document.getSelectedDataAsync(Office.CoercionType.Text, async function(asyncResult) {
  //     if (asyncResult.status == Office.AsyncResultStatus.Failed) {
  //       console.log("Action failed. Error: " + asyncResult.error.message);
  //     } else {
  //       if (!(asyncResult.value == "" || asyncResult.value == selectedText)) {
  //         console.log("Selected data: " + asyncResult.value);
  //       }
  //     }
  //   });
  // }
  //
  // Office.context.document.addHandlerAsync(Office.EventType.DocumentSelectionChanged, myHandler);
  const handleAddFile = (file) => {
    props.form.file = file.raw;
    const temp = {};
    temp.name = file.raw.name;
    temp.url = "test";
    setFileList((prevFileList) => [...prevFileList, temp]);
    if (!file.raw.name.endsWith(".docx")) {
      Message.warning(`我们仅支持docx格式或doc格式。${file.raw.name} 不是。请更换文件！`);
    } else {
      Message.success("上传成功！");
    }
  };

  const handlePreview = (file) => {
    console.log(file);
  };

  const handleRemove = () => {
    props.form.file = "";
    setFileList([]);
  };

  const test = () => {
    console.log(fileList);
  };

  return (
    <div>
      <Card className="box-card">
        <Form model={props.form} labelWidth="80">
          <Form.Item label="标题">
            <Input value={props.form.title} onChange={(value) => props.handleChange(props.id, "title", value)}></Input>
          </Form.Item>
          <Form.Item label="制定机关">
            <Input
              value={props.form.office}
              onChange={(value) => props.handleChange(props.id, "office", value)}
            ></Input>
          </Form.Item>
          <Form.Item label="当前状态">
            <Select
              value={props.form.status}
              placeholder="请选择状态"
              onChange={(value) => props.handleChange(props.id, "status", value)}
            >
              <Select.Option label="生效" value="生效"></Select.Option>
              <Select.Option label="尚未生效" value="尚未生效"></Select.Option>
              <Select.Option label="无" value="无"></Select.Option>
              <Select.Option label="已修改" value="已修改"></Select.Option>
              <Select.Option label="已废止" value="已废止"></Select.Option>
            </Select>
          </Form.Item>
          <Form.Item label="类型">
            <Select
              value={props.form.type}
              placeholder="请选择类型"
              onChange={(value) => props.handleChange(props.id, "type", value)}
            >
              <Select.Option label="地方性法规" value="地方性法规"></Select.Option>
              <Select.Option label="宪法" value="宪法"></Select.Option>
              <Select.Option label="法律" value="法律"></Select.Option>
              <Select.Option label="有关法律问题和重大问题的决定" value="有关法律问题和重大问题的决定"></Select.Option>
              <Select.Option label="法律解释" value="法律解释"></Select.Option>
              <Select.Option label="司法解释" value="司法解释"></Select.Option>
              <Select.Option label="行政法规" value="行政法规"></Select.Option>
              <Select.Option label="修改、废止的决定" value="修改、废止的决定"></Select.Option>
              <Select.Option label="监查法规" value="监查法规"></Select.Option>
            </Select>
          </Form.Item>
          <Form.Item label="公布日期">
            <DatePicker
              value={props.form.publish}
              placeholder="发布日期"
              onChange={(value) => props.handleChange(props.id, "publish", value)}
            />
          </Form.Item>
          <Form.Item label="生效日期">
            <DatePicker
              value={props.form.expiry}
              placeholder="生效日期"
              onChange={(value) => props.handleChange(props.id, "expiry", value)}
            />
          </Form.Item>
          <Form.Item label="正文">
            <Input
              type="textarea"
              value={props.form.fullContent}
              onChange={(value) => props.handleChange(props.id, "fullContent", value)}
              autosize={{ minRows: 5, maxRows: 10 }}
            ></Input>
          </Form.Item>
        </Form>

        <div style={{ textAlign: "center" }}>
          <Upload
            className="upload-demo"
            action="none"
            onPreview={handlePreview}
            onRemove={handleRemove}
            onChange={(file, fileList) => handleAddFile(file, fileList)}
            fileList={fileList}
            limit={1}
            onExceed={(files, fileList) => {
              Message.warning(
                `当前限制选择 3 个文件，本次选择了 ${files.length} 个文件，共选择了 ${
                  files.length + fileList.length
                } 个文件`
              );
            }}
            tip={<div className="el-upload__tip">文件格式仅限于doc/docx/txt</div>}
          >
            <Button type="primary">点击上传文件</Button>
          </Upload>
        </div>
        <div style={{ textAlign: "right" }}>
          <Button type="primary" onClick={props.addForm}>
            继续导入
          </Button>
          {!props.isFirstForm(props.id) && (
            <Button plain={true} type="danger" onClick={() => props.deleteForm(props.form.id)}>
              删除
            </Button>
          )}
        </div>
      </Card>
    </div>
  );
}

AddLawItem.propTypes = {
  form: PropTypes.object,
  handleChange: PropTypes.func,
  addForm: PropTypes.func,
  deleteForm: PropTypes.func,
  id: PropTypes.number,
  isFirstForm: PropTypes.func
};
