package com.rjgc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rjgc.Vo.FamilyGenusVo;

import java.util.List;

public interface FamilyGenusVoService extends IService<FamilyGenusVo> {

    List<FamilyGenusVo> selectByFamilyId(int familyId);

    List<FamilyGenusVo> selectByGenusId(int genusId);
}
