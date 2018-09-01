package com.zt.poseidon.flie.controller;

import com.zt.poseidon.common.entity.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: ZhouTian
 * @Description: 文件管理
 * @Date: 2018/8/28
 */
@RestController
@RequestMapping("/file")
public class FileController {

    @RequestMapping("/")
    public Result file(HttpServletRequest request){
        return null;
    }

}
