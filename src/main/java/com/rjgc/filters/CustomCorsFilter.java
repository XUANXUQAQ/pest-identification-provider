package com.rjgc.filters;

import com.rjgc.configs.cors.CorsProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zhaoyunjie
 * @date 2021-04-15 16:46
 */
@Component
public class CustomCorsFilter implements Filter {

    @Autowired
    private CorsProperties corsProperties;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse resp = (HttpServletResponse) response;
        resp.setHeader("Access-Control-Allow-Origin", corsProperties.getAllowOrigin());
        resp.setHeader("Access-Control-Allow-Credentials", String.valueOf(corsProperties.isAllowCredentials()));
        resp.setHeader("Access-Control-Allow-Methods", corsProperties.getAllowMethods());
        resp.setHeader("Access-Control-Max-Age", String.valueOf(corsProperties.getMaxAge()));
        resp.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
        chain.doFilter(request, response);
    }
}
