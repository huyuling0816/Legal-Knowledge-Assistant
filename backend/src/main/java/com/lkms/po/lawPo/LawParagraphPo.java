package com.lkms.po.lawPo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.lkms.utils.conventer.povo.LawParaConvertMapper;
import com.lkms.vo.lawVo.LawParagraphVo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.cglib.beans.BeanCopier;

@Data
@Builder
@TableName("lawparagraph")
@AllArgsConstructor
public class LawParagraphPo {
    private String id;

    private String paragraphSeq;

    private String paragraphName;

    private String articleId;

    private String sectionId;

    private String chapterId;

    private String partId;

    private String docId;

    public LawParagraphPo(){}

    public LawParagraphPo(LawParagraphPo lawParagraphPo){
        BeanCopier beanCopier = BeanCopier.create(lawParagraphPo.getClass(),this.getClass(),false);
        beanCopier.copy(lawParagraphPo,this,null);
    }

    public LawParagraphPo(LawParagraphVo lawParagraphVo){
        this(LawParaConvertMapper.INSTANCE.vo2po(lawParagraphVo));
    }
}
