package com.example.base.config.oauth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthentication;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 鉴权管理器，用于判断是否有资源的访问权限
 */
@Component
@Slf4j
public class AuthorizationManager implements ReactiveAuthorizationManager<AuthorizationContext> {

    @Override
    public Mono<AuthorizationDecision> check(Mono<Authentication> mono, AuthorizationContext authorizationContext) {
        ServerHttpRequest request = authorizationContext.getExchange().getRequest();
        // 对应跨域的预检请求直接放行
        if (request.getMethod() == HttpMethod.OPTIONS) {
            return Mono.just(new AuthorizationDecision(true));
        }
        // 请求地址
        URI uri = authorizationContext.getExchange().getRequest().getURI();
        String path = uri.getPath();
        System.out.println(path);
        // 获取路径对应的可访问角色信息
        List<String> authorities;
        if (!path.contains("consumer")) {
            authorities = new ArrayList<>();
        } else {
            authorities = new ArrayList<String>() {{
                add("ROLE_ADMIN");
                add("ROLE_USER");
            }};
        }
        //认证通过且角色匹配的用户可访问当前路径
        /*
        OAuth2IntrospectionAuthenticatedPrincipal principal = (OAuth2IntrospectionAuthenticatedPrincipal)authentication.getPrincipal();
        System.out.println(principal.getAttribute("authorities").toString());
        */
        return mono
                .filter(authentication -> {
//                    System.out.println(authentication.getClass());
//                    System.out.println((authentication).getAuthorities());
//                    System.out.println(((BearerTokenAuthentication)authentication).getTokenAttributes());
                    return authentication.isAuthenticated();
                })
                .flatMapIterable(authentication -> {
                    Map<String, Object> tokenAttributes = ((BearerTokenAuthentication) authentication).getTokenAttributes();
                    return (List<String>) tokenAttributes.get("authorities");
                })
                .any(authorities::contains)
                .map(AuthorizationDecision::new)
                .defaultIfEmpty(new AuthorizationDecision(false));
    }
}
