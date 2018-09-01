package com.zt.poseidon.blog.model;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * 我的关注
 * </p>
 *
 * @author ZhouTian
 * @since 2018-08-28
 */
@TableName("t_concern")
public class Concern implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id，自增长
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 关注的用户昵称
     */
    private String concernName;
    /**
     * 关注的用户id
     */
    private Integer concernId;
    /**
     * 关注时间
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getConcernName() {
        return concernName;
    }

    public void setConcernName(String concernName) {
        this.concernName = concernName;
    }

    public Integer getConcernId() {
        return concernId;
    }

    public void setConcernId(Integer concernId) {
        this.concernId = concernId;
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
        return "Concern{" +
        ", id=" + id +
        ", userId=" + userId +
        ", concernName=" + concernName +
        ", concernId=" + concernId +
        ", createTime=" + createTime +
        ", version=" + version +
        "}";
    }
}
