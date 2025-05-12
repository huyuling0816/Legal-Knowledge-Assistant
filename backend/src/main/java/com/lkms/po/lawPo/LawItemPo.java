package com.lkms.po.lawPo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.lkms.utils.conventer.povo.LawItemConvertMapper;
import com.lkms.vo.lawVo.LawItemVo;
import lombok.Data;
import org.springframework.cglib.beans.BeanCopier;

@Data
@TableName("lawitem")
public class LawItemPo {
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

    public LawItemPo(){}

    public LawItemPo(LawItemPo lawItemPo){
        BeanCopier beanCopier = BeanCopier.create(lawItemPo.getClass(),this.getClass(),false);
        beanCopier.copy(lawItemPo,this,null);
    }

    public LawItemPo(LawItemVo lawItemVo){
        this(LawItemConvertMapper.INSTANCE.vo2po(lawItemVo));
    }
}
