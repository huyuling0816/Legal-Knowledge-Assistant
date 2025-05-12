package com.lkms;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.SortOrder;
import co.elastic.clients.elasticsearch._types.query_dsl.BoolQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.QueryBuilders;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lkms.enums.errorCode.impl.DefaultStatusCode;
import com.lkms.esDao.LawDocESDao;
import com.lkms.exception.ElasticSearchException;
import com.lkms.mapper.LawDocMapper;
import com.lkms.po.lawPo.LawDocPo;
import com.lkms.vo.DocQueryParam;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpeedTest {
    @Autowired
    LawDocMapper lawDocMapper;
    DocQueryParam docQueryParam = new DocQueryParam();
    @Autowired
    ElasticsearchClient client;
    @Before
    public void beforeTest() {
        docQueryParam.setInput("家庭暴力");
//        docQueryParam.setPublishStart(new Date(2015, Calendar.JANUARY,1));
//        docQueryParam.setPublishEnd(new Date(2024, Calendar.JANUARY,1));
        docQueryParam.setSearchRange("正文");
//        docQueryParam.setSearchType("标题");
        docQueryParam.setRankType("正序");
        docQueryParam.setSortType("title");
    }

    // 231篇
    // 3072
    @Test
    public void testMysqlSpeed () {
        QueryWrapper<LawDocPo> wrapper = new QueryWrapper<>();
        wrapper.like("fullContent", docQueryParam.getInput());
//        wrapper.ge("publish", docQueryParam.getPublishStart());
//        wrapper.le("publish", docQueryParam.getPublishEnd());
        wrapper.orderByAsc("title");
        long currentTimeMillis1 = System.currentTimeMillis();
        List<LawDocPo> lawDocPos = lawDocMapper.selectList(wrapper);
        long currentTimeMillis2 = System.currentTimeMillis();
        System.out.println(lawDocPos.size());
        System.out.println(currentTimeMillis2 - currentTimeMillis1);
    }
    // 438
    @Test
    public void testEsSpeed () {
        SearchResponse<LawDocPo> search;
        List<LawDocPo> res = new ArrayList<>();
        try {
            BoolQuery.Builder boolQueryBuilder = new BoolQuery.Builder();
//            boolQueryBuilder.must(m -> m.match(mm -> mm.field("fullContent").query(docQueryParam.getInput())));
            boolQueryBuilder.must(m -> m.term(mm -> mm.field("fullContent").value(docQueryParam.getInput())));
            SearchRequest.Builder searchBuilder = new SearchRequest.Builder();
            searchBuilder.index("law_doc").query(q -> q.bool(boolQueryBuilder.build()))
                    .from(0).size(300);
            searchBuilder.sort(s -> s.field(v -> v.field(docQueryParam.retrieveSortType()).order(SortOrder.Asc)));
            long currentTimeMillis1 = System.currentTimeMillis();
            search = client.search(searchBuilder.build(), LawDocPo.class);
            for (Hit<LawDocPo> hit : search.hits().hits()) {
                res.add(hit.source());
            }
            long currentTimeMillis2 = System.currentTimeMillis();
            System.out.println(currentTimeMillis2 - currentTimeMillis1);
            System.out.println(res.size());
        } catch (IOException e) {
            e.printStackTrace();
            throw new ElasticSearchException(DefaultStatusCode.ES_FAIL);
        }
    }

}
