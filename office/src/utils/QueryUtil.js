export function getQueryLawPayload(
  input = "",
  office = "",
  publishStart = "",
  publishEnd = "",
  expiryStart = "",
  expiryEnd = "",
  status = "",
  category = "",
  sortType = "",
  rankType = "",
  searchRange = "title",
  searchType = "implicit",
  pageNum = 1,
  pageSize = 10
) {
  return {
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
  };
}
