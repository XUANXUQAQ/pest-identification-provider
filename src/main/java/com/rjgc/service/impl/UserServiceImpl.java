package com.rjgc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rjgc.entity.User;
import com.rjgc.mapper.UserMapper;
import com.rjgc.service.UserService;

import java.util.List;

/**
 * @author
 * @date 2021-04-09 22:55
 */
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Override
    public List<User> selectUser() {
        //todo 从user表中读取账号密码
    }
}
