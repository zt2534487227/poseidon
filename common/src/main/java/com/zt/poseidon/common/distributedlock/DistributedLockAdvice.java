package com.zt.poseidon.common.distributedlock;

import com.zt.poseidon.common.constant.StatusCode;
import com.zt.poseidon.common.entity.Result;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Method;

/**
 * @Author: ZhouTian
 * @Description:
 * @Date: 2018/8/8
 */
public class DistributedLockAdvice implements MethodInterceptor {

    private DistributedLock distributedLock;

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        Method method = methodInvocation.getMethod();
        Object result=new Result<Object>(false,StatusCode.Status.TIMEOUT_ERROR);
        if (method.isAnnotationPresent(Lock.class)) {
            Lock annotation = method.getAnnotation(Lock.class);
            String name = annotation.name();
            int timeOut = annotation.timeOut();
            if (StringUtils.isNotBlank(name)){
                Result<String> stringAjaxJson = distributedLock.acquireDistributedLock(name, timeOut);
                //获取分布式锁成功,才会执行锁定的方法,否则返回超时
                if (stringAjaxJson.isSuccess()){
                   result=methodInvocation.proceed();
               }
                distributedLock.releaseDistributedLock(stringAjaxJson.getData());
            }
        }
        return result;
    }


    public DistributedLockAdvice(DistributedLock distributedLock) {
        this.distributedLock = distributedLock;
    }
}
