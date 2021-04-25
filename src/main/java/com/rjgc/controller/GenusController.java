package com.rjgc.controller;

import com.rjgc.Vo.OrderFamilyVo;
import com.rjgc.Vo.SpeciesVo;
import com.rjgc.entity.Family;
import com.rjgc.entity.Genus;
import com.rjgc.exceptions.BizException;
import com.rjgc.exceptions.ExceptionsEnum;
import com.rjgc.exceptions.ResBody;
import com.rjgc.service.*;
import com.rjgc.utils.DBUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;
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
    @Qualifier("genusVoServiceImpl")
    private GenusVoService genusVoService;

    @Autowired
    @Qualifier("genusServiceImpl")
    private GenusService genusService;

    @Autowired
    @Qualifier("familyVoServiceImpl")
    private FamilyVoService familyService;

    @Autowired
    @Qualifier("orderServiceImpl")
    private OrderService orderService;

    @Autowired
    @Qualifier("familyGenusServiceImpl")
    private FamilyGenusService familyGenusService;

    @Autowired
    @Qualifier("orderFamilyVoServiceImpl")
    private OrderFamilyVoService orderFamilyVoService;

    @Autowired
    @Qualifier("orderFamilyServiceImpl")
    private OrderFamilyService orderFamilyService;

    @Autowired
    @Qualifier("speciesVoServiceImpl")
    private SpeciesVoService speciesVoService;

    @Autowired
    private DBUtils dbUtils;

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
        return ResBody.success(genusVoService.selectAllGenuses(pageNum, pageSize));
    }

    @GetMapping("name")
    @ApiOperation("根据名称查询")
    public ResBody<Map<String, Object>> selectGenusByName(@RequestParam int pageNum, @RequestParam int pageSize, @RequestParam String name) {
        return ResBody.success(genusVoService.selectGenusesByName(pageNum, pageSize, name));
    }

    /**
     * 根据id查询
     *
     * @param id id
     * @return 结果
     */
    @GetMapping
    @ApiOperation("根据id查询")
    public ResBody<Map<String, Object>> selectGenusById(@RequestParam int id) {
        return ResBody.success(genusVoService.selectGenusesById(id));
    }

    /**
     * 插入属
     *
     * @param genus 属
     * @return int
     */
    @PostMapping("{familyId}")
    @ApiOperation("插入属")
    @Transactional
    public ResBody<Integer> insertGenus(@PathVariable int familyId, @RequestBody Genus genus) {
        //检查科id是否有效
        List<Family> families = (List<Family>) familyService.selectFamiliesById(familyId).get("data");
        if (families.isEmpty()) {
            //无效id
            throw new BizException(ExceptionsEnum.INVALID_ID);
        }
        Genus tmp = new Genus();
        BeanUtils.copyProperties(genus, tmp, "id");
        if (!dbUtils.executeSql("alter table genus AUTO_INCREMENT=1")) {
            throw new BizException(ExceptionsEnum.DATABASE_FAILED);
        }
        if (genusService.insertGenus(familyId, tmp) != 1) {
            throw new BizException(ExceptionsEnum.DATABASE_FAILED);
        }
        if (familyGenusService.insert(familyId, tmp.getId()) != 1) {
            throw new BizException(ExceptionsEnum.DATABASE_FAILED);
        }
        return ResBody.success();
    }

    /**
     * 删除属
     *
     * @param id id
     * @return int
     */
    @DeleteMapping
    @ApiOperation("删除属")
    @Transactional
    public ResBody<Integer> deleteGenusById(@RequestParam int id) {
        //检查是否仍有种依赖于该属
        List<SpeciesVo> list = (List<SpeciesVo>) speciesVoService.selectSpeciesVoByGenusId(id).get("data");
        if (list.isEmpty()) {
            if (genusService.deleteGenusById(id) != 1) {
                throw new BizException(ExceptionsEnum.DATABASE_FAILED);
            }
            if (familyGenusService.deleteByGenusId(id) != 1) {
                throw new BizException(ExceptionsEnum.DATABASE_FAILED);
            }
            return ResBody.success();
        } else {
            throw new BizException(ExceptionsEnum.IN_USE);
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
    @Transactional
    public ResBody<Integer> updateGenus(@RequestParam int familyId, @RequestBody Genus newGenus) {
        // 检查目id和科id是否有效
        List<OrderFamilyVo> families = orderFamilyVoService.selectByFamilyId(familyId);
        if (families.isEmpty()) {
            throw new BizException(ExceptionsEnum.INVALID_ID);
        }
        OrderFamilyVo orderFamily = families.get(0);
        if (genusService.updateGenus(newGenus) != 1) {
            throw new BizException(ExceptionsEnum.DATABASE_FAILED);
        }
        if (familyGenusService.updateByGenusId(familyId, newGenus.getId()) != 1) {
            throw new BizException(ExceptionsEnum.DATABASE_FAILED);
        }
        if (orderFamilyService.updateByFamilyId(orderFamily.getOrderId(), familyId) != 1) {
            throw new BizException(ExceptionsEnum.DATABASE_FAILED);

        }
        return ResBody.success();
    }
}
