import { toInteger } from "chinese-numbers-to-arabic";

const paraSort = (a, b) => {
  let numa = a.paraSeq.match(/第(\d+)款/)[1];
  let numb = b.paraSeq.match(/第(\d+)款/)[1];
  return numa - numb;
};

const itemSort = (a, b) => {
  let numa = a.itemSeq.match(/第(\d+)目/)[1];
  let numb = b.itemSeq.match(/第(\d+)目/)[1];
  return numa - numb;
};

const articleSort = (a, b) => {
  let numa = a.articleSeq.match(/第(.+)条/)[1];
  let numb = b.articleSeq.match(/第(.+)条/)[1];
  return toInteger(numa) - toInteger(numb);
};

export { paraSort, itemSort, articleSort };
