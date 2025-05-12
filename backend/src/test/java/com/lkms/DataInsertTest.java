package com.lkms;

import com.lkms.mapper.*;
import com.lkms.translator.LogicTranslator;
import com.lkms.translator.MainTranslator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
@RunWith(SpringRunner.class)
@SpringBootTest
public class DataInsertTest {

    @Autowired
    LawDocMapper lawDocDAO;

    @Autowired
    LawPartMapper lawPartDAO;

    @Autowired
    LawChapterMapper lawChapDAO;

    @Autowired
    LawSectionMapper lawSectDAO;

    @Autowired
    LawArticleMapper lawArticleDAO;

    @Autowired
    LawParagraphMapper lawParaDAO;

    @Autowired
    LawSubparagraphMapper lawSubParaDAO;

    @Autowired
    LawItemMapper lawItemDAO;

    @Autowired
    LawAppendixMapper appendixDAO;

    @Autowired
    LogicMapper logicDAO;

    @Test
    public  void insertTestSingle() throws Exception {
        LogicTranslator logicTranslator = new LogicTranslator(logicDAO);
        MainTranslator mainTranslator = new MainTranslator(lawDocDAO,lawPartDAO,lawChapDAO,lawSectDAO,lawArticleDAO,lawParaDAO,lawSubParaDAO,lawItemDAO,appendixDAO,logicDAO);
        mainTranslator.translatorFile("/Users/huyuling/Desktop/gitlab_lkms/data/国家法律法规数据库/行政法规/中药品种保护条例.docx");
    }

    @Test
    public void insertTest() throws Exception {
        String folderPath = "/Users/huyuling/Desktop/gitlab_lkms/data/国家法律法规数据库";
        File folder = new File(folderPath);

        if (folder.isDirectory()) {
            readFiles(folder);
        } else {
            System.out.println("指定路径不是一个文件夹。");
        }
    }

    private void readFiles(File folder) throws Exception {

        MainTranslator mainTranslator = new MainTranslator(lawDocDAO,lawPartDAO,lawChapDAO,lawSectDAO,lawArticleDAO,lawParaDAO,lawSubParaDAO,lawItemDAO,appendixDAO,logicDAO);
        File[] files = folder.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    readFiles(file); // 递归处理子文件夹
                } else {
                    try {
                        mainTranslator.translatorFile(String.valueOf(file));
                    } catch (Exception | NoClassDefFoundError e){
                        System.out.println(e);
                        continue;
                    }
                }
            }
        }
    }
}
