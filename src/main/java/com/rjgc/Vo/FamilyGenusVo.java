package com.rjgc.Vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.rjgc.entity.FamilyGenus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FamilyGenusVo extends FamilyGenus {

    @JSONField(name = "family_code")
    private String familyCode;

    @JSONField(name = "family_name")
    private String familyName;
}
