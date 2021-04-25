package com.rjgc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rjgc.entity.OrderFamily;
import com.rjgc.mapper.OrderFamilyMapper;
import com.rjgc.service.OrderFamilyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhaoyunjie
 * @date 2021-04-08 17:02
 */
@Service("orderFamilyServiceImpl")
public class OrderFamilyServiceImpl extends ServiceImpl<OrderFamilyMapper, OrderFamily> implements OrderFamilyService {

    @Autowired
    private OrderFamilyMapper orderFamilyMapper;

    @Override
    public int insert(int orderId, int familyId) {
        OrderFamily orderFamily = new OrderFamily();
        orderFamily.setFamilyId(familyId);
        orderFamily.setOrderId(orderId);
        return orderFamilyMapper.insert(orderFamily);
    }

    @Override
    public int updateByFamilyId(int orderId, int familyId) {
        OrderFamily orderFamily = new OrderFamily();
        orderFamily.setOrderId(orderId);
        orderFamily.setFamilyId(familyId);
        return orderFamilyMapper.updateById(orderFamily);
    }
}
