package com.lkms.esDao;

import cn.hutool.core.lang.Pair;
import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.query_dsl.BoolQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.RangeQuery;
import co.elastic.clients.elasticsearch.core.DeleteByQueryResponse;
import co.elastic.clients.elasticsearch.core.IndexResponse;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.json.JsonData;
import co.elastic.clients.transport.endpoints.BooleanResponse;
import com.lkms.constant.Constants;
import com.lkms.enums.DocLevel;
import com.lkms.enums.errorCode.impl.DefaultStatusCode;
import com.lkms.exception.ElasticSearchException;
import com.lkms.exception.ServiceException;
import com.lkms.po.lawPo.LawArticlePo;
import com.lkms.po.lawPo.LawDocPo;
import com.lkms.utils.conventer.NumberConverter;
import com.lkms.utils.object.StringUtils;
import com.lkms.utils.PageInfo;
import com.lkms.vo.DocQueryParam;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Repository
public class LawDocESDao {
    @Resource
    ElasticsearchClient client;

    private final String[] esIndices = new String[]{"law_article", "law_paragraph", "law_subparagraph", "law_item"};
    private final String[] searchFields = new String[]{"articleName", "paragraphName", "subparagraphName", "itemName"};
    private final String[] setMethods = new String[]{"setArticleName", "setParagraphName", "setSubparagraphName", "setItemName"};

