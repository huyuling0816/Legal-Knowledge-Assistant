package com.lkms.utils.conventer.povo;

import com.lkms.po.lawPo.LawItemPo;
import com.lkms.vo.lawVo.LawItemVo;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-24T11:55:10+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_362 (Amazon.com Inc.)"
)
public class LawItemConvertMapperImpl implements LawItemConvertMapper {

    @Override
    public LawItemVo po2vo(LawItemPo lawItemPo) {
        if ( lawItemPo == null ) {
            return null;
        }

        LawItemVo lawItemVo = new LawItemVo();

        lawItemVo.setId( lawItemPo.getId() );
        lawItemVo.setItemSeq( lawItemPo.getItemSeq() );
        lawItemVo.setItemName( lawItemPo.getItemName() );
        lawItemVo.setSubparagraphId( lawItemPo.getSubparagraphId() );
        lawItemVo.setParagraphId( lawItemPo.getParagraphId() );
        lawItemVo.setArticleId( lawItemPo.getArticleId() );
        lawItemVo.setSectionId( lawItemPo.getSectionId() );
        lawItemVo.setChapterId( lawItemPo.getChapterId() );
        lawItemVo.setPartId( lawItemPo.getPartId() );
        lawItemVo.setDocId( lawItemPo.getDocId() );

        return lawItemVo;
    }

    @Override
    public LawItemPo vo2po(LawItemVo lawItemVo) {
        if ( lawItemVo == null ) {
            return null;
        }

        LawItemPo lawItemPo = new LawItemPo();

        lawItemPo.setId( lawItemVo.getId() );
        lawItemPo.setItemSeq( lawItemVo.getItemSeq() );
        lawItemPo.setItemName( lawItemVo.getItemName() );
        lawItemPo.setSubparagraphId( lawItemVo.getSubparagraphId() );
        lawItemPo.setParagraphId( lawItemVo.getParagraphId() );
        lawItemPo.setArticleId( lawItemVo.getArticleId() );
        lawItemPo.setSectionId( lawItemVo.getSectionId() );
        lawItemPo.setChapterId( lawItemVo.getChapterId() );
        lawItemPo.setPartId( lawItemVo.getPartId() );
        lawItemPo.setDocId( lawItemVo.getDocId() );

        return lawItemPo;
    }
}
