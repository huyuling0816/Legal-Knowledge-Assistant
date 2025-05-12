package com.lkms.service.impl;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lkms.mapper.*;
import com.lkms.po.lawPo.*;
import com.lkms.service.LawTranslateService;
import com.lkms.translator.MainTranslator;
import com.lkms.utils.BeanCopierWithCacheUtil;
import com.lkms.vo.lawVo.LawChapterVo;
import com.lkms.vo.lawVo.LawDocVo;
import com.lkms.vo.lawVo.LawPartVo;
import com.lkms.vo.lawVo.LawSectionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class LawTranslateServiceImpl extends ServiceImpl<LawDocMapper, LawDocPo> implements LawTranslateService {
    @Autowired
    LawDocMapper lawDocDAO;

    @Autowired
    LawPartMapper lawPartDAO;

    @Autowired
    LawChapterMapper lawChapterDAO;

    @Autowired
    LawSectionMapper lawSectionDAO;

    @Autowired
    LawArticleMapper lawArticleDAO;

    @Autowired
    LawParagraphMapper lawParagraphDAO;

    @Autowired
    LawSubparagraphMapper lawSubparagraphDAO;

    @Autowired
    LawItemMapper lawItemDAO;

    @Autowired
    LawAppendixMapper lawAppendixDAO;

    @Autowired
    ElasticsearchClient client;

    public MainTranslator initMainTranslator(){
        MainTranslator mainTranslator = new MainTranslator(lawDocDAO,lawPartDAO,lawChapterDAO,lawSectionDAO,lawArticleDAO,lawParagraphDAO,lawSubparagraphDAO,lawItemDAO,lawAppendixDAO);
        return mainTranslator;
    }

    @Override
    public boolean translateFile(String filepath) throws Exception {
        MainTranslator mainTranslator = this.initMainTranslator();
        mainTranslator.translatorFile(filepath);
        return true;
    }

    @Override
    public boolean translateFolder(String folderPath) throws Exception {
        MainTranslator mainTranslator = this.initMainTranslator();
        mainTranslator.translatorFolder(folderPath);
        return true;
    }

    @Override
    public String getFullDocInfo(String id) {
        LawDocPo lawDocPo = lawDocDAO.selectById(id);
        LawDocVo lawDocVo = new LawDocVo();
        BeanCopierWithCacheUtil.copy(lawDocPo,lawDocVo);
        HashMap<String,Object> docIdParameter = new HashMap<>();
        docIdParameter.put("docId",lawDocPo.getId());
        List<LawPartPo> partPos = lawPartDAO.selectByMap(docIdParameter);
        for (LawPartPo partPo : partPos) {
            System.out.println(partPo);
        }
        List<LawChapterPo> chapterPos = lawChapterDAO.selectByMap(docIdParameter);
        for (LawChapterPo chapterPo : chapterPos){
            System.out.println(chapterPo);
        }
        List<LawSectionPo> sectionPos = lawSectionDAO.selectByMap(docIdParameter);
        for (LawSectionPo sectionPo : sectionPos){
            System.out.println(sectionPo);
        }
        List<LawArticlePo> articlePos = lawArticleDAO.selectByMap(docIdParameter);
        for (LawArticlePo articlePo : articlePos){
            System.out.println(articlePo);
        }
        List<LawParagraphPo> paragraphPos = lawParagraphDAO.selectByMap(docIdParameter);
        for (LawParagraphPo paragraphPo : paragraphPos){
            System.out.println(paragraphPo);
        }
        List<LawSubparagraphPo> subparagraphPos = lawSubparagraphDAO.selectByMap(docIdParameter);
        for (LawSubparagraphPo subparagraphPo : subparagraphPos){
            System.out.println(subparagraphPo);
        }
        List<LawItemPo> itemPos = lawItemDAO.selectByMap(docIdParameter);
        for (LawItemPo itemPo : itemPos){
            System.out.println(itemPo);
        }
        return null;
    }
}
