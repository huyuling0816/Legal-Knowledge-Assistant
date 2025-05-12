package com.lkms.utils.conventer.povo;

import com.lkms.po.lawPo.LawArticlePo;
import com.lkms.po.lawPo.LawArticlePo.LawArticlePoBuilder;
import com.lkms.vo.lawVo.LawArticleVo;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-24T11:55:10+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_362 (Amazon.com Inc.)"
)
public class LawArticleConvertMapperImpl implements LawArticleConvertMapper {

    @Override
    public LawArticleVo po2vo(LawArticlePo lawArticlePo) {
        if ( lawArticlePo == null ) {
            return null;
        }

        LawArticleVo lawArticleVo = new LawArticleVo();

        lawArticleVo.setId( lawArticlePo.getId() );
        lawArticleVo.setArticleSeq( lawArticlePo.getArticleSeq() );
        lawArticleVo.setArticleName( lawArticlePo.getArticleName() );
        lawArticleVo.setSectionId( lawArticlePo.getSectionId() );
        lawArticleVo.setChapterId( lawArticlePo.getChapterId() );
        lawArticleVo.setPartId( lawArticlePo.getPartId() );
        lawArticleVo.setDocId( lawArticlePo.getDocId() );

        return lawArticleVo;
    }

    @Override
    public LawArticlePo vo2po(LawArticleVo lawArticleVo) {
        if ( lawArticleVo == null ) {
            return null;
        }

        LawArticlePoBuilder lawArticlePo = LawArticlePo.builder();

        lawArticlePo.id( lawArticleVo.getId() );
        lawArticlePo.articleSeq( lawArticleVo.getArticleSeq() );
        lawArticlePo.articleName( lawArticleVo.getArticleName() );
        lawArticlePo.sectionId( lawArticleVo.getSectionId() );
        lawArticlePo.chapterId( lawArticleVo.getChapterId() );
        lawArticlePo.partId( lawArticleVo.getPartId() );
        lawArticlePo.docId( lawArticleVo.getDocId() );

        return lawArticlePo.build();
    }
}
