package com.lkms.po.lawPo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.lkms.utils.conventer.povo.LawArticleConvertMapper;
import com.lkms.vo.lawVo.LawArticleVo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

@Data
@Builder
@TableName("lawarticle")
@Document(indexName = "lawarticle")
@AllArgsConstructor
public class LawArticlePo {
    @Id
    @Field(name = "id")
    private String id;
    @Field(name = "articleSeq")
    @JsonProperty("articleSeq")
    private String articleSeq;
    @Field(name = "articleName")
    @JsonProperty("articleName")
    private String articleName;
    @Field(name = "sectionId")
    @JsonProperty("sectionId")
    private String sectionId;
    @Field(name = "chapterId")
    @JsonProperty("chapterId")
    private String chapterId;
    @Field(name = "partId")
    @JsonProperty("partId")
    private String partId;
    @Field(name = "docId")
    @JsonProperty("docId")
    private String docId;

    public LawArticlePo() {
    }

    public LawArticlePo(LawArticlePo lawArticlePo) {
        BeanCopier beanCopier = BeanCopier.create(lawArticlePo.getClass(), this.getClass(), false);
        beanCopier.copy(lawArticlePo, this, null);
    }

    public LawArticlePo(LawArticleVo lawArticleVo) {
        this(LawArticleConvertMapper.INSTANCE.vo2po(lawArticleVo));
    }
}
