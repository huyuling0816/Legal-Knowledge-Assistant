package com.lkms.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.lkms.po.lawPo.LawAppendixPo;
import com.lkms.utils.conventer.povo.LogicConvertMapper;
import com.lkms.vo.LogicVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.beans.BeanCopier;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("logic")
public class LogicPo {
    String body;

    String antecedent;

    String consequence;

    String id;

    String docId;

    String partId;

    String chapterId;

    String sectionId;

    String articleId;

    public LogicPo(LogicPo logicPo){
        BeanCopier beanCopier = BeanCopier.create(logicPo.getClass(),this.getClass(),false);
        beanCopier.copy(logicPo,this,null);
    }

    public LogicPo(LogicVo logicVo){
        this(LogicConvertMapper.INSTANCE.vo2po(logicVo));
    }
}
