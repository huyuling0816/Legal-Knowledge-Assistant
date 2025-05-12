import * as React from "react";
import PropTypes from "prop-types";
import { DefaultButton } from "@fluentui/react";
import Header from "./Header";
import HeroList from "./HeroList";
import Progress from "./Progress";
import {
  BrowserRouter,
  Routes,
  Route,
  Link,
  useNavigate,
  NavLink,
  HashRouter as Router
} from "react-router-dom";
import { Button, Layout } from "element-react";
/* global Word, require, Office, console */
import { base64Image } from "../../utils/base64Image";
import MainArea from "./editLaw/MainArea";
import RouterView from "../../router";
import { Menu } from "element-react";
import { connect, useSelector } from "react-redux";
import { useEffect } from "react";

function write(proxy, text) {
  proxy.setState({
    prompt: "qqqqqqqqqqqqq"
  });
}

export default class App extends React.Component {
  constructor(props, context) {
    super(props, context);
    this.state = {
      listItems: [],
      prompt: "word"
    };
  }

  // componentDidMount() {
  //   this.setState({
  //     listItems: [
  //       {
  //         icon: "Ribbon",
  //         primaryText: "ice integration"
  //       },
  //       {
  //         icon: "Unlock",
  //         primaryText: "Unlock features and functionality"
  //       },
  //       {
  //         icon: "Design",
  //         primaryText: "Create and visualize like a pro"
  //       }
  //     ],
  //     prompt: "wwwwwwwwww"
  //   });
  // }

  click = async () => {
    return Word.run(async (context) => {
      /**
       * Insert your Word code here
       */

        // insert a paragraph at the end of the document.
      const paragraph = context.document.body.insertParagraph("kjugss", Word.InsertLocation.end);

      // change the paragraph color to blue.
      paragraph.font.color = "green";

      await context.sync();
    });
  };

  applyStyle = async () => {
    return Word.run(async (context) => {
      /**
       * Insert your Word code here
       */
        // insert a paragraph at the end of the document.
      const secondParagraph = context.document.body.paragraphs.getFirst().getNext();
      secondParagraph.font.set({
        name: "Courier New",
        bold: true,
        size: 18
      });
    });
  };
  test = async () => {
    // this.getSelectedData();
    this.setSelectedData();
  };

  getSelectedData = () => {
    Office.context.document.getSelectedDataAsync(Office.CoercionType.Text, function(asyncResult) {
      write(this, asyncResult.status);
      // if (asyncResult.status == Office.AsyncResultStatus.Failed) {
      //   write("Action failed. Error: " + asyncResult.error.message);
      // } else {
      //   write("Selected data: " + asyncResult.value);
      // }
    });
  };
  setSelectedData = () => {
    Office.context.document.setSelectedDataAsync("Hello World!", function(asyncResult) {
      this.setState({ prompt: "aaa" });
      if (asyncResult.status == Office.AsyncResultStatus.Failed) {
        write(this, asyncResult.error.message);
      }
    });
  };
  //insert text
  insertSentenceIntoParagraph = async () => {
    return Word.run(async (context) => {
      const paragraphs = context.document.getSelection().paragraphs;
      paragraphs.load();
      await context.sync();
      paragraphs.items[0].insertText(" New sentence in the paragraph.", Word.InsertLocation.end);
      await context.sync();
    });
  };
  insertTextIntoRange = async () => {
    return Word.run(async (context) => {
      const doc = context.document;
      const originalRange = doc.getSelection();
      originalRange.insertText(" (M365)", Word.InsertLocation.end);
      //Start, Before, After, Replace
      originalRange.load("text");
      await context.sync();
      doc.body.insertParagraph("Original range: " + originalRange.text, Word.InsertLocation.end);
      await context.sync();
    });
  };
  insertTextOutsideRange = async () => {
    return Word.run(async (context) => {
      //在前面插入
      const doc = context.document;
      const originalRange = doc.getSelection();
      originalRange.insertText("Office 2019, ", Word.InsertLocation.before);
      //加载并等待text属性完成
      originalRange.load("text");
      await context.sync();
      // 后面插入
      doc.body.insertParagraph("Current text of original range: " + originalRange.text, Word.InsertLocation.end);
      await context.sync();
    });
  };
  replaceText = async () => {
    return Word.run(async (context) => {
      const doc = context.document;
      const originalRange = doc.getSelection();
      originalRange.insertText("many", Word.InsertLocation.replace);
      await context.sync();
    });
  };

