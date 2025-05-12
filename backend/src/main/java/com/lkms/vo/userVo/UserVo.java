package com.lkms.vo.userVo;

import com.lkms.utils.conventer.povo.UserInfoConvertMapper;
import com.lkms.po.userPo.UserPo;
import com.lkms.utils.BeanCopierWithCacheUtil;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserVo {
    private Integer id;
    private String name;
    @NotNull(message = "EMPTY_PASSWORD")
    @Size(min = 6, max = 16, message = "PASSWORD_FORMAT_ERROR")
    private String password;
    @NotNull(message = "EMPTY_EMAIL")
    @Email(message = "EMAIL_FORMAT_ERROR")
    private String email;

    public UserVo(UserVo userVO) {
        BeanCopierWithCacheUtil.copy(userVO, this);
    }

    public UserVo(@NonNull UserPo userPo) {
        this(UserInfoConvertMapper.INSTANCE.po2vo(userPo));
    }
}
