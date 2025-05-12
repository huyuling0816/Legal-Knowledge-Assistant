package com.lkms.utils.conventer.povo;

import com.lkms.po.lawPo.LawPartPo;
import com.lkms.vo.lawVo.LawPartVo;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface LawPartConvertMapper {
    LawPartConvertMapper INSTANCE = Mappers.getMapper(LawPartConvertMapper.class);

    LawPartVo po2vo(LawPartPo lawPartPo);

    LawPartPo vo2po(LawPartVo lawPartVo);
}
