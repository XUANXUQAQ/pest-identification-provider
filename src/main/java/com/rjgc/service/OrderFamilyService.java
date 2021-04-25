package com.rjgc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rjgc.entity.OrderFamily;

/**
 * @author zhaoyunjie
 * @date 2021-04-08 16:34
 */
public interface OrderFamilyService extends IService<OrderFamily> {

    int insert(int orderId, int familyId);

    int updateByFamilyId(int orderId, int familyId);

    int deleteByFamilyId(int familyId);
}
