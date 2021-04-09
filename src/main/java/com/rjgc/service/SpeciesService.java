package com.rjgc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rjgc.entity.Species;

import java.util.List;

/**
 * @author zhaoyunjie
 * @date 2021-04-09 10:21
 */
public interface SpeciesService extends IService<Species> {

    List<Species> selectAllSpecies(int pageNum, int pageSize);

    List<Species> selectSpeciesById(int id);

    int insertSpecies(Species species);

    int deleteSpeciesById(int id);

    int updateSpecies(Species species);
}
