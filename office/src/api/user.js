import axios from "axios";
import { baseUrl, USER_MODULE } from "./_prefix";

axios.defaults.withCredentials = true;

export const register = (payload) => {
  const url = baseUrl + USER_MODULE + "/register";
  const { name, email, password } = payload;
  return axios
    .post(url, {
      name,
      email,
      password
    })
    .then((res) => {
      return res.data;
    });
};

export const login = (payload) => {
  const url = baseUrl + USER_MODULE + "/login";
  let axiosConfig = {
    withCredentials: true
  };
  return axios.post(url, payload, axiosConfig).then(res => {
    console.log(res.headers);
    return res.data;
  });
};
