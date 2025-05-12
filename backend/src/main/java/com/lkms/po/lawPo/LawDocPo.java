package com.lkms.po.lawPo;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.lkms.utils.conventer.povo.LawDocConvertMapper;
import com.lkms.vo.lawVo.LawDocVo;
import lombok.Data;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import java.util.Date;

@Data
@TableName("lawdoc")
@Document(indexName = "lawdoc")
public class LawDocPo {
    @Id
    @Field(name = "id")
    private String id;
    private String title;
    private String office;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date publish;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date expiry;

    private String status;

    private String type;

    @Field(name = "docCategory")
    @JsonProperty("docCategory")
    private String docCategory;

    @Field(name = "docText")
    @JsonProperty("docText")
    private String docText;

    @Field(name = "articleSum")
    @JsonProperty("articleSum")
    private Short articleSum;

    private Short divided;
    private String suffix;

    @TableField(value = "docURL")
    @Field(name = "docURL")
    @JsonProperty("docURL")
    private String docURL;

    @Field(name = "fullContent")
    @JsonProperty("fullContent")
    private String fullContent;

    public LawDocPo() {
    }

    public LawDocPo(LawDocPo lawDocPo) {
        BeanCopier beanCopier = BeanCopier.create(lawDocPo.getClass(), this.getClass(), false);
        beanCopier.copy(lawDocPo, this, null);
    }

    public LawDocPo(LawDocVo lawDocVo){
        this(LawDocConvertMapper.INSTANCE.vo2po(lawDocVo));
    }
}
