package com.zt.poseidon.common.search.solr;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @Author: ZhouTian
 * @Description:
 * @Date: 2018/7/11
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(SolrConfig.class)
public @interface EnableSolrManage {
}
