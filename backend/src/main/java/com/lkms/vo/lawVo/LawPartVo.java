package com.lkms.vo.lawVo;

import com.lkms.po.lawPo.LawPartPo;
import com.lkms.utils.conventer.povo.LawPartConvertMapper;
import lombok.Data;
import org.springframework.cglib.beans.BeanCopier;

import javax.validation.constraints.NotNull;

@Data
public class LawPartVo {
    private String id;

    private String partSeq;

    private String partName;

    private String docId;

    public LawPartVo() {
    }

    public LawPartVo(LawPartVo lawPartVo) {
        BeanCopier beanCopier = BeanCopier.create(lawPartVo.getClass(), this.getClass(), false);
        beanCopier.copy(lawPartVo, this, null);
    }

    public LawPartVo(@NotNull LawPartPo lawPartPo) {
        this(LawPartConvertMapper.INSTANCE.po2vo(lawPartPo));
    }
}
