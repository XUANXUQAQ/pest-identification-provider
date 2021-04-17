package com.rjgc.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhaoyunjie
 * @date 2021-04-09 22:17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("user")
public class UserInfo {

    int id;

    @JSONField(name = "username")
    private String username;

    @JSONField(name = "password")
    private String password;

    @JSONField(name = "role")
    private String role;
}
