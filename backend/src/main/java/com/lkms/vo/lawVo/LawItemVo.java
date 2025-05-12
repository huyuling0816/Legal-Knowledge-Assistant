package com.lkms.vo.lawVo;

import com.lkms.po.lawPo.LawItemPo;
import com.lkms.utils.conventer.povo.LawItemConvertMapper;
import lombok.Data;
import org.springframework.cglib.beans.BeanCopier;

import javax.validation.constraints.NotNull;

@Data
public class LawItemVo {
    private String id;

    private String itemSeq;

    private String itemName;

    private String subparagraphId;

    private String paragraphId;

    private String articleId;

    private String sectionId;

    private String chapterId;

    private String partId;

    private String docId;

    public LawItemVo() {
    }

    public LawItemVo(LawItemVo lawItemVo) {
        BeanCopier beanCopier = BeanCopier.create(lawItemVo.getClass(), this.getClass(), false);
        beanCopier.copy(lawItemVo, this, null);
    }

    public LawItemVo(@NotNull LawItemPo lawItemPo) {
        this(LawItemConvertMapper.INSTANCE.po2vo(lawItemPo));
    }
}