  //insert media
  insertImage = async () => {
    return Word.run(async (context) => {
      context.document.body.insertInlinePictureFromBase64(base64Image, Word.InsertLocation.end);
      await context.sync();
    });
  };
  insertHtml = async () => {
    return Word.run(async (context) => {
      const blankParagraph = context.document.body.paragraphs.getLast().insertParagraph("", Word.InsertLocation.after);
      blankParagraph.insertHtml(
        "<p style=\"font-family: verdana;\">Inserted HTML.</p><p>Another paragraph</p>",
        Word.InsertLocation.end
      );
      await context.sync();
    });
  };
  insertTable = async () => {
    return Word.run(async (context) => {
      //获取对第二段的引用
      const secondParagraph = context.document.body.paragraphs.getFirst().getNext();
      const tableData = [
        ["Name", "ID", "Birth City"],
        ["Bob", "434", "Chicago"],
        ["Sue", "719", "Havana"]
      ];
      secondParagraph.insertTable(3, 3, Word.InsertLocation.after, tableData);
      await context.sync();
    });
  };
  createContentPanel = async () => {
    return Word.run(async (context) => {
      const serviceNameRange = context.document.getSelection();
      const serviceNameContentControl = serviceNameRange.insertContentControl();
      serviceNameContentControl.title = "Service Name";
      serviceNameContentControl.tag = "serviceName";
      serviceNameContentControl.appearance = "Tags";
      serviceNameContentControl.color = "blue";
      await context.sync();
    });
  };
  replaceContentInControl = async () => {
    return Word.run(async (context) => {
      const serviceNameContentControl = context.document.contentControls.getByTag("serviceName").getFirst();
      serviceNameContentControl.insertText("Fabrikam Online Productivity Suite", Word.InsertLocation.replace);
      await context.sync();
    });
  };

  render() {
    // if (!isOfficeInitialized) {
    //   return (
    //     <Progress
    //       title={title}
    //       logo={require("./../../../assets/logo-filled.png")}
    //       message="Please sideload your addin to see app body."
    //     />
    //   );
    // }

    return (
      <div className="ms-welcome">
        <Router>
          <Menu
            theme="dark"
            defaultActive="1"
            className="el-menu-demo"
            mode="horizontal"
            style={{ width: "100%" }}
            // onSelect={this.onSelect.bind(this)}
          >
            <Menu.Item index="1">
              <NavLink style={{ textDecoration: "none" }} to={"/lawdb"}>
                法条数据库
              </NavLink>
            </Menu.Item>
            <Menu.Item index="2">
              <NavLink style={{ textDecoration: "none" }} to={"/myupload"}>
                我的上传
              </NavLink>
            </Menu.Item>
            <Menu.Item index="3">
              <NavLink style={{textDecoration: "none"}} to={"/editDoc"}>
                文书编辑
              </NavLink>
            </Menu.Item>
            <Menu.Item index="4">
              <NavLink style={{textDecoration: "none"}} to={"/attachedLaw"}>
                相关法律
              </NavLink>
            </Menu.Item>
            <Menu.Item index="5">
              <NavLink style={{textDecoration: "none"}} to={"/resultPrediction"}>
                判决预测
              </NavLink>
            </Menu.Item>
          </Menu>
          <RouterView />
        </Router>
      </div>
    );
  }
}

App.propTypes = {
  title: PropTypes.string,
  isOfficeInitialized: PropTypes.bool,
};
