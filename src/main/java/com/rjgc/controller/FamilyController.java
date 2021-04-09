package com.rjgc.controller;

import com.rjgc.entity.Family;
import com.rjgc.entity.FamilyGenus;
import com.rjgc.entity.Orders;
import com.rjgc.exceptions.BizException;
import com.rjgc.exceptions.ExceptionsEnum;
import com.rjgc.exceptions.ResBody;
import com.rjgc.service.FamilyGenusService;
import com.rjgc.service.FamilyService;
import com.rjgc.service.OrderService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @Qualifier("familyGenusServiceImpl")
    private FamilyGenusService familyGenusService;

    @Autowired
    @Qualifier("orderServiceImpl")
    private OrderService orderService;

    /**
     * 查询所有科
     *
     * @param pageNum  当前查询起始页
     * @param pageSize 需要的页数
     * @return 结果list
     */
    @GetMapping("all")
    @ApiOperation("查询所有科")
    public ResBody<List<Family>> selectAllFamilies(@RequestParam int pageNum, @RequestParam int pageSize) {
        return ResBody.success(familyService.selectAllFamilies(pageNum, pageSize));
    }

    /**
     * 通过id查询科
     *
     * @param id id
     * @return 结果list
     */
    @GetMapping
    @ApiOperation("根据id查询科")
    public ResBody<List<Family>> selectFamiliesById(@RequestParam int id) {
        return ResBody.success(familyService.selectFamiliesById(id));
    }

    /**
     * 插入科信息
     *
     * @param family 插入科
     * @return int
     */
    @PostMapping("{orderId}")
    @ApiOperation("插入科信息")
    public ResBody<Integer> insertFamily(@PathVariable int orderId, @RequestBody Family family) {
        //检查目id是否有效
        List<Orders> orders = orderService.selectOrdersById(orderId);
        if (orders.isEmpty()) {
            //无效目id
            throw new BizException(ExceptionsEnum.INVALID_ID);
        }
        Family tmp = new Family();
        String[] ignored = {"id"};
        BeanUtils.copyProperties(family, tmp, ignored);
        if (familyService.insertFamily(orderId, family) == 1) {
            return ResBody.success();
        } else {
            throw new BizException(ExceptionsEnum.DATABASE_FAILED);
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
    public ResBody<Integer> updateFamily(@RequestBody Family newFamily) {
        //检查是否仍有种关联于该属
        List<FamilyGenus> familyGenus = familyGenusService.selectByFamilyId(newFamily.getId());
        if (familyGenus.isEmpty()) {
            if (familyService.updateFamily(newFamily) == 1) {
                return ResBody.success();
            } else {
                throw new BizException(ExceptionsEnum.DATABASE_FAILED);
            }
        } else {
            //id仍被使用
            throw new BizException(ExceptionsEnum.IN_USE);
        }
    }

    /**
     * 根据id删除科
     *
     * @param id id
     * @return int
     */
    @DeleteMapping
    @ApiOperation("根据id删除科")
    public ResBody<Integer> deleteFamilyById(@RequestParam int id) {
        //检查是否仍有种关联于该属
        List<FamilyGenus> familyGenus = familyGenusService.selectByFamilyId(id);
        if (familyGenus.isEmpty()) {
            if (familyService.deleteFamilyById(id) == 1) {
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
