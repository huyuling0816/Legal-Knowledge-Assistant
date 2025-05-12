package com.lkms.vo;

import com.lkms.po.LogicPo;
import com.lkms.utils.conventer.povo.LogicConvertMapper;
import com.lkms.vo.lawVo.LawAppendixVo;
import com.lkms.vo.lawVo.LawArticleVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.beans.BeanCopier;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogicVo {
    String body;

    String antecedent;

    String consequence;

    String id;

    String docId;

    String partId;

    String chapterId;

    String sectionId;

    String articleId;

    LawArticleVo articleVo;

    public LogicVo(LogicVo logicVo){
        BeanCopier beanCopier = BeanCopier.create(logicVo.getClass(),this.getClass(),false);
        beanCopier.copy(logicVo,this,null);
    }

    public LogicVo(LogicPo logicPo){
        this(LogicConvertMapper.INSTANCE.po2vo(logicPo));
    }
}
