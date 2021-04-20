package com.rjgc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
import java.util.List;
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
    public Map<String, Object> selectAllFamilies(int pageNum, int pageSize) {
        QueryWrapper<Family> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("id");
        return getStringObjectMap(pageNum, pageSize, queryWrapper);
    }

    @Override
    public List<Family> selectFamiliesById(int id) {
        QueryWrapper<Family> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        return familyMapper.selectList(queryWrapper);
    }

    @Override
    public Map<String, Object> selectFamiliesByName(int pageNum, int pageSize, String name) {
        QueryWrapper<Family> wrapper = new QueryWrapper<>();
        wrapper.like("name", name);
        wrapper.orderByAsc("id");
        return getStringObjectMap(pageNum, pageSize, wrapper);
    }

    private Map<String, Object> getStringObjectMap(int pageNum, int pageSize, QueryWrapper<Family> wrapper) {
        IPage<Family> page = this.page(new Page<>(pageNum, pageSize), wrapper);
        HashMap<String, Object> listLongHashMap = new HashMap<>();
        listLongHashMap.put("data", page.getRecords());
        listLongHashMap.put("pages", page.getPages());
        return listLongHashMap;
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