    public <T> List<T> searchByContent(String content, DocLevel level, Class<T> tClass) {
        String index = esIndices[level.getCode()];
        String searchField = searchFields[level.getCode()];

        BoolQuery.Builder boolQueryBuilder = new BoolQuery.Builder();
        boolQueryBuilder.must(m -> m.matchPhrase(mm -> mm.field(searchField).query(content)));
        SearchRequest.Builder searchBuilder = new SearchRequest.Builder();
        searchBuilder.index(index)
                .query(q -> q.bool(boolQueryBuilder.build()));
        searchBuilder.highlight(h -> h.fields(searchField, v -> v.preTags(Constants.highlightPrefix).postTags(Constants.highlightSuffix)));

        SearchResponse<T> search;
        try {
            search = client.search(searchBuilder.build(), tClass);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ElasticSearchException(DefaultStatusCode.ES_FAIL);
        }

        List<T> res = new ArrayList<>();
        for (Hit<T> hit : search.hits().hits()) {
            T source = hit.source();
            if (!hit.highlight().keySet().isEmpty()) {
                try {
                    String methodName = setMethods[level.getCode()];
                    Method method = tClass.getMethod(methodName, String.class);
                    method.invoke(source, hit.highlight().get(searchField).get(0));
                } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
            res.add(source);
        }
        return res;
    }

    public List<LawArticlePo> searchArticlesByContent(String content) {
        BoolQuery.Builder boolQueryBuilder = new BoolQuery.Builder();
        boolQueryBuilder.must(m -> m.matchPhrase(mm -> mm.field("articleName").query(content)));
        SearchRequest.Builder searchBuilder = new SearchRequest.Builder();
        searchBuilder.index("law_article")
                .query(q -> q.bool(boolQueryBuilder.build()));
        searchBuilder.highlight(h -> h.fields("articleName", v -> v.preTags(Constants.highlightPrefix).postTags(Constants.highlightSuffix)));
        SearchResponse<LawArticlePo> search;
        try {
            search = client.search(searchBuilder.build(), LawArticlePo.class);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ElasticSearchException(DefaultStatusCode.ES_FAIL);
        }

        List<LawArticlePo> res = new ArrayList<>();
        for (Hit<LawArticlePo> hit : search.hits().hits()) {
            LawArticlePo source = hit.source();
            if (!hit.highlight().keySet().isEmpty()) {
                source.setArticleName(hit.highlight().get("articleName").get(0));
            }
            res.add(source);
        }

        return res;
    }

    public PageInfo<LawDocPo> searchDoc(DocQueryParam param) {
        SearchResponse<LawDocPo> search;
        List<LawDocPo> res = new ArrayList<>();
        try {
            BoolQuery.Builder boolQueryBuilder = new BoolQuery.Builder();
            if (!StringUtils.isEmpty(param.getOffice())) {
                boolQueryBuilder.must(m -> m.match(mm -> mm.field("office").query(param.getOffice())));
            }
            if (param.isPublishExist()) {
                // 需要限制publish时间
                RangeQuery.Builder rangeQueryBuilder = new RangeQuery.Builder();
                rangeQueryBuilder.field("publish");
                if (!StringUtils.isEmpty(param.getPublishStart().toString()))
                    rangeQueryBuilder.gte(JsonData.of(param.getPublishStart()));
                if (!StringUtils.isEmpty(param.getPublishEnd().toString()))
                    rangeQueryBuilder.lte(JsonData.of(param.getPublishEnd()));
                boolQueryBuilder.must(m -> m.range(rangeQueryBuilder.build()));
            }
            if (param.isExpiryExist()) {
                // 需要限制expiry
                RangeQuery.Builder rangeQueryBuilder = new RangeQuery.Builder();
                rangeQueryBuilder.field("expiry");
                if (!StringUtils.isEmpty(param.getExpiryStart().toString()))
                    rangeQueryBuilder.gte(JsonData.of(param.getExpiryStart()));
                if (!StringUtils.isEmpty(param.getExpiryEnd().toString()))
                    rangeQueryBuilder.lte(JsonData.of(param.getExpiryEnd()));
                boolQueryBuilder.must(m -> m.range(rangeQueryBuilder.build()));
            }
            // 需要限制status
            if (param.getStatus() != 0) {
                boolQueryBuilder.filter(f -> f.term(t -> t.field("status").value(param.getStatus())));
            }
            // 需要限制category
            if (!StringUtils.isEmpty(param.getCategory()) && !param.getCategory().equals("全部")) {
                boolQueryBuilder.filter(f -> f.term(t -> t.field("type").value(param.getCategory())));
            }
            if (!StringUtils.isEmpty(param.getInput())) {
                if (param.getSearchType() == null || param.getSearchType().equals("implicit")) {
                    boolQueryBuilder.must(m -> m.match(mm -> mm.field(param.getSearchRangeField()).query(param.getInput())));
                } else {
                    boolQueryBuilder.must(m -> m.matchPhrase(mm -> mm.field(param.getSearchRangeField()).query(param.getInput())));
                }
            }
            SearchRequest.Builder searchBuilder = new SearchRequest.Builder();
            searchBuilder.index("law_doc")
                    .from((param.getPageNum() - 1) * param.getPageSize())
                    .size(param.getPageSize())
                    .query(q -> q.bool(boolQueryBuilder.build()));
            if (!StringUtils.isEmpty(param.getSortType())) {
                searchBuilder.sort(so -> so.field(v -> v.field(param.retrieveSortType()).order(param.getSearchRankType())));
            }
            searchBuilder.highlight(h -> h.fields(param.getSearchRangeField(), v -> v.preTags(Constants.highlightPrefix).postTags(Constants.highlightSuffix)));
            search = client.search(searchBuilder.build(), LawDocPo.class);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ElasticSearchException(DefaultStatusCode.ES_FAIL);
        }
        for (Hit<LawDocPo> hit : search.hits().hits()) {
            LawDocPo source = hit.source();
            if (hit.highlight().keySet().size() != 0) {
                if (param.getSearchRangeField().equals("title")) {
                    source.setTitle(hit.highlight().get("title").get(0).toString());
                } else if (param.getSearchRangeField().equals("fullContent")) {
//                    source.setFullContent(hit.highlight().get("fullContent").get(0).toString());
                    List<String> lawArticles = searchArticle(source.getId(), param.getInput(), param.getSearchType().equals("explicit"));
                    if (lawArticles.size() == 0) continue;
                    StringBuilder sb = new StringBuilder();
                    for (String article : lawArticles) {
                        sb.append(article);
                        sb.append("<br>");
                    }
                    sb.delete(sb.length() - 4, sb.length());
                    source.setFullContent(sb.toString());
                }
            }
            if (source.getFullContent().length() >= Constants.contentMaxLength) {
                source.setFullContent(this.filterFullContent(source.getFullContent()));
            }
            res.add(source);
            System.out.println(source.getTitle());
        }
        return PageInfo.<LawDocPo>builder().pageNum(param.getPageNum()).pageSize(param.getPageSize()).totalPage((int) (search.hits().total().value() / param.getPageSize())).data(res).build();
    }

    public List<String> searchArticle(String docId, String keyword, boolean isExplicit) {
        SearchResponse<LawArticlePo> search;
        List<LawArticlePo> res = new ArrayList<>();
        try {
            BoolQuery.Builder builder = new BoolQuery.Builder();
            builder.must(m -> m.match(mm -> mm.field("docId").query(docId)));
            if (isExplicit) {
                builder.must(m -> m.matchPhrase(mm -> mm.field("articleName").query(keyword)));
            } else {
                builder.must(m -> m.match(mm -> mm.field("articleName").query(keyword)));
            }
            SearchRequest.Builder searchBuilder = new SearchRequest.Builder();
            searchBuilder.index("law_article").query(q -> q.bool(builder.build()));
            searchBuilder.highlight(h -> h.fields("articleName", v -> v.preTags(Constants.highlightPrefix).postTags(Constants.highlightSuffix)));
            search = client.search(searchBuilder.build(), LawArticlePo.class);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ElasticSearchException(DefaultStatusCode.ES_FAIL);
        }
        for (Hit<LawArticlePo> hit : search.hits().hits()) {
            LawArticlePo source = hit.source();
            assert source != null;
            source.setArticleName(hit.highlight().get("articleName").get(0).replace("@Subp@", "").
                    replace("@Para@", ""));
            res.add(source);
        }
        res.sort(Comparator.comparingInt(a -> NumberConverter.getNumInChineseString(a.getArticleSeq())));
        List<String> strRes = new ArrayList<>();
        for (LawArticlePo po : res) {
            String article = po.getArticleSeq() + " " + po.getArticleName();
            strRes.add(article);
        }
        return strRes;
    }

    public boolean createInEs(LawDocPo lawDocPo) {
        try {
            BooleanResponse isExist = client.exists(e -> e.index("law_doc").id(lawDocPo.getId()));
            if (isExist.value()) {
                throw new ServiceException(DefaultStatusCode.ES_FAIL);
            }
            IndexResponse response = client.index(i -> i.index("law_doc").id(lawDocPo.getId()).document(lawDocPo));
            if (!response.result().toString().equals("Created")) {
                throw new RuntimeException(lawDocPo.getId() + "创建失败");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean deleteInEs(String docId) {
        try {
            client.deleteByQuery(q -> q.index("law_doc").query(qq -> qq.term(t -> t.field("id").value(docId))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    public boolean updateInEs(LawDocPo lawDocPo) {
        try {
            client.update(request -> request.index("law_doc").id(lawDocPo.getId()).doc(lawDocPo), LawDocPo.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    private String filterFullContent(String content) {
        int firstPos = content.indexOf(Constants.highlightPrefix);
        int lastPos = content.indexOf(Constants.highlightSuffix);
        if (firstPos >= 0 && lastPos >= 0) {
            firstPos = firstPos - 8 < 0 ? 0 : firstPos - 8;
            lastPos = lastPos + 8 > content.length() ? content.length() : lastPos + 8;
            return "..." + content.substring(firstPos, lastPos) + "...";
        }
        return "..." + content.substring(0, Constants.contentMaxLength) + "...";
    }

    public List<Pair<LawDocPo, Double>> getMentionedDocTilesDocs(List<String> mentionedDocTiles) {
        SearchResponse<LawDocPo> search1 = null;
        List<Pair<LawDocPo, Double>> res = new ArrayList<>();
        BoolQuery.Builder boolQueryBuilder = new BoolQuery.Builder();
        if (mentionedDocTiles.isEmpty()) return res;
        for (String title : mentionedDocTiles) {
            boolQueryBuilder.should(m -> m.match(t -> t.field("title").query(title)));
        }
        try {
            SearchRequest.Builder searchBuilder = new SearchRequest.Builder();
            searchBuilder.index("law_doc")
                    .query(q -> q.bool(boolQueryBuilder.build()));
            search1 = client.search(searchBuilder.build(), LawDocPo.class);

        } catch (IOException e) {
            e.printStackTrace();
            throw new ElasticSearchException(DefaultStatusCode.ES_FAIL);
        }
        return getPairs(search1, res);
    }

    public List<Pair<LawDocPo, Double>> getSimilarTitleLawDocs(String docId, String title) {
        SearchResponse<LawDocPo> search1 = null;
        BoolQuery.Builder boolQueryBuilder = new BoolQuery.Builder();
        List<Pair<LawDocPo, Double>> res = new ArrayList<>();
        try {
            boolQueryBuilder.should(m -> m.match(mm -> mm.field("title").query(title)));
            boolQueryBuilder.should(m -> m.matchPhrase(mm -> mm.field("full_content").query(title)));
            SearchRequest.Builder searchBuilder = new SearchRequest.Builder();
            searchBuilder.index("law_doc")
                    .query(q -> q.bool(boolQueryBuilder.build()));

            search1 = client.search(searchBuilder.build(), LawDocPo.class);


        } catch (IOException e) {
            e.printStackTrace();
            throw new ElasticSearchException(DefaultStatusCode.ES_FAIL);
        }
        return getPairs(search1, res);
    }

    private List<Pair<LawDocPo, Double>> getPairs(SearchResponse<LawDocPo> search1, List<Pair<LawDocPo, Double>> res) {
        for (Hit<LawDocPo> hit : search1.hits().hits()) {
            LawDocPo pd = hit.source();
            if (pd != null) {
                pd.setFullContent("");
            }
            res.add(new Pair<>(pd, hit.score()));
        }
        return res;
    }

}
