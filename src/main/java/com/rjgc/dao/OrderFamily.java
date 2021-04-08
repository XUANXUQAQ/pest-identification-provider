package com.rjgc.dao;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author zhaoyunjie
 * @date 2021-04-08 16:59
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "order_family")
public class OrderFamily {

    @JSONField(name = "order_id")
    private int orderId;

    @Id
    private int familyId;
}
