package com.lkms.vo.lawVo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lkms.po.lawPo.LawArticlePo;
import com.lkms.po.lawPo.LawDocPo;
import com.lkms.utils.BeanCopierWithCacheUtil;
import com.lkms.utils.conventer.povo.LawDocConvertMapper;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class LawDocVo {
    private String id;

    private String title;

    private String office;

    private String status;

    private String type;

    private String docCategory;

    private String suffix;

    private String docText;

    private Short articleSum;

    private Short divided;

    private String docURL;

    private String fullContent;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date publish;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date expiry;

    private List<LawArticlePo> articles;

    public LawDocVo(LawDocVo lawDocVO) {
        BeanCopierWithCacheUtil.copy(lawDocVO, this);
    }

    public LawDocVo(@NotNull LawDocPo lawDocPO) {
        this(LawDocConvertMapper.INSTANCE.po2vo(lawDocPO));
    }

//    public double getScore() {
//        try {
//            Class<?> lawDocVoClass = this.getClass();
//            Field score = lawDocVoClass.getDeclaredField("score");
//            score.setAccessible(true);
//            return (double) score.get(this);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return 0.0;
//        }
//    }
}
