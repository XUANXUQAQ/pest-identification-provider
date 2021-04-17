package com.rjgc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rjgc.entity.UserInfo;

import java.util.List;

/**
 * @author zhaoyunjie
 * @date 2021-04-09 22:53
 */
public interface UserService extends IService<UserInfo> {

    List<UserInfo> selectUserByName(String name);
}
