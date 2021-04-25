package com.rjgc.Vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.rjgc.entity.Species;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author zhaoyunjie
 * @date 2021-04-09 16:36
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpeciesVo extends Species {

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

    private String image;
}
