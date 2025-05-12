package com.lkms.controller;

import com.lkms.auth.Authentication;
import com.lkms.enums.errorCode.impl.DefaultStatusCode;
import com.lkms.po.lawPo.LawDocPo;
import com.lkms.service.LawDocService;
import com.lkms.utils.PageInfo;
import com.lkms.utils.ResultHelper;
import com.lkms.vo.ResultVo;
import com.lkms.vo.DocQueryParam;
import com.lkms.vo.lawVo.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/law")
public class LawController {
    @Resource
    LawDocService lawDocService;
    @Resource
    ResultHelper resultHelper;

    @PostMapping("/query")
    public ResultVo<PageInfo<LawDocVo>> query(@RequestBody DocQueryParam param) {
        return resultHelper.success(lawDocService.query(param));
    }

    @GetMapping("/docSimple/{docId}")
    public ResultVo<LawDocVo> getSimpleDocById(@PathVariable String docId) {
        LawDocPo lawDocPo = lawDocService.getById(docId);
        if (lawDocPo != null) {
            return resultHelper.success(new LawDocVo(lawDocPo));
        } else {
            return resultHelper.fail(DefaultStatusCode.DATA_FAIL);
        }
    }

    @GetMapping("/partSimple/{docId}")
    public ResultVo<List<LawPartVo>> getPartByDocId(@PathVariable String docId) {
        return resultHelper.success(lawDocService.getPartByDocId(docId));
    }

    @GetMapping("/chapSimple/{docId}")
    public ResultVo<List<LawChapterVo>> getChapterByDocId(@PathVariable String docId) {
        return resultHelper.success(lawDocService.getChapByDocId(docId));
    }

    @GetMapping("/sectSimple/{docId}")
    public ResultVo<List<LawSectionVo>> getSectionByDocId(@PathVariable String docId) {
        return resultHelper.success(lawDocService.getSectByDocId(docId));
    }

    @GetMapping("/articleSimple/{docId}")
    public ResultVo<List<LawArticleVo>> getArticleByDocId(@PathVariable String docId) {
        return resultHelper.success(lawDocService.getArticleByDocId(docId));
    }

    @GetMapping("/paraSimple/{docId}")
    public ResultVo<List<LawParagraphVo>> getParagraphByDocId(@PathVariable String docId) {
        return resultHelper.success(lawDocService.getParaByDocId(docId));
    }

    @GetMapping("/subParaSimple/{docId}")
    public ResultVo<List<LawSubparagraphVo>> getSubparagraphByDocId(@PathVariable String docId) {
        return resultHelper.success(lawDocService.getSubParaByDocId(docId));
    }

    @GetMapping("/itemSimple/{docId}")
    public ResultVo<List<LawItemVo>> getItemByDocId(@PathVariable String docId) {
        return resultHelper.success(lawDocService.getItemByDocId(docId));
    }

    @GetMapping("/appendixSimple/{docId}")
    public ResultVo<List<LawAppendixVo>> getAppendixByDocId(@PathVariable String docId) {
        return resultHelper.success(lawDocService.getAppendixByDocId(docId));
    }

    @GetMapping("/getFullArticles/{docId}")
    public ResultVo<List<LawArticleVo>> getFullArticlesByDocId(@PathVariable String docId) {
        return resultHelper.success(lawDocService.getFullArticlesByDocId(docId));
    }

    @GetMapping("/getArticlesByContent/{content}")
    public ResultVo<List<LawArticleVo>> getArticlesByContent(@PathVariable String content){
        return resultHelper.success(lawDocService.searchArticlesByContent(content));
    }

    @PostMapping("/createByText/{userId}")
    public ResultVo<LawDocVo> createByText(@PathVariable Integer userId, @RequestBody LawDocVo lawDocVo) throws Exception {
        return resultHelper.success(lawDocService.createByText(userId, lawDocVo));
    }

    @PostMapping("/createByFile")
    public ResultVo<List<LawDocVo>> createByFile(@RequestParam("docFile") @NotNull MultipartFile file, LawDocVo lawDocVo, Integer userId) throws Exception {
        return resultHelper.success(lawDocService.createByFile(file, lawDocVo, userId));
    }

    @GetMapping("/delete/{docId}")
    public ResultVo<String> deleteLaw(@PathVariable String docId, @RequestParam("userId") Integer userId) {
        lawDocService.deleteLaw(docId, userId);
        return resultHelper.success("删除成功");
    }

    @GetMapping("/getByUserId/{userId}")
    public ResultVo<List<LawDocVo>> getByUserId(@PathVariable Integer userId) {
        return resultHelper.success(lawDocService.getByUserId(userId));
    }

    @PostMapping("/update/{userId}")
    public ResultVo<LawDocVo> updateLaw(@PathVariable Integer userId, @RequestBody LawDocVo lawDocVo) {
        return resultHelper.success(lawDocService.updateLaw(userId, lawDocVo));
    }

    @GetMapping("/getRelated/{docId}")
    public ResultVo<List<LawDocVo>> getRelated(@PathVariable String docId){
        return resultHelper.success(lawDocService.getRelatedLaws(docId));
    }
}
