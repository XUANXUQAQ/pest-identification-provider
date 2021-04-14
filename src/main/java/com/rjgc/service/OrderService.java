package com.rjgc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rjgc.entity.Orders;

import java.util.List;
import java.util.Map;

/**
 * @author zhaoyunjie
 * @date 2021-04-08 13:19
 */
public interface OrderService extends IService<Orders> {
    Map<List<Orders>, Long> selectAllOrders(int pageNum, int pageSize);

    List<Orders> selectOrdersById(int id);

    Map<List<Orders>, Long> selectOrdersByName(int pageNum, int pageSize, String name);

    int insertOrder(Orders orders);

    int deleteOrderById(int id);

    int updateOrder(Orders newOrders);
}
