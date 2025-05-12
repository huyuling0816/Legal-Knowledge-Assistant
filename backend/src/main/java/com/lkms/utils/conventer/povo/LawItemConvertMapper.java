package com.lkms.utils.conventer.povo;

import com.lkms.po.lawPo.LawItemPo;
import com.lkms.vo.lawVo.LawItemVo;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface LawItemConvertMapper {
    LawItemConvertMapper INSTANCE = Mappers.getMapper(LawItemConvertMapper.class);

    LawItemVo po2vo(LawItemPo lawItemPo);

    LawItemPo vo2po(LawItemVo lawItemVo);
}
