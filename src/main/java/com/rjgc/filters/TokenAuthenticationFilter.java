package com.rjgc.filters;

import com.rjgc.exceptions.BizException;
import com.rjgc.exceptions.ExceptionsEnum;
import com.rjgc.exceptions.ResBody;
import com.rjgc.service.UserService;
import com.rjgc.utils.JwtTokenUtils;
import com.rjgc.utils.ResponseUtils;
import io.jsonwebtoken.Claims;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.AntPathMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author zhaoyunjie
 * @date 2021-04-15 20:19
 */
public class TokenAuthenticationFilter extends BasicAuthenticationFilter {

    private final JwtTokenUtils jwtTokenUtil;

    private final UserService userService;

    private final String[] patterns = {
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html/**",
            "/webjars/**",
            "login"
    };

    public TokenAuthenticationFilter(AuthenticationManager authManager, JwtTokenUtils jwtTokenUtil, UserService userService) {
        super(authManager);
        this.jwtTokenUtil = jwtTokenUtil;
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        //不需要鉴权
        if (isExcludeUrl(request.getRequestURI())) {
            chain.doFilter(request, response);
        }
        UsernamePasswordAuthenticationToken authentication;
        try {
            authentication = getAuthentication(request.getHeader("token"));
        } catch (Exception e) {
            e.printStackTrace();
            ResponseUtils.out(response, ResBody.error(new BizException(ExceptionsEnum.AUTHORIZE_FAILED)));
            return;
        }
        if (authentication != null) {
            AtomicBoolean hasAdmin = new AtomicBoolean(false);
            Collection<GrantedAuthority> authorities = authentication.getAuthorities();
            authorities.forEach(each -> {
                if ("admin".equalsIgnoreCase(each.getAuthority())) {
                    hasAdmin.set(true);
                }
            });
            if (hasAdmin.get()) {
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                ResponseUtils.out(response, ResBody.error(new BizException(ExceptionsEnum.AUTHORIZE_FAILED)));
                return;
            }
        } else {
            ResponseUtils.out(response, ResBody.error(new BizException(ExceptionsEnum.AUTHORIZE_FAILED)));
            return;
        }
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(String token) {
        // 解析令牌
        Claims claims = jwtTokenUtil.getClaimByToken(token);

        // 校验令牌是否有效
        if (claims == null) {
            throw new AccountExpiredException("令牌无效");
        }

        // 校验令牌是否过期
        boolean expired = jwtTokenUtil.isTokenExpired(claims.getExpiration());
        if (expired) {
            throw new AccountExpiredException("令牌已过期");
        }

        // 获取主体信息
        String username = claims.getSubject();
        // 判断主体信息是否为空
        if (username != null && !username.isEmpty()) {
            // 获取权限字符串
            String authorityStr = userService.selectUser().get(0).getRole();
            // 将权限信息整理成以逗号分隔的字符串
            List<GrantedAuthority> authorityList =
                    AuthorityUtils.commaSeparatedStringToAuthorityList(authorityStr);
            // 返回认证信息
            return new UsernamePasswordAuthenticationToken(username, token, authorityList);
        }
        // 否则返回null
        return null;
    }

    private boolean isExcludeUrl(String url) {
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        for (String each : patterns) {
            if (antPathMatcher.match(each, url)) {
                return true;
            }
        }
        return false;
    }
}
