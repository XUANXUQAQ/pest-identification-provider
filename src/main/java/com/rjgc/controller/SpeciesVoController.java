package com.rjgc.controller;

import com.rjgc.entity.SpeciesVo;
import com.rjgc.exceptions.ResBody;
import com.rjgc.service.SpeciesVoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zhaoyunjie
 * @date 2021-04-09 17:07
 */
@RestController
@RequestMapping("/species")
public class SpeciesVoController {

    @Autowired
    @Qualifier("speciesVoServiceImpl")
    private SpeciesVoService speciesVoService;

    @GetMapping
    @ApiOperation("根据id查询种")
    public ResBody<List<SpeciesVo>> selectSpeciesVoById(@RequestParam int id) {
        return ResBody.success(speciesVoService.selectSpeciesVoById(id));
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
    public ResBody<List<SpeciesVo>> selectAllSpecies(@RequestParam int pageNum, @RequestParam int pageSize) {
        return ResBody.success(speciesVoService.selectAllSpeciesVo(pageNum, pageSize));
    }
}
