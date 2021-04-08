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
 * @date 2021-04-08 13:04
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "family")
public class Family {

    @Id
    private int id;

    @JSONField(name = "name")
    private String name;

    @JSONField(name = "code")
    private String code;
}
