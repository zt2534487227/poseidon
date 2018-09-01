package com.zt.poseidon.common.zookeeper;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author: ZhouTian
 * @Description:
 * @Date: 2018/8/8
 */
@ConfigurationProperties(prefix = "zt.zookeeper")
public class ZookeeperProps {
    private String addresses="localhost:4181";
    private Integer timeOut=1000;
    private String aopExpression="@annotation(com.zt.poseidon.common.distributedlock.Lock)";
    private boolean lock;

    public String getAddresses() {
        return addresses;
    }

    public void setAddresses(String addresses) {
        this.addresses = addresses;
    }

    public Integer getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(Integer timeOut) {
        this.timeOut = timeOut;
    }

    public String getAopExpression() {
        return aopExpression;
    }

    public void setAopExpression(String aopExpression) {
        this.aopExpression = aopExpression;
    }

    public boolean isLock() {
        return lock;
    }

    public void setLock(boolean lock) {
        this.lock = lock;
    }
}
