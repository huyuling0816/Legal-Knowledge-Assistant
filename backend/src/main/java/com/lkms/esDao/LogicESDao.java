package com.lkms.esDao;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.query_dsl.BoolQuery;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import com.lkms.enums.errorCode.impl.DefaultStatusCode;
import com.lkms.exception.ElasticSearchException;
import com.lkms.po.LogicPo;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class LogicESDao {
    @Resource
    ElasticsearchClient client;

    public List<LogicPo> getLogicsFromBody(String body, int logicNum){
        BoolQuery.Builder boolQueryBuilder = new BoolQuery.Builder();
        boolQueryBuilder.must(m -> m.match(mm -> mm.field("body").query(body)));
        SearchRequest.Builder searchBuilder = new SearchRequest.Builder();
        searchBuilder.index("logic")
                .query(q->q.bool(boolQueryBuilder.build()));
        searchBuilder.size(logicNum);

        SearchResponse<LogicPo> search;
        try {
            search = client.search(searchBuilder.build(), LogicPo.class);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ElasticSearchException(DefaultStatusCode.ES_FAIL);
        }

        List<LogicPo> res=new ArrayList<>();
        for (Hit<LogicPo> hit: search.hits().hits()){
            LogicPo source=hit.source();
            res.add(source);
        }
        return res;
    }
}
