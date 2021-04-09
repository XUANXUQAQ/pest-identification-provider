package com.rjgc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rjgc.entity.Genus;
import com.rjgc.mapper.GenusMapper;
import com.rjgc.service.FamilyGenusService;
import com.rjgc.service.GenusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhaoyunjie
 * @date 2021-04-08 16:36
 */
@Service("genusServiceImpl")
public class GenusServiceImpl extends ServiceImpl<GenusMapper, Genus> implements GenusService {

    @Autowired
    private GenusMapper genusMapper;

    @Autowired
    @Qualifier("familyGenusServiceImpl")
    private FamilyGenusService familyGenusService;

    @Override
    public List<Genus> selectAllGenuses(int pageNum, int pageSize) {
        QueryWrapper<Genus> genusQueryWrapper = new QueryWrapper<>();
        return this.page(new Page<>(pageNum, pageSize), genusQueryWrapper).getRecords();
    }

    @Override
    public List<Genus> selectGenusesById(int id) {
        QueryWrapper<Genus> genusQueryWrapper = new QueryWrapper<>();
        return genusMapper.selectList(genusQueryWrapper);
    }

    @Override
    public int insertGenus(int familyId, Genus genus) {
        if (familyGenusService.insert(familyId, genus.getId()) == 1) {
            if (genusMapper.insert(genus) == 1) {
                return 1;
            }
        }
        return 0;
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
