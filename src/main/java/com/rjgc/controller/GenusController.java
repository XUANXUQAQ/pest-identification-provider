package com.rjgc.controller;

import com.rjgc.dao.Family;
import com.rjgc.dao.Genus;
import com.rjgc.service.FamilyService;
import com.rjgc.service.GenusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
     * @param pageNum 当前页
     * @param pageSize 需要查询的页数
     * @return 结果list
     */
    @GetMapping("all")
    public List<Genus> selectAllGenus(@RequestParam int pageNum, @RequestParam int pageSize) {
        return genusService.selectAllGenuses(pageNum, pageSize);
    }

    /**
     * 根据id查询
     * @param id id
     * @return 结果
     */
    @GetMapping
    public List<Genus> selectGenusById(@RequestParam int id) {
        return genusService.selectGenusesById(id);
    }

    /**
     * 插入属
     * @param genus 属
     * @return int
     */
    @PostMapping("{familyId}")
    public int insertGenus(@PathVariable int familyId, @RequestBody Genus genus) {
        //检查属id是否有效
        List<Family> families = familyService.selectFamiliesById(familyId);
        if (families.isEmpty()) {
            //todo 抛出错误
            return 0;
        }
        return genusService.insertGenus(familyId, genus);
    }

    /**
     * 删除属
     * @param id id
     * @return int
     */
    @DeleteMapping
    public int deleteGenusById(@RequestParam int id) {
        return genusService.deleteGenusById(id);
    }

    /**
     * 更新属
     * @param newGenus 新属
     * @return int
     */
    @PutMapping
    public int updateGenus(@RequestBody Genus newGenus) {
        return genusService.updateGenus(newGenus);
    }
}
