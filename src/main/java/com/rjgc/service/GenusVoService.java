package com.rjgc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rjgc.Vo.GenusVo;

import java.util.Map;

public interface GenusVoService extends IService<GenusVo> {

    Map<String, Object> selectAllGenuses(int pageNum, int pageSize);

    Map<String, Object> selectGenusesById(int id);

    Map<String, Object> selectGenusesByName(int pageNum, int pageSize, String name);
}
