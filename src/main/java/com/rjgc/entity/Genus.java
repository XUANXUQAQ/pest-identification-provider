package com.rjgc.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author zhaoyunjie
 * @date 2021-04-08 13:06
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "genus")
public class Genus {

    @Id
    private int id;

    @JSONField(name = "name")
    private String name;

    @JSONField(name = "code")
    private String code;
}
