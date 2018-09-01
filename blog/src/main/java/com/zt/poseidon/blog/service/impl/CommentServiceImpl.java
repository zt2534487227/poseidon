package com.zt.poseidon.blog.service.impl;

import com.zt.poseidon.blog.model.Comment;
import com.zt.poseidon.blog.dao.CommentDao;
import com.zt.poseidon.blog.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 评论表 服务实现类
 * </p>
 *
 * @author ZhouTian
 * @since 2018-08-23
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentDao, Comment> implements CommentService {

}
