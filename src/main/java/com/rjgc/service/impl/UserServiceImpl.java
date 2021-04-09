package com.rjgc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rjgc.entity.User;
import com.rjgc.mapper.UserMapper;
import com.rjgc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhaoyunjie
 * @date 2021-04-09 22:55
 */
@Service("userServiceImpl")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> selectUser() {
        return userMapper.selectList(new QueryWrapper<>());
    }
}
