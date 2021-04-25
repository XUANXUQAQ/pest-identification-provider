package com.rjgc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rjgc.entity.Family;
import com.rjgc.mapper.FamilyMapper;
import com.rjgc.mapper.OrderFamilyMapper;
import com.rjgc.service.FamilyService;
import com.rjgc.service.OrderFamilyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhaoyunjie
 * @date 2021-04-08 13:28
 */
@Service("familyServiceImpl")
public class FamilyServiceImpl extends ServiceImpl<FamilyMapper, Family> implements FamilyService {

    @Autowired
    private FamilyMapper familyMapper;

    @Autowired
    private OrderFamilyMapper orderFamilyMapper;

    @Autowired
    @Qualifier("orderFamilyServiceImpl")
    private OrderFamilyService orderFamilyService;

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
        Map<String, Object> map = new HashMap<>();
        map.put("family_id", id);
        if (orderFamilyMapper.deleteByMap(map) == 1) {
            if (familyMapper.deleteById(id) == 1) {
                return 1;
            }
        }
        return 0;
    }

    @Override
    public int updateFamily(Family newFamily) {
        return familyMapper.updateById(newFamily);
    }
}
