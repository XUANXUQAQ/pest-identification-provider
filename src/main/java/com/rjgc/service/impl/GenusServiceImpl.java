package com.rjgc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rjgc.entity.Genus;
import com.rjgc.mapper.FamilyGenusMapper;
import com.rjgc.mapper.GenusMapper;
import com.rjgc.service.FamilyGenusService;
import com.rjgc.service.GenusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @author zhaoyunjie
 * @date 2021-04-08 16:36
 */
@Service("genusServiceImpl")
public class GenusServiceImpl extends ServiceImpl<GenusMapper, Genus> implements GenusService {

    @Autowired
    private GenusMapper genusMapper;

    @Autowired
    private FamilyGenusMapper familyGenusMapper;

    @Autowired
    @Qualifier("familyGenusServiceImpl")
    private FamilyGenusService familyGenusService;

    @Override
    public int insertGenus(int familyId, Genus genus) {
        return genusMapper.insert(genus);
    }

    @Override
    public int deleteGenusById(int id) {
        return genusMapper.deleteById(id);
    }

    @Override
    public int updateGenus(Genus newGenus) {
        return genusMapper.updateById(newGenus);
    }
}
