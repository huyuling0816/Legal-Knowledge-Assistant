package com.lkms.utils.conventer.povo;

import com.lkms.po.lawPo.LawPartPo;
import com.lkms.vo.lawVo.LawPartVo;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-24T11:55:10+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_362 (Amazon.com Inc.)"
)
public class LawPartConvertMapperImpl implements LawPartConvertMapper {

    @Override
    public LawPartVo po2vo(LawPartPo lawPartPo) {
        if ( lawPartPo == null ) {
            return null;
        }

        LawPartVo lawPartVo = new LawPartVo();

        lawPartVo.setId( lawPartPo.getId() );
        lawPartVo.setPartSeq( lawPartPo.getPartSeq() );
        lawPartVo.setPartName( lawPartPo.getPartName() );
        lawPartVo.setDocId( lawPartPo.getDocId() );

        return lawPartVo;
    }

    @Override
    public LawPartPo vo2po(LawPartVo lawPartVo) {
        if ( lawPartVo == null ) {
            return null;
        }

        LawPartPo lawPartPo = new LawPartPo();

        lawPartPo.setId( lawPartVo.getId() );
        lawPartPo.setPartSeq( lawPartVo.getPartSeq() );
        lawPartPo.setPartName( lawPartVo.getPartName() );
        lawPartPo.setDocId( lawPartVo.getDocId() );

        return lawPartPo;
    }
}
