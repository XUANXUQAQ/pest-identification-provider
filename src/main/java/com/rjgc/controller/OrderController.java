package com.rjgc.controller;

import com.rjgc.entity.OrderFamily;
import com.rjgc.entity.Orders;
import com.rjgc.exceptions.BizException;
import com.rjgc.exceptions.ExceptionsEnum;
import com.rjgc.exceptions.ResBody;
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
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    @Qualifier("orderFamilyServiceImpl")
    private OrderFamilyService orderFamilyService;


    @Autowired
    @Qualifier("orderServiceImpl")
    private OrderService orderService;

    @GetMapping("all")
    @ApiOperation("查询所有目")
    public ResBody<List<Orders>> selectAllOrders(@RequestParam int pageNum, @RequestParam int pageSize) {
        return ResBody.success(orderService.selectAllOrders(pageNum, pageSize));
    }

    @GetMapping
    @ApiOperation("根据id查询目")
    public ResBody<List<Orders>> selectOrderById(@RequestParam int id) {
        return ResBody.success(orderService.selectOrdersById(id));
    }

    @PostMapping
    @ApiOperation("添加目")
    public ResBody<Integer> insertOrder(@RequestBody Orders orders) {
        if (orderService.insertOrder(orders) == 1) {
            return ResBody.success();
        } else {
            throw new BizException(ExceptionsEnum.DATABASE_FAILED);
        }
    }

    @DeleteMapping
    @ApiOperation("根据id删除目")
    public ResBody<Integer> deleteOrderById(@RequestParam int id) {
        //检查是否仍有属关联于该目
        List<OrderFamily> orderFamilies = orderFamilyService.selectByOrderId(id);
        if (orderFamilies.isEmpty()) {
            if (orderService.deleteOrderById(id) == 1) {
                return ResBody.success();
            } else {
                throw new BizException(ExceptionsEnum.DATABASE_FAILED);
            }
        } else {
            //id仍被使用
            throw new BizException(ExceptionsEnum.IN_USE);
        }
    }

    @PutMapping
    @ApiOperation("更新目")
    public ResBody<Integer> updateOrder(@RequestBody Orders newOrders) {
        //检查是否仍有属关联于该目
        List<OrderFamily> orderFamilies = orderFamilyService.selectByOrderId(newOrders.getId());
        if (orderFamilies.isEmpty()) {
            if (orderService.updateOrder(newOrders) == 1) {
                return ResBody.success();
            } else {
                throw new BizException(ExceptionsEnum.DATABASE_FAILED);
            }
        } else {
            //id仍被使用
            throw new BizException(ExceptionsEnum.IN_USE);
        }
    }
}
