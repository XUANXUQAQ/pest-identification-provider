package com.rjgc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rjgc.Vo.FamilyGenusVo;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author zhaoyunjie
 * @date 2021-04-08 16:53
 */
public interface FamilyGenusVoMapper extends BaseMapper<FamilyGenusVo> {

    @Select("SELECT genus_id  as genus_id,\n" +
            "       family_id as family_id,\n" +
            "       f.code    as family_code,\n" +
            "       f.name    as family_name\n" +
            "FROM family_genus\n" +
            "left join family f on family_genus.family_id = f.id\n" +
            "WHERE genus_id = #{id}")
    List<FamilyGenusVo> selectByGenusId(int id);

    @Select("SELECT genus_id  as genus_id,\n" +
            "       family_id as family_id,\n" +
            "       f.code    as family_code,\n" +
            "       f.name    as family_name\n" +
            "FROM family_genus\n" +
            "left join family f on family_genus.family_id = f.id\n" +
            "WHERE family_id = #{id}")
    List<FamilyGenusVo> selectByFamilyId(int id);
}
