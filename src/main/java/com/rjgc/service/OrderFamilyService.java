package com.rjgc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rjgc.dao.OrderFamily;

import java.util.List;

/**
 * @author zhaoyunjie
 * @date 2021-04-08 16:34
 */
public interface OrderFamilyService extends IService<OrderFamily> {

    List<OrderFamily> selectByOrderId(int orderId);

    int insert(int orderId, int familyId);
}
