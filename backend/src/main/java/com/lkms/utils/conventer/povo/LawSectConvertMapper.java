package com.lkms.utils.conventer.povo;

import com.lkms.po.lawPo.LawSectionPo;
import com.lkms.vo.lawVo.LawSectionVo;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface LawSectConvertMapper {
    LawSectConvertMapper INSTANCE = Mappers.getMapper(LawSectConvertMapper.class);

    LawSectionVo po2vo(LawSectionPo lawSectionPo);

    LawSectionPo vo2po(LawSectionVo lawSectionVo);
}
