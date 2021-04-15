package com.rjgc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rjgc.entity.Genus;

import java.util.List;
import java.util.Map;

/**
 * @author zhaoyunjie
 * @date 2021-04-08 16:34
 */
public interface GenusService extends IService<Genus> {
    Map<String, Object> selectAllGenuses(int pageNum, int pageSize);

    List<Genus> selectGenusesById(int id);

    Map<String, Object> selectGenusesByName(int pageNum, int pageSize, String name);

    int insertGenus(int familyId, Genus genus);

    int deleteGenusById(int id);

    int updateGenus(Genus newGenus);
}
