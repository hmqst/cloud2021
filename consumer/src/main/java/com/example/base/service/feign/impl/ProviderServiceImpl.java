package com.example.base.service.feign.impl;

import com.example.base.po.Hello;
import com.example.base.po.Hellos;
import com.example.base.service.feign.ProviderService;
import com.example.base.utils.ResultBean;
import org.springframework.stereotype.Component;

/**
 * @author benben
 * @program cloud2021
 * @Description
 * @date 2021-04-30 13:24
 */
@Component
public class ProviderServiceImpl implements ProviderService {

    @Override
    public ResultBean<Hello> getProvider() {
        return ResultBean.fail("getProvider异常：provider服务器异常或运行出错");
    }

    @Override
    public ResultBean<Hellos> getProviders() {
        return ResultBean.fail("getProviders异常：provider服务器异常或运行出错");
    }
}
