package com.lkms.utils.conventer.povo;

import com.lkms.po.lawPo.LawParagraphPo;
import com.lkms.vo.lawVo.LawParagraphVo;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LawParaConvertMapper {
    LawParaConvertMapper INSTANCE = Mappers.getMapper(LawParaConvertMapper.class);

    LawParagraphVo po2vo(LawParagraphPo lawParagraphPo);

    LawParagraphPo vo2po(LawParagraphVo lawParagraphVo);
}
