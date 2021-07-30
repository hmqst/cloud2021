package com.example.base.config;

import com.example.base.service.UserDetailsServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.*;


/**
 * Oauth2 授权服务器
 */
@Configuration
@EnableAuthorizationServer
@AllArgsConstructor
public class Oauth2ServerConfig extends AuthorizationServerConfigurerAdapter {

    private TokenStore tokenStore;
    private AuthenticationManager authenticationManager;
    private PasswordEncoder passwordEncoder;
    private UserDetailsServiceImpl userDetailsService;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        //配置客户端的 service，也就是应用怎么获取到客户端的信息，一般来说是从内存或者数据库中获取，已经提供了他们的默认实现，你也可以自定义。
        clients.inMemory()
                //客户端id  标识客户的id
                .withClient("root")
                //配置密码 没有就不用配置
                .secret(passwordEncoder.encode("root"))
                //资源id
                .resourceIds("refuse_classification")
                //access_token过期时间（自定义TokenServices后此处失效）
                .accessTokenValiditySeconds(60 * 60 * 12)
                //refresh_token过期时间（自定义TokenServices后此处失效）
                .refreshTokenValiditySeconds(60 * 60 * 24 * 7)
                //此客户端可以使用的授权类型
                .authorizedGrantTypes("password", "refresh_token")
                //用来限制客户端访问范围,如果为空 则不限制
                .scopes("all");
        //.redirectUris("http://localhost/index.html");
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                //认证管理器，当你选择了资源所有者密码(password)授权类型的时候，请设置 这个属性注入一个 AuthenticationManager 对象。
                .authenticationManager(authenticationManager)
                .tokenStore(tokenStore)
                //获取token请求方式
                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST)
                .userDetailsService(userDetailsService)
                .tokenServices(defaultTokenServices());
        //.pathMapping("/oauth/token","/getToken");  //修改获取token的url
    }

    /**
     * 自定义TokenServices
     */
    @Bean
    @Primary
    public AuthorizationServerTokenServices defaultTokenServices(){
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setTokenStore(tokenStore);
        tokenServices.setSupportRefreshToken(true);
        // token有效期自定义设置，默认12小时
        tokenServices.setAccessTokenValiditySeconds(60 * 60 * 12);
        // refresh_token默认7天
        tokenServices.setRefreshTokenValiditySeconds(60 * 60 * 24 * 7);
        return tokenServices;
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("isAuthenticated()");
        security.checkTokenAccess("isAuthenticated()");
        security.allowFormAuthenticationForClients();
    }
}
