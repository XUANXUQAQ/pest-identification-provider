package com.rjgc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rjgc.dao.Orders;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author zhaoyunjie
 * @date 2021-04-08 15:33
 */
@Mapper
public interface OrderMapper extends BaseMapper<Orders> {
}