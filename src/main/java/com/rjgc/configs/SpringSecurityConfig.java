package com.rjgc.configs;

import com.rjgc.exceptions.BizException;
import com.rjgc.exceptions.ExceptionsEnum;
import com.rjgc.exceptions.ResBody;
import com.rjgc.filters.CustomCorsFilter;
import com.rjgc.filters.TokenAuthenticationFilter;
import com.rjgc.filters.TokenLoginFilter;
import com.rjgc.service.CustomUserDetainsService;
import com.rjgc.service.UserService;
import com.rjgc.utils.JwtTokenUtils;
import com.rjgc.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.header.HeaderWriterFilter;

/**
 * @author zhaoyunjie
 * @date 2021-04-09 18:39
 */
@EnableWebSecurity
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetainsService userDetainsService;

    @Autowired
    private CsrfProperties csrfProperties;

    @Autowired
    private CustomCorsFilter customCorsFilter;

    @Autowired
    private JwtTokenUtils jwtTokenUtils;

    @Autowired
    @Qualifier("userServiceImpl")
    private UserService userService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and()
                .exceptionHandling()
                .authenticationEntryPoint((httpServletRequest, httpServletResponse, e) ->
                        ResponseUtils.out(httpServletResponse, ResBody.error(new BizException(ExceptionsEnum.AUTHORIZE_FAILED))))
                .and()
                .authorizeRequests()
                .anyRequest().permitAll();
        if (csrfProperties.getCsrfDisabled()) {
            http.csrf().disable();
        }

        http.addFilterAfter(customCorsFilter, HeaderWriterFilter.class)
                .addFilter(new TokenLoginFilter(authenticationManager(), jwtTokenUtils))
                .addFilter(new TokenAuthenticationFilter(authenticationManager(), jwtTokenUtils, userService)).httpBasic();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetainsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
