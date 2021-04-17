package com.rjgc.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private int genusId;
}
