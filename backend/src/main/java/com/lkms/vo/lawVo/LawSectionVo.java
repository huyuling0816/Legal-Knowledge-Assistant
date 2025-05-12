package com.lkms.vo.lawVo;

import com.lkms.mapper.LawSectionMapper;
import com.lkms.po.lawPo.LawSectionPo;
import com.lkms.utils.conventer.povo.LawSectConvertMapper;
import lombok.Data;
import org.springframework.cglib.beans.BeanCopier;

import javax.validation.constraints.NotNull;

@Data
public class LawSectionVo {
    private String id;

    private String sectionSeq;

    private String sectionName;

    private String chapterId;

    private String partId;

    private String docId;

    public LawSectionVo() {
    }

    public LawSectionVo(LawSectionVo lawSectionVo) {
        BeanCopier beanCopier = BeanCopier.create(lawSectionVo.getClass(), this.getClass(), false);
        beanCopier.copy(lawSectionVo, this, null);
    }

    public LawSectionVo(@NotNull LawSectionPo lawSectionPo) {
        this(LawSectConvertMapper.INSTANCE.po2vo(lawSectionPo));
    }
}
