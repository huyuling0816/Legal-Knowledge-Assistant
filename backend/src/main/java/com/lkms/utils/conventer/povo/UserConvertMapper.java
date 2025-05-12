package com.lkms.utils.conventer.povo;

import com.lkms.po.userPo.UserPo;
import com.lkms.vo.UserVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR, imports = {})
public interface UserConvertMapper {
    UserConvertMapper INSTANCE = Mappers.getMapper(UserConvertMapper.class);

    @Mappings({
            @Mapping(target = "password", ignore = true)
    })
    UserVo po2vo(UserPo userPo);

    @Mappings({

    }) UserPo vo2po(UserVo userVo);

}
