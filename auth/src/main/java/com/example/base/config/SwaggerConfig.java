package com.example.base.config;

import com.example.base.config.swagger.BaseSwaggerConfig;
import com.example.base.config.swagger.SwaggerProperties;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger API文档相关配置
 * Created by macro on 2018/4/26.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig extends BaseSwaggerConfig {

    @Override
    public SwaggerProperties swaggerProperties() {
        return SwaggerProperties.builder()
                .apiBasePackage("com.example.base.controller")
                .title("Oauth2.0认证中心")
                .description("Oauth2.0认证中心相关接口文档")
                .contactName("benben")
                .version("1.0")
                .enableSecurity(true)
                .build();
    }
}
