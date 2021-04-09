package com.rjgc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rjgc.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author zhaoyunjie
 * @date 2021-04-09 22:25
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
