package com.rjgc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rjgc.Vo.FamilyVo;

import java.util.Map;

public interface FamilyVoService extends IService<FamilyVo> {
    Map<String, Object> selectAllFamilies(int pageNum, int pageSize);

    Map<String, Object> selectFamiliesById(int id);

    Map<String, Object> selectFamiliesByName(int pageNum, int pageSize, String name);
}
