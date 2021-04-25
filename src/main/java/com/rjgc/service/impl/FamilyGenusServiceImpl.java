package com.rjgc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rjgc.entity.FamilyGenus;
import com.rjgc.mapper.FamilyGenusMapper;
import com.rjgc.service.FamilyGenusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhaoyunjie
 * @date 2021-04-08 16:52
 */
@Service("familyGenusServiceImpl")
public class FamilyGenusServiceImpl extends ServiceImpl<FamilyGenusMapper, FamilyGenus> implements FamilyGenusService {

    @Autowired
    private FamilyGenusMapper familyGenusMapper;

    @Override
    public int insert(int familyId, int genusId) {
        FamilyGenus familyGenus = new FamilyGenus();
        familyGenus.setFamilyId(familyId);
        familyGenus.setGenusId(genusId);
        return familyGenusMapper.insert(familyGenus);
    }

    @Override
    public int updateByGenusId(int familyId, int genusId) {
        FamilyGenus familyGenus = new FamilyGenus();
        familyGenus.setGenusId(genusId);
        familyGenus.setFamilyId(familyId);
        return familyGenusMapper.updateById(familyGenus);
    }
}
