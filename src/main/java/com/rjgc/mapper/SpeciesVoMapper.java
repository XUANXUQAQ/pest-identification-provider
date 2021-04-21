package com.rjgc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rjgc.entity.SpeciesVo;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author zhaoyunjie
 * @date 2021-04-09 16:38
 */
public interface SpeciesVoMapper extends BaseMapper<SpeciesVo> {

    @Select("select species.id    as id,\n" +
            "               species.name  as name,\n" +
            "               species.latin as latin,\n" +
            "               species.plant as plant,\n" +
            "               species.area  as area,\n" +
            "               species.image as image,\n" +
            "               g.name        as genus_name,\n" +
            "               g.id          as genus_id,\n" +
            "               f.name        as family_name,\n" +
            "               f.id          as family_id,\n" +
            "               o.name        as order_name,\n" +
            "               o.id          as order_id\n" +
            "        from species\n" +
            "                 left join genus g on species.genus_id = g.id\n" +
            "                 left join family_genus on g.id = family_genus.genus_id\n" +
            "                 left join family f on family_genus.family_id = f.id\n" +
            "                 left join order_family on order_family.family_id = f.id\n" +
            "                 left join orders o on order_family.order_id = o.id\n" +
            "        where species.id = #{id}")
    List<SpeciesVo> selectSpeciesById(int id);

    @Select("select species.id    as id,\n" +
            "               species.name  as name,\n" +
            "               species.latin as latin,\n" +
            "               species.plant as plant,\n" +
            "               species.area  as area,\n" +
            "               species.image as image,\n" +
            "               g.name        as genus_name,\n" +
            "               g.id          as genus_id,\n" +
            "               f.name        as family_name,\n" +
            "               f.id          as family_id,\n" +
            "               o.name        as order_name,\n" +
            "               o.id          as order_id\n" +
            "        from species\n" +
            "                 left join genus g on species.genus_id = g.id\n" +
            "                 left join family_genus on g.id = family_genus.genus_id\n" +
            "                 left join family f on family_genus.family_id = f.id\n" +
            "                 left join order_family on order_family.family_id = f.id\n" +
            "                 left join orders o on order_family.order_id = o.id order by id\n" +
            "        limit #{pageNum}, #{pageSize}")
    List<SpeciesVo> selectAllSpecies(int pageNum, int pageSize);

    @Select("select species.id    as id,\n" +
            "       species.name  as name,\n" +
            "       species.latin as latin,\n" +
            "       species.plant as plant,\n" +
            "       species.area  as area,\n" +
            "       species.image as image,\n" +
            "       g.name        as genus_name,\n" +
            "       g.id          as genus_id,\n" +
            "       f.name        as family_name,\n" +
            "       f.id          as family_id,\n" +
            "       o.name        as order_name,\n" +
            "       o.id          as order_id\n" +
            "from species\n" +
            "         left join genus g on species.genus_id = g.id\n" +
            "         left join family_genus on g.id = family_genus.genus_id\n" +
            "         left join family f on family_genus.family_id = f.id\n" +
            "         left join order_family on order_family.family_id = f.id\n" +
            "         left join orders o on order_family.order_id = o.id\n" +
            "where species.code = \"${code}\"")
    List<SpeciesVo> selectSpeciesByCode(String code);

    @Select("select species.id    as id,\n" +
            "       species.name  as name,\n" +
            "       species.latin as latin,\n" +
            "       species.plant as plant,\n" +
            "       species.area  as area,\n" +
            "       species.image as image,\n" +
            "       g.name        as genus_name,\n" +
            "       g.id          as genus_id,\n" +
            "       f.name        as family_name,\n" +
            "       f.id          as family_id,\n" +
            "       o.name        as order_name,\n" +
            "       o.id          as order_id\n" +
            "from species\n" +
            "         left join genus g on species.genus_id = g.id\n" +
            "         left join family_genus on g.id = family_genus.genus_id\n" +
            "         left join family f on family_genus.family_id = f.id\n" +
            "         left join order_family on order_family.family_id = f.id\n" +
            "         left join orders o on order_family.order_id = o.id order by id\n" +
            "where species.name like \"%${name}%\"")
    List<SpeciesVo> selectSpeciesByName(String name);
}
