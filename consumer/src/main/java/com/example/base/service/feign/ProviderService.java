package com.example.base.service.feign;

import com.example.base.po.Hello;
import com.example.base.po.Hellos;
import com.example.base.service.feign.impl.ProviderServiceImpl;
import com.example.base.utils.ResultBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author benben
 * @program cloud2021
 * @Description 生产者Feign接口定义
 * @date 2021-04-29 15:51
 */
@Component
@FeignClient(value = "provider", fallback = ProviderServiceImpl.class)
public interface ProviderService {

    @GetMapping("/getProvider")
    ResultBean<Hello> getProvider();

    @GetMapping("/getProviders")
    ResultBean<Hellos> getProviders();
}
