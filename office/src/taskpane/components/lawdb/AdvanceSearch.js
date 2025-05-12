import * as React from "react";
import { Input, Layout, Form, DatePicker, Radio, Checkbox, Button, Select } from "element-react";
import PropTypes from "prop-types";
import { category, categoryOptions, status, statusOptions } from "../../../utils/consts";

export default class AdvanceSearch extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      form: {
        // 标题、正文、标题+正文
        searchRange: "标题",
        // 精确查询、模糊查询
        searchType: "精确查询",
        publishStart: null,
        publishEnd: null,
        expiryStart: null,
        expiryEnd: null,
        status: status.all,
        office: "",
        // 按发布时间、按生效时间、按标题
        sortType: "按标题",
        // 正序、倒序
        rankType: "正序",
        category: category.all
      }
    };
  }

  onSubmit(e) {
    e.preventDefault();
    console.log(this.props.onFormSubmit);
    this.props.onFormSubmit(this.state.form, 1);
  }

  onChange(key, value) {
    this.state.form[key] = value;
    this.forceUpdate();
  }

  handleReset(e) {
    e.preventDefault();
    this.setState({
      form: {
        // 标题、正文、标题+正文
        searchRange: "标题",
        // 精确查询、模糊查询
        searchType: "精确查询",
        publishStart: null,
        publishEnd: null,
        expiryStart: null,
        expiryEnd: null,
        status: status.all,
        office: "",
        sortType: "按标题",
        // 正序、倒序
        rankType: "正序",
        category: category.all
      }
    });
  }

  render() {
    return (
      <div>
        <Form model={this.state.form} labelWidth="80" onSubmit={this.onSubmit.bind(this)}>
          <Form.Item label="检索范围">
            <Radio.Group value={this.state.form.searchRange} onChange={this.onChange.bind(this, "searchRange")}>
              <Radio value="标题"></Radio>
              <Radio value="正文"></Radio>
            </Radio.Group>
          </Form.Item>
          <Form.Item label="检索方式">
            <Radio.Group value={this.state.form.searchType} onChange={this.onChange.bind(this, "searchType")}>
              <Radio value="精确查询"></Radio>
              <Radio value="模糊查询"></Radio>
            </Radio.Group>
          </Form.Item>
          <Form.Item label="公布日期">
            <Layout.Col span="11">
              <Form.Item prop="publishStart" labelWidth="0px">
                <DatePicker
                  value={this.state.form.publishStart}
                  placeholder="起始日期"
                  onChange={this.onChange.bind(this, "publishStart")}
                />
              </Form.Item>
            </Layout.Col>
            <Layout.Col className="line" span="2">
              -
            </Layout.Col>
            <Layout.Col span="11">
              <Form.Item prop="publishEnd" labelWidth="0px">
                <DatePicker
                  value={this.state.form.publishEnd}
                  placeholder="结束日期"
                  onChange={this.onChange.bind(this, "publishEnd")}
                />
              </Form.Item>
            </Layout.Col>
          </Form.Item>
          <Form.Item label="生效日期">
            <Layout.Col span="11">
              <Form.Item prop="expiryStart" labelWidth="0px">
                <DatePicker
                  value={this.state.form.expiryStart}
                  placeholder="起始日期"
                  onChange={this.onChange.bind(this, "expiryStart")}
                />
              </Form.Item>
            </Layout.Col>
            <Layout.Col className="line" span="2">
              -
            </Layout.Col>
            <Layout.Col span="11">
              <Form.Item prop="expiryEnd" labelWidth="0px">
                <DatePicker
                  value={this.state.form.expiryEnd}
                  placeholder="结束日期"
                  onChange={this.onChange.bind(this, "expiryEnd")}
                />
              </Form.Item>
            </Layout.Col>
          </Form.Item>
          {/*<Form.Item label="时效性">*/}
          {/*  <Checkbox.Group value={this.state.form.status} onChange={this.onChange.bind(this, "status")}>*/}
          {/*    <Checkbox label="尚未生效" name="status"></Checkbox>*/}
          {/*    <Checkbox label="生效" name="status"></Checkbox>*/}
          {/*    <Checkbox label="已修改" name="status"></Checkbox>*/}
          {/*    <Checkbox label="已废止" name="status"></Checkbox>*/}
          {/*  </Checkbox.Group>*/}
          {/*</Form.Item>*/}
          <Form.Item label="时效性">
            <Select value={this.state.form.status} placeholder="请选择当前状态" onChange={this.onChange.bind(this, "status")}>
              {statusOptions.map((el) => {
                return <Select.Option value={el.value} key={el.value} label={el.label} />;
              })}
            </Select>
          </Form.Item>
          <Form.Item label="制定机关">
            <Input value={this.state.form.office} onChange={this.onChange.bind(this, "office")}></Input>
          </Form.Item>
          <Form.Item label="类型">
            <Select value={this.state.form.category} onChange={this.onChange.bind(this, "category")}>
              {categoryOptions.map((el) => {
                return <Select.Option value={el.value} key={el.value} label={el.value} />
              })}
            </Select>
          </Form.Item>
          <Form.Item label="排序方式">
            <Radio.Group value={this.state.form.sortType} onChange={this.onChange.bind(this, "sortType")}>
              <Radio value="按标题"></Radio>
              <Radio value="按公布日期"></Radio>
              <Radio value="按生效日期"></Radio>
            </Radio.Group>
          </Form.Item>
          <Form.Item label="正/倒序">
            <Radio.Group value={this.state.form.rankType} onChange={this.onChange.bind(this, "rankType")}>
              <Radio value="正序"></Radio>
              <Radio value="倒序"></Radio>
            </Radio.Group>
          </Form.Item>
        </Form>
        <div style={{ textAlign: "right", marginBottom: "10px" }}>
          <Button type="primary" onClick={this.onSubmit.bind(this)}>
            确定
          </Button>
          <Button onClick={this.handleReset.bind(this)}>重置</Button>
        </div>
      </div>
    );
  }
}

AdvanceSearch.propTypes = {
  handleSubmitAdvancedSearch: PropTypes.func
};
