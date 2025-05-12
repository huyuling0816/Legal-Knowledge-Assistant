package com.lkms.po.attachedLawPo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("attachedArticle")
public class AttachedArticlePo {
    private String id;
    private String articleId;
    private String filePath;
}
