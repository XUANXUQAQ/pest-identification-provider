package com.rjgc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rjgc.Vo.SpeciesVo;

import java.util.Map;

/**
 * @author zhaoyunjie
 * @date 2021-04-09 17:01
 */
public interface SpeciesVoService extends IService<SpeciesVo> {

    Map<String, Object> selectSpeciesVoById(int id);

    Map<String, Object> selectAllSpeciesVo(int pageNum, int pageSize);

    Map<String, Object> selectSpeciesVoByCode(String code);

    Map<String, Object> selectSpeciesVoByName(int pageNum, int pageSize, String name);
}
