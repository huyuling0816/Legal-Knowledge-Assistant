package com.lkms;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.BulkResponse;
import co.elastic.clients.elasticsearch.core.bulk.BulkOperation;
import com.lkms.enums.DocLevel;
import com.lkms.esDao.LawDocESDao;
import com.lkms.mapper.*;
import com.lkms.po.LogicPo;
import com.lkms.po.lawPo.LawArticlePo;
import com.lkms.po.lawPo.LawDocPo;
import com.lkms.po.lawPo.LawParagraphPo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EsTest {
    @Resource
    ElasticsearchClient client;
    @Resource
    LawDocMapper docMapper;
    @Resource
    LawArticleMapper articleMapper;
    @Resource
    LawParagraphMapper paragraphMapper;
    @Resource
    LawSubparagraphMapper subparagraphMapper;
    @Resource
    LawItemMapper itemMapper;

    @Resource
    LawDocESDao lawDocESDao;
    @Resource
    LogicMapper logicMapper;

    @Test
    public void insertDoc() {
        int batchSize = 1000;
        int offset = 0;
        while (true) {
            List<LawDocPo> byBatch = docMapper.getByBatch(offset, batchSize);
            if (byBatch.isEmpty()) break;
            List<BulkOperation> bulkOperations = new ArrayList<>();
            for (LawDocPo po : byBatch) {
                bulkOperations.add(BulkOperation.of(o -> o.create(c -> c.index("law_doc").id(po.getId()).document(po))));
            }
            BulkResponse bulkResponse = null;
            try {
                bulkResponse = client.bulk(b -> b.operations(bulkOperations));
            } catch (IOException e) {
                e.printStackTrace();
            }
            offset += batchSize;
            System.out.println(bulkResponse.toString());
        }
    }

    @Test
    public void insertArticle() {
        int batchSize = 5000;
        int offset = 0;
        while (true) {
            List<LawArticlePo> byBatch = articleMapper.getByBatch(offset, batchSize);
            List<BulkOperation> bulkOperations = new ArrayList<>();
            for (LawArticlePo articlePo : byBatch) {
                bulkOperations.add(BulkOperation.of(o -> o.create(c -> c.index("law_article").id(articlePo.getId()).document(articlePo))));
            }
            BulkResponse bulkResponse = null;
            try {
                bulkResponse = client.bulk(b -> b.operations(bulkOperations));
            } catch (IOException e) {
                e.printStackTrace();
            }
            offset += batchSize;
            System.out.println(bulkResponse);
        }
    }

    @Test
    public void bulkInsertDocIntoEs() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("divided", 1);
        List<LawDocPo> lawDocPos = docMapper.selectByMap(map);
//        Map<String, Object> docMap=new HashMap<>();
//        docMap.put("title", "中华人民共和国刑法");
//        List<LawDocPo> lawDocPos=docMapper.selectByMap(docMap);
        List<BulkOperation> bulkOperations = new ArrayList<>();
        for (LawDocPo lawDocPo : lawDocPos) {
            bulkOperations.add(BulkOperation.of(o -> o.create(c -> c.index("law_doc").id(lawDocPo.getId()).document(lawDocPo))));
        }
        BulkResponse bulkResponse = null;
        try {
            bulkResponse = client.bulk(b -> b.operations(bulkOperations));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(bulkResponse.toString());
    }

    @Test
    public void bulkInsertArticleIntoEs() {
        HashMap<String, Object> map = new HashMap<>();
        List<LawArticlePo> lawArticlePos = articleMapper.selectByMap(map);
        List<BulkOperation> bulkOperations = new ArrayList<>();
        for (LawArticlePo articlePo : lawArticlePos) {
            bulkOperations.add(BulkOperation.of(o -> o.create(c -> c.index("law_article").id(articlePo.getId()).document(articlePo))));
        }
        BulkResponse bulkResponse = null;
        try {
            bulkResponse = client.bulk(b -> b.operations(bulkOperations));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(bulkResponse.toString());
    }

    @Test
    public void bulkInsertParagraphIntoEs() {
        HashMap<String, Object> map = new HashMap<>();
        List<LawParagraphPo> paragraphPos = paragraphMapper.selectByMap(map);
        List<BulkOperation> bulkOperations = new ArrayList<>();
        for (LawParagraphPo paragraphPo : paragraphPos) {
            bulkOperations.add(BulkOperation.of(o -> o.create(c -> c.index("law_paragraph").id(paragraphPo.getId()).document(paragraphPo))));
        }
        BulkResponse bulkResponse = null;
        try {
            bulkResponse = client.bulk(b -> b.operations(bulkOperations));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(bulkResponse.toString());
    }

    @Test
    public void bulkInsertLogicIntoEs() {
        HashMap<String, Object> map = new HashMap<>();
        List<LogicPo> logicPos = logicMapper.selectByMap(map);
        List<BulkOperation> bulkOperations = new ArrayList<>();
        for (LogicPo logicPo : logicPos) {
            bulkOperations.add(BulkOperation.of(o -> o.create(c -> c.index("logic").id(logicPo.getId()).document(logicPo))));
        }
        BulkResponse bulkResponse = null;
        try {
            bulkResponse = client.bulk(b -> b.operations(bulkOperations));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(bulkResponse.toString());
    }

    @Test
    public void searchByContentTest() {
        String content = "危害国家安全";
        List<LawArticlePo> results = lawDocESDao.searchByContent(content, DocLevel.ARTICLE, LawArticlePo.class);
        for (LawArticlePo po : results) {
            System.out.println(po.getArticleSeq());
        }
    }

    @Test
    public void searchParagraphTest() {
        String content = "危害国家安全";
        List<LawParagraphPo> results = lawDocESDao.searchByContent(content, DocLevel.PARAGRAPH, LawParagraphPo.class);
        for (LawParagraphPo po : results) {
            System.out.println(po.getParagraphName());
        }
    }

}
