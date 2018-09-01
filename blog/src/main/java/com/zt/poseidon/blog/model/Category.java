package com.zt.poseidon.blog.model;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * 分类表
 * </p>
 *
 * @author ZhouTian
 * @since 2018-08-23
 */
@TableName("t_category")
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id，自增长
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 分类名称
     */
    private String name;
    /**
     * 排序
     */
    private Integer index;
    /**
     * 状态（1：有效，2：无效）
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
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
        return "Category{" +
        ", id=" + id +
        ", name=" + name +
        ", index=" + index +
        ", status=" + status +
        ", creatTime=" + creatTime +
        ", version=" + version +
        "}";
    }
}
