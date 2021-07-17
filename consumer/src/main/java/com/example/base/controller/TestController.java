package com.example.base.controller;

import com.example.base.po.Hello;
import com.example.base.po.Hellos;
import com.example.base.service.feign.ProviderService;
import com.example.base.utils.ResultBean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author benben
 * @program cloud
 * @Description
 * @date 2021-04-27 13:21
 */
@RestController
public class TestController {

    @Resource
    private ProviderService providerService;

    @GetMapping("/getConsumer")
    public ResultBean<Hello> getConsumer() {
        ResultBean<Hello> hello = providerService.getProvider();
        return hello;
    }

    @GetMapping("/getConsumers")
    public ResultBean<Hellos> getConsumers() {
        ResultBean<Hellos> hellos = providerService.getProviders();
        return hellos;
    }


}
