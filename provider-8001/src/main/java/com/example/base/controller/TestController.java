package com.example.base.controller;

import com.example.base.po.Hello;
import com.example.base.po.Hellos;
import com.example.base.utils.ResultBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.UUID;

/**
 * @author benben
 * @program cloud2021
 * @Description
 * @date 2021-04-27 15:33
 */
@RestController
public class TestController {

    @Value("${server.port}")
    private String port;

    @GetMapping("/getProvider")
    public ResultBean<Hello> getProvider(){
        return ResultBean.success(new Hello(1, "端口号：" + port + "  " + UUID.randomUUID()));
    }

    @GetMapping("/getProviders")
    public ResultBean<Hellos> getProviders(){
        return ResultBean.success(new Hellos(2, new ArrayList<String>(){{
            add("provider1");
            add("provider2");
            add("provider3");
            add("provider4");
        }}));
    }
}
