package com.rjgc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rjgc.entity.Family;
import com.rjgc.mapper.FamilyMapper;
import com.rjgc.service.FamilyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author zhaoyunjie
 * @date 2021-04-08 13:28
 */
@Service("familyServiceImpl")
public class FamilyServiceImpl extends ServiceImpl<FamilyMapper, Family> implements FamilyService {

    @Autowired
    private FamilyMapper familyMapper;

    @Override
    @Transactional
    public int insertFamily(int orderId, Family family) {
        return familyMapper.insert(family);
    }

    @Override
    @Transactional
    public int deleteFamilyById(int id) {
        return familyMapper.deleteById(id);
    }

    @Override
    public int updateFamily(Family newFamily) {
        return familyMapper.updateById(newFamily);
    }
}
