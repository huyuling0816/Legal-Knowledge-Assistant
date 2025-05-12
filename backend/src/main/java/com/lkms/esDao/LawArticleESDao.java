package com.lkms.esDao;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.DeleteByQueryResponse;
import co.elastic.clients.elasticsearch.core.IndexResponse;
import co.elastic.clients.transport.endpoints.BooleanResponse;
import com.lkms.enums.errorCode.impl.DefaultStatusCode;
import com.lkms.exception.ServiceException;
import com.lkms.po.lawPo.LawArticlePo;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.io.IOException;

@Repository
public class LawArticleESDao {
    @Resource
    ElasticsearchClient client;

    public boolean insertArticleInEs(LawArticlePo lawArticlePo) {
        try {
            BooleanResponse isExist = client.exists(e -> e.index("law_article").id(lawArticlePo.getId()));
            if (isExist.value()) throw new ServiceException(DefaultStatusCode.ES_FAIL);
            IndexResponse response = client.index(i -> i.index("law_article").id(lawArticlePo.getId()).document(lawArticlePo));
            if (!response.result().toString().equals("Created")) {
                throw new RuntimeException(lawArticlePo.getId() + "创建失败");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean deleteArticleInEsByDocId(String docId) {
        try {
            client.deleteByQuery(q -> q.index("law_article").query(qq -> qq.term(t -> t.field("docId").value(docId))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return true;
    }
}
