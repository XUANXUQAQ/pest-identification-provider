package com.rjgc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rjgc.entity.Orders;
import org.apache.ibatis.annotations.Options;

/**
 * @author zhaoyunjie
 * @date 2021-04-08 15:33
 */
public interface OrderMapper extends BaseMapper<Orders> {

    @Override
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Orders entity);
}
