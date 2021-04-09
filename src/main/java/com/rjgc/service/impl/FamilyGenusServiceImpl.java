package com.rjgc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rjgc.entity.FamilyGenus;
import com.rjgc.mapper.FamilyGenusMapper;
import com.rjgc.service.FamilyGenusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhaoyunjie
 * @date 2021-04-08 16:52
 */
@Service("familyGenusServiceImpl")
public class FamilyGenusServiceImpl extends ServiceImpl<FamilyGenusMapper, FamilyGenus> implements FamilyGenusService {

    @Autowired
    private FamilyGenusMapper familyGenusMapper;

    @Override
    public List<FamilyGenus> selectByFamilyId(int familyId) {
        QueryWrapper<FamilyGenus> wrapper = new QueryWrapper<>();
        wrapper.eq("family_id", familyId);
        return familyGenusMapper.selectList(wrapper);
    }

    @Override
    public int insert(int familyId, int genusId) {
        FamilyGenus familyGenus = new FamilyGenus();
        familyGenus.setFamilyId(familyId);
        familyGenus.setGenusId(genusId);
        return familyGenusMapper.insert(familyGenus);
    }
}
