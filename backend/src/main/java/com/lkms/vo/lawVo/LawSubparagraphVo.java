package com.lkms.vo.lawVo;

import com.lkms.po.lawPo.LawSubparagraphPo;
import com.lkms.utils.conventer.povo.LawSubParaConvertMapper;
import lombok.Data;
import org.springframework.cglib.beans.BeanCopier;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class LawSubparagraphVo {
    private String id;

    private String subparagraphSeq;

    private String subparagraphName;

    private String paragraphId;

    private String articleId;

    private String sectionId;

    private String chapterId;

    private String partId;

    private String docId;

    public LawSubparagraphVo() {
    }
    private List<LawItemVo> items;


    public LawSubparagraphVo(LawSubparagraphVo lawSubparagraphVo) {
        BeanCopier beanCopier = BeanCopier.create(lawSubparagraphVo.getClass(), this.getClass(), false);
        beanCopier.copy(lawSubparagraphVo, this, null);
    }

    public LawSubparagraphVo(@NotNull LawSubparagraphPo lawSubparagraphPo) {
        this(LawSubParaConvertMapper.INSTANCE.po2vo(lawSubparagraphPo));
    }
}
