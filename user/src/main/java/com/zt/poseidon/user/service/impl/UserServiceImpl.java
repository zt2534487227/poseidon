package com.zt.poseidon.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zt.poseidon.user.dao.UserDao;
import com.zt.poseidon.user.model.User;
import com.zt.poseidon.user.service.UserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author ZhouTian
 * @since 2018-08-23
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {

    @Override
    public Integer deleteById(Integer id) {
        return baseMapper.deleteById(id);
    }
}
