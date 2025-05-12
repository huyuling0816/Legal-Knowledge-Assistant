import * as React from "react";
import { Button, Card, Form, Input, Message } from "element-react";
import { useState } from "react";
import { Link } from "react-router-dom";
import { useNavigate } from "react-router-dom";
import { checkEmail } from "../../../utils/inputCheck";
import { login } from "../../../api/user";
import { useDispatch } from "react-redux";
import { setLoginStatus, setUser } from "../../../store/userSlice";

/* global document, Office, module, require */
export default function Login() {
  const dispatch = useDispatch();
  const navigate = useNavigate();
  const [form, setForm] = useState({
    email: "",
    password: ""
  });
  const onSubmit = () => {
    const email = form.email;
    const password = form.password;
    if (email === "" || !checkEmail(email)) {
      Message({
        message: "请输入正确的邮箱格式！",
        type: "warning"
      });
    } else if (password === "") {
      Message({
        message: "请输入密码！",
        type: "warning"
      });
    } else {
      login({ email, password }).then((res) => {
        if (res.code === 0) {
          Message({
            message: "登陆成功！",
            type: "success"
          });
          console.log(document.cookie);
          dispatch(setLoginStatus(true));
          dispatch(
            setUser({
              name: res.data.name,
              id: res.data.id,
              email: res.data.email
            })
          );
          navigate("/lawdb");
        } else {
          Message({
            message: "邮箱或密码错误！",
            type: "warning"
          });
        }
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
        <h1>登陆账号</h1>
        <Form model={form} labelWidth={80} style={{ width: "400px" }}>
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
            提交
          </Button>
          <div style={{ marginTop: "15px" }}>
            暂无账号？
            <Link to={"/user/register"}>
              立即注册
            </Link>
          </div>
        </div>
      </Card>
    </div>
  );
}
