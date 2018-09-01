package com.zt.poseidon.applet.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: ZhouTian
 * @Description:
 * @Date: 2018/8/21
 */
@RestController
public class IndexController {


    @RequestMapping("/")
    public String index(){
        return "hello,world";
    }
}
