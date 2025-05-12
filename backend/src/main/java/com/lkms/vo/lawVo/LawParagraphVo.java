package com.lkms.vo.lawVo;

import com.lkms.po.lawPo.LawParagraphPo;
import com.lkms.utils.conventer.povo.LawParaConvertMapper;
import lombok.Data;
import org.springframework.cglib.beans.BeanCopier;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class LawParagraphVo {
    private String id;

    private String paragraphSeq;

    private String paragraphName;

    private String articleId;

    private String sectionId;

    private String chapterId;

    private String partId;

    private String docId;

    private List<LawSubparagraphVo> subparagraphs;

    public LawParagraphVo() {
    }




    public LawParagraphVo(LawParagraphVo lawParagraphVo) {
        BeanCopier beanCopier = BeanCopier.create(lawParagraphVo.getClass(), this.getClass(), false);
        beanCopier.copy(lawParagraphVo, this, null);
    }

    public LawParagraphVo(@NotNull LawParagraphPo lawParagraphPo) {
        this(LawParaConvertMapper.INSTANCE.po2vo(lawParagraphPo));
    }
}
