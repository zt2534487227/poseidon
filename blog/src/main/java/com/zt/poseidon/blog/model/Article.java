package com.zt.poseidon.blog.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 文章表
 * </p>
 *
 * @author ZhouTian
 * @since 2018-08-23
 */
@TableName("t_article")
public class Article implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id，自增长
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 标题
     */
    private String title;
    /**
     * 博客摘要
     */
    private String desc;
    /**
     * 文章内容
     */
    private String content;
    /**
     * 分类id
     */
    private Integer categoryId;
    /**
     * 标签（多个用","分隔）
     */
    private String tags;
    /**
     * 发布时间
     */
    private String publishTime;
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 点击数
     */
    private Integer ckickHit;
    /**
     * 评论数
     */
    private Integer replyHit;
    /**
     * 文章模式（1：公开，2：私有）
     */
    private Integer showMode;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 创建时间
     */
    private Date creatTime;
    /**
     * 版本号
     */
    private Date version;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCkickHit() {
        return ckickHit;
    }

    public void setCkickHit(Integer ckickHit) {
        this.ckickHit = ckickHit;
    }

    public Integer getReplyHit() {
        return replyHit;
    }

    public void setReplyHit(Integer replyHit) {
        this.replyHit = replyHit;
    }

    public Integer getShowMode() {
        return showMode;
    }

    public void setShowMode(Integer showMode) {
        this.showMode = showMode;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    public Date getVersion() {
        return version;
    }

    public void setVersion(Date version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "Article{" +
        ", id=" + id +
        ", title=" + title +
        ", desc=" + desc +
        ", content=" + content +
        ", categoryId=" + categoryId +
        ", tags=" + tags +
        ", publishTime=" + publishTime +
        ", userId=" + userId +
        ", ckickHit=" + ckickHit +
        ", replyHit=" + replyHit +
        ", showMode=" + showMode +
        ", status=" + status +
        ", creatTime=" + creatTime +
        ", version=" + version +
        "}";
    }
}
