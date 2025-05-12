package com.lkms.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lkms.po.userPo.UserPo;
import com.lkms.utils.BeanCopierWithCacheUtil;
import com.lkms.utils.conventer.povo.UserConvertMapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserVo {
    private Integer id;
    private String name;

    @NotNull(message = "NO_PASSWORD")
    @Size(min = 6, max = 16, message = "WRONG_PASSWORD")
    private String password;

    private String email;

    public UserVo(UserVo userVo){
        BeanCopierWithCacheUtil.copy(userVo,this);
    }
     public UserVo(@NotNull UserPo userPo){
        this(UserConvertMapper.INSTANCE.po2vo(userPo));
     }
}
