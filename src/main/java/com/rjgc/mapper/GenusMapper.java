package com.rjgc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rjgc.entity.Genus;
import org.apache.ibatis.annotations.Options;

/**
 * @author zhaoyunjie
 * @date 2021-04-08 16:37
 */
public interface GenusMapper extends BaseMapper<Genus> {

    @Override
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Genus entity);
}
