package com.rjgc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rjgc.Vo.GenusVo;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface GenusVoMapper extends BaseMapper<GenusVo> {

    @Select("select genus.id    as id,\n" +
            "       genus.code  as code,\n" +
            "       genus.name  as name,\n" +
            "       f.id        as family_id,\n" +
            "       f.code      as family_code,\n" +
            "       f.name      as family_name,\n" +
            "       orders.id   as order_id,\n" +
            "       orders.code as order_code,\n" +
            "       orders.name as order_name\n" +
            "from genus\n" +
            "         left join family_genus fg on genus.id = fg.genus_id\n" +
            "         left join family f on fg.family_id = f.id\n" +
            "         left join order_family on f.id = order_family.family_id\n" +
            "         left join orders on orders.id = order_family.order_id\n" +
            "order by genus.id\n" +
            "limit #{pageNum}, #{pageSize}")
    List<GenusVo> selectAllGenus(int pageNum, int pageSize);

    @Select("select genus.id    as id,\n" +
            "       genus.code  as code,\n" +
            "       genus.name  as name,\n" +
            "       f.id        as family_id,\n" +
            "       f.code      as family_code,\n" +
            "       f.name      as family_name,\n" +
            "       orders.id   as order_id,\n" +
            "       orders.code as order_code,\n" +
            "       orders.name as order_name\n" +
            "from genus\n" +
            "         left join family_genus fg on genus.id = fg.genus_id\n" +
            "         left join family f on fg.family_id = f.id\n" +
            "         left join order_family on f.id = order_family.family_id\n" +
            "         left join orders on orders.id = order_family.order_id\n" +
            "where genus.id = #{id}\n" +
            "order by genus.id")
    List<GenusVo> selectGenusById(int id);

    @Select("select genus.id    as id,\n" +
            "       genus.code  as code,\n" +
            "       genus.name  as name,\n" +
            "       f.id        as family_id,\n" +
            "       f.code      as family_code,\n" +
            "       f.name      as family_name,\n" +
            "       orders.id   as order_id,\n" +
            "       orders.code as order_code,\n" +
            "       orders.name as order_name\n" +
            "from genus\n" +
            "         left join family_genus fg on genus.id = fg.genus_id\n" +
            "         left join family f on fg.family_id = f.id\n" +
            "         left join order_family on f.id = order_family.family_id\n" +
            "         left join orders on orders.id = order_family.order_id\n" +
            "where genus.name like \"%${name}%\"\n" +
            "order by genus.id\n" +
            "limit #{pageNum}, #{pageSize}")
    List<GenusVo> selectGenusByName(int pageNum, int pageSize, String name);
}
