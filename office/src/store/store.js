import { configureStore } from "@reduxjs/toolkit";
import lawReducer from "./lawSlice";
import counterReducer from "./counterSlice";
import userReducer from "./userSlice"
export default configureStore({
  reducer: {
    law: lawReducer,
    counter: counterReducer,
    user: userReducer
  },
});
