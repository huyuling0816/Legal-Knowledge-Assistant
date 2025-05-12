import * as React from "react";
import { useRoutes } from "react-router-dom";
import LawDb from "../taskpane/components/lawdb/LawDb";
import AddLaw from "../taskpane/components/lawdb/AddLaw";
import MyUpload from "../taskpane/components/myupload/MyUpload";
import LawInfo from "../taskpane/components/lawdb/LawInfo";
import MainArea from "../taskpane/components/editLaw/MainArea";
import AttachedLaws from "../taskpane/components/attachedLaw/AttachedLaws";
import ResultPrediction from "../taskpane/components/resultPrediction/ResultPrediction";
import MyUploadLawInfo from "../taskpane/components/myupload/MyUploadLawInfo";
import Register from "../taskpane/components/user/Register";
import Login from "../taskpane/components/user/Login";

const routes = [
  {
    path: "/",
    element: <LawDb />
  },
  {
    path: "/lawdb",
    element: <LawDb />
  },
  {
    path: "/lawdb/:lawId",
    element: <LawInfo></LawInfo>
  },
  {
    path: "/addlaw",
    element: <AddLaw></AddLaw>
  },
  {
    path: "/myupload",
    element: <MyUpload />
  },
  {
    path: "/editDoc",
    element: <MainArea></MainArea>
  },
  {
    path: "/attachedLaw",
    element: <AttachedLaws></AttachedLaws>
  },
  {
    path: "/resultPrediction",
    element: <ResultPrediction></ResultPrediction>
  },
  {
    path: "/myupload/:lawId",
    element: <MyUploadLawInfo />
  },
  {
    path: "/user/login",
    element: <Login />
  },
  {
    path: "/user/register",
    element: <Register />
  },
];

export default function RouterView() {
  // 创建路由并返回接口
  return useRoutes(routes);
}
