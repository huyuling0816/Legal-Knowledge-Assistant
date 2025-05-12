import { str } from "mockjs/src/mock/random/basic";

export function dateFormatter(date) {
  date = date.toString();
  // date: Tue Apr 30 2024 00:00:00 GMT+0800 (CST)
  const array = date.split(" ");
  const year = array[3];
  let month = array[1];
  const day = array[2];
  if (month === "Jan") month = "01";
  else if (month === "Feb") month = "02";
  else if (month === "Mar") month = "03";
  else if (month === "Apr") month = "04";
  else if (month === "May") month = "05";
  else if (month === "Jun") month = "06";
  else if (month === "Jul") month = "07";
  else if (month === "Aug") month = "08";
  else if (month === "Sep") month = "09";
  else if (month === "Oct") month = "10";
  else if (month === "Nov") month = "11";
  else if (month === "Dec") month = "12";
  return year + "-" + month + "-" + day;
}
