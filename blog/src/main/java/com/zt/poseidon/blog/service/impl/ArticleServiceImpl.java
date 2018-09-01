package com.zt.poseidon.blog.service.impl;

import com.zt.poseidon.blog.model.Article;
import com.zt.poseidon.blog.dao.ArticleDao;
import com.zt.poseidon.blog.service.ArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 文章表 服务实现类
 * </p>
 *
 * @author ZhouTian
 * @since 2018-08-23
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleDao, Article> implements ArticleService {

}
