package com.rjgc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rjgc.entity.Family;

import java.util.List;

/**
 * @author zhaoyunjie
 * @date 2021-04-08 13:19
 */
public interface FamilyService extends IService<Family> {
    List<Family> selectAllFamilies(int pageNum, int pageSize);

    List<Family> selectFamiliesById(int id);

    int insertFamily(int orderId, Family family);

    int deleteFamilyById(int id);

    int updateFamily(Family newFamily);
}
