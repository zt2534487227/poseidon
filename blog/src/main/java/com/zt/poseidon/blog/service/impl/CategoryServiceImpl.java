package com.zt.poseidon.blog.service.impl;

import com.zt.poseidon.blog.model.Category;
import com.zt.poseidon.blog.dao.CategoryDao;
import com.zt.poseidon.blog.service.CategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 分类表 服务实现类
 * </p>
 *
 * @author ZhouTian
 * @since 2018-08-23
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, Category> implements CategoryService {

}
