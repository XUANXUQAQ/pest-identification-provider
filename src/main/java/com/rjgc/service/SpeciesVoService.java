package com.rjgc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rjgc.entity.SpeciesVo;

import java.util.List;

/**
 * @author zhaoyunjie
 * @date 2021-04-09 17:01
 */
public interface SpeciesVoService extends IService<SpeciesVo> {

    List<SpeciesVo> selectSpeciesVoById(int id);

    List<SpeciesVo> selectAllSpeciesVo(int pageNum, int pageSize);
}
