package com.rjgc.controller;

import com.rjgc.entity.Family;
import com.rjgc.entity.Genus;
import com.rjgc.exceptions.BizException;
import com.rjgc.exceptions.ExceptionsEnum;
import com.rjgc.exceptions.ResBody;
import com.rjgc.service.FamilyService;
import com.rjgc.service.GenusService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author zhaoyunjie
 * @date 2021-04-08 16:40
 */
@RestController
@RequestMapping("/genus")
public class GenusController {

    @Autowired
    @Qualifier("genusServiceImpl")
    private GenusService genusService;

    @Autowired
    @Qualifier("familyServiceImpl")
    private FamilyService familyService;

    /**
     * 查询所有属
     *
     * @param pageNum  当前页
     * @param pageSize 需要查询的页数
     * @return 结果list
     */
    @GetMapping("all")
    @ApiOperation("查询所有属")
    public ResBody<Map<String, Object>> selectAllGenus(@RequestParam int pageNum, @RequestParam int pageSize) {
        return ResBody.success(genusService.selectAllGenuses(pageNum, pageSize));
    }

    @GetMapping("name")
    @ApiOperation("根据名称查询")
    public ResBody<Map<String, Object>> selectGenusByName(@RequestParam int pageNum, @RequestParam int pageSize, @RequestParam String name) {
        return ResBody.success(genusService.selectGenusesByName(pageNum, pageSize, name));
    }

    /**
     * 根据id查询
     *
     * @param id id
     * @return 结果
     */
    @GetMapping
    @ApiOperation("根据id查询")
    public ResBody<List<Genus>> selectGenusById(@RequestParam int id) {
        return ResBody.success(genusService.selectGenusesById(id));
    }

    /**
     * 插入属
     *
     * @param genus 属
     * @return int
     */
    @PostMapping("{familyId}")
    @ApiOperation("插入属")
    public ResBody<Integer> insertGenus(@PathVariable int familyId, @RequestBody Genus genus) {
        //检查属id是否有效
        List<Family> families = familyService.selectFamiliesById(familyId);
        if (families.isEmpty()) {
            //无效id
            throw new BizException(ExceptionsEnum.INVALID_ID);
        }
        if (genusService.insertGenus(familyId, genus) == 1) {
            return ResBody.success();
        } else {
            throw new BizException(ExceptionsEnum.DATABASE_FAILED);
        }
    }

    /**
     * 删除属
     *
     * @param id id
     * @return int
     */
    @DeleteMapping
    @ApiOperation("删除属")
    public ResBody<Integer> deleteGenusById(@RequestParam int id) {
        if (genusService.deleteGenusById(id) == 1) {
            return ResBody.success();
        } else {
            throw new BizException(ExceptionsEnum.DATABASE_FAILED);
        }
    }

    /**
     * 更新属
     *
     * @param newGenus 新属
     * @return int
     */
    @PutMapping
    @ApiOperation("更新属")
    public ResBody<Integer> updateGenus(@RequestBody Genus newGenus) {
        if (genusService.updateGenus(newGenus) == 1) {
            return ResBody.success();
        } else {
            throw new BizException(ExceptionsEnum.DATABASE_FAILED);
        }
    }
}
