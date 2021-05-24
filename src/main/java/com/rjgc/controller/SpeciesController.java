package com.rjgc.controller;

import com.rjgc.entity.Species;
import com.rjgc.exceptions.BizException;
import com.rjgc.exceptions.ExceptionsEnum;
import com.rjgc.exceptions.ResBody;
import com.rjgc.service.GenusVoService;
import com.rjgc.service.SpeciesService;
import com.rjgc.service.SpeciesVoService;
import com.rjgc.utils.CASUtils;
import com.rjgc.utils.DBUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

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
    @Qualifier("genusVoServiceImpl")
    private GenusVoService genusVoService;

    @Autowired
    @Qualifier("speciesVoServiceImpl")
    private SpeciesVoService speciesVoService;

    @Autowired
    private DBUtils dbUtils;

    @Autowired
    private CASUtils casUtils;

    @GetMapping
    @ApiOperation("根据id查询种")
    public ResBody<Map<String, Object>> selectSpeciesVoById(@RequestParam int id) {
        return ResBody.success(speciesVoService.selectSpeciesVoById(id));
    }

    @GetMapping("{code}")
    @ApiOperation("根据code查询种")
    public ResBody<Map<String, Object>> selectSpeciesByCode(@PathVariable String code) {
        return ResBody.success(speciesVoService.selectSpeciesVoByCode(code));
    }

    @GetMapping("name")
    @ApiOperation("根据name查询")
    public ResBody<Map<String, Object>> selectSpeciesByName(@RequestParam int pageNum, @RequestParam int pageSize, @RequestParam String name) {
        return ResBody.success(speciesVoService.selectSpeciesVoByName(pageNum, pageSize, name));
    }

    /**
     * 查询所有种
     *
     * @param pageNum  当前页
     * @param pageSize 页数
     * @return list
     */
    @GetMapping("all")
    @ApiOperation("查询所有种")
    public ResBody<Map<String, Object>> selectAllSpecies(@RequestParam int pageNum, @RequestParam int pageSize) {
        return ResBody.success(speciesVoService.selectAllSpeciesVo(pageNum, pageSize));
    }

    /**
     * 插入种
     *
     * @param species 种
     * @return int
     */
    @PostMapping
    @ApiOperation("插入种")
    public ResBody<Integer> insertSpecies(@RequestBody Species species) {
        Map<String, Object> map = genusVoService.selectGenusesById(species.getGenusId());
        List<Object> list = (List<Object>) map.get("data");
        if (list.isEmpty()) {
            throw new BizException(ExceptionsEnum.INVALID_ID);
        }
        if (!dbUtils.executeSql("alter table species AUTO_INCREMENT=1")) {
            throw new BizException(ExceptionsEnum.DATABASE_FAILED);
        }
        Species tmp = new Species();
        BeanUtils.copyProperties(species, tmp, "id");
        if (speciesService.insertSpecies(tmp) != 1) {
            throw new BizException(ExceptionsEnum.DATABASE_FAILED);
        }
        return ResBody.success();
    }

    /**
     * 删除种
     *
     * @param id 种id
     * @return int
     */
    @DeleteMapping
    @ApiOperation("根据id删除种")
    public ResBody<Integer> deleteSpecieSsById(@RequestParam int id) {
        Timestamp time = casUtils.getUpdateTime("species", id);
        if (casUtils.compareAndSet(time, "species", id)) {
            if (speciesService.deleteSpeciesById(id) == 1) {
                return ResBody.success();
            } else {
                throw new BizException(ExceptionsEnum.DATABASE_FAILED);
            }
        } else {
            throw new BizException(ExceptionsEnum.OTHER_USER_IN_USE);
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
        Timestamp time = casUtils.getUpdateTime("species", species.getId());
        if (casUtils.compareAndSet(time, "species", species.getId())) {
            if (speciesService.updateSpecies(species) == 1) {
                return ResBody.success();
            } else {
                throw new BizException(ExceptionsEnum.DATABASE_FAILED);
            }
        } else {
            throw new BizException(ExceptionsEnum.OTHER_USER_IN_USE);
        }
    }
}
