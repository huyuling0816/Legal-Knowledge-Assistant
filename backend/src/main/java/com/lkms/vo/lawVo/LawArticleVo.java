package com.lkms.vo.lawVo;


import com.lkms.po.lawPo.LawArticlePo;
import com.lkms.utils.conventer.povo.LawArticleConvertMapper;
import lombok.Data;
import org.springframework.cglib.beans.BeanCopier;

import javax.validation.constraints.NotNull;
import com.lkms.po.lawPo.LawParagraphPo;
import lombok.Data;
import org.springframework.cglib.beans.BeanCopier;

import java.util.List;


@Data
public class LawArticleVo {
    private String id;

    private String articleSeq;

    private String articleName;

    private String sectionId;

    private String chapterId;

    private String partId;

    private String docId;

    private String docTitle;

    private List<LawParagraphVo> paragraphs;
    public LawArticleVo(){

    }

    public LawArticleVo(LawArticleVo lawArticleVo) {
        BeanCopier beanCopier = BeanCopier.create(lawArticleVo.getClass(), this.getClass(), false);
        beanCopier.copy(lawArticleVo, this, null);
    }

    public LawArticleVo(@NotNull LawArticlePo lawArticlePo) {
        this(LawArticleConvertMapper.INSTANCE.po2vo(lawArticlePo));
    }
}
