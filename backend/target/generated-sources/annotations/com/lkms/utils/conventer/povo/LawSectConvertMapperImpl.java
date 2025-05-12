package com.lkms.utils.conventer.povo;

import com.lkms.po.lawPo.LawSectionPo;
import com.lkms.vo.lawVo.LawSectionVo;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-24T11:55:10+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_362 (Amazon.com Inc.)"
)
public class LawSectConvertMapperImpl implements LawSectConvertMapper {

    @Override
    public LawSectionVo po2vo(LawSectionPo lawSectionPo) {
        if ( lawSectionPo == null ) {
            return null;
        }

        LawSectionVo lawSectionVo = new LawSectionVo();

        lawSectionVo.setId( lawSectionPo.getId() );
        lawSectionVo.setSectionSeq( lawSectionPo.getSectionSeq() );
        lawSectionVo.setSectionName( lawSectionPo.getSectionName() );
        lawSectionVo.setChapterId( lawSectionPo.getChapterId() );
        lawSectionVo.setPartId( lawSectionPo.getPartId() );
        lawSectionVo.setDocId( lawSectionPo.getDocId() );

        return lawSectionVo;
    }

    @Override
    public LawSectionPo vo2po(LawSectionVo lawSectionVo) {
        if ( lawSectionVo == null ) {
            return null;
        }

        LawSectionPo lawSectionPo = new LawSectionPo();

        lawSectionPo.setId( lawSectionVo.getId() );
        lawSectionPo.setSectionSeq( lawSectionVo.getSectionSeq() );
        lawSectionPo.setSectionName( lawSectionVo.getSectionName() );
        lawSectionPo.setChapterId( lawSectionVo.getChapterId() );
        lawSectionPo.setPartId( lawSectionVo.getPartId() );
        lawSectionPo.setDocId( lawSectionVo.getDocId() );

        return lawSectionPo;
    }
}
