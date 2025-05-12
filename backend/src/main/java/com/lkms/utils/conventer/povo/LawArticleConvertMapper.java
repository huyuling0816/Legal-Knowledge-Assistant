package com.lkms.utils.conventer.povo;

import com.lkms.po.lawPo.LawArticlePo;
import com.lkms.vo.lawVo.LawArticleVo;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LawArticleConvertMapper {
    LawArticleConvertMapper INSTANCE = Mappers.getMapper(LawArticleConvertMapper.class);

    LawArticleVo po2vo(LawArticlePo lawArticlePo);

    LawArticlePo vo2po(LawArticleVo lawArticleVo);
}
