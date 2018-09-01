package com.zt.poseidon.blog.model;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * 评论表
 * </p>
 *
 * @author ZhouTian
 * @since 2018-08-23
 */
@TableName("t_comment")
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id，自增长
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 评论者昵称
     */
    private String userName;
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 评论的内容
     */
    private String content;
    /**
     * 文章id
     */
    private Integer articleId;
    /**
     * 博主id
     */
    private Integer bloggerId;
    /**
     * 父级评论id
     */
    private Integer parentId;
    /**
     * 树形路径（如：0,1,2）
     */
    private String treePath;
    /**
     * 评论时间
     */
    private String commentTime;
    /**
     * 是否展示（1：是，2：否）
     */
    private Integer isShow;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 创建时间
     */
    private Date createTime;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public Integer getBloggerId() {
        return bloggerId;
    }

    public void setBloggerId(Integer bloggerId) {
        this.bloggerId = bloggerId;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getTreePath() {
        return treePath;
    }

    public void setTreePath(String treePath) {
        this.treePath = treePath;
    }

    public String getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(String commentTime) {
        this.commentTime = commentTime;
    }

    public Integer getIsShow() {
        return isShow;
    }

    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getVersion() {
        return version;
    }

    public void setVersion(Date version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "Comment{" +
        ", id=" + id +
        ", userName=" + userName +
        ", userId=" + userId +
        ", content=" + content +
        ", articleId=" + articleId +
        ", bloggerId=" + bloggerId +
        ", parentId=" + parentId +
        ", treePath=" + treePath +
        ", commentTime=" + commentTime +
        ", isShow=" + isShow +
        ", status=" + status +
        ", createTime=" + createTime +
        ", version=" + version +
        "}";
    }
}
