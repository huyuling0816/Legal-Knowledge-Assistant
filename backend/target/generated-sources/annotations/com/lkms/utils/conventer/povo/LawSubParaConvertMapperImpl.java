package com.lkms.utils.conventer.povo;

import com.lkms.po.lawPo.LawSubparagraphPo;
import com.lkms.po.lawPo.LawSubparagraphPo.LawSubparagraphPoBuilder;
import com.lkms.vo.lawVo.LawSubparagraphVo;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-24T11:55:10+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_362 (Amazon.com Inc.)"
)
public class LawSubParaConvertMapperImpl implements LawSubParaConvertMapper {

    @Override
    public LawSubparagraphVo po2vo(LawSubparagraphPo lawSubparagraphPo) {
        if ( lawSubparagraphPo == null ) {
            return null;
        }

        LawSubparagraphVo lawSubparagraphVo = new LawSubparagraphVo();

        lawSubparagraphVo.setId( lawSubparagraphPo.getId() );
        lawSubparagraphVo.setSubparagraphSeq( lawSubparagraphPo.getSubparagraphSeq() );
        lawSubparagraphVo.setSubparagraphName( lawSubparagraphPo.getSubparagraphName() );
        lawSubparagraphVo.setParagraphId( lawSubparagraphPo.getParagraphId() );
        lawSubparagraphVo.setArticleId( lawSubparagraphPo.getArticleId() );
        lawSubparagraphVo.setSectionId( lawSubparagraphPo.getSectionId() );
        lawSubparagraphVo.setChapterId( lawSubparagraphPo.getChapterId() );
        lawSubparagraphVo.setPartId( lawSubparagraphPo.getPartId() );
        lawSubparagraphVo.setDocId( lawSubparagraphPo.getDocId() );

        return lawSubparagraphVo;
    }

    @Override
    public LawSubparagraphPo vo2po(LawSubparagraphVo lawSubparagraphVo) {
        if ( lawSubparagraphVo == null ) {
            return null;
        }

        LawSubparagraphPoBuilder lawSubparagraphPo = LawSubparagraphPo.builder();

        lawSubparagraphPo.id( lawSubparagraphVo.getId() );
        lawSubparagraphPo.subparagraphSeq( lawSubparagraphVo.getSubparagraphSeq() );
        lawSubparagraphPo.subparagraphName( lawSubparagraphVo.getSubparagraphName() );
        lawSubparagraphPo.paragraphId( lawSubparagraphVo.getParagraphId() );
        lawSubparagraphPo.articleId( lawSubparagraphVo.getArticleId() );
        lawSubparagraphPo.sectionId( lawSubparagraphVo.getSectionId() );
        lawSubparagraphPo.chapterId( lawSubparagraphVo.getChapterId() );
        lawSubparagraphPo.partId( lawSubparagraphVo.getPartId() );
        lawSubparagraphPo.docId( lawSubparagraphVo.getDocId() );

        return lawSubparagraphPo.build();
    }
}
