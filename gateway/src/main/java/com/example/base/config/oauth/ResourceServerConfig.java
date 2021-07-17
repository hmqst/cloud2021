package com.example.base.config.oauth;


import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

/**
 * 资源服务器配置
 * Created by macro on 2020/6/19.
 */
@AllArgsConstructor
@Configuration
@EnableWebFluxSecurity
public class ResourceServerConfig {
    private final AuthorizationManager authorizationManager;
    private final MyAccessDeniedHandler accessDeniedHandler;
    private final MyAuthenticationEntryPoint authenticationEntryPoint;

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {

        http.authorizeExchange()
                .pathMatchers(HttpMethod.OPTIONS).permitAll()
                // 白名单配置
                .pathMatchers(
                        "/actuator/**", "/auth/oauth/token", "/auth/oauth/check_token",
                        "/webjars/**",
                        "/doc.html",
                        "/swagger-resources/**",
                        "/swagger/**",
                        "/*/v2/api-docs",
                        "/*/v3/api-docs",
                        "/*/*.js",
                        "/*/*.css",
                        "/*/*.png",
                        "/*/*.ico").permitAll()
                // 鉴权管理器配置
                .anyExchange().access(authorizationManager)
                .and().exceptionHandling()
                //处理未授权
                .accessDeniedHandler(accessDeniedHandler)
                //处理未认证
                .authenticationEntryPoint(authenticationEntryPoint)
                .and().csrf().disable();
        // 指定认证服务器
        http.oauth2ResourceServer()
                .opaqueToken()
                .introspectionUri("http://localhost:8000/auth/oauth/check_token")
                .introspectionClientCredentials("root", "root");
        return http.build();
    }

}
