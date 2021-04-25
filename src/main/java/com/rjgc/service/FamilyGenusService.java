package com.rjgc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rjgc.entity.FamilyGenus;

public interface FamilyGenusService extends IService<FamilyGenus> {

    int insert(int familyId, int genusId);

    int updateByGenusId(int familyId, int genusId);

}
