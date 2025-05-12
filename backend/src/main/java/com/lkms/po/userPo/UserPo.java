package com.lkms.po.userPo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.lkms.utils.conventer.povo.UserInfoConvertMapper;
import com.lkms.utils.BeanCopierWithCacheUtil;
import com.lkms.vo.userVo.UserVo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("userInfo")
public class UserPo {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String name;
    private String password;
    private String email;

    public UserPo(UserPo user) {
        BeanCopierWithCacheUtil.copy(user, this);
    }

    public UserPo(UserVo userVo) {
        this(UserInfoConvertMapper.INSTANCE.vo2po(userVo));
    }
}
