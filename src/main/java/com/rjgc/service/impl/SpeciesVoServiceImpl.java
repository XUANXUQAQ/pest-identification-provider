package com.rjgc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rjgc.Vo.SpeciesVo;
import com.rjgc.configs.DatabaseIdConfig;
import com.rjgc.entity.Species;
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

    @Autowired
    private DatabaseIdConfig databaseIdConfig;

    @Override
    public Map<String, Object> selectSpeciesVoById(int id) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("data", speciesVoMapper.selectSpeciesById(id));
        map.put("pages", 1);
        return map;
    }

    @Override
    public Map<String, Object> selectAllSpeciesVo(int pageNum, int pageSize) {
        pageNum = (pageNum - 1) * pageSize + 1;
        if (databaseIdConfig.isIdStartFromZero()) {
            pageNum--;
        }
        List<SpeciesVo> speciesVos = speciesVoMapper.selectAllSpecies(pageNum, pageSize);
        QueryWrapper<Species> wrapper = new QueryWrapper<>();
        Integer count = speciesMapper.selectCount(wrapper);
        HashMap<String, Object> map = new HashMap<>();
        map.put("data", speciesVos);
        map.put("pages", (long) ((count + pageSize - 1) / pageSize));
        return map;
    }

    @Override
    public Map<String, Object> selectSpeciesVoByCode(String code) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("data", speciesVoMapper.selectSpeciesByCode(code));
        map.put("pages", 1);
        return map;
    }

    @Override
    public Map<String, Object> selectSpeciesVoByName(int pageNum, int pageSize, String name) {
        pageNum = (pageNum - 1) * pageSize + 1;
        if (databaseIdConfig.isIdStartFromZero()) {
            pageNum--;
        }
        List<SpeciesVo> speciesVos = speciesVoMapper.selectSpeciesByName(pageNum, pageSize, name);
        QueryWrapper<Species> wrapper = new QueryWrapper<>();
        wrapper.like("name", name);
        Integer count = speciesMapper.selectCount(wrapper);
        HashMap<String, Object> map = new HashMap<>();
        map.put("data", speciesVos);
        map.put("pages", (long) ((count + pageSize - 1) / pageSize));
        return map;
    }

    @Override
    public Map<String, Object> selectSpeciesVoByGenusId(int genusId) {
        List<SpeciesVo> speciesVos = speciesVoMapper.selectSpeciesByGenusId(genusId);
        HashMap<String, Object> map = new HashMap<>();
        map.put("data", speciesVos);
        map.put("pages", 1);
        return map;
    }
}
