package com.lkms.po.lawPo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.lkms.utils.conventer.povo.LawAppendixConvertMapper;
import com.lkms.vo.lawVo.LawAppendixVo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.cglib.beans.BeanCopier;

@Data
@Builder
@AllArgsConstructor
@TableName("lawappendix")
public class LawAppendixPo {
    private String id;

    private String appendixSeq;

    private String appendixName;

    private String docId;

    public LawAppendixPo(){}

    public LawAppendixPo(LawAppendixPo lawAppendixPo){
        BeanCopier beanCopier = BeanCopier.create(lawAppendixPo.getClass(),this.getClass(),false);
        beanCopier.copy(lawAppendixPo,this,null);
    }

    public LawAppendixPo(LawAppendixVo appendixVo){
        this(LawAppendixConvertMapper.INSTANCE.vo2po(appendixVo));
    }
}
