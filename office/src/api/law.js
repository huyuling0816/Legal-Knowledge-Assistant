import { baseUrl, LAW_MODULE } from "./_prefix";

export async function queryLaw(payload) {
  const {
    input,
    office,
    publishStart,
    publishEnd,
    expiryStart,
    expiryEnd,
    status,
    category,
    sortType,
    rankType,
    searchRange,
    searchType,
    pageNum,
    pageSize,
  } = payload;
  const url = baseUrl + `${LAW_MODULE}/query`;
  console.log(url);
  let response = await fetch(url, {
    method: "POST",
    body: JSON.stringify(payload),
    headers: {
      "Content-Type": "application/json",
    },
    mode: "cors",
  });
  return await response.json();
}

export async function createByText(forms, userId) {
  const url = baseUrl + `${LAW_MODULE}/createByText/${userId}`;
  console.log(url);
  let response = await fetch(url, {
    method: "POST",
    body: JSON.stringify(forms),
    headers: {
      "Content-Type": "application/json"
    },
    mode: "cors"
  });
  return await response.json();
}

export async function getSimpleDoc(payload) {
  payload = payload.replace(/%/g, "%25");
  //TESTED
  const url = baseUrl + LAW_MODULE + `/docSimple/${payload}`;
  let response = await fetch(url, {
    method: "GET",
    mode: "cors",
  });
  return await response.json();
}

export async function getSimplePartByDocId(payload) {
  payload = payload.replace(/%/g, "%25");
  const url = baseUrl + LAW_MODULE + `/partSimple/${payload}`;
  let response = await fetch(url, {
    method: "GET",
    mode: "cors",
  });
  return await response.json();
}

export async function getSimpleChapByDocId(payload) {
  payload = payload.replace(/%/g, "%25");
  const url = baseUrl + LAW_MODULE + `/chapSimple/${payload}`;
  let response = await fetch(url, {
    method: "GET",
    mode: "cors",
  });
  return await response.json();
}

export async function getSimpleSectByDocId(payload) {
  payload = payload.replace(/%/g, "%25");
  const url = baseUrl + LAW_MODULE + `/sectSimple/${payload}`;
  let response = await fetch(url, {
    method: "GET",
    mode: "cors",
  });
  return await response.json();
}

export async function getSimpleArticleByDocId(payload) {
  payload = payload.replace(/%/g, "%25");
  const url = baseUrl + LAW_MODULE + `/articleSimple/${payload}`;
  let response = await fetch(url, {
    method: "GET",
    mode: "cors",
  });
  return await response.json();
}

export async function getFullArticlesByDocId(payload) {
  const docId = payload;
  console.log(docId);
  const url = baseUrl + LAW_MODULE + `/getFullArticles/${docId}`;
  let response = await fetch(url, {
    method: "GET",
    mode: "cors",
  });
  let jsonRes = await response.json();
  // console.log(jsonRes);
  return jsonRes;
}

export async function getArticlesByContent(content) {
  const url = baseUrl + LAW_MODULE + `/getArticlesByContent/${content}`;
  let response = await fetch(url, {
    method: "GET",
    mode: "cors",
  });
  let jsonRes = await response.json();
  return jsonRes;
}

export async function getSimpleParaByDocId(payload) {
  payload = payload.replace(/%/g, "%25");
  const url = baseUrl + LAW_MODULE + `/paraSimple/${payload}`;
  let response = await fetch(url, {
    method: "GET",
    mode: "cors",
  });
  return await response.json();
}

export async function getSimpleSubParaByDocId(payload) {
  payload = payload.replace(/%/g, "%25");
  const url = baseUrl + LAW_MODULE + `/subParaSimple/${payload}`;
  let response = await fetch(url, {
    method: "GET",
    mode: "cors",
  });
  return await response.json();
}

export async function getByUserId(payload) {
  //TESTED
  const url = baseUrl + LAW_MODULE + `/getByUserId/${payload}`;
  let response = await fetch(url, {
    method: "GET",
    mode: "cors"
  });
  return await response.json();
}

export async function deleteByDocId(payload, userId) {
  payload = payload.replace(/%/g, "%25");
  console.log(payload);
  //TESTED;
  const url = baseUrl + LAW_MODULE + `/delete/${payload}?userId=${userId}`;
  let response = await fetch(url, {
    method: "GET",
    mode: "cors"
  });
  return await response.json();
}

export async function updateLaw(payload, userId) {
  //TESTED;
  const url = baseUrl + LAW_MODULE + `/update/${userId}`;
  let response = await fetch(url, {
    method: "POST",
    mode: "cors",
    body: JSON.stringify(payload),
    headers: {
      "Content-Type": "application/json"
    }
  });
  return await response.json();
}

export async function createByFile(payload) {
  const url = baseUrl + LAW_MODULE + `/createByFile`;
  console.log(url);
  let response = await fetch(url, {
    method: "POST",
    mode: "cors",
    body: payload,
    // headers: {
    //   "Content-Type": "multipart/form-data",
    // },
  });
  return await response.json();
}

export async function getRelatedLaws(payload) {
  payload = payload.replace(/%/g, "%25");
  const url = baseUrl + LAW_MODULE + `/getRelated/${payload}`;
  let response = await fetch(url, {
    method: "GET",
    mode: "cors",
  });
  return await response.json();
}

export async function getSimpleItemByDocId(payload) {
  payload = payload.replace(/%/g, "%25");
  const url = baseUrl + LAW_MODULE + `/itemSimple/${payload}`;
  let response = await fetch(url, {
    method: "GET",
    mode: "cors",
  });
  return await response.json();
}

export async function getSimpleAppendixByDocId(payload) {
  payload = payload.replace(/%/g, "%25");
  const url = baseUrl + LAW_MODULE + `/appendixSimple/${payload}`;
  let response = await fetch(url, {
    method: "GET",
    mode: "cors",
  });
  return await response.json();
}
