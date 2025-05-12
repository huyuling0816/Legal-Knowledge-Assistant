package com.lkms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lkms.po.lawPo.LawDocPo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;

import java.util.List;

@Mapper
public interface LawDocMapper extends BaseMapper<LawDocPo> {
    public String selectByName(@Param(value = "docName") String docName);

    public void updateByDocName(@Param(value = "docText") String docText, @Param(value = "fullContent") String fullContent,
                                @Param(value = "docName") String docName);
    public List<LawDocPo> selectByUserId(@Param(value = "userId") Integer userId);
    public List<LawDocPo> getByBatch(@Param("offset") int offset, @Param("limit") int limit);
}
