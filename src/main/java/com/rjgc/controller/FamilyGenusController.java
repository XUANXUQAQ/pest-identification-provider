package com.rjgc.controller;

import com.rjgc.Vo.FamilyGenusVo;
import com.rjgc.exceptions.ResBody;
import com.rjgc.service.FamilyGenusVoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/familyGenus")
public class FamilyGenusController {

    @Autowired
    @Qualifier("familyGenusVoServiceImpl")
    private FamilyGenusVoService familyGenusService;

    @GetMapping
    @ApiOperation("根据属id查询科信息")
    public ResBody<Map<String, Object>> selectFamilyByGenusId(@RequestParam int id) {
        List<FamilyGenusVo> familyGenus = familyGenusService.selectByGenusId(id);
        HashMap<String, Object> map = new HashMap<>();
        map.put("pages", 1);
        map.put("data", familyGenus);
        return ResBody.success(map);
    }
}
