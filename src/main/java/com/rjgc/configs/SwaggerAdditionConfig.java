package com.rjgc.configs;

import com.fasterxml.classmate.TypeResolver;
import com.google.common.collect.Sets;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import springfox.documentation.builders.OperationBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiDescription;
import springfox.documentation.service.Operation;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.ApiListingScannerPlugin;
import springfox.documentation.spi.service.contexts.DocumentationContext;
import springfox.documentation.spring.web.readers.operation.CachingOperationNameGenerator;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author zhaoyunjie
 * @date 2021-04-10 00:16
 */
@Component
public class SwaggerAdditionConfig implements ApiListingScannerPlugin {

    @Override
    public List<ApiDescription> apply(DocumentationContext documentationContext) {
        Operation login = buildLogin();
        Operation logout = buildLogout();
        ApiDescription loginApiDescription = new ApiDescription("login", "/login", "登录接口",
                Collections.singletonList(login), false);
        ApiDescription logoutApiDescription = new ApiDescription("logout", "/logout", "注销接口",
                Collections.singletonList(logout), false);

        return Arrays.asList(loginApiDescription, logoutApiDescription);
    }

    private Operation buildLogout() {
        return new OperationBuilder(new CachingOperationNameGenerator())
                .method(HttpMethod.POST)
                .summary("logout")
                .notes("注销")
                .consumes(Sets.newHashSet(MediaType.APPLICATION_FORM_URLENCODED_VALUE)) // 接收参数格式
                .tags(Sets.newHashSet("logout"))
                .responseMessages(Collections.singleton(
                        new ResponseMessageBuilder().code(200).message("请求成功").build()))
                .build();
    }

    private Operation buildLogin() {
        return new OperationBuilder(new CachingOperationNameGenerator())
                .method(HttpMethod.POST)
                .summary("login")
                .notes("username/password登录")
                .consumes(Sets.newHashSet(MediaType.APPLICATION_FORM_URLENCODED_VALUE)) // 接收参数格式
                .tags(Sets.newHashSet("login"))
                .parameters(Arrays.asList(
                        new ParameterBuilder()
                                .description("用户名")
                                .type(new TypeResolver().resolve(String.class))
                                .name("username")
                                .defaultValue("admin")
                                .parameterType("query")
                                .parameterAccess("access")
                                .required(true)
                                .modelRef(new ModelRef("string"))
                                .build(),
                        new ParameterBuilder()
                                .description("密码")
                                .type(new TypeResolver().resolve(String.class))
                                .name("password")
                                .defaultValue("admin123456")
                                .parameterType("query")
                                .parameterAccess("access")
                                .required(true)
                                .modelRef(new ModelRef("string"))
                                .build()
                ))
                .responseMessages(Collections.singleton(
                        new ResponseMessageBuilder().code(200).message("请求成功").build()))
                .build();
    }

    @Override
    public boolean supports(DocumentationType documentationType) {
        return DocumentationType.SWAGGER_2.equals(documentationType);
    }
}
