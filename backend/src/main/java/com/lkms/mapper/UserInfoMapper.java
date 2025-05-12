package com.lkms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lkms.po.userPo.UserPo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserInfoMapper extends BaseMapper<UserPo> {
}
