import { baseUrl, LOGIC_MODULE } from "./_prefix";

export async function getLogic(body){
    const url = baseUrl + `${LOGIC_MODULE}/getLogic?body=${body}`;
    let response = await fetch(url, {
      method: "GET",
      mode: "cors",
    });
    let responseData = await response.json();
    console.log(responseData.data);
    return responseData.data;
}