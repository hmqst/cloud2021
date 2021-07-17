package com.example.base.po;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Set;

/**
 * Oauth2获取Token返回信息封装
 * Created by macro on 2020/7/17.
 */
@Data
@Builder
public class Oauth2TokenDto {
    private String access_token;
    private String refresh_token;
    private String token_type;
    private int expires_in;
    private Set<String> scope;
}
