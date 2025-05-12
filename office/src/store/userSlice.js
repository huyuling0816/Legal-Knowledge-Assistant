import { createSlice } from "@reduxjs/toolkit";

export const userSlice = createSlice({
  name: "user",
  initialState: {
    isLogin: false,
    user: {}
  },
  reducers: {
    setUser: (state, action) => {
      state.user = action.payload;
    },
    setLoginStatus: (state, action) => {
      state.isLogin = action.payload;
    }
  }
});

export const { setUser, setLoginStatus } = userSlice.actions;
export default userSlice.reducer;
