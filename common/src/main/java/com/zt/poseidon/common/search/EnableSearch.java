package com.zt.poseidon.common.search;

import com.zt.poseidon.common.search.elasticsearch.ElasticSearchConfig;

/**
 * @Author: ZhouTian
 * @Description:
 * @Date: 2018/8/21
 */
public @interface EnableSearch {
    Class[] register() default ElasticSearchConfig.class;
}
