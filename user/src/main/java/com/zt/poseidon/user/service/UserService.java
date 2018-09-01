package com.zt.poseidon.user.service;

import com.zt.poseidon.user.model.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author ZhouTian
 * @since 2018-08-23
 */
public interface UserService extends IService<User> {

    Integer deleteById(Integer id);

}
