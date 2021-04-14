package com.rjgc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rjgc.entity.Species;
import com.rjgc.entity.SpeciesVo;
import com.rjgc.mapper.SpeciesMapper;
import com.rjgc.mapper.SpeciesVoMapper;
import com.rjgc.service.SpeciesVoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhaoyunjie
 * @date 2021-04-09 17:53
 */
@Service("speciesVoServiceImpl")
public class SpeciesVoServiceImpl extends ServiceImpl<SpeciesVoMapper, SpeciesVo> implements SpeciesVoService {

    @Autowired
    private SpeciesVoMapper speciesVoMapper;

    @Autowired
    private SpeciesMapper speciesMapper;

    @Override
    public List<SpeciesVo> selectSpeciesVoById(int id) {
        return speciesVoMapper.selectSpeciesById(id);
    }

    @Override
    public Map<List<SpeciesVo>, Long> selectAllSpeciesVo(int pageNum, int pageSize) {
        List<SpeciesVo> speciesVos = speciesVoMapper.selectAllSpecies(pageNum, pageSize);
        QueryWrapper<Species> wrapper = new QueryWrapper<>();
        Integer count = speciesMapper.selectCount(wrapper);
        HashMap<List<SpeciesVo>, Long> map = new HashMap<>();
        map.put(speciesVos, (long) ((count + pageSize - 1) / pageSize));
        return map;
    }

    @Override
    public List<SpeciesVo> selectSpeciesVoByCode(String code) {
        return speciesVoMapper.selectSpeciesByCode(code);
    }

    @Override
    public Map<List<SpeciesVo>, Long> selectSpeciesByName(int pageNum, int pageSize, String name) {
        QueryWrapper<SpeciesVo> wrapper = new QueryWrapper<>();
        wrapper.like("name", name);
        IPage<SpeciesVo> page = this.page(new Page<>(pageNum, pageSize), wrapper);
        HashMap<List<SpeciesVo>, Long> map = new HashMap<>();
        map.put(page.getRecords(), page.getPages());
        return map;
    }
}
