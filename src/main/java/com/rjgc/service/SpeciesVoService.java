package com.rjgc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rjgc.entity.SpeciesVo;

import java.util.List;
import java.util.Map;

/**
 * @author zhaoyunjie
 * @date 2021-04-09 17:01
 */
public interface SpeciesVoService extends IService<SpeciesVo> {

    List<SpeciesVo> selectSpeciesVoById(int id);

    Map<List<SpeciesVo>, Long> selectAllSpeciesVo(int pageNum, int pageSize);

    List<SpeciesVo> selectSpeciesVoByCode(String code);

    Map<List<SpeciesVo>, Long> selectSpeciesByName(int pageNum, int pageSize, String name);
}