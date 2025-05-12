package com.lkms.utils.conventer.povo;

import com.lkms.po.lawPo.LawDocPo;
import com.lkms.vo.lawVo.LawDocVo;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-24T11:55:10+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_362 (Amazon.com Inc.)"
)
public class LawDocConvertMapperImpl implements LawDocConvertMapper {

    @Override
    public LawDocVo po2vo(LawDocPo lawDocPO) {
        if ( lawDocPO == null ) {
            return null;
        }

        LawDocVo lawDocVo = new LawDocVo();

        lawDocVo.setId( lawDocPO.getId() );
        lawDocVo.setTitle( lawDocPO.getTitle() );
        lawDocVo.setOffice( lawDocPO.getOffice() );
        lawDocVo.setStatus( lawDocPO.getStatus() );
        lawDocVo.setType( lawDocPO.getType() );
        lawDocVo.setDocCategory( lawDocPO.getDocCategory() );
        lawDocVo.setSuffix( lawDocPO.getSuffix() );
        lawDocVo.setDocText( lawDocPO.getDocText() );
        lawDocVo.setArticleSum( lawDocPO.getArticleSum() );
        lawDocVo.setDivided( lawDocPO.getDivided() );
        lawDocVo.setDocURL( lawDocPO.getDocURL() );
        lawDocVo.setFullContent( lawDocPO.getFullContent() );
        lawDocVo.setPublish( lawDocPO.getPublish() );
        lawDocVo.setExpiry( lawDocPO.getExpiry() );

        return lawDocVo;
    }

    @Override
    public LawDocPo vo2po(LawDocVo lawDocVO) {
        if ( lawDocVO == null ) {
            return null;
        }

        LawDocPo lawDocPo = new LawDocPo();

        lawDocPo.setId( lawDocVO.getId() );
        lawDocPo.setTitle( lawDocVO.getTitle() );
        lawDocPo.setOffice( lawDocVO.getOffice() );
        lawDocPo.setPublish( lawDocVO.getPublish() );
        lawDocPo.setExpiry( lawDocVO.getExpiry() );
        lawDocPo.setStatus( lawDocVO.getStatus() );
        lawDocPo.setType( lawDocVO.getType() );
        lawDocPo.setDocCategory( lawDocVO.getDocCategory() );
        lawDocPo.setDocText( lawDocVO.getDocText() );
        lawDocPo.setArticleSum( lawDocVO.getArticleSum() );
        lawDocPo.setDivided( lawDocVO.getDivided() );
        lawDocPo.setSuffix( lawDocVO.getSuffix() );
        lawDocPo.setDocURL( lawDocVO.getDocURL() );
        lawDocPo.setFullContent( lawDocVO.getFullContent() );

        return lawDocPo;
    }
}
