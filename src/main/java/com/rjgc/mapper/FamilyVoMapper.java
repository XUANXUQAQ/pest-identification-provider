package com.rjgc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rjgc.Vo.FamilyVo;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface FamilyVoMapper extends BaseMapper<FamilyVo> {

    @Select("select\n" +
            "       family.id as id,\n" +
            "       family.code as code,\n" +
            "       family.name as name,\n" +
            "       orders.id as order_id,\n" +
            "       orders.code as order_code,\n" +
            "       orders.name as order_name\n" +
            "from family\n" +
            "left join order_family o on family.id = o.family_id\n" +
            "left join orders on o.order_id = orders.id\n" +
            "order by family_id\n" +
            "limit #{pageNum}, #{pageSize}")
    List<FamilyVo> selectAllFamilies(int pageNum, int pageSize);

    @Select("select\n" +
            "       family.id as id,\n" +
            "       family.code as code,\n" +
            "       family.name as name,\n" +
            "       orders.id as order_id,\n" +
            "       orders.code as order_code,\n" +
            "       orders.name as order_name\n" +
            "from family\n" +
            "left join order_family o on family.id = o.family_id\n" +
            "left join orders on o.order_id = orders.id\n" +
            "where family_id = #{id}\n" +
            "order by family_id")
    List<FamilyVo> selectFamilyById(int id);

    @Select("select\n" +
            "       family.id as id,\n" +
            "       family.code as code,\n" +
            "       family.name as name,\n" +
            "       orders.id as order_id,\n" +
            "       orders.code as order_code,\n" +
            "       orders.name as order_name\n" +
            "from family\n" +
            "left join order_family o on family.id = o.family_id\n" +
            "left join orders on o.order_id = orders.id\n" +
            "where family.name like \"%${name}%\"\n" +
            "order by family_id\n" +
            "limit #{pageNum}, #{pageSize}")
    List<FamilyVo> selectFamilyByName(int pageNum, int pageSize, String name);
}
