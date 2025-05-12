import { baseUrl, ATTACHED_LAW_MODULE } from "./_prefix";
import { MessageBox } from "element-react";

let filePath = "";
Office.context.document.getFilePropertiesAsync(function (asyncResult) {
  filePath = asyncResult.value.url;
});

export async function addAttachedDoc(docId) {
  const url = baseUrl + `${ATTACHED_LAW_MODULE}/addDocToWordDocument`;
  var data = { docId: docId, filePath: filePath };
  let response = await fetch(url, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(data),
    mode: "cors",
  });
  // console.log(response);
}

export async function deleteAttachedDoc(docId) {
  const url = baseUrl + `${ATTACHED_LAW_MODULE}/deleteDocFromWordDocument`;
  var data = { docId: docId, filePath: filePath };
  let response = await fetch(url, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(data),
    mode: "cors",
  });
  console.log(response);
}

export async function getAttachedDoc() {
  var path = filePath.replaceAll("/", "%2f");
  const url = baseUrl + `${ATTACHED_LAW_MODULE}/getAttachedDoc?filePath=${path}`;
  let response = await fetch(url, {
    method: "GET",
    // headers: {
    //   "Content-Type": "application/json",
    // },
    // body: JSON.stringify(data),
    mode: "cors",
  });
  let responseData = await response.json();
  //   console.log(responseData.data);
  return responseData.data;
}

export async function getAttachedArticles() {
  var path = filePath.replaceAll("/", "%2f");
  const url = baseUrl + `${ATTACHED_LAW_MODULE}/getAttachedArticle?filePath=${path}`;
  let response = await fetch(url, {
    method: "GET",
    mode: "cors",
  });
  let responseData = await response.json();
  console.log(responseData.data);
  return responseData.data;
}

export async function addAttachedArticle(articleId) {
  const url = baseUrl + `${ATTACHED_LAW_MODULE}/addArticleToWordDocument`;
  var data = { articleId: articleId, filePath: filePath };
  let response = await fetch(url, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(data),
    mode: "cors",
  });
  console.log(response)
}

export async function deleteAttachedArticle(articleId){
  const url = baseUrl + `${ATTACHED_LAW_MODULE}/deleteArticleFromWordDocument`;
  var data={articleId: articleId, filePath: filePath};
  let response=await fetch(url,{
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(data),
    mode: "cors",
  });
  console.log(response);
}

export async function getMentionedFiles(){
  var path = filePath.replaceAll("/", "%2f");
  const url = baseUrl + `${ATTACHED_LAW_MODULE}/getMentionedFiles?filePath=${path}`;
  let response = await fetch(url, {
    method: "GET",
    mode: "cors",
  });
  console.log(response);
  MessageBox.alert("解析成功！");
}
