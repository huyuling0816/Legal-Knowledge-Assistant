package com.lkms.controller;

import com.lkms.po.attachedLawPo.AttachedDocPo;
import com.lkms.service.AttachedDocService;
import com.lkms.utils.ResultHelper;
import com.lkms.vo.ResultVo;
import com.lkms.vo.lawVo.LawArticleVo;
import com.lkms.vo.lawVo.LawDocVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/attachedDoc")
public class AttachedDocController {
    @Resource
    AttachedDocService attachedDocService;
    @Resource
    ResultHelper resultHelper;

    @PostMapping("/addDocToWordDocument")
    public ResultVo addDocToWordDocument (@RequestBody Map<String, String> docIdAndFilePath){
        return resultHelper.success(attachedDocService.addDocToWordDocument(docIdAndFilePath.get("docId"),docIdAndFilePath.get("filePath")));
    }

    @GetMapping("/getAttachedDoc")
    public ResultVo<List<LawDocVo>> getAttachedDocs (String filePath){
//        String path=filePath.get("filePath");
        List<LawDocVo> docVos=attachedDocService.getAttachedDocs(filePath);
        return resultHelper.success(docVos);
    }

    @PostMapping("/deleteDocFromWordDocument")
    public ResultVo deleteDocFromWordDocument(@RequestBody Map<String,String> docIdAndFilePath){
        return resultHelper.success(attachedDocService.deleteDocFromWordDocument(docIdAndFilePath.get("docId"),docIdAndFilePath.get("filePath")));
    }


    @GetMapping("/getMentionedFiles")
    public ResultVo<List<AttachedDocPo>> getMentionedFiles (String filePath) {
        return resultHelper.success(attachedDocService.getMentionedFiles(filePath));
    }
    @PostMapping("/addArticleToWordDocument")
    public ResultVo addArticleToWordDocument(@RequestBody Map<String,String> articleIdAndFilePath){
        return resultHelper.success(attachedDocService.addArticleToWordDocument(articleIdAndFilePath.get("articleId"),articleIdAndFilePath.get("filePath")));
    }

    @GetMapping("/getAttachedArticle")
    public ResultVo<List<LawArticleVo>> getAttachedArticles(String filePath){
        return resultHelper.success(attachedDocService.getAttachedArticles(filePath));
    }

    @PostMapping("/deleteArticleFromWordDocument")
    public ResultVo deleteArticleFromWordDocument(@RequestBody Map<String, String> articleIdAndFilePath){
        return resultHelper.success(attachedDocService.deleteArticleFromWordDocument(articleIdAndFilePath.get("articleId"), articleIdAndFilePath.get("filePath")));
    }
}
