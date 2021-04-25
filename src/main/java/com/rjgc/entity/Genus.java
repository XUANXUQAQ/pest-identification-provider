package com.rjgc.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhaoyunjie
 * @date 2021-04-08 13:06
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Genus {

    @TableId(type = IdType.AUTO)
    private int id;

    @JSONField(name = "name")
    private String name;

    @JSONField(name = "code")
    private String code;
}
