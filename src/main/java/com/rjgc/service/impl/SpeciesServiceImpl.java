package com.rjgc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rjgc.entity.Species;
import com.rjgc.mapper.SpeciesMapper;
import com.rjgc.service.SpeciesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhaoyunjie
 * @date 2021-04-09 10:23
 */
@Service("speciesServiceImpl")
public class SpeciesServiceImpl extends ServiceImpl<SpeciesMapper, Species> implements SpeciesService {

    @Autowired
    private SpeciesMapper speciesMapper;

    /**
     * 查询所有种
     *
     * @param pageNum  当前页数
     * @param pageSize 需要查询的页数
     * @return List
     */
    @Override
    public List<Species> selectAllSpecies(int pageNum, int pageSize) {
        QueryWrapper<Species> wrapper = new QueryWrapper<>();
        return this.page(new Page<>(pageNum, pageSize), wrapper).getRecords();
    }

    /**
     * 根据id查询种
     *
     * @param id id
     * @return list
     */
    @Override
    public List<Species> selectSpeciesById(int id) {
        QueryWrapper<Species> speciesQueryWrapper = new QueryWrapper<>();
        return speciesMapper.selectList(speciesQueryWrapper);
    }

    @Override
    public int insertSpecies(Species species) {
        return speciesMapper.insert(species);
    }

    @Override
    public int deleteSpeciesById(int id) {
        return speciesMapper.deleteById(id);
    }

    @Override
    public int updateSpecies(Species species) {
        return speciesMapper.updateById(species);
    }
}
