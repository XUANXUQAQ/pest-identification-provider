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
 * @date 2021-04-08 16:49
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "family_genus")
@Entity
public class FamilyGenus {

    @JSONField(name = "family_id")
    private int familyId;

    @Id
    private int genusId;
}
