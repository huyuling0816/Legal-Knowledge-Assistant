package com.lkms;

import com.lkms.service.LawDocService;
import com.lkms.utils.PageInfo;
import com.lkms.vo.DocQueryParam;
import com.lkms.vo.lawVo.LawArticleVo;
import com.lkms.vo.lawVo.LawDocVo;
import com.lkms.vo.lawVo.LawPartVo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LawDocTest {
    @Autowired
    LawDocService lawDocService;

    @Test
    public void getFullArticlesByDocIdTest() {
        String docId = "ZmY4MDgxODE3OTZhNjM2YTAxNzk4MjJhMTk2NDBjOTI%3D";
        List<LawArticleVo> articleVos = lawDocService.getFullArticlesByDocId(docId);
        for (int i = 0; i < 10; i++) {
            System.out.println(articleVos.get(i).getArticleSeq());
        }
    }

    @Test
    public void getDocByContentTest() {
        DocQueryParam param = new DocQueryParam();
        param.setSearchRange("text");
        param.setStatus(0);
        param.setInput("保卫国家安全");
        PageInfo<LawDocVo> queryResults = lawDocService.query(param);
        List<LawDocVo> resultData = queryResults.getData();
        for (LawDocVo vo : resultData) {
            System.out.println(vo.getTitle());
        }
    }

    @Test
    public void searchArticlesByContentTest() {
        String content = ("刑法");
        List<LawArticleVo> queryResults = lawDocService.searchArticlesByContent(content);
        for (LawArticleVo vo : queryResults) {
            System.out.println(vo.getArticleSeq());
        }
    }

    @Test
    public void testCreateByText() throws Exception {
        LawDocVo lawDocVo = new LawDocVo();
        lawDocVo.setTitle("my law");
        lawDocVo.setFullContent("目　　录\n" + "　　第一章　总则\n" + "\n" + "第一章　总则\n" + "\n" + "　　第一条　为了保障邮政普遍服务，加强对邮政市场的监督管理，维护邮政通信与信息安全，保护通信自由和通信秘密，保护用户合法权益，促进邮政业健康发展，适应经济社会发展和人民生活需要，制定本法。");
        LawDocVo createdLaw = lawDocService.createByText(6, lawDocVo);
        LawDocVo docById = lawDocService.getDocById(createdLaw.getId());
        Assert.assertNotNull(docById);
        Assert.assertEquals("my law", docById.getTitle());
    }

    @Test
    public void testUpdateLaw() {
        String id = "45986a63-369f-478d-b3d0-251a180bcccb";
        LawDocVo updateLaw = new LawDocVo();
        updateLaw.setId(id);
        updateLaw.setTitle("我的法律");
        lawDocService.updateLaw(6, updateLaw);
        LawDocVo docById = lawDocService.getDocById(id);
        Assert.assertEquals(docById.getTitle(), "我的法律");
    }

    @Test
    public void testQueryLaws() {
        DocQueryParam param = new DocQueryParam();
        param.setPageNum(1);
        param.setPageSize(20);
        param.setInput("家庭暴力");
        param.setSearchRange("title");
        PageInfo<LawDocVo> query = lawDocService.query(param);
        Assert.assertNotNull(query);
        Assert.assertEquals(20, query.getData().size());
    }

    @Test
    public void testDeleteLaw() {
        String id = "45986a63-369f-478d-b3d0-251a180bcccb";
        lawDocService.deleteLaw(id, 6);
        LawDocVo docById = lawDocService.getDocById(id);
        Assert.assertNull(docById);
    }

    @Test
    public void testGetPartByDocId() {
        String docId = "ZmY4MDgwODE3MjlkMWVmZTAxNzI5ZDUwYjVjNTAwYmY%3D";
        List<LawPartVo> parts = lawDocService.getPartByDocId(docId);
        Assert.assertEquals(15, parts.size());
    }
}
