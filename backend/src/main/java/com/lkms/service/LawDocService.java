package com.lkms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lkms.po.lawPo.LawDocPo;
import com.lkms.utils.PageInfo;
import com.lkms.vo.DocQueryParam;
import com.lkms.vo.lawVo.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface LawDocService extends IService<LawDocPo> {
    public PageInfo<LawDocVo> query(DocQueryParam param);

    public List<LawPartVo> getPartByDocId(String docId);

    public List<LawChapterVo> getChapByDocId(String docId);

    public List<LawSectionVo> getSectByDocId(String docId);

    public List<LawArticleVo> getArticleByDocId(String docId);

    public List<LawParagraphVo> getParaByDocId(String docId);

    public List<LawSubparagraphVo> getSubParaByDocId(String docId);

    public List<LawItemVo> getItemByDocId(String docId);

    public List<LawAppendixVo> getAppendixByDocId(String docId);

    public LawArticleVo getArticleById(String articleId);

    public List<LawSubparagraphVo> getSubparagraphByArticleId(String articleId);

    public LawDocVo createByText(Integer userId, LawDocVo lawDocVO) throws Exception;

    public List<LawDocVo> getByUserId(Integer userId);

    public boolean deleteLaw(String docId, Integer userId);

    public LawDocVo updateLaw(Integer userId, LawDocVo lawDocVO);

    public List<LawDocVo> createByFile(MultipartFile file, LawDocVo lawDocVo, Integer uid) throws Exception;

    public List<LawArticleVo> getFullArticlesByDocId(String docId);

    LawArticleVo getFullArticleById(String articleId);

    public List<LawArticleVo> searchArticlesByContent(String content);

    public LawDocVo getDocById(String docId);

    public List<LawDocVo> getRelatedLaws(String docId);

    public List<LawDocVo> getRelatedLawsByPath(String path);
}
