package com.lkms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lkms.esDao.LogicESDao;
import com.lkms.mapper.LawDocMapper;
import com.lkms.mapper.LogicMapper;
import com.lkms.po.LogicPo;
import com.lkms.po.lawPo.LawArticlePo;
import com.lkms.po.lawPo.LawDocPo;
import com.lkms.service.AttachedDocService;
import com.lkms.service.LawDocService;
import com.lkms.service.LogicService;
import com.lkms.vo.LogicVo;
import com.lkms.vo.lawVo.LawArticleVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class LogicServiceImpl extends ServiceImpl<LogicMapper, LogicPo> implements LogicService {
    @Resource
    LogicESDao logicESDao;
    @Resource
    LawDocService lawDocService;


    @Override
    public List<LogicVo> getLogicsFromBody(String body) {
        List<LogicPo> logicPos=logicESDao.getLogicsFromBody(body,5);
        List<LogicVo> logicVos=new ArrayList<>();
        for(LogicPo logicPo:logicPos){
            String articleId=logicPo.getArticleId();
            if(articleId==null){
                continue;
            }
            LawArticleVo articleVo=lawDocService.getFullArticleById(articleId);
            String docTitle=lawDocService.getDocById(logicPo.getDocId()).getTitle();
            articleVo.setDocTitle(docTitle);

            LogicVo logicVo=new LogicVo(logicPo);
            logicVo.setArticleVo(articleVo);

            logicVos.add(logicVo);
        }
        return logicVos;
    }
}
