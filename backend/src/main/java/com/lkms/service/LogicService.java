package com.lkms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lkms.po.LogicPo;
import com.lkms.vo.LogicVo;

import java.util.List;

public interface LogicService extends IService<LogicPo> {
    public List<LogicVo> getLogicsFromBody(String body);
}
