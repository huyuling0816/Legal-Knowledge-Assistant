import * as React from "react";
import { Button, Card, Form, Input, Message } from "element-react";
import { useState } from "react";
import { checkEmail, checkPassword } from "../../../utils/inputCheck";
import { register } from "../../../api/user";
import { useNavigate } from "react-router-dom";

export default function Register() {
  const navigate = useNavigate();
  const [form, setForm] = useState({
    name: "",
    email: "",
    password: ""
  });
  const onSubmit = () => {
    const name = form.name;
    const password = form.password;
    const email = form.email;
    if (name === "") {
      Message({
        message: "请输入用户名！",
        type: "warning"
      });
    } else if (email === "" || !checkEmail(email)) {
      Message({
        message: "请输入正确的邮箱格式！",
        type: "warning"
      });
    } else if (password === "" || !checkPassword(password)) {
      Message({
        message: "请输入长度为6-16位的密码！",
        type: "warning"
      });
    } else {
      console.log(form);
      register({ name, email, password }).then((res) => {
        if (res.code === 0) {
          Message({
            message: "注册成功！",
            type: "success"
          });
        } else {
          Message({
            message: "该邮箱已存在！",
            type: "warning"
          });
        }
        navigate("/user/login");
      });
    }
  };
  const handleChange = (name, value) => {
    setForm((prevForm) => ({
      ...prevForm,
      [name]: value
    }));
  };
  return (
    <div
      style={{
        display: "flex",
        justifyContent: "center",
        alignItems: "center",
        marginTop: "130px"
      }}
    >
      <Card style={{ width: "500px", textAlign: "center" }}>
        <h1>注册账号</h1>
        <Form model={form} labelWidth={80} style={{ width: "400px" }}>
          <Form.Item label="用户名">
            <Input
              value={form.name}
              onChange={(value) => handleChange("name", value)}
              placeholder={"请输入用户名"}
            ></Input>
          </Form.Item>
          <Form.Item label="邮箱">
            <Input
              value={form.email}
              onChange={(value) => handleChange("email", value)}
              placeholder={"请输入邮箱"}
            ></Input>
          </Form.Item>
          <Form.Item label="密码">
            <Input
              value={form.password}
              onChange={(value) => handleChange("password", value)}
              placeholder={"请输入密码"}
            ></Input>
          </Form.Item>
        </Form>
        <div style={{ textAlign: "center", marginTop: "10px" }}>
          <Button type="primary" onClick={onSubmit}>
            注册
          </Button>
        </div>
      </Card>
    </div>
  );
}
