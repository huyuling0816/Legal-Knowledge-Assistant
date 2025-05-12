package com.lkms.utils.conventer.povo;

import com.lkms.po.lawPo.LawChapterPo;
import com.lkms.vo.lawVo.LawChapterVo;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface LawChapConvertMapper {
    LawChapConvertMapper INSTANCE = Mappers.getMapper(LawChapConvertMapper.class);

    LawChapterVo po2vo(LawChapterPo lawChapterPo);

    LawChapterPo vo2po(LawChapterVo lawChapterVo);
}
