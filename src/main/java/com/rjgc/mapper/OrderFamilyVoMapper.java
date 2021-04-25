package com.rjgc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rjgc.Vo.OrderFamilyVo;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author zhaoyunjie
 * @date 2021-04-08 17:04
 */
public interface OrderFamilyVoMapper extends BaseMapper<OrderFamilyVo> {

    @Select("SELECT family_id as family_id,\n" +
            "       order_id as order_id,\n" +
            "       o.code as order_code,\n" +
            "       o.name as order_name\n" +
            "FROM order_family\n" +
            "left join orders o on order_family.order_id = o.id\n" +
            "WHERE family_id = #{id}")
    List<OrderFamilyVo> selectByFamilyId(int id);

    @Select("SELECT family_id as family_id,\n" +
            "       order_id as order_id,\n" +
            "       o.code as order_code,\n" +
            "       o.name as order_name\n" +
            "FROM order_family\n" +
            "left join orders o on order_family.order_id = o.id\n" +
            "WHERE order_id = #{id}")
    List<OrderFamilyVo> selectByOrderId(int id);
}
