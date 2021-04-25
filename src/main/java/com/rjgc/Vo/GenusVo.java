package com.rjgc.Vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.rjgc.entity.Genus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenusVo extends Genus {

    @JSONField(name = "family_id")
    private int familyId;

    @JSONField(name = "family_code")
    private String familyCode;

    @JSONField(name = "family_name")
    private String familyName;

    @JSONField(name = "order_id")
    private int orderId;

    @JSONField(name = "order_code")
    private String orderCode;

    @JSONField(name = "order_name")
    private String orderName;
}
