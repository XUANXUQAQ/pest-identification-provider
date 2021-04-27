package com.rjgc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rjgc.Vo.GenusVo;
import com.rjgc.configs.DatabaseIdConfig;
import com.rjgc.entity.Genus;
import com.rjgc.mapper.GenusMapper;
import com.rjgc.mapper.GenusVoMapper;
import com.rjgc.service.GenusVoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("genusVoServiceImpl")
public class GenusVoServiceImpl extends ServiceImpl<GenusVoMapper, GenusVo> implements GenusVoService {

    @Autowired
    private GenusVoMapper genusVoMapper;

    @Autowired
    private GenusMapper genusMapper;

    @Autowired
    private DatabaseIdConfig databaseIdConfig;

    @Override
    public Map<String, Object> selectAllGenuses(int pageNum, int pageSize) {
        pageNum = (pageNum - 1) * pageSize + 1;
        if (databaseIdConfig.isIdStartFromZero()) {
            pageNum--;
        }
        List<GenusVo> genusVos = genusVoMapper.selectAllGenus(pageNum, pageSize);
        HashMap<String, Object> map = new HashMap<>();
        QueryWrapper<Genus> wrapper = new QueryWrapper<>();
        Integer count = genusMapper.selectCount(wrapper);
        map.put("pages", (long) ((count + pageSize - 1) / pageSize));
        map.put("data", genusVos);
        return map;
    }

    @Override
    public Map<String, Object> selectGenusesById(int id) {
        List<GenusVo> genusVos = genusVoMapper.selectGenusById(id);
        HashMap<String, Object> map = new HashMap<>();
        map.put("pages", 1);
        map.put("data", genusVos);
        return map;
    }

    @Override
    public Map<String, Object> selectGenusesByName(int pageNum, int pageSize, String name) {
        pageNum = (pageNum - 1) * pageSize + 1;
        if (databaseIdConfig.isIdStartFromZero()) {
            pageNum--;
        }
        List<GenusVo> genusVos = genusVoMapper.selectGenusByName(pageNum, pageSize, name);
        HashMap<String, Object> map = new HashMap<>();
        map.put("data", genusVos);
        QueryWrapper<Genus> wrapper = new QueryWrapper<>();
        wrapper.like("name", name);
        Integer count = genusMapper.selectCount(wrapper);
        map.put("pages", (long) ((count + pageSize - 1) / pageSize));
        return map;
    }
}
