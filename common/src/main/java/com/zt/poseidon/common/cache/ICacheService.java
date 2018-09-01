package com.zt.poseidon.common.cache;

/**
 * @Author: ZhouTian
 * @Description:
 * @Date: 2018/8/21
 */
public interface ICacheService<T>{

    /**
     * 指定缓存失效时间
     * @param key 键
     * @param time 时间(秒)
     * @return
     */
     boolean expire(String key,long time);

    /**
     * 根据key 获取过期时间
     * @param key 键 不能为null
     * @return 时间(秒) 返回0代表为永久有效
     */
    Long getExpire(String key);

    /**
     * 判断key是否存在
     * @param key 键
     * @return true 存在 false不存在
     */
    boolean hasKey(String key);

    /**
     * 删除缓存
     * @param key 可以传一个值 或多个
     */
    void del(String ... key);


    /**
     * 普通缓存获取
     * @param key 键
     * @return 值
     */
    Object get(String key);

    /**
     * 普通缓存放入
     * @param key 键
     * @param value 值
     * @return true成功 false失败
     */
    boolean set(String key,Object value);

    /**
     * 普通缓存放入并设置时间
     * @param key 键
     * @param value 值
     * @param time 时间(秒) time要大于0 如果time小于等于0 将设置无限期
     * @return true成功 false 失败
     */
    boolean set(String key,Object value,long time);


    T getClient();

}
