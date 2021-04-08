package com.rjgc.controller;

import com.rjgc.dao.Orders;
import com.rjgc.dao.OrderFamily;
import com.rjgc.service.OrderFamilyService;
import com.rjgc.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zhaoyunjie
 * @date 2021-04-08 17:30
 */
@RestController("order")
public class OrderController {

    @Autowired
    @Qualifier("orderFamilyServiceImpl")
    private OrderFamilyService orderFamilyService;


    @Autowired
    @Qualifier("orderServiceImpl")
    private OrderService orderService;

    @GetMapping("all")
    public List<Orders> selectAllOrders(@RequestParam int pageNum, @RequestParam int pageSize) {
        return orderService.selectAllOrders(pageNum, pageSize);
    }

    @GetMapping
    public List<Orders> selectOrderById(@RequestParam int id) {
        return orderService.selectOrdersById(id);
    }

    @PostMapping
    public int insertOrder(@RequestBody Orders orders) {
        return orderService.insertOrder(orders);
    }

    @DeleteMapping
    public int deleteOrderById(@RequestParam int id) {
        //检查是否仍有属关联于该目
        List<OrderFamily> orderFamilies = orderFamilyService.selectByOrderId(id);
        if (orderFamilies.isEmpty()) {
            return orderService.deleteOrderById(id);
        } else {
            //todo 抛出错误
            return 0;
        }
    }

    @PutMapping
    public int updateOrder(@RequestBody Orders newOrders) {
        //检查是否仍有属关联于该目
        List<OrderFamily> orderFamilies = orderFamilyService.selectByOrderId(newOrders.getId());
        if (orderFamilies.isEmpty()) {
            return orderService.updateOrder(newOrders);
        } else {
            //todo 抛出错误
            return 0;
        }
    }
}
