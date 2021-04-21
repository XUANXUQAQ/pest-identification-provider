package com.rjgc.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhaoyunjie
 * @date 2021-04-08 13:14
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Species {

    private int id;

    @JSONField(name = "code")
    private String code;

    @JSONField(name = "name")
    private String name;

    @JSONField(name = "latin")
    private String latin;

    @JSONField(name = "plant")
    private String plant;

    @JSONField(name = "area")
    private String area;

    @JSONField(name = "genus_id")
    private int genusId;

    private String image;
}

