package com.lkms.utils.conventer.povo;

import com.lkms.po.lawPo.LawChapterPo;
import com.lkms.vo.lawVo.LawChapterVo;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-24T11:55:10+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_362 (Amazon.com Inc.)"
)
public class LawChapConvertMapperImpl implements LawChapConvertMapper {

    @Override
    public LawChapterVo po2vo(LawChapterPo lawChapterPo) {
        if ( lawChapterPo == null ) {
            return null;
        }

        LawChapterVo lawChapterVo = new LawChapterVo();

        lawChapterVo.setId( lawChapterPo.getId() );
        lawChapterVo.setChapterSeq( lawChapterPo.getChapterSeq() );
        lawChapterVo.setChapterName( lawChapterPo.getChapterName() );
        lawChapterVo.setPartId( lawChapterPo.getPartId() );
        lawChapterVo.setDocId( lawChapterPo.getDocId() );

        return lawChapterVo;
    }

    @Override
    public LawChapterPo vo2po(LawChapterVo lawChapterVo) {
        if ( lawChapterVo == null ) {
            return null;
        }

        LawChapterPo lawChapterPo = new LawChapterPo();

        lawChapterPo.setId( lawChapterVo.getId() );
        lawChapterPo.setChapterSeq( lawChapterVo.getChapterSeq() );
        lawChapterPo.setChapterName( lawChapterVo.getChapterName() );
        lawChapterPo.setPartId( lawChapterVo.getPartId() );
        lawChapterPo.setDocId( lawChapterVo.getDocId() );

        return lawChapterPo;
    }
}
