package com.rjgc.controller;

import com.rjgc.dao.Orders;
import com.rjgc.dao.OrderFamily;
import com.rjgc.service.OrderFamilyService;
import com.rjgc.service.OrderService;
import io.swagger.annotations.ApiOperation;
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
    @ApiOperation("查询所有目")
    public List<Orders> selectAllOrders(@RequestParam int pageNum, @RequestParam int pageSize) {
        return orderService.selectAllOrders(pageNum, pageSize);
    }

    @GetMapping
    @ApiOperation("根据id查询目")
    public List<Orders> selectOrderById(@RequestParam int id) {
        return orderService.selectOrdersById(id);
    }

    @PostMapping
    @ApiOperation("添加目")
    public int insertOrder(@RequestBody Orders orders) {
        return orderService.insertOrder(orders);
    }

    @DeleteMapping
    @ApiOperation("根据id删除目")
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
    @ApiOperation("更新目")
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
