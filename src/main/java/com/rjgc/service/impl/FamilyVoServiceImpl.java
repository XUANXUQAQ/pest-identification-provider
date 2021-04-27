package com.rjgc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rjgc.Vo.FamilyVo;
import com.rjgc.configs.DatabaseIdConfig;
import com.rjgc.entity.Family;
import com.rjgc.mapper.FamilyMapper;
import com.rjgc.mapper.FamilyVoMapper;
import com.rjgc.service.FamilyVoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("familyVoServiceImpl")
public class FamilyVoServiceImpl extends ServiceImpl<FamilyVoMapper, FamilyVo> implements FamilyVoService {

    @Autowired
    private FamilyVoMapper familyVoMapper;

    @Autowired
    private FamilyMapper familyMapper;

    @Autowired
    private DatabaseIdConfig databaseIdConfig;

    @Override
    public Map<String, Object> selectAllFamilies(int pageNum, int pageSize) {
        pageNum = (pageNum - 1) * pageSize + 1;
        if (databaseIdConfig.isIdStartFromZero()) {
            pageNum--;
        }
        List<FamilyVo> familyVos = familyVoMapper.selectAllFamilies(pageNum, pageSize);
        HashMap<String, Object> map = new HashMap<>();
        QueryWrapper<Family> wrapper = new QueryWrapper<>();
        Integer count = familyMapper.selectCount(wrapper);
        map.put("pages", (long) ((count + pageSize - 1) / pageSize));
        map.put("data", familyVos);
        return map;
    }

    @Override
    public Map<String, Object> selectFamiliesById(int id) {
        List<FamilyVo> familyVos = familyVoMapper.selectFamilyById(id);
        HashMap<String, Object> map = new HashMap<>();
        map.put("pages", 1);
        map.put("data", familyVos);
        return map;
    }

    @Override
    public Map<String, Object> selectFamiliesByName(int pageNum, int pageSize, String name) {
        pageNum = (pageNum - 1) * pageSize + 1;
        if (databaseIdConfig.isIdStartFromZero()) {
            pageNum--;
        }
        List<FamilyVo> familyVos = familyVoMapper.selectFamilyByName(pageNum, pageSize, name);
        HashMap<String, Object> map = new HashMap<>();
        QueryWrapper<Family> wrapper = new QueryWrapper<>();
        wrapper.like("name", name);
        Integer count = familyMapper.selectCount(wrapper);
        map.put("pages", (long) ((count + pageSize - 1) / pageSize));
        map.put("data", familyVos);
        return map;
    }
}
