import { createSlice } from "@reduxjs/toolkit";

export const lawSlice = createSlice({
  name: "law",
  initialState: {
    lawDoc: {},
    lawParts: [],
    lawChaps: [],
    lawSects: [],
    lawArticles: [],
    lawParas: [],
    lawSubParas: [],
    lawItems: [],
    lawAppendixes: [],
    relatedLaws: [],
  },
  reducers: {
    setDoc: (state, action) => {
      state.lawDoc = action.payload;
    },
    setParts: (state, action) => {
      state.lawParts = action.payload;
    },
    setChaps: (state, action) => {
      state.lawChaps = action.payload;
    },
    setSects: (state, action) => {
      state.lawSects = action.payload;
    },
    setArticles: (state, action) => {
      state.lawArticles = action.payload;
    },
    setParas: (state, action) => {
      state.lawParas = action.payload;
    },
    setSubParas: (state, action) => {
      state.lawSubParas = action.payload;
    },
    setItems: (state, action) => {
      state.lawItems = action.payload;
    },
    setAppendixes: (state, action) => {
      state.lawAppendixes = action.payload;
    },
    setRelatedLaws: (state, action) => {
      state.relatedLaws = action.payload;
    },
  }
});

export const {
  setDoc,
  setParts,
  setChaps,
  setSects,
  setArticles,
  setParas,
  setSubParas,
  setItems,
  setAppendixes,
  setRelatedLaws
} = lawSlice.actions;

export default lawSlice.reducer;
