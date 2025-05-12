import * as React from "react";
import { Button, Table, Input, Layout, Message, MessageBox } from "element-react";
import { Link, useNavigate } from "react-router-dom";
import { useSelector } from "react-redux";
import { useEffect } from "react";
import { deleteByDocId, getByUserId } from "../../../api/law";
import { statusDict } from "../../../utils/consts";

export default class MyUpload extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      columns: [
        {
          label: "标题",
          prop: "title",
          width: 200,
        },
        {
          label: "制定机关",
          prop: "office",
          width: 160,
        },
        {
          label: "法律性质",
          prop: "docCategory",
          // width: 120
        },
        {
          label: "时效性",
          prop: "status",
          width: 85,
        },
        {
          label: "公布日期",
          prop: "publish",
          width: 115,
        },
        {
          label: "生效/失效日期",
          prop: "expiry",
          width: 140,
        },
        {
          label: "操作",
          prop: "zip",
          width: 80,
          render: (row, column, index) => {
            return (
              <span>
                <Link to={`/myupload/${row.id}`} style={{ textDecoration: "none", color: "#20A0FF" }}>
                  <i className="el-icon-edit"></i>
                </Link>
                <i
                  className="el-icon-delete"
                  style={{ marginLeft: "10px", color: "red" }}
                  onClick={() => this.deleteLaw(row.id)}
                ></i>
              </span>
            );
          },
        },
      ],
      data: [],
      userId: 0,
    };
  }

  deleteLaw = async (docId) => {
    MessageBox.confirm("确定删除该篇法律?", "提示", {
      type: "warning",
    })
      .then(async () => {
        await deleteByDocId(docId, this.state.userId);
        await this.setData();
        Message({
          type: "success",
          message: "删除成功!",
        });
      })
      .catch(() => {
        Message({
          type: "info",
          message: "已取消删除",
        });
      });
  };

  setData = async () => {
    let res = await getByUserId(this.state.userId);
    let tempData = [...res.data];
    tempData = tempData.map((item) => {
      item.status = statusDict[item.status];
      return item;
    });
    this.setState(() => ({
      data: tempData,
    }));
  };
  DummyView = () => {
    const navigate = useNavigate();
    const loginStatus = useSelector((state) => state.user.isLogin);
    const user = useSelector((state) => state.user.user);
    useEffect(async () => {
      // await this.setData();
      if (loginStatus === false) {
        navigate("/user/login");
      } else {
        let res = await getByUserId(user.id);
        let tempData = [...res.data];
        tempData = tempData.map((item) => {
          item.status = statusDict[item.status];
          return item;
        });
        this.setState(() => ({
          data: tempData,
          userId: user.id,
        }));
      }
    }, []);
    return null;
  };

  render() {
    return (
      <div>
        <this.DummyView />
        <Table style={{ width: "100%" }} columns={this.state.columns} data={this.state.data} border={true} />
      </div>
    );
  }
}
