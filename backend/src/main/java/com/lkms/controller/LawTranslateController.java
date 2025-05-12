package com.lkms.controller;

import com.lkms.service.LawTranslateService;
import com.lkms.utils.ResultHelper;
import com.lkms.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping("/lawTranslate")
public class LawTranslateController {
    @Resource
    LawTranslateService lawTranslateService;

    ResultHelper resultHelper;

    @Autowired
    public void initResultHelper(ResultHelper resultHelper){
        this.resultHelper = resultHelper;
    }

    @GetMapping("/file")
    public void translateFile() throws Exception {
        //TODO
        String filePath = "";
        lawTranslateService.translateFile(filePath);
    }

    @GetMapping("/folder")
    public void translateFolder() throws Exception{
        //TODO
        String folderPath = "";
        lawTranslateService.translateFolder(folderPath);
    }

    @GetMapping("/getFile/{docId}")
    public ResultVo<String> getFile(@PathVariable String docId){
        lawTranslateService.getFullDocInfo(docId);
        return resultHelper.success("success");
    }
}
