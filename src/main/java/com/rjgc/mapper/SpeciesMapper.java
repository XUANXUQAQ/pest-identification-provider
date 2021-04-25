package com.rjgc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rjgc.entity.Species;
import org.apache.ibatis.annotations.Options;

/**
 * @author zhaoyunjie
 * @date 2021-04-09 10:23
 */
public interface SpeciesMapper extends BaseMapper<Species> {

    @Override
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Species entity);
}
