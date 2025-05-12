package com.lkms;

import com.lkms.service.AttachedDocService;
import com.lkms.vo.lawVo.LawArticleVo;
import com.lkms.vo.lawVo.LawDocVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AttachedDocTest {
    @Autowired
    AttachedDocService attachedDocService;

    @Test
    public void addDocToWordDocumentTest(){
        String docId="MmM5MDlmZGQ2NzhiZjE3OTAxNjc4YmY2M2I5ZTAzMzk%3D";
        String filePath="/Users/zhangtongke/Desktop/word_test.docx";
        attachedDocService.addDocToWordDocument(docId,filePath);
    }

    @Test
    public void deleteDocFromWordTest(){
        String docId="ZmY4MDgxODE3OTZhNjM2YTAxNzk4MjJhMTk2NDBjOTI%3D";
        String filePath="/Users/zhangtongke/Desktop/word_test.docx";
        attachedDocService.deleteDocFromWordDocument(docId,filePath);
    }

    @Test
    public void getAttachedDocsTest(){
        String filePath="/Users/zhangtongke/Desktop/word_test.docx";
        List<LawDocVo> lawDocVos=attachedDocService.getAttachedDocs(filePath);
        for(LawDocVo vo:lawDocVos){
            System.out.println(vo.getTitle());
        }
    }

    @Test
    public void addArticleTest(){
        String articleId="065f831a-867f-43db-9dc0-9e42ce7e10c4";
        String filePath="/Users/zhangtongke/Desktop/word_test.docx";
        attachedDocService.addArticleToWordDocument(articleId,filePath);
    }

    @Test
    public void deleteArticleTest(){
        String articleId="04121a0a-3fab-416e-a43a-09281f5eca6b";
        String filePath="/Users/zhangtongke/Desktop/word_test.docx";
        attachedDocService.deleteArticleFromWordDocument(articleId,filePath);
    }

    @Test
    public void getArticleTest(){
        String filePath="/Users/zhangtongke/Desktop/word_test.docx";
        List<LawArticleVo> lawArticleVos=attachedDocService.getAttachedArticles(filePath);
        for(LawArticleVo vo:lawArticleVos){
            System.out.println(vo.getArticleName());
        }
    }

    @Test
    public void getMentionedFilesTest(){
        String filePath="/Users/zhangtongke/Desktop/文书例.docx";
        attachedDocService.getMentionedFiles(filePath);
    }

}
