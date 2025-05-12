package com.lkms.vo.lawVo;

import com.lkms.po.lawPo.LawAppendixPo;
import com.lkms.utils.conventer.povo.LawAppendixConvertMapper;
import lombok.Data;
import org.springframework.cglib.beans.BeanCopier;

import javax.validation.constraints.NotNull;


@Data
public class LawAppendixVo {
    private String id;

    private String appendixSeq;

    private String appendixName;

    private String docId;

    public LawAppendixVo() {
    }

    public LawAppendixVo(LawAppendixVo lawAppendixVo) {
        BeanCopier beanCopier = BeanCopier.create(lawAppendixVo.getClass(), this.getClass(), false);
        beanCopier.copy(lawAppendixVo, this, null);
    }

    public LawAppendixVo(@NotNull LawAppendixPo lawAppendixPo) {
        this(LawAppendixConvertMapper.INSTANCE.po2vo(lawAppendixPo));
    }
}
