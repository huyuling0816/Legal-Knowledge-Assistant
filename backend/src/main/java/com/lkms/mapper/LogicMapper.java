package com.lkms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lkms.po.LogicPo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
public interface LogicMapper extends BaseMapper<LogicPo> {
}
