package com.rjgc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rjgc.Vo.OrderFamilyVo;
import com.rjgc.mapper.OrderFamilyVoMapper;
import com.rjgc.service.OrderFamilyVoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("orderFamilyVoServiceImpl")
public class OrderFamilyVoServiceImpl extends ServiceImpl<OrderFamilyVoMapper, OrderFamilyVo> implements OrderFamilyVoService {

    @Autowired
    private OrderFamilyVoMapper orderFamilyVoMapper;

    @Override
    public List<OrderFamilyVo> selectByOrderId(int orderId) {
        return orderFamilyVoMapper.selectByOrderId(orderId);
    }

    @Override
    public List<OrderFamilyVo> selectByFamilyId(int familyId) {
        return orderFamilyVoMapper.selectByFamilyId(familyId);
    }
}
