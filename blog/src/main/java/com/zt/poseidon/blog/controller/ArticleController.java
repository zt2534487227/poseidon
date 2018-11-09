package com.zt.poseidon.blog.controller;

import com.zt.poseidon.blog.model.Article;
import com.zt.poseidon.blog.service.ArticleService;
import com.zt.poseidon.common.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: ZhouTian
 * @Description: 文章管理
 * @Date: 2018/8/28
 */
@RestController
@RequestMapping("/blog/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;


    /**
     * 添加文章
     * @return
     */
    @RequestMapping("/save")
    public Result add(Article article){
        Result result=new Result();
        boolean insert = articleService.save(article);
        result.setSuccess(insert);
        return result;
    }

    /**
     * 发布文章
     * @return
     */
    @RequestMapping("/publish")
    public Result publish(Article article){
        articleService.saveOrUpdate(article);
        //发布文章
        articleService.updateById(article);
        return null;
    }

    /**
     * 文章详情
     * @return
     */
    @RequestMapping("/")
    public Result detail(){
        return null;
    }


}
