package com.lkms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lkms.enums.DocLevel;
import com.lkms.mapper.AttachedArticleMapper;
import com.lkms.mapper.AttachedDocMapper;
import com.lkms.mapper.LawArticleMapper;
import com.lkms.mapper.LawDocMapper;
import com.lkms.po.attachedLawPo.AttachedArticlePo;
import com.lkms.po.attachedLawPo.AttachedDocPo;
import com.lkms.po.lawPo.LawArticlePo;
import com.lkms.po.lawPo.LawDocPo;
import com.lkms.service.AttachedDocService;
import com.lkms.service.LawDocService;
import com.lkms.utils.file.FileRWUtil;
import com.lkms.vo.lawVo.LawArticleVo;
import com.lkms.vo.lawVo.LawDocVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class AttachedDocServiceImpl extends ServiceImpl<LawDocMapper, LawDocPo>  implements AttachedDocService {
    @Resource
    AttachedDocMapper attachedDocMapper;
    @Resource
    AttachedArticleMapper attachedArticleMapper;
    @Resource
    LawDocService lawDocService;
    @Resource
    LawDocMapper lawDocMapper;
    @Resource
    LawArticleMapper lawArticleMapper;

    @Override
    public int addArticleToWordDocument(String articleId, String wordPath) {
        Map<String, Object> map=new HashMap<>();
        map.put("articleId", articleId);
        map.put("filePath", wordPath);
        List<AttachedArticlePo> attachedArticlePos=attachedArticleMapper.selectByMap(map);
        if(!attachedArticlePos.isEmpty()){
            return 0;
        }
        String id=UUID.randomUUID().toString();
        AttachedArticlePo po=new AttachedArticlePo(id,articleId,wordPath);
        attachedArticleMapper.insert(po);

        LawArticlePo articlePo=lawArticleMapper.selectById(articleId);
        String docId=articlePo.getDocId();
        if(docId!=null){
            addDocToWordDocument(docId,wordPath);
        }
        return 0;
    }

    @Override
    public int deleteArticleFromWordDocument(String articleId, String wordPath) {
        Map<String, Object> map=new HashMap<>();
        map.put("articleId",articleId);
        map.put("filePath",wordPath);
        attachedArticleMapper.deleteByMap(map);
        return 0;
    }

    @Override
    public List<LawArticleVo> getAttachedArticles(String wordPath) {
        Map<String, Object> map=new HashMap<>();
        map.put("filePath",wordPath);
        List<AttachedArticlePo> attachedArticlePos=attachedArticleMapper.selectByMap(map);
        List<LawArticleVo> articleVos=new ArrayList<>();
        for(AttachedArticlePo attachedArticlePo:attachedArticlePos){
            LawArticleVo articleVo=lawDocService.getFullArticleById(attachedArticlePo.getArticleId());
            String docTitle=lawDocService.getDocById(articleVo.getDocId()).getTitle();
            articleVo.setDocTitle(docTitle);
            articleVos.add(articleVo);
        }
        return articleVos;
    }

    private String getFileKey(String path) throws IOException {
        File file=new File(path);
        BasicFileAttributeView basicview = Files.getFileAttributeView(file.toPath(), BasicFileAttributeView.class);
        BasicFileAttributes attr = basicview.readAttributes();
        Object key=attr.fileKey();
        String keyStr=key.toString();
        return keyStr;
    }

    @Override
    public int addDocToWordDocument(String docId, String wordPath) {

        Map<String, Object> map=new HashMap<>();
        map.put("docId",docId);
        map.put("filePath",wordPath);
        List<AttachedDocPo> attachedDocPos=attachedDocMapper.selectByMap(map);
        if(!attachedDocPos.isEmpty()){
            return 0;
        }

        String id=UUID.randomUUID().toString();
        AttachedDocPo po=new AttachedDocPo(id,docId,wordPath);
        attachedDocMapper.insert(po);
        return 0;
    }

    @Override
    public int deleteDocFromWordDocument(String docId, String wordPath) {
        Map<String, Object> map=new HashMap<>();
        map.put("docId",docId);
        map.put("filePath",wordPath);
        attachedDocMapper.deleteByMap(map);
        return 0;
    }

    @Override
    public List<LawDocVo> getAttachedDocs(String wordPath) {
        Map<String,Object> map=new HashMap<>();
        map.put("filePath",wordPath);
        List<AttachedDocPo> attachedDocPos=attachedDocMapper.selectByMap(map);
        List<LawDocVo> results=new ArrayList<>();
        for(AttachedDocPo attachedDocPo:attachedDocPos){
            String docId=attachedDocPo.getDocId();
            results.add(lawDocService.getDocById(docId));
        }
        return results;
    }

    @Override
    public List<AttachedDocPo> getMentionedFiles(String wordPath) {
        String fullContent = FileRWUtil.readPathFile(wordPath);
        List<AttachedDocPo> attachedDocPoList = new ArrayList<>();
        List<String> mentionedFiles = new ArrayList<>();
        Pattern namePattern = Pattern.compile("《.+?》");
        Matcher nameMatcher = namePattern.matcher(fullContent);
        while (nameMatcher.find()){
            mentionedFiles.add(nameMatcher.group(0).substring(1,nameMatcher.group(0).length()-1));
        }
        for (String name : mentionedFiles){
            name=name.replace("<","《");
            name=name.replace(">","》");

            String docId=lawDocMapper.selectByName(name);
            Map<String,Object> map=new HashMap<>();
            map.put("filePath",wordPath);
            map.put("docId",docId);
            List<AttachedDocPo> existingPos=attachedDocMapper.selectByMap(map);
            if(!existingPos.isEmpty()){
                continue;
            }

            AttachedDocPo attachedDocPo = new AttachedDocPo();
            attachedDocPo.setFilePath(wordPath);
            attachedDocPo.setId(UUID.randomUUID().toString());
            attachedDocPo.setDocId(docId);
            attachedDocMapper.insert(attachedDocPo);
            attachedDocPoList.add(attachedDocPo);
        }
        return attachedDocPoList;
    }

}
