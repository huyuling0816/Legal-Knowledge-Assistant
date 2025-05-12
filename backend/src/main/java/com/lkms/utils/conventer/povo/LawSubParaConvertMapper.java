package com.lkms.utils.conventer.povo;

import com.lkms.po.lawPo.LawSubparagraphPo;
import com.lkms.vo.lawVo.LawSubparagraphVo;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LawSubParaConvertMapper {
    LawSubParaConvertMapper INSTANCE = Mappers.getMapper(LawSubParaConvertMapper.class);

    LawSubparagraphVo po2vo(LawSubparagraphPo lawSubparagraphPo);

    LawSubparagraphPo vo2po(LawSubparagraphVo lawSubparagraphVo);
}
