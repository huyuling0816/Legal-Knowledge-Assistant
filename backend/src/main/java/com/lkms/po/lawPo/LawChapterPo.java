package com.lkms.po.lawPo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.lkms.utils.conventer.povo.LawChapConvertMapper;
import com.lkms.vo.lawVo.LawChapterVo;
import lombok.Data;
import org.springframework.cglib.beans.BeanCopier;

import javax.validation.constraints.NotNull;

@Data
@TableName("lawchapter")
public class LawChapterPo {
    private String id;

    private String chapterSeq;

    private String chapterName;

    private String partId;

    private String docId;

    public LawChapterPo (){}

    public LawChapterPo(LawChapterPo lawChapterPo){
        BeanCopier beanCopier = BeanCopier.create(lawChapterPo.getClass(),this.getClass(),false);
        beanCopier.copy(lawChapterPo,this,null);
    }

    public LawChapterPo(@NotNull LawChapterVo lawChapVO){
        this(LawChapConvertMapper.INSTANCE.vo2po(lawChapVO));
    }
}
