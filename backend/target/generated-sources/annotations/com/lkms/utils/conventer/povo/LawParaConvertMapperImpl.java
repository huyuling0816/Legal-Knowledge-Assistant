package com.lkms.utils.conventer.povo;

import com.lkms.po.lawPo.LawParagraphPo;
import com.lkms.po.lawPo.LawParagraphPo.LawParagraphPoBuilder;
import com.lkms.vo.lawVo.LawParagraphVo;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-24T11:55:10+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_362 (Amazon.com Inc.)"
)
public class LawParaConvertMapperImpl implements LawParaConvertMapper {

    @Override
    public LawParagraphVo po2vo(LawParagraphPo lawParagraphPo) {
        if ( lawParagraphPo == null ) {
            return null;
        }

        LawParagraphVo lawParagraphVo = new LawParagraphVo();

        lawParagraphVo.setId( lawParagraphPo.getId() );
        lawParagraphVo.setParagraphSeq( lawParagraphPo.getParagraphSeq() );
        lawParagraphVo.setParagraphName( lawParagraphPo.getParagraphName() );
        lawParagraphVo.setArticleId( lawParagraphPo.getArticleId() );
        lawParagraphVo.setSectionId( lawParagraphPo.getSectionId() );
        lawParagraphVo.setChapterId( lawParagraphPo.getChapterId() );
        lawParagraphVo.setPartId( lawParagraphPo.getPartId() );
        lawParagraphVo.setDocId( lawParagraphPo.getDocId() );

        return lawParagraphVo;
    }

    @Override
    public LawParagraphPo vo2po(LawParagraphVo lawParagraphVo) {
        if ( lawParagraphVo == null ) {
            return null;
        }

        LawParagraphPoBuilder lawParagraphPo = LawParagraphPo.builder();

        lawParagraphPo.id( lawParagraphVo.getId() );
        lawParagraphPo.paragraphSeq( lawParagraphVo.getParagraphSeq() );
        lawParagraphPo.paragraphName( lawParagraphVo.getParagraphName() );
        lawParagraphPo.articleId( lawParagraphVo.getArticleId() );
        lawParagraphPo.sectionId( lawParagraphVo.getSectionId() );
        lawParagraphPo.chapterId( lawParagraphVo.getChapterId() );
        lawParagraphPo.partId( lawParagraphVo.getPartId() );
        lawParagraphPo.docId( lawParagraphVo.getDocId() );

        return lawParagraphPo.build();
    }
}
