package com.rjgc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rjgc.entity.Orders;
import com.rjgc.mapper.OrderMapper;
import com.rjgc.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhaoyunjie
 * @date 2021-04-08 16:24
 */
@Service("orderServiceImpl")
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Orders> implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public Map<String, Object> selectAllOrders(@RequestParam int pageNum, @RequestParam int pageSize) {
        QueryWrapper<Orders> wrapper = new QueryWrapper<>();
        return getListPagesMap(pageNum, pageSize, wrapper);
    }

    @Override
    public List<Orders> selectOrdersById(int id) {
        QueryWrapper<Orders> wrapper = new QueryWrapper<>();
        wrapper.eq("id", id);
        return orderMapper.selectList(wrapper);
    }

    @Override
    public Map<String, Object> selectOrdersByName(int pageNum, int pageSize, String name) {
        QueryWrapper<Orders> wrapper = new QueryWrapper<>();
        wrapper.like("name", name);
        return getListPagesMap(pageNum, pageSize, wrapper);
    }

    private Map<String, Object> getListPagesMap(int pageNum, int pageSize, QueryWrapper<Orders> wrapper) {
        IPage<Orders> page = this.page(new Page<>(pageNum, pageSize), wrapper);
        HashMap<String, Object> map = new HashMap<>();
        map.put("data", page.getRecords());
        map.put("pages", page.getPages());
        return map;
    }

    @Override
    public int insertOrder(Orders orders) {
        return orderMapper.insert(orders);
    }

    @Override
    public int deleteOrderById(int id) {
        return orderMapper.deleteById(id);
    }

    @Override
    public int updateOrder(Orders newOrders) {
        return orderMapper.updateById(newOrders);
    }
}
