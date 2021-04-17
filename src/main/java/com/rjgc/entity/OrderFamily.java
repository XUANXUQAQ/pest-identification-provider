package com.rjgc.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhaoyunjie
 * @date 2021-04-08 16:59
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderFamily {

    @JSONField(name = "order_id")
    private int orderId;

    @TableId
    private int familyId;
}
