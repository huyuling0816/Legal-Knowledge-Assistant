import { baseUrl, API_VERSION, NOTE_MODULE, COMPLETE_MODULE } from "./_prefix";
/* global fetch, console */
export async function getInterpretationArticleList(payload) {
  //TESTED
  const url = baseUrl + `${NOTE_MODULE}/getInterpretationArticleList`;
  let response = await fetch(url, {
    method: "POST",
    body: { selectedText: payload },
    headers: {
      "Content-Type": "application/json",
    },
    mode: "cors",
  });
  let jsonRes = await response.json();
  return jsonRes;
}
export async function getRelatedArticleList(payload) {
  const url = baseUrl + `${NOTE_MODULE}/getRelatedArticleList`;
  let response = await fetch(url, {
    method: "POST",
    body: JSON.stringify({ selectedText: payload }),
    headers: {
      "Content-Type": "application/json",
    },
    mode: "cors",
  });
  let jsonRes = await response.json();
  return jsonRes;
}
export async function getRelatedDocList(payload) {
  const url = baseUrl + `${NOTE_MODULE}/getRelatedArticleList`;
  let response = await fetch(url, {
    method: "POST",
    body: { selectedText: payload },
    headers: {
      "Content-Type": "application/json",
    },
    mode: "cors",
  });
  let jsonRes = await response.json();
  return jsonRes;
}

export async function getKeywordCompletion(payload) {
  //TESTED
  const url = baseUrl + COMPLETE_MODULE + `/keyword?text=${payload}`;
  let response = await fetch(url, {
    method: "GET",
    mode: "cors",
  });
  let jsonRes = await response.json();
  return jsonRes;
}

export async function getDocTitleCompletion(payload) {
  //TESTED
  const url = baseUrl + `${COMPLETE_MODULE}/lawTitle?text=${payload}`;
  let response = await fetch(url, {
    method: "GET",
    mode: "cors",
  });
  let jsonRes = await response.json();
  return jsonRes;
}
