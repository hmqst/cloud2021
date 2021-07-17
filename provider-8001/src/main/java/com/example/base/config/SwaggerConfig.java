package com.example.base.config;

import com.example.base.config.swagger.BaseSwaggerConfig;
import com.example.base.config.swagger.SwaggerProperties;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author benben
 * @program cloud2021
 * @Description
 * @date 2021-05-09 11:16
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig extends BaseSwaggerConfig {
    @Override
    public SwaggerProperties swaggerProperties() {
        return SwaggerProperties.builder()
                .apiBasePackage("com.example.base.controller")
                .title("微服务1")
                .description("微服务1后台相关接口文档")
                .contactName("benben")
                .version("1.0")
                .enableSecurity(true)
                .build();
    }
}
