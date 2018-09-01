package com.zt.poseidon.applet.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author: ZhouTian
 * @Description: 小程序配置文件
 * @Date: 2018/8/22
 */
@ConfigurationProperties(prefix = "zt.weixin.applet")
public class AppletProps {
    private String appId;
    private String appSecret;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }
}
