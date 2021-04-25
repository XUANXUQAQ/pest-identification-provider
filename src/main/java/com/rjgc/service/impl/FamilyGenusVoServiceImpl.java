package com.rjgc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rjgc.Vo.FamilyGenusVo;
import com.rjgc.mapper.FamilyGenusVoMapper;
import com.rjgc.service.FamilyGenusVoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("familyGenusVoServiceImpl")
public class FamilyGenusVoServiceImpl extends ServiceImpl<FamilyGenusVoMapper, FamilyGenusVo> implements FamilyGenusVoService {

    @Autowired
    private FamilyGenusVoMapper familyGenusVoMapper;

    @Override
    public List<FamilyGenusVo> selectByFamilyId(int familyId) {
        return familyGenusVoMapper.selectByFamilyId(familyId);
    }

    @Override
    public List<FamilyGenusVo> selectByGenusId(int genusId) {
        return familyGenusVoMapper.selectByGenusId(genusId);
    }
}
