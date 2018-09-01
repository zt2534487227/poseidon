package com.zt.poseidon.blog.controller;

import com.zt.poseidon.common.entity.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: ZhouTian
 * @Description:
 * @Date: 2018/8/28
 */
@RestController
@RequestMapping("/blog")
public class IndexController {




    @RequestMapping("/")
    public Result get(){
        return null;
    }
}
