package com.lkms.utils.conventer.povo;


import com.lkms.po.LogicPo;
import com.lkms.vo.LogicVo;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface LogicConvertMapper {
    LogicConvertMapper INSTANCE = Mappers.getMapper(LogicConvertMapper.class);

    LogicPo vo2po(LogicVo logicVo);

    LogicVo po2vo(LogicPo logicPo);
}
