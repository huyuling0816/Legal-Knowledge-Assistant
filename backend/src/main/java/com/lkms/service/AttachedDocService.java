package com.lkms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lkms.po.attachedLawPo.AttachedDocPo;
import com.lkms.po.lawPo.LawDocPo;
import com.lkms.utils.PageInfo;
import com.lkms.vo.DocQueryParam;
import com.lkms.vo.lawVo.*;

import java.util.List;

public interface AttachedDocService extends IService<LawDocPo> {
    public int addArticleToWordDocument(String articleId, String wordPath);

    public int deleteArticleFromWordDocument(String articleId, String wordPath);

    public List<LawArticleVo> getAttachedArticles(String wordPath);

    public int addDocToWordDocument(String docId, String wordPath);

    public int deleteDocFromWordDocument(String docId, String wordPath);

    public List<LawDocVo> getAttachedDocs(String wordPath);

    public List<AttachedDocPo> getMentionedFiles(String wordPath);
}

