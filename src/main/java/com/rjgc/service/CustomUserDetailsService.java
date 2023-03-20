package com.rjgc.service;

import com.rjgc.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhaoyunjie
 * @date 2021-04-09 23:04
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    @Qualifier("userServiceImpl")
    private UserService userService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<UserInfo> userInfos = userService.selectUserByName(username);
        if (userInfos.isEmpty()) {
            throw new UsernameNotFoundException("未找到用户" + username);
        }
        UserInfo userInfo = userInfos.get(0);
        return new User(userInfo.getUsername(),
                userInfo.getPassword(),
                AuthorityUtils.commaSeparatedStringToAuthorityList(userInfo.getRole()));
    }
}
