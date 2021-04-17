package com.rjgc.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhaoyunjie
 * @date 2021-04-08 13:13
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Orders {

    private int id;

    @JSONField(name = "name")
    private String name;

    @JSONField(name = "code")
    private String code;
}
