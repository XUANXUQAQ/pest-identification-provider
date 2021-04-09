package com.rjgc.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author zhaoyunjie
 * @date 2021-04-09 16:36
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class SpeciesVo {
    @Id
    private int id;

    @JSONField(name = "name")
    private String name;

    @JSONField(name = "latin")
    private String latin;

    @JSONField(name = "plant")
    private String plant;

    @JSONField(name = "area")
    private String area;

    @JSONField(name = "genus_name")
    private String genusName;

    @JSONField(name = "genus_id")
    private int genusId;

    @JSONField(name = "family_name")
    private String familyName;

    @JSONField(name = "family_id")
    private int familyId;

    @JSONField(name = "order_name")
    private String orderName;

    @JSONField(name = "order_id")
    private int orderId;
}
