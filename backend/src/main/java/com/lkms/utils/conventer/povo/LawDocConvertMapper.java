package com.lkms.utils.conventer.povo;

import com.lkms.po.lawPo.LawDocPo;
import com.lkms.vo.lawVo.LawDocVo;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LawDocConvertMapper {
    LawDocConvertMapper INSTANCE = Mappers.getMapper(LawDocConvertMapper.class);

    LawDocVo po2vo(LawDocPo lawDocPO);

    LawDocPo vo2po(LawDocVo lawDocVO);
}
