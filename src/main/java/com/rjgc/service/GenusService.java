package com.rjgc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rjgc.entity.Genus;

import java.util.List;

/**
 * @author zhaoyunjie
 * @date 2021-04-08 16:34
 */
public interface GenusService extends IService<Genus> {
    List<Genus> selectAllGenuses(int pageNum, int pageSize);

    List<Genus> selectGenusesById(int id);

    int insertGenus(int familyId, Genus genus);

    int deleteGenusById(int id);

    int updateGenus(Genus newGenus);
}
