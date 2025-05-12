package com.lkms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lkms.po.lawPo.LawArticlePo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;

import java.util.List;

@Mapper
public interface LawArticleMapper extends BaseMapper<LawArticlePo> {
    public List<LawArticlePo> selectByDocId(@Param(value = "docId") String docId);
    public List<LawArticlePo> getByBatch(@Param("offset") int offset, @Param("limit") int limit);
}
