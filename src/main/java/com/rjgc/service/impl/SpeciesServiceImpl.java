package com.rjgc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rjgc.entity.Species;
import com.rjgc.mapper.SpeciesMapper;
import com.rjgc.service.SpeciesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhaoyunjie
 * @date 2021-04-09 10:23
 */
@Service("speciesServiceImpl")
public class SpeciesServiceImpl extends ServiceImpl<SpeciesMapper, Species> implements SpeciesService {

    @Autowired
    private SpeciesMapper speciesMapper;

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
