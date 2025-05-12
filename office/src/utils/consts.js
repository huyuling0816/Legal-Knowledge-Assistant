const searchRange = {
  "标题": "title",
  "正文": "text"
};
const searchType = {
  "精确查询": "explicit",
  "模糊查询": "implicit"
};
const status = {
  valid: "1", // 生效
  toBeValid: "3", // 尚未生效
  modified: "5", // 已修改
  none: "7", // 无
  abolished: "9", // 已废止
  all: "0"
};
const statusDict = {
  1: "生效",
  3: "尚未生效",
  5: "已修改",
  7: "无",
  9: "已废止",
  0: "全部"
};
const chinese2Status = {
  生效: 1,
  尚未生效: 3,
  已修改: 5,
  无: 7,
  已废止: 9,
  全部: 0
};
const statusOptions = [
  {
    value: "1",
    label: "生效"
  },
  {
    value: "3",
    label: "尚未生效"
  },
  {
    value: "5",
    label: "已修改"
  },
  {
    value: "7",
    label: "无"
  },
  {
    value: "9",
    label: "已废止"
  }, {
    value: "0",
    label: "全部"
  }
];
const category = {
  local: "地方性法规",
  constitution: "宪法",
  law: "法律",
  problemDecision: "有关法律问题和重大问题的决定",
  lawInterpretation: "法律解释",
  judicialInterpretation: "司法解释",
  administrativeRegulations: "行政法规",
  modifyOrAbolishDecision: "修改、废止的决定",
  superVisionRegulation: "监查法规",
  all: "全部"
};
const categoryOptions = [
  { value: "地方性法规" },
  { value: "宪法" },
  { value: "法律" },
  { value: "有关法律问题和重大问题的决定" },
  { value: "法律解释" },
  { value: "司法解释" },
  { value: "行政法规" },
  { value: "废止的决定" },
  { value: "监查法规" },
  { value: "全部" }
];
const rankType = {
  "正序": "asc",
  "倒序": "desc"
};
const sortType = {
  "按标题": "title",
  "按公布日期": "publish",
  "按生效日期": "expiry"
};
const sortTypeOptions = [
  { value: "title", name: "按标题" },
  { value: "publish", name: "发布时间" },
  { value: "expiry", name: "生效时间" }
];

export {
  status,
  statusDict,
  searchRange,
  searchType,
  category,
  categoryOptions,
  rankType,
  sortType,
  sortTypeOptions,
  statusOptions,
  chinese2Status
};
