package com.rjgc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rjgc.entity.Species;

/**
 * @author zhaoyunjie
 * @date 2021-04-09 10:21
 */
public interface SpeciesService extends IService<Species> {

    int insertSpecies(Species species);

    int deleteSpeciesById(int id);

    int updateSpecies(Species species);
}
