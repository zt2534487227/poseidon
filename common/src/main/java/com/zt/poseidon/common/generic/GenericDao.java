package com.zt.poseidon.common.generic;

/**
 * @Author: ZhouTian
 * @Description: 所有自定义Dao的顶级接口, 封装常用的增删查改操作,
 *  Model : 代表数据库中的表 映射的Java对象类型
 *  PK :代表对象的主键类型
 * @Date: 2018/7/9
 */
public interface GenericDao<Model,PK> {

    int deleteByPrimaryKey(PK id);

    int insertSelective(Model model);

    Model selectByPrimaryKey(PK id);

    int updateByPrimaryKeySelective(Model model);

}
