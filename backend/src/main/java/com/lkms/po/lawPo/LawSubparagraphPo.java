package com.lkms.po.lawPo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.lkms.utils.conventer.povo.LawSubParaConvertMapper;
import com.lkms.vo.lawVo.LawSubparagraphVo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.cglib.beans.BeanCopier;

@Data
@Builder
@AllArgsConstructor
@TableName("lawsubparagraph")
public class LawSubparagraphPo {

    private String id;

    private String subparagraphSeq;

    private String subparagraphName;

    private String paragraphId;

    private String articleId;

    private String sectionId;

    private String chapterId;

    private String partId;

    private String docId;

    public LawSubparagraphPo(){}

    public  LawSubparagraphPo(LawSubparagraphPo lawSubparagraphPo){
        BeanCopier beanCopier = BeanCopier.create(lawSubparagraphPo.getClass(),this.getClass(),false);
        beanCopier.copy(lawSubparagraphPo,this,null);
    }

    public LawSubparagraphPo(LawSubparagraphVo lawSubparagraphVo){
        this(LawSubParaConvertMapper.INSTANCE.vo2po(lawSubparagraphVo));
    }
}
