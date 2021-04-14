package com.rjgc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rjgc.entity.Genus;
import com.rjgc.mapper.GenusMapper;
import com.rjgc.service.FamilyGenusService;
import com.rjgc.service.GenusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public Map<List<Genus>, Long> selectAllGenuses(int pageNum, int pageSize) {
        QueryWrapper<Genus> genusQueryWrapper = new QueryWrapper<>();
        return getListPagesMap(pageNum, pageSize, genusQueryWrapper);
    }

    @Override
    public List<Genus> selectGenusesById(int id) {
        QueryWrapper<Genus> genusQueryWrapper = new QueryWrapper<>();
        return genusMapper.selectList(genusQueryWrapper);
    }

    @Override
    public Map<List<Genus>, Long> selectGenusesByName(int pageNum, int pageSize, String name) {
        QueryWrapper<Genus> wrapper = new QueryWrapper<>();
        wrapper.like("name", name);
        return getListPagesMap(pageNum, pageSize, wrapper);
    }

    private Map<List<Genus>, Long> getListPagesMap(int pageNum, int pageSize, QueryWrapper<Genus> wrapper) {
        IPage<Genus> page = this.page(new Page<>(pageNum, pageSize), wrapper);
        HashMap<List<Genus>, Long> map = new HashMap<>();
        map.put(page.getRecords(), page.getPages());
        return map;
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
