package com.lkms.utils.conventer.povo;

import com.lkms.po.userPo.UserPo;
import com.lkms.vo.userVo.UserVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface UserInfoConvertMapper {
    UserInfoConvertMapper INSTANCE = Mappers.getMapper(UserInfoConvertMapper.class);
    @Mapping(target = "password", ignore=true)
    UserVo po2vo(UserPo userPo);
    UserPo vo2po(UserVo userVo);
}
