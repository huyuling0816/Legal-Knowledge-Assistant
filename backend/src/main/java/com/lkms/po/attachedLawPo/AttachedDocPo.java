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
@TableName("attachedDoc")
public class AttachedDocPo {
    private String id;
    private String docId;
    private String filePath;
}
