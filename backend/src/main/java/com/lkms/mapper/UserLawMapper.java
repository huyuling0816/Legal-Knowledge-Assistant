package com.lkms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lkms.po.userPo.UserLawPo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserLawMapper extends BaseMapper<UserLawPo> {
}
