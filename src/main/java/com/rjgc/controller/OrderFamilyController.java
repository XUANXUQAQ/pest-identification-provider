package com.rjgc.controller;

import com.rjgc.Vo.OrderFamilyVo;
import com.rjgc.exceptions.ResBody;
import com.rjgc.service.OrderFamilyVoService;
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
@RequestMapping("orderFamily")
public class OrderFamilyController {

    @Autowired
    @Qualifier("orderFamilyVoServiceImpl")
    private OrderFamilyVoService orderFamilyService;

    @GetMapping
    @ApiOperation("根据科id查询目")
    public ResBody<Map<String, Object>> selectOrderByFamilyId(@RequestParam int id) {
        List<OrderFamilyVo> orderFamilies = orderFamilyService.selectByFamilyId(id);
        HashMap<String, Object> map = new HashMap<>();
        map.put("pages", 1);
        map.put("data", orderFamilies);
        return ResBody.success(map);
    }
}
