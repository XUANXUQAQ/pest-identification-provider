package com.rjgc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rjgc.entity.Family;
import org.apache.ibatis.annotations.Options;

/**
 * @author zhaoyunjie
 * @date 2021-04-08 13:28
 */
public interface FamilyMapper extends BaseMapper<Family> {

    @Override
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Family entity);
}
