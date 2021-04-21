package com.rjgc.controller;

import com.rjgc.entity.Species;
import com.rjgc.exceptions.BizException;
import com.rjgc.exceptions.ExceptionsEnum;
import com.rjgc.exceptions.ResBody;
import com.rjgc.service.GenusService;
import com.rjgc.service.SpeciesService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhaoyunjie
 * @date 2021-04-09 10:30
 */
@RestController
@RequestMapping("/species")
public class SpeciesController {

    @Autowired
    @Qualifier("speciesServiceImpl")
    private SpeciesService speciesService;

    @Autowired
    @Qualifier("genusServiceImpl")
    private GenusService genusService;

    /**
     * 插入种
     *
     * @param species 种
     * @return int
     */
    @PostMapping
    @ApiOperation("插入种")
    public ResBody<Integer> insertSpecies(@RequestBody Species species) {
        if (genusService.selectGenusesById(species.getGenusId()).isEmpty()) {
            throw new BizException(ExceptionsEnum.INVALID_ID);
        }
        if (speciesService.insertSpecies(species) == 1) {
            return ResBody.success();
        } else {
            throw new BizException(ExceptionsEnum.DATABASE_FAILED);
        }
    }

    /**
     * 删除种
     *
     * @param id 种id
     * @return int
     */
    @DeleteMapping
    @ApiOperation("根据id删除种")
    public ResBody<Integer> deleteSpeciesById(@RequestParam int id) {
        if (speciesService.deleteSpeciesById(id) == 1) {
            return ResBody.success();
        } else {
            throw new BizException(ExceptionsEnum.DATABASE_FAILED);
        }
    }

    /**
     * 更新种
     *
     * @param species 种
     * @return int
     */
    @PutMapping
    @ApiOperation("更新种")
    public ResBody<Integer> updateSpecies(@RequestBody Species species) {
        if (speciesService.updateSpecies(species) == 1) {
            return ResBody.success();
        } else {
            throw new BizException(ExceptionsEnum.DATABASE_FAILED);
        }
    }
}
