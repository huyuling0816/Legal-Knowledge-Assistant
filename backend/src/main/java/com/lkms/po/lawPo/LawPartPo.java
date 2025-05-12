package com.lkms.po.lawPo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.lkms.utils.conventer.povo.LawPartConvertMapper;
import com.lkms.vo.lawVo.LawPartVo;
import lombok.Data;
import org.springframework.cglib.beans.BeanCopier;

@Data
@TableName("lawpart")
public class LawPartPo {
    private String id;

    private String partSeq;

    private String partName;

    private String docId;

    public LawPartPo(){}

    public LawPartPo(LawPartPo lawPartPo){
        BeanCopier beanCopier = BeanCopier.create(lawPartPo.getClass(),this.getClass(),false);
        beanCopier.copy(lawPartPo,this,null);
    }

    public LawPartPo(LawPartVo lawPartVo){
        this(LawPartConvertMapper.INSTANCE.vo2po(lawPartVo));
    }
}
