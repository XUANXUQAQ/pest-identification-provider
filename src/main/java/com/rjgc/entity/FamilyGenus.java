package com.rjgc.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * @author zhaoyunjie
 * @date 2021-04-08 16:49
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class FamilyGenus {

    @JSONField(name = "family_id")
    private int familyId;

    @TableId
    @JSONField(name = "genus_id")
    private int genusId;

    @JSONField(name = "update_time")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Timestamp updateTime;
}
