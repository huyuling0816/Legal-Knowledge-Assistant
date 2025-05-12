import * as React from "react";
import { Button, Table, Input, Layout, Message, Pagination } from "element-react";
import AdvanceSearch from "./AdvanceSearch";
import { category, rankType, searchRange, searchType, sortType, status, statusDict } from "../../../utils/consts";
import {
  Routes,
  Route,
  Link,
  useNavigate,
  NavLink,
  HashRouter as Router,
  redirect,
  RedirectFunction
} from "react-router-dom";
import { getQueryLawPayload } from "../../../utils/QueryUtil";
import { queryLaw } from "../../../api/law";
import { jwtDecode } from "jwt-decode";
import Login from "../user/Login";
import { useSelector } from "react-redux";
import { useEffect } from "react";
/* global Word, require, Office, console, OfficeRuntime  */
export default class LawDb extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      isLogin: false,
      showAdvancedSearch: false,
      // advancedSearchForm: {},
      input: "",
      columns: [
        {
          type: "expand",
          expandPannel: function (row) {
            return <span dangerouslySetInnerHTML={{ __html: row.fullContent }}></span>;
          },
        },
        {
          label: "标题",
          prop: "title",
          width: 200,
          render: (row, column, index) => {
            return <span dangerouslySetInnerHTML={{ __html: row.title }}></span>;
          }
          // fixed: "left"
        },
        {
          label: "制定机关",
          prop: "office",
          width: 160
        },
        {
          label: "法律性质",
          prop: "docCategory",
          width: 120
        },
        {
          label: "时效性",
          prop: "status",
          width: 85,
          render: (row) => {
            return <span>{statusDict[row.status]}</span>;
          }
        },
        {
          label: "公布日期",
          prop: "publish",
          width: 115
        },
        {
          label: "生效/失效日期",
          prop: "expiry",
          width: 140
        },
        {
          label: "操作",
          prop: "zip",
          // width: 70,
          render: (row, column, index) => {
            return (
              <span>
                <Button type="text" size="small">
                  <Link to={`/lawdb/${row.id}`} style={{ textDecoration: "none", color: "#20A0FF" }}>
                    查看
                  </Link>
                </Button>
              </span>
            );
          }
        }
      ],
      data: [],
      totalPage: "",
      form: {
        searchRange: "标题",
        searchType: "精确查询",
        publishStart: null,
        publishEnd: null,
        expiryStart: null,
        expiryEnd: null,
        status: status.all,
        office: "",
        sortType: "按标题",
        rankType: "正序",
        category: category.all
      }
    };
  }

  clickAdvancedSearch = () => {
    this.setState((prevState) => ({
      showAdvancedSearch: !prevState.showAdvancedSearch
    }));
  };
  handleSubmitAdvancedSearch = async (form) => {
    if (this.state.input === "") {
      Message({
        message: "请输入搜索内容！",
        type: "warning"
      });
      return;
    }
    this.setState(() => ({
      form: form
    }));
    this.setState((prevState) => ({
      showAdvancedSearch: !prevState.showAdvancedSearch
    }));
    let param = getQueryLawPayload(
      this.state.input,
      form.office,
      form.publishStart,
      form.publishEnd,
      form.expiryStart,
      form.expiryEnd,
      form.status,
      form.category,
      sortType[form.sortType],
      rankType[form.rankType],
      searchRange[form.searchRange],
      searchType[form.searchType]
    );
    console.log(param);
    let res = await queryLaw(param);
    this.setState(() => ({
      data: res.data.data
    }));
    this.setState(() => ({
      totalPage: res.data.totalPage
    }));
  };

  handleInputChange = (text) => {
    this.setState({
      input: text
    });
  };

  simpleSearch = async () => {
    if (this.state.input === "") {
      Message({
        message: "请输入搜索内容！",
        type: "warning"
      });
      return;
    }
    // simpleSearch = () => {
    let param = getQueryLawPayload(this.state.input);
    let res = await queryLaw(param);
    this.setState(() => ({
      data: res.data.data
    }));
    this.setState(() => ({
      totalPage: res.data.totalPage
    }));
  };

  handlePageChange = async (pageNum) => {
    let param = getQueryLawPayload(
      this.state.input,
      this.state.form.office,
      this.state.form.publishStart,
      this.state.form.publishEnd,
      this.state.form.expiryStart,
      this.state.form.expiryEnd,
      this.state.form.status,
      this.state.form.category,
      sortType[this.state.form.sortType],
      rankType[this.state.form.rankType],
      searchRange[this.state.form.searchRange],
      searchType[this.state.form.searchType],
      pageNum
    );
    let res = await queryLaw(param);
    this.setState(() => ({
      data: res.data.data
    }));
  };

  DummyView = () => {
    const loginStatus = useSelector((state) => state.user.isLogin);
    useEffect(() => {
      this.setState({
        isLogin: loginStatus
      });
    }, []);
    return null;
  };

  render() {
    const { showAdvancedSearch } = this.state;
    return (
      <div>
        <this.DummyView />
        {/*<Login />*/}
        <Layout.Row
          className="row-bg"
          type={"flex"}
          justify={"end"}
          style={{ marginTop: "10px", marginBottom: "10px" }}
        >
          <Layout.Col span={"10"}>
            <Button type="primary">
              <Link
                to={this.state.isLogin ? "/addlaw" : "/user/login"}
                // to={"/addlaw"}
                style={{ textDecoration: "none", color: "white" }}
              >
                新增法律
              </Link>
            </Button>
          </Layout.Col>
          <Layout.Col span={"14"} style={{ display: "flex", alignItems: "center" }}>
            <Input
              placeholder="请输入内容"
              value={this.state.input}
              icon="search"
              onIconClick={this.simpleSearch}
              onChange={(value) => this.handleInputChange(value)}
            />
            <Button icon={showAdvancedSearch ? "arrow-up" : "arrow-down"} onClick={this.clickAdvancedSearch}>
              高级搜索
            </Button>
          </Layout.Col>
        </Layout.Row>
        {showAdvancedSearch && <AdvanceSearch onFormSubmit={this.handleSubmitAdvancedSearch} />}
        <Table style={{ width: "100%" }} columns={this.state.columns} data={this.state.data} border={true} />
        <div className="last" style={{ textAlign: "center" }}>
          <div className="block">
            <Pagination
              layout="pageCount, prev, pager, next, jumper"
              pageCount={this.state.totalPage}
              // pageSizes={[100, 200, 300, 400]}
              pageSize={10}
              currentPage={1}
              onCurrentChange={(currentPage) => this.handlePageChange(currentPage)}
            />
          </div>
        </div>
      </div>
    );
  }
}
