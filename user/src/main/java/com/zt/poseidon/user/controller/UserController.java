package com.zt.poseidon.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zt.poseidon.common.constant.Constants;
import com.zt.poseidon.common.entity.Result;
import com.zt.poseidon.common.util.Md5Encrypt;
import com.zt.poseidon.common.util.VerifyCodeUtil;
import com.zt.poseidon.user.model.User;
import com.zt.poseidon.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: ZhouTian
 * @Description:
 * @Date: 2018/8/22
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 用户登录
     * @param user
     * @return
     */
    @RequestMapping("/login")
    public Result login(User user){
        Result result=null;
        User login = userService.getOne(new QueryWrapper<User>().lambda()
                .eq(User::getUserAccount, user.getUserAccount()));
        if (null == login){
            return new Result(false,Constants.Status.BUSINESS_ERROR.getCode(),"用户不存在");
        }
        //验证密码
        String md5 = Md5Encrypt.md5(user.getPassword() + login.getCheckCode());
        if (md5.equals(login.getPassword())){
            result=new Result(true,Constants.Status.SUCCESS);
        }else {
            result=new Result(false,Constants.Status.BUSINESS_ERROR.getCode(),"密码错误");
        }
        return result;
    }

    /**
     * 校验用户名是否注册
     * @param userAccount
     * @return
     */
    @RequestMapping("/checkUserAccount")
    public Result chechUserAccount(String userAccount){
        Result result=new Result(true,Constants.Status.SUCCESS);;
        User user = userService.getOne(new QueryWrapper<User>().lambda()
                .eq(User::getUserAccount, userAccount));
        if (user != null){
            result=new Result(false,Constants.Status.BUSINESS_ERROR.getCode(),"该用户名已注册");
        }
        return result;
    }

    /**
     * 用户注册
     * @param user
     * @return
     */
    @RequestMapping("/register")
    public Result register(User user){
        //正则判断用户账号是否合法
        Result result=null;
        //有字母数字下划线组成且开头必须是字母，不能超过16位；
        Pattern pattern=Pattern.compile("^[a-zA-Z]{1}[a-zA-Z0-9_]{1,15}$");
        Matcher matcher = pattern.matcher(user.getUserAccount());
        if (!matcher.matches()){
            return new Result(false,Constants.Status.BUSINESS_ERROR.getCode(),"用户账号不合法");
        }
        //判断用户账号是否重复
        User user1 = userService.getOne(new QueryWrapper<User>().lambda()
                .eq(User::getUserAccount, user.getUserAccount()));
        if (null != user1){
            return new Result(false,Constants.Status.BUSINESS_ERROR.getCode(),"用户账号已存在");
        }
        //盐值  10位随机码
        String code = VerifyCodeUtil.generateTextCode(VerifyCodeUtil.TYPE_ALL_MIXED, 10, null);
        user.setCheckCode(code);
        //MD5 加密
        String md5Password = Md5Encrypt.md5(user.getPassword() + code);
        user.setPassword(md5Password);
        boolean insert = userService.save(user);
        if (insert){
            result=new Result(true,Constants.Status.SUCCESS);
        }else {
            result=new Result(false,Constants.Status.BUSINESS_ERROR.getCode(),"注册失败");
        }
        return result;
    }

    /**
     * 获取用户信息
     * @param userId
     * @return
     */
    @RequestMapping("/getUserInfo")
    public Result getUserInfo(Integer userId){
        Result<User> result=new Result<User>(true,Constants.Status.SUCCESS);
        User user = userService.getById(userId);
        result.setData(user);
        return result;
    }

    /**
     * 删除用户
     * @param userId
     * @return
     */
    @RequestMapping("/del")
    public Result del(Integer userId){
        Result result=new Result();
        Integer ret = userService.deleteById(userId);
        if (null != ret && ret >= 1){
            result.setSuccess(true);
        }
        return result;
    }

}
