package com.rjgc.controller;

import com.rjgc.dao.Family;
import com.rjgc.dao.FamilyGenus;
import com.rjgc.dao.Orders;
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
     * @param pageNum 当前查询起始页
     * @param pageSize 需要的页数
     * @return 结果list
     */
    @GetMapping("all")
    @ApiOperation("查询所有科")
    public List<Family> selectAllFamilies(@RequestParam int pageNum, @RequestParam int pageSize) {
        return familyService.selectAllFamilies(pageNum, pageSize);
    }

    /**
     * 通过id查询科
     * @param id id
     * @return 结果list
     */
    @GetMapping
    @ApiOperation("根据id查询科")
    public List<Family> selectFamiliesById(@RequestParam int id) {
        return familyService.selectFamiliesById(id);
    }

    /**
     * 插入科信息
     * @param family 插入科
     * @return int
     */
    @PostMapping("{orderId}")
    @ApiOperation("插入科信息")
    public int insertFamily(@PathVariable int orderId, @RequestBody Family family) {
        //检查目id是否有效
        List<Orders> orders = orderService.selectOrdersById(orderId);
        if (orders.isEmpty()) {
            //todo 抛出错误
            return 0;
        }
        Family tmp = new Family();
        String[] ignored = {"id"};
        BeanUtils.copyProperties(family, tmp, ignored);
        return familyService.insertFamily(orderId, family);
    }

    /**
     * 更新科信息
     * @param newFamily 更新科信息
     * @return int
     */
    @PutMapping
    @ApiOperation("更新科信息")
    public int updateFamily(@RequestBody Family newFamily) {
        //检查是否仍有种关联于该属
        List<FamilyGenus> familyGenus = familyGenusService.selectByFamilyId(newFamily.getId());
        if (familyGenus.isEmpty()) {
            return familyService.updateFamily(newFamily);
        } else {
            //todo 抛出错误
            return 0;
        }
    }

    /**
     * 根据id删除科
     * @param id id
     * @return int
     */
    @DeleteMapping
    @ApiOperation("根据id删除科")
    public int deleteFamilyById(@RequestParam int id) {
        //检查是否仍有种关联于该属
        List<FamilyGenus> familyGenus = familyGenusService.selectByFamilyId(id);
        if (familyGenus.isEmpty()) {
            return familyService.deleteFamilyById(id);
        } else {
            //todo 抛出错误
            return 0;
        }
    }
}
