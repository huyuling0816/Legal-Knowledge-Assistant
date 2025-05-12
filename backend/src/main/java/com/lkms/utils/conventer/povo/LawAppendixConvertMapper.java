package com.lkms.utils.conventer.povo;

import com.lkms.po.lawPo.LawAppendixPo;
import com.lkms.vo.lawVo.LawAppendixVo;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface LawAppendixConvertMapper {
    LawAppendixConvertMapper INSTANCE = Mappers.getMapper(LawAppendixConvertMapper.class);

    LawAppendixVo po2vo(LawAppendixPo lawAppendixPo);

    LawAppendixPo vo2po(LawAppendixVo lawAppendixVo);
}
