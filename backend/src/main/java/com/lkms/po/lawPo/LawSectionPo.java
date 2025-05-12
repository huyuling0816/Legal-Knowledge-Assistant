package com.lkms.po.lawPo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.lkms.utils.conventer.povo.LawSectConvertMapper;
import com.lkms.vo.lawVo.LawSectionVo;
import lombok.Data;
import org.springframework.cglib.beans.BeanCopier;

@Data
@TableName("lawsection")
public class LawSectionPo {
    private String id;

    private String sectionSeq;

    private String sectionName;

    private String chapterId;

    private String partId;

    private String docId;

    public LawSectionPo(){}

    public LawSectionPo(LawSectionPo lawSectionPo){
        BeanCopier beanCopier = BeanCopier.create(lawSectionPo.getClass(),this.getClass(),false);
        beanCopier.copy(lawSectionPo,this,null);
    }

    public LawSectionPo(LawSectionVo lawSectionVo){
        this(LawSectConvertMapper.INSTANCE.vo2po(lawSectionVo));
    }

}
