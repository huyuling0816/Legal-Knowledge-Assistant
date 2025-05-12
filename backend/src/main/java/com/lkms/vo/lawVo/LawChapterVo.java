package com.lkms.vo.lawVo;

import com.lkms.po.lawPo.LawChapterPo;
import com.lkms.utils.conventer.povo.LawChapConvertMapper;
import lombok.Data;
import org.springframework.cglib.beans.BeanCopier;

import javax.validation.constraints.NotNull;

@Data
public class LawChapterVo {
    private String id;

    private String chapterSeq;

    private String chapterName;

    private String partId;

    private String docId;

    public LawChapterVo() {
    }

    public LawChapterVo(LawChapterVo lawChapterVo) {
        BeanCopier beanCopier = BeanCopier.create(lawChapterVo.getClass(), this.getClass(), false);
        beanCopier.copy(lawChapterVo, this, null);
    }

    public LawChapterVo(@NotNull LawChapterPo lawChapterPo) {
        this(LawChapConvertMapper.INSTANCE.po2vo(lawChapterPo));
    }
}
