package com.rjgc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rjgc.dao.Family;
import com.rjgc.mapper.FamilyMapper;
import com.rjgc.service.FamilyService;
import com.rjgc.service.OrderFamilyService;
import com.rjgc.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhaoyunjie
 * @date 2021-04-08 13:28
 */
@Service("familyServiceImpl")
public class FamilyServiceImpl extends ServiceImpl<FamilyMapper, Family> implements FamilyService {

    @Autowired
    private FamilyMapper familyMapper;

    @Autowired
    @Qualifier("orderFamilyServiceImpl")
    private OrderFamilyService orderFamilyService;

    @Autowired
    @Qualifier("orderServiceImpl")
    private OrderService orderService;

    @Override
    public List<Family> selectAllFamilies(int pageNum, int pageSize) {
        QueryWrapper<Family> queryWrapper = new QueryWrapper<>();
        return this.page(new Page<>(pageNum, pageSize), queryWrapper).getRecords();
    }

    @Override
    public List<Family> selectFamiliesById(int id) {
        QueryWrapper<Family> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        return familyMapper.selectList(queryWrapper);
    }

    @Override
    public int insertFamily(int orderId, Family family) {
        if (orderFamilyService.insert(orderId, family.getId()) == 1) {
            if (familyMapper.insert(family) == 1) {
                return 1;
            }
        }
        return 0;
    }

    @Override
    public int deleteFamilyById(int id) {
        return familyMapper.deleteById(id);
    }

    @Override
    public int updateFamily(Family newFamily) {
        return familyMapper.updateById(newFamily);
    }
}
