package com.rjgc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rjgc.entity.User;

import java.util.List;

/**
 * @author zhaoyunjie
 * @date 2021-04-09 22:53
 */
public interface UserService extends IService<User> {

    List<User> selectUser();
}
