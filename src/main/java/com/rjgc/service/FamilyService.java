package com.rjgc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rjgc.entity.Family;

/**
 * @author zhaoyunjie
 * @date 2021-04-08 13:19
 */
public interface FamilyService extends IService<Family> {

    int insertFamily(int orderId, Family family);

    int deleteFamilyById(int id);

    int updateFamily(Family newFamily);
}
