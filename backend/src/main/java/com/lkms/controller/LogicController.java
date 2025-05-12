package com.lkms.controller;

import com.lkms.po.LogicPo;
import com.lkms.po.attachedLawPo.AttachedDocPo;
import com.lkms.service.LogicService;
import com.lkms.utils.ResultHelper;
import com.lkms.vo.LogicVo;
import com.lkms.vo.ResultVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/logic")
public class LogicController {
    @Resource
    LogicService logicService;
    @Resource
    ResultHelper resultHelper;

    @GetMapping("/getLogic")
    public ResultVo<List<LogicVo>> getLogic (String body) {
        return resultHelper.success(logicService.getLogicsFromBody(body));
    }
}
