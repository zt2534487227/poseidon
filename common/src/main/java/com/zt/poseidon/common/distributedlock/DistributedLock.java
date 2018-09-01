package com.zt.poseidon.common.distributedlock;

import com.zt.poseidon.common.entity.Result;

/**
 * @Author: ZhouTian
 * @Description:
 * @Date: 2018/8/8
 */
public interface DistributedLock {

    //获取分布式锁
    Result<String> acquireDistributedLock(String name, int timeOut);

    //释放分布式锁
    void releaseDistributedLock(String lock);
}
