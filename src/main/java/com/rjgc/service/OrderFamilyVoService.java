package com.rjgc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rjgc.Vo.OrderFamilyVo;

import java.util.List;

public interface OrderFamilyVoService extends IService<OrderFamilyVo> {

    List<OrderFamilyVo> selectByOrderId(int orderId);

    List<OrderFamilyVo> selectByFamilyId(int familyId);
}
