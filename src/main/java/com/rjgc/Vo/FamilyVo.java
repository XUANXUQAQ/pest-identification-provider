package com.rjgc.Vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.rjgc.entity.Family;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FamilyVo extends Family {

    @JSONField(name = "order_id")
    private int orderId;

    @JSONField(name = "order_code")
    private String orderCode;

    @JSONField(name = "order_name")
    private String orderName;
}
