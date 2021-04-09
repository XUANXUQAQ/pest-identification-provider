package com.rjgc.service;

import com.rjgc.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhaoyunjie
 * @date 2021-04-09 23:04
 */
@Service
public class CustomUserDetainsService implements UserDetailsService {

    @Autowired
    @Qualifier("userServiceImpl")
    private UserService userService;


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        List<User> users = userService.selectUser();
        if (users.isEmpty()) {
            throw new NullPointerException("user数据库为空");
        }
        User user = users.get(0);
        List<GrantedAuthority> authorities = new ArrayList<>();

        String[] roles = user.getRole().split(",");

        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
        }

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }
}
