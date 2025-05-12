package com.lkms.utils.conventer.povo;

import com.lkms.po.LogicPo;
import com.lkms.vo.LogicVo;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-23T09:00:43+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_362 (Amazon.com Inc.)"
)
public class LogicConvertMapperImpl implements LogicConvertMapper {

    @Override
    public LogicPo vo2po(LogicVo logicVo) {
        if ( logicVo == null ) {
            return null;
        }

        LogicPo logicPo = new LogicPo();

        logicPo.setBody( logicVo.getBody() );
        logicPo.setAntecedent( logicVo.getAntecedent() );
        logicPo.setConsequence( logicVo.getConsequence() );
        logicPo.setId( logicVo.getId() );
        logicPo.setDocId( logicVo.getDocId() );
        logicPo.setPartId( logicVo.getPartId() );
        logicPo.setChapterId( logicVo.getChapterId() );
        logicPo.setSectionId( logicVo.getSectionId() );

        return logicPo;
    }

    @Override
    public LogicVo po2vo(LogicPo logicPo) {
        if ( logicPo == null ) {
            return null;
        }

        LogicVo logicVo = new LogicVo();

        logicVo.setBody( logicPo.getBody() );
        logicVo.setAntecedent( logicPo.getAntecedent() );
        logicVo.setConsequence( logicPo.getConsequence() );
        logicVo.setId( logicPo.getId() );
        logicVo.setDocId( logicPo.getDocId() );
        logicVo.setPartId( logicPo.getPartId() );
        logicVo.setChapterId( logicPo.getChapterId() );
        logicVo.setSectionId( logicPo.getSectionId() );

        return logicVo;
    }
}
