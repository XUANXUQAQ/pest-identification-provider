package com.rjgc.configs;

import com.rjgc.filters.CustomCorsFilter;
import com.rjgc.filters.TokenAuthenticationFilter;
import com.rjgc.filters.TokenLoginFilter;
import com.rjgc.service.CustomUserDetainsService;
import com.rjgc.service.UserService;
import com.rjgc.utils.JwtTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
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
                //添加cors响应头
                .addFilterAfter(customCorsFilter, HeaderWriterFilter.class)
                //添加token登录过滤器
                .addFilterBefore(new TokenLoginFilter(authenticationManager(), jwtTokenUtils), UsernamePasswordAuthenticationFilter.class)
                //添加token认证过滤器
                .addFilterBefore(new TokenAuthenticationFilter(authenticationManager(), jwtTokenUtils, userService), BasicAuthenticationFilter.class).httpBasic()
                .and()
                //关闭session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                .antMatchers("/login").permitAll()
                .anyRequest().hasRole("admin");
        if (csrfProperties.getCsrfDisabled()) {
            http.csrf().disable();
        }
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetainsService).passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/v2/api-docs",
                        "/swagger-resources/configuration/ui",
                        "/swagger-resources",
                        "/swagger-resources/configuration/security",
                        "/swagger-ui.html",
                        "/webjars/**");
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
