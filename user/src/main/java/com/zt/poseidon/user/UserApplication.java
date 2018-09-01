package com.zt.poseidon.user;

import com.zt.poseidon.common.datasource.EnableDataSourceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: ZhouTian
 * @Description:
 * @Date: 2018/8/21
 */
@SpringBootApplication
@EnableDataSourceConfig
public class UserApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }
}