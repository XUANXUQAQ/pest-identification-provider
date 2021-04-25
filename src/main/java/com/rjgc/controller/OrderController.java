package com.rjgc.controller;

import com.rjgc.Vo.OrderFamilyVo;
import com.rjgc.entity.Orders;
import com.rjgc.exceptions.BizException;
import com.rjgc.exceptions.ExceptionsEnum;
import com.rjgc.exceptions.ResBody;
import com.rjgc.service.OrderFamilyVoService;
import com.rjgc.service.OrderService;
import com.rjgc.utils.DBUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author zhaoyunjie
 * @date 2021-04-08 17:30
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    @Qualifier("orderFamilyVoServiceImpl")
    private OrderFamilyVoService orderFamilyService;


    @Autowired
    @Qualifier("orderServiceImpl")
    private OrderService orderService;

    @Autowired
    private DBUtils dbUtils;


    @GetMapping("all")
    @ApiOperation("查询所有目")
    public ResBody<Map<String, Object>> selectAllOrders(@RequestParam int pageNum, @RequestParam int pageSize) {
        return ResBody.success(orderService.selectAllOrders(pageNum, pageSize));
    }

    @GetMapping("name")
    @ApiOperation("根据名称查询")
    public ResBody<Map<String, Object>> selectOrdersByName(@RequestParam int pageNum, @RequestParam int pageSize, @RequestParam String name) {
        return ResBody.success(orderService.selectOrdersByName(pageNum, pageSize, name));
    }

    @GetMapping
    @ApiOperation("根据id查询目")
    public ResBody<Map<String, Object>> selectOrderById(@RequestParam int id) {
        return ResBody.success(orderService.selectOrdersById(id));
    }

    @PostMapping
    @ApiOperation("添加目")
    public ResBody<Integer> insertOrder(@RequestBody Orders orders) {
        if (!dbUtils.executeSql("alter table orders AUTO_INCREMENT=1")) {
            throw new BizException(ExceptionsEnum.DATABASE_FAILED);
        }
        Orders tmp = new Orders();
        BeanUtils.copyProperties(orders, tmp, "id");
        if (orderService.insertOrder(tmp) != 1) {
            throw new BizException(ExceptionsEnum.DATABASE_FAILED);
        }
        return ResBody.success();
    }

    @DeleteMapping
    @ApiOperation("根据id删除目")
    public ResBody<Integer> deleteOrderById(@RequestParam int id) {
        //检查是否仍有属关联于该目
        List<OrderFamilyVo> orderFamilies = orderFamilyService.selectByOrderId(id);
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
        //检查是否仍有科关联于该目
        List<OrderFamilyVo> orderFamilies = orderFamilyService.selectByOrderId(newOrders.getId());
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
