package com.lkms.service.impl;

import cn.hutool.core.lang.Pair;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lkms.enums.errorCode.impl.DataStatusCode;
import com.lkms.enums.DocLevel;
import com.lkms.enums.errorCode.impl.DefaultStatusCode;
import com.lkms.enums.errorCode.impl.UserStatusCode;
import com.lkms.esDao.LawArticleESDao;
import com.lkms.esDao.LawDocESDao;
import com.lkms.exception.ServiceException;
import com.lkms.mapper.*;
import com.lkms.po.attachedLawPo.AttachedDocPo;
import com.lkms.po.lawPo.*;
import com.lkms.po.userPo.UserLawPo;
import com.lkms.service.LawDocService;
import com.lkms.translator.LogicTranslator;
import com.lkms.translator.MainTranslator;
import com.lkms.utils.PageInfo;
import com.lkms.utils.UUIDUtil;
import com.lkms.utils.conventer.NumberConverter;
import com.lkms.utils.file.COSClientUtil;
import com.lkms.utils.file.FileRWUtil;
import com.lkms.utils.file.PathConstants;
import com.lkms.utils.reflect.AddProperty;
import com.lkms.vo.DocQueryParam;
import com.lkms.vo.lawVo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import com.lkms.vo.lawVo.LawArticleVo;
import com.lkms.vo.lawVo.LawDocVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class LawDocServiceImpl extends ServiceImpl<LawDocMapper, LawDocPo> implements LawDocService {
    @Resource
    LawDocESDao lawDocESDao;
    @Resource
    LawArticleESDao lawArticleESDao;
    @Resource
    LawDocMapper lawDocMapper;
    @Resource
    LawPartMapper lawPartMapper;
    @Resource
    LawChapterMapper lawChapterMapper;
    @Resource
    LawSectionMapper lawSectionMapper;
    @Resource
    LawArticleMapper lawArticleMapper;
    @Resource
    LawParagraphMapper lawParagraphMapper;
    @Resource
    LawSubparagraphMapper lawSubparagraphMapper;
    @Resource
    LawItemMapper lawItemMapper;
    @Resource
    LawAppendixMapper lawAppendixMapper;
    @Resource
    UserLawMapper userLawMapper;
    @Resource
    PathConstants pathConstants;
    @Resource
    LogicMapper logicDao;

    @Autowired
    COSClientUtil cosClientUtil;

    public PageInfo<LawDocVo> query(DocQueryParam param) {
        return PageInfo.convert(lawDocESDao.searchDoc(param), LawDocVo.class);
    }

    @Override
    public List<LawPartVo> getPartByDocId(String docId) {
        Map<String, Object> docIdMap = new HashMap<>();
        docIdMap.put("docId", docId);
        List<LawPartPo> lawPartPos = lawPartMapper.selectByMap(docIdMap);
        if (lawPartPos == null) throw new ServiceException(DefaultStatusCode.DATA_FAIL);
        return lawPartPos.stream().map(LawPartVo::new).sorted(Comparator.comparingInt(a -> NumberConverter.getNumInChineseString(a.getPartSeq()))).collect(Collectors.toList());
    }

    @Override
    public List<LawChapterVo> getChapByDocId(String docId) {
        Map<String, Object> docIdMap = new HashMap<>();
        docIdMap.put("docId", docId);
        List<LawChapterPo> lawChapterPos = lawChapterMapper.selectByMap(docIdMap);
        if (lawChapterPos == null) throw new ServiceException(DefaultStatusCode.DATA_FAIL);
        return lawChapterPos.stream().map(LawChapterVo::new).sorted(Comparator.comparingInt(a -> NumberConverter.getNumInChineseString(a.getChapterSeq()))).collect(Collectors.toList());
    }

    @Override
    public List<LawSectionVo> getSectByDocId(String docId) {
        Map<String, Object> docIdMap = new HashMap<>();
        docIdMap.put("docId", docId);
        List<LawSectionPo> lawSectionPos = lawSectionMapper.selectByMap(docIdMap);
        if (lawSectionPos == null) throw new ServiceException(DefaultStatusCode.DATA_FAIL);
        return lawSectionPos.stream().map(LawSectionVo::new).sorted(Comparator.comparingInt(a -> NumberConverter.getNumInChineseString(a.getSectionSeq()))).collect(Collectors.toList());
    }

    @Override
    public List<LawArticleVo> getArticleByDocId(String docId) {
        Map<String, Object> docIdMap = new HashMap<>();
        docIdMap.put("docId", docId);
        List<LawArticlePo> lawArticlePos = lawArticleMapper.selectByMap(docIdMap);
        if (lawArticlePos == null) throw new ServiceException(DefaultStatusCode.DATA_FAIL);
        return lawArticlePos.stream().map(LawArticleVo::new).sorted(Comparator.comparingInt(a -> NumberConverter.getNumInChineseString(a.getArticleSeq()))).collect(Collectors.toList());
    }

    @Override
    public List<LawParagraphVo> getParaByDocId(String docId) {
        Map<String, Object> docIdMap = new HashMap<>();
        docIdMap.put("docId", docId);
        List<LawParagraphPo> lawParagraphPos = lawParagraphMapper.selectByMap(docIdMap);
        if (lawParagraphPos == null) throw new ServiceException(DefaultStatusCode.DATA_FAIL);
        return lawParagraphPos.stream().map(LawParagraphVo::new).sorted(Comparator.comparingInt(a -> NumberConverter.getNumInChineseString(a.getParagraphSeq()))).collect(Collectors.toList());
    }

    @Override
    public List<LawSubparagraphVo> getSubParaByDocId(String docId) {
        Map<String, Object> docIdMap = new HashMap<>();
        docIdMap.put("docId", docId);
        List<LawSubparagraphPo> lawSubparagraphPos = lawSubparagraphMapper.selectByMap(docIdMap);
        if (lawSubparagraphPos == null) throw new ServiceException(DefaultStatusCode.DATA_FAIL);
        return lawSubparagraphPos.stream().map(LawSubparagraphVo::new).sorted(Comparator.comparingInt(a -> NumberConverter.getNumInChineseString(a.getSubparagraphSeq()))).collect(Collectors.toList());
    }

    @Override
    public List<LawItemVo> getItemByDocId(String docId) {
        Map<String, Object> docIdMap = new HashMap<>();
        docIdMap.put("docId", docId);
        List<LawItemPo> lawItemPos = lawItemMapper.selectByMap(docIdMap);
        if (lawItemPos == null) throw new ServiceException(DefaultStatusCode.DATA_FAIL);
        return lawItemPos.stream().map(LawItemVo::new).sorted(Comparator.comparingInt(a -> NumberConverter.getNumInChineseString(a.getItemSeq()))).collect(Collectors.toList());
    }

    @Override
    public List<LawAppendixVo> getAppendixByDocId(String docId) {
        Map<String, Object> docIdMap = new HashMap<>();
        docIdMap.put("docId", docId);
        List<LawAppendixPo> lawAppendixPos = lawAppendixMapper.selectByMap(docIdMap);
        if (lawAppendixPos == null) throw new ServiceException(DefaultStatusCode.DATA_FAIL);
        return lawAppendixPos.stream().map(LawAppendixVo::new).sorted(Comparator.comparingInt(a -> NumberConverter.getNumInChineseString(a.getAppendixSeq()))).collect(Collectors.toList());
    }

    @Override
    public LawArticleVo getArticleById(String articleId) {
        return new LawArticleVo(lawArticleMapper.selectById(articleId));
    }

    @Override
    public List<LawSubparagraphVo> getSubparagraphByArticleId(String articleId) {
        Map<String, Object> docIdMap = new HashMap<>();
        docIdMap.put("articleId", articleId);
        List<LawSubparagraphPo> lawSubparagraphPos = lawSubparagraphMapper.selectByMap(docIdMap);
        if (lawSubparagraphPos == null) throw new ServiceException(DefaultStatusCode.DATA_FAIL);
        return lawSubparagraphPos.stream().map(LawSubparagraphVo::new).sorted(Comparator.comparingInt(a -> NumberConverter.getNumInChineseString(a.getSubparagraphSeq()))).collect(Collectors.toList());
    }

    @Override
    public LawDocVo createByText(Integer userId, LawDocVo lawDocVo) {
        lawDocVo.setId(UUIDUtil.getUUIDString());
        LawDocPo po = new LawDocPo(lawDocVo);
        String docId = lawDocMapper.selectByName(po.getTitle());
        if (docId != null) {
            return updateLaw(userId, lawDocVo);
        }
        lawDocMapper.insert(po);
        lawDocESDao.createInEs(po);
        try {
            divideFile(lawDocVo);
        } catch (Exception e) {
            System.out.println(e);
        }
        userLawMapper.insert(UserLawPo.builder().userId(userId).docId(po.getId()).build());
        List<LawArticlePo> lawArticlePos = lawArticleMapper.selectByDocId(po.getId());
        for (LawArticlePo lawArticlePo : lawArticlePos) {
            lawArticleESDao.insertArticleInEs(lawArticlePo);
        }
        return lawDocVo;
    }

    @Override
    public List<LawDocVo> createByFile(MultipartFile file, LawDocVo lawDocVo, Integer userId) throws Exception {
        List<LawDocVo> res = new ArrayList<>();
//        for (LawDocVo lawDocVo : lawDocVos) {
        lawDocVo.setId(UUIDUtil.getUUIDString());
        lawDocVo.setFullContent(FileRWUtil.readMultipartFile(file));
        lawDocVo.setDocURL(cosClientUtil.upload(cosClientUtil.multipartFileToFile(file), "/lawDoc", true));
        lawDocMapper.insert(new LawDocPo(lawDocVo));
        userLawMapper.insert(UserLawPo.builder().userId(userId).docId(lawDocVo.getId()).build());
        lawDocESDao.createInEs(new LawDocPo(lawDocVo));
        divideFile(lawDocVo);
        List<LawArticlePo> lawArticlePos = lawArticleMapper.selectByDocId(lawDocVo.getId());
        for (LawArticlePo lawArticlePo : lawArticlePos) {
            lawArticleESDao.insertArticleInEs(lawArticlePo);
//            }
        }
        return res;
    }


    @Override
    public List<LawDocVo> getByUserId(Integer userId) {
        List<LawDocVo> collect = lawDocMapper.selectByUserId(userId).stream().map(LawDocVo::new).collect(Collectors.toList());
        return collect;
    }

    @Override
    public boolean deleteLaw(String docId, Integer userId) {
        if (!this.verifyUserLaw(userId, docId)) {
            throw new ServiceException(UserStatusCode.INSUFFICIENT_PRIVILEGES);
        }
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        params.put("docId", docId);
        userLawMapper.deleteByMap(params);
        deleteAllByDocId(docId);
        return true;
    }

    private void deleteAllByDocId(String docId) {
        Map<String, Object> map = new HashMap<>();
        map.put("docId", docId);
        logicDao.deleteByMap(map);
        lawAppendixMapper.deleteByMap(map);
        lawItemMapper.deleteByMap(map);
        lawSubparagraphMapper.deleteByMap(map);
        lawParagraphMapper.deleteByMap(map);
        lawArticleMapper.deleteByMap(map);
        lawSectionMapper.deleteByMap(map);
        lawChapterMapper.deleteByMap(map);
        lawPartMapper.deleteByMap(map);
        lawDocMapper.deleteById(docId);
        lawDocESDao.deleteInEs(docId);
        lawArticleESDao.deleteArticleInEsByDocId(docId);
    }

    private void divideFile(LawDocVo lawDocVo) throws Exception {
        String title = lawDocVo.getTitle();
        String path = FileRWUtil.saveToWord(pathConstants.tempFilePath, title, lawDocVo.getFullContent());
        MainTranslator mainTranslator = new MainTranslator(lawDocMapper, lawPartMapper, lawChapterMapper, lawSectionMapper, lawArticleMapper, lawParagraphMapper, lawSubparagraphMapper, lawItemMapper, lawAppendixMapper, logicDao);
        mainTranslator.translatorFile(path);
        FileRWUtil.deleteFile(path);
    }

    public LawDocVo updateLaw(Integer userId, LawDocVo lawDocVO) {
        if (!this.verifyUserLaw(userId, lawDocVO.getId())) {
            throw new ServiceException(UserStatusCode.INSUFFICIENT_PRIVILEGES);
        }
        LawDocPo po = new LawDocPo(lawDocVO);
        if (lawDocMapper.updateById(po) != 0) {
            lawDocESDao.updateInEs(po);
            return lawDocVO;
        } else {
            throw new ServiceException(DataStatusCode.UPDATE_ERROR);
        }
    }

    private boolean verifyUserLaw(Integer userId, String docId) {
        Map<String, Object> param = new HashMap<>();
        param.put("userId", userId);
        param.put("docId", docId);
        List<UserLawPo> userLawPos = userLawMapper.selectByMap(param);
        return userLawPos != null && userLawPos.size() != 0;
    }

    public List<LawArticleVo> getFullArticlesByDocId(String docId) {
        Map<String, Object> docIdMap = new HashMap<>();
        docIdMap.put("docId", docId);
        List<LawArticlePo> lawArticlePos = lawArticleMapper.selectByMap(docIdMap);
        List<LawArticleVo> sortedLawArticleVos = lawArticlePos.stream().map(LawArticleVo::new).sorted(Comparator.comparingInt(a -> NumberConverter.getNumInChineseString(a.getArticleSeq()))).collect(Collectors.toList());
        for (LawArticleVo articleVo : sortedLawArticleVos) {
            String articleId = articleVo.getId();
            List<LawParagraphVo> paragraphVos = getParagraphsByArticleId(articleId);
            articleVo.setParagraphs(paragraphVos);
        }
        return sortedLawArticleVos;
    }

    @Override
    public LawArticleVo getFullArticleById(String articleId) {
        LawArticlePo articlePo = lawArticleMapper.selectById(articleId);
        LawArticleVo articleVo = new LawArticleVo(articlePo);
        List<LawParagraphVo> paragraphVos = getParagraphsByArticleId(articleId);
        articleVo.setParagraphs(paragraphVos);
        return articleVo;
    }

    @Override
    public List<LawArticleVo> searchArticlesByContent(String content) {
        List<LawArticleVo> results = new ArrayList<>();

        Map<String, LawArticleVo> articleMap = new HashMap<>();
        Map<String, LawParagraphVo> paragraphMap = new HashMap<>();
        Map<String, LawSubparagraphVo> subparagraphMap = new HashMap<>();

        List<LawArticlePo> articlePos = lawDocESDao.searchByContent(content, DocLevel.ARTICLE, LawArticlePo.class);
        for (LawArticlePo articlePo : articlePos) {
            LawArticleVo articleVo = new LawArticleVo(articlePo);
            results.add(articleVo);
            articleMap.put(articleVo.getId(), articleVo);
        }

        List<LawParagraphPo> paragraphPos = lawDocESDao.searchByContent(content, DocLevel.PARAGRAPH, LawParagraphPo.class);
        for (LawParagraphPo paragraphPo : paragraphPos) {
            LawParagraphVo paragraphVo = new LawParagraphVo(paragraphPo);
            paragraphMap.put(paragraphVo.getId(), paragraphVo);

            String articleId=paragraphVo.getArticleId();
            if(articleId==null) continue;
            LawArticleVo articleVo=articleMap.get(articleId);
            if(articleVo==null) {
                continue;
            }
            List<LawParagraphVo> paragraphVos=articleVo.getParagraphs()!=null?articleVo.getParagraphs():new ArrayList<>();
            paragraphVos.add(paragraphVo);
            articleVo.setParagraphs(paragraphVos);
        }

        List<LawSubparagraphPo> subparagraphPos = lawDocESDao.searchByContent(content, DocLevel.SUBPARAGRAPH, LawSubparagraphPo.class);
        for (LawSubparagraphPo subparagraphPo : subparagraphPos) {
            LawSubparagraphVo subparagraphVo = new LawSubparagraphVo(subparagraphPo);
            subparagraphMap.put(subparagraphVo.getId(), subparagraphVo);

            String paragraphId = subparagraphVo.getParagraphId();
            LawParagraphVo paragraphVo = paragraphMap.get(paragraphId);
            List<LawSubparagraphVo> subparagraphVos = paragraphVo.getSubparagraphs() != null ? paragraphVo.getSubparagraphs() : new ArrayList<>();
            subparagraphVos.add(subparagraphVo);
            paragraphVo.setSubparagraphs(subparagraphVos);
        }

        List<LawItemPo> itemPos = lawDocESDao.searchByContent(content, DocLevel.ITEM, LawItemPo.class);
        for (LawItemPo itemPo : itemPos) {
            LawItemVo itemVo = new LawItemVo(itemPo);
            String subparagraphId = itemVo.getSubparagraphId();
            LawSubparagraphVo subparagraphVo = subparagraphMap.get(subparagraphId);
            List<LawItemVo> itemVos = subparagraphVo.getItems() != null ? subparagraphVo.getItems() : new ArrayList<>();
            itemVos.add(itemVo);
            subparagraphVo.setItems(itemVos);
        }

        for (LawArticleVo res : results) {
            String docTitle = lawDocMapper.selectById(res.getDocId()).getTitle();
            res.setDocTitle(docTitle);
        }
//        List<LawArticlePo> articlePos=lawDocESDao.searchArticlesByContent(content);
//        List<LawArticleVo> articleVos=articlePos.stream().map(LawArticleVo::new).collect(Collectors.toList());
        return results;
    }

    @Override
    public LawDocVo getDocById(String docId) {
        LawDocPo docPo = lawDocMapper.selectById(docId);
        if (docPo == null) return null;
        return new LawDocVo(docPo);
    }

    @Override
    public List<LawDocVo> getRelatedLaws(String docId) {
        LawDocPo lawDocPo = lawDocMapper.selectById(docId);
        Set<LawDocVo> lawDocVos = new HashSet<>();
        List<String> mentionedFiles = new ArrayList<>();
        Pattern namePattern = Pattern.compile("《.+?》");
        Matcher nameMatcher = namePattern.matcher(lawDocPo.getFullContent());
        while (nameMatcher.find()) {
            mentionedFiles.add(nameMatcher.group(0).substring(1, nameMatcher.group(0).length() - 1));
        }
        List<Pair<LawDocPo, Double>> samePairs = lawDocESDao.getMentionedDocTilesDocs(mentionedFiles);
        for (Pair<LawDocPo, Double> samePair : samePairs) {
            LawDocVo lawDocVo = new LawDocVo(samePair.getKey());
            lawDocVo = (LawDocVo) AddProperty.addProperty(lawDocVo, "score", samePair.getValue());
            lawDocVos.add(lawDocVo);
        }
        List<Pair<LawDocPo, Double>> similarParis = lawDocESDao.getSimilarTitleLawDocs(lawDocPo.getId(), lawDocPo.getTitle());
        for (Pair<LawDocPo, Double> paris : similarParis) {
            LawDocVo lawDocVo = new LawDocVo(paris.getKey());
            lawDocVo = (LawDocVo) AddProperty.addProperty(lawDocVo, "score", paris.getValue());
            lawDocVos.add(lawDocVo);
        }
        List<LawDocVo> res = new ArrayList<>(lawDocVos);
        return res.stream().filter(law -> !law.getId().equals(docId)).collect(Collectors.toList());
    }

    @Override
    public List<LawDocVo> getRelatedLawsByPath(String path) {
        AttachedDocPo attachedDocPo = new AttachedDocPo();
        FileRWUtil fileRWUtil = new FileRWUtil();
        return null;
    }

    private List<LawParagraphVo> getParagraphsByArticleId(String articleId) {
        Map<String, Object> articleIdMap = new HashMap<>();
        articleIdMap.put("articleId", articleId);
        List<LawParagraphPo> lawParagraphPos = lawParagraphMapper.selectByMap(articleIdMap);
        List<LawParagraphVo> sortedLawParagraphVos = lawParagraphPos.stream().map(LawParagraphVo::new).sorted(Comparator.comparingInt(a -> NumberConverter.getNumInChineseString(a.getParagraphSeq()))).collect(Collectors.toList());
        for (LawParagraphVo paragraphVo : sortedLawParagraphVos) {
            List<LawSubparagraphVo> subparagraphVos = getSubparagraphsByParagraphId(paragraphVo.getId());
            paragraphVo.setSubparagraphs(subparagraphVos);
        }
        return sortedLawParagraphVos;
    }

    private List<LawSubparagraphVo> getSubparagraphsByParagraphId(String paragraphId) {
        Map<String, Object> paragraphIdMap = new HashMap<>();
        paragraphIdMap.put("paragraphId", paragraphId);
        List<LawSubparagraphPo> lawSubparagraphPos = lawSubparagraphMapper.selectByMap(paragraphIdMap);
        List<LawSubparagraphVo> sortedSubparagraphVos = lawSubparagraphPos.stream().map(LawSubparagraphVo::new).sorted(Comparator.comparingInt(a -> NumberConverter.getNumInChineseString(a.getSubparagraphSeq()))).collect(Collectors.toList());
        for (LawSubparagraphVo subparagraphVo : sortedSubparagraphVos) {
            List<LawItemVo> lawItemVos = getItemsBySubparaId(subparagraphVo.getId());
            subparagraphVo.setItems(lawItemVos);
        }
        return sortedSubparagraphVos;
    }

    private List<LawItemVo> getItemsBySubparaId(String subparaId) {
        Map<String, Object> subparaIdMap = new HashMap<>();
        subparaIdMap.put("subparagraphId", subparaId);
        List<LawItemPo> lawItemPos = lawItemMapper.selectByMap(subparaIdMap);
        return lawItemPos.stream().map(LawItemVo::new).sorted(Comparator.comparingInt(a -> NumberConverter.getNumInChineseString(a.getItemSeq()))).collect(Collectors.toList());
    }
}
