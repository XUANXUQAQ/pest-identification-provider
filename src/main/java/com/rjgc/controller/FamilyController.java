package com.rjgc.controller;

import com.rjgc.Vo.FamilyGenusVo;
import com.rjgc.entity.Family;
import com.rjgc.entity.OrderFamily;
import com.rjgc.entity.Orders;
import com.rjgc.exceptions.BizException;
import com.rjgc.exceptions.ExceptionsEnum;
import com.rjgc.exceptions.ResBody;
import com.rjgc.service.*;
import com.rjgc.utils.DBUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author zhaoyunjie
 * @date 2021-04-08 13:18
 */
@RestController
@RequestMapping("/family")
public class FamilyController {

    @Qualifier("familyServiceImpl")
    @Autowired
    private FamilyService familyService;

    @Autowired
    @Qualifier("familyGenusVoServiceImpl")
    private FamilyGenusVoService familyGenusVoService;

    @Autowired
    @Qualifier("orderFamilyServiceImpl")
    private OrderFamilyService orderFamilyService;

    @Autowired
    @Qualifier("orderServiceImpl")
    private OrderService orderService;


    @Qualifier("familyVoServiceImpl")
    @Autowired
    private FamilyVoService familyVoService;

    @Autowired
    private DBUtils dbUtils;

    /**
     * 通过id查询科
     *
     * @param id id
     * @return 结果list
     */
    @GetMapping
    @ApiOperation("根据id查询科")
    public ResBody<Map<String, Object>> selectFamiliesById(@RequestParam int id) {
        return ResBody.success(familyVoService.selectFamiliesById(id));
    }

    /**
     * 查询所有科
     *
     * @param pageNum  当前查询起始页
     * @param pageSize 需要的页数
     * @return 结果list
     */
    @GetMapping("all")
    @ApiOperation("查询所有科")
    public ResBody<Map<String, Object>> selectAllFamilies(@RequestParam int pageNum, @RequestParam int pageSize) {
        return ResBody.success(familyVoService.selectAllFamilies(pageNum, pageSize));
    }

    @GetMapping("name")
    @ApiOperation("根据名称查询")
    public ResBody<Map<String, Object>> selectFamiliesByName(@RequestParam int pageNum, @RequestParam int pageSize, @RequestParam String name) {
        return ResBody.success(familyVoService.selectFamiliesByName(pageNum, pageSize, name));
    }

    /**
     * 插入科信息
     *
     * @param family 插入科
     * @return int
     */
    @PostMapping("{orderId}")
    @ApiOperation("插入科信息")
    @Transactional
    public ResBody<Integer> insertFamily(@PathVariable int orderId, @RequestBody Family family) {
        //检查目id是否有效
        List<Orders> orders = (List<Orders>) orderService.selectOrdersById(orderId).get("data");
        if (orders.isEmpty()) {
            //无效目id
            throw new BizException(ExceptionsEnum.INVALID_ID);
        }
        Family tmp = new Family();
        BeanUtils.copyProperties(family, tmp, "id");
        if (!dbUtils.executeSql("alter table family AUTO_INCREMENT=1")) {
            throw new BizException(ExceptionsEnum.DATABASE_FAILED);
        }
        if (familyService.insertFamily(orderId, tmp) != 1) {
            throw new BizException(ExceptionsEnum.DATABASE_FAILED);
        }
        if (orderFamilyService.insert(orderId, tmp.getId()) != 1) {
            throw new BizException(ExceptionsEnum.DATABASE_FAILED);
        } else {
            return ResBody.success();
        }
    }

    /**
     * 更新科信息
     *
     * @param newFamily 更新科信息
     * @return int
     */
    @PutMapping
    @ApiOperation("更新科信息")
    @Transactional
    public ResBody<Integer> updateFamily(@RequestParam int orderId, @RequestBody Family newFamily) {
        // 检查目id是否有效
        List<Orders> list = (List<Orders>) orderService.selectOrdersById(orderId).get("data");
        if (list.isEmpty()) {
            throw new BizException(ExceptionsEnum.INVALID_ID);
        }
        OrderFamily orderFamily = new OrderFamily();
        orderFamily.setOrderId(orderId);
        orderFamily.setFamilyId(newFamily.getId());
        if (familyService.updateFamily(newFamily) != 1) {
            throw new BizException(ExceptionsEnum.DATABASE_FAILED);
        }
        if (orderFamilyService.updateByFamilyId(orderId, newFamily.getId()) != 1) {
            throw new BizException(ExceptionsEnum.DATABASE_FAILED);
        }
        return ResBody.success();
    }

    /**
     * 根据id删除科
     *
     * @param id id
     * @return int
     */
    @DeleteMapping
    @ApiOperation("根据id删除科")
    @Transactional
    public ResBody<Integer> deleteFamilyById(@RequestParam int id) {
        //检查是否仍有属关联于该科
        List<FamilyGenusVo> familyGenus = familyGenusVoService.selectByFamilyId(id);
        if (familyGenus.isEmpty()) {
            if (orderFamilyService.deleteByFamilyId(id) != 1) {
                throw new BizException(ExceptionsEnum.DATABASE_FAILED);
            }
            if (familyService.deleteFamilyById(id) != 1) {
                throw new BizException(ExceptionsEnum.DATABASE_FAILED);
            }
        } else {
            //id仍被使用
            throw new BizException(ExceptionsEnum.IN_USE);
        }
        return ResBody.success();
    }
}
