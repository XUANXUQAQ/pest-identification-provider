package com.rjgc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rjgc.dao.FamilyGenus;

import java.util.List;

public interface FamilyGenusService extends IService<FamilyGenus> {

    List<FamilyGenus> selectByFamilyId(int familyId);

    int insert(int familyId, int genusId);

}
