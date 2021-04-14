package com.rjgc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rjgc.entity.Family;
import com.rjgc.mapper.FamilyMapper;
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
    @Qualifier("orderFamilyServiceImpl")
    private OrderFamilyService orderFamilyService;

    @Override
    public Map<List<Family>, Long> selectAllFamilies(int pageNum, int pageSize) {
        QueryWrapper<Family> queryWrapper = new QueryWrapper<>();
        return getListPagesMap(pageNum, pageSize, queryWrapper);
    }

    @Override
    public List<Family> selectFamiliesById(int id) {
        QueryWrapper<Family> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        return familyMapper.selectList(queryWrapper);
    }

    @Override
    public Map<List<Family>, Long> selectFamiliesByName(int pageNum, int pageSize, String name) {
        QueryWrapper<Family> wrapper = new QueryWrapper<>();
        wrapper.like("name", name);
        return getListPagesMap(pageNum, pageSize, wrapper);
    }

    private Map<List<Family>, Long> getListPagesMap(int pageNum, int pageSize, QueryWrapper<Family> wrapper) {
        IPage<Family> page = this.page(new Page<>(pageNum, pageSize), wrapper);
        HashMap<List<Family>, Long> listLongHashMap = new HashMap<>();
        listLongHashMap.put(page.getRecords(), page.getPages());
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
        return familyMapper.deleteById(id);
    }

    @Override
    public int updateFamily(Family newFamily) {
        return familyMapper.updateById(newFamily);
    }
}
