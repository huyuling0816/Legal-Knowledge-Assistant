package com.lkms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lkms.po.lawPo.LawDocPo;

public interface LawTranslateService extends IService<LawDocPo> {
    public  boolean translateFile(String filepath) throws Exception;

    public boolean translateFolder(String folderPath) throws Exception;

    public String getFullDocInfo(String id);
}
