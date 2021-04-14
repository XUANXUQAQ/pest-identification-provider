package com.rjgc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rjgc.entity.Family;

import java.util.List;
import java.util.Map;

/**
 * @author zhaoyunjie
 * @date 2021-04-08 13:19
 */
public interface FamilyService extends IService<Family> {
    Map<List<Family>, Long> selectAllFamilies(int pageNum, int pageSize);

    List<Family> selectFamiliesById(int id);

    Map<List<Family>, Long> selectFamiliesByName(int pageNum, int pageSize, String name);

    int insertFamily(int orderId, Family family);

    int deleteFamilyById(int id);

    int updateFamily(Family newFamily);
}
