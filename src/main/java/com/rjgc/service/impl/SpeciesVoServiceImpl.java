package com.rjgc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rjgc.entity.SpeciesVo;
import com.rjgc.mapper.SpeciesVoMapper;
import com.rjgc.service.SpeciesVoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhaoyunjie
 * @date 2021-04-09 17:53
 */
@Service("speciesVoServiceImpl")
public class SpeciesVoServiceImpl extends ServiceImpl<SpeciesVoMapper, SpeciesVo> implements SpeciesVoService {

    @Autowired
    private SpeciesVoMapper speciesVoMapper;

    @Override
    public List<SpeciesVo> selectSpeciesVoById(int id) {
        return speciesVoMapper.selectSpeciesById(id);
    }

    @Override
    public List<SpeciesVo> selectAllSpeciesVo(int pageNum, int pageSize) {
        return speciesVoMapper.selectAllSpecies(pageNum, pageSize);
    }
}
