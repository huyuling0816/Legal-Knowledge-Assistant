package com.lkms.utils.conventer.povo;

import com.lkms.po.lawPo.LawAppendixPo;
import com.lkms.po.lawPo.LawAppendixPo.LawAppendixPoBuilder;
import com.lkms.vo.lawVo.LawAppendixVo;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-24T11:55:09+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_362 (Amazon.com Inc.)"
)
public class LawAppendixConvertMapperImpl implements LawAppendixConvertMapper {

    @Override
    public LawAppendixVo po2vo(LawAppendixPo lawAppendixPo) {
        if ( lawAppendixPo == null ) {
            return null;
        }

        LawAppendixVo lawAppendixVo = new LawAppendixVo();

        lawAppendixVo.setId( lawAppendixPo.getId() );
        lawAppendixVo.setAppendixSeq( lawAppendixPo.getAppendixSeq() );
        lawAppendixVo.setAppendixName( lawAppendixPo.getAppendixName() );
        lawAppendixVo.setDocId( lawAppendixPo.getDocId() );

        return lawAppendixVo;
    }

    @Override
    public LawAppendixPo vo2po(LawAppendixVo lawAppendixVo) {
        if ( lawAppendixVo == null ) {
            return null;
        }

        LawAppendixPoBuilder lawAppendixPo = LawAppendixPo.builder();

        lawAppendixPo.id( lawAppendixVo.getId() );
        lawAppendixPo.appendixSeq( lawAppendixVo.getAppendixSeq() );
        lawAppendixPo.appendixName( lawAppendixVo.getAppendixName() );
        lawAppendixPo.docId( lawAppendixVo.getDocId() );

        return lawAppendixPo.build();
    }
}
