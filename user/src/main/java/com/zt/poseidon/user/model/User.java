package com.zt.poseidon.user.model;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author ZhouTian
 * @since 2018-08-23
 */
@TableName("t_user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id，自增长
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 用户账号，唯一
     */
    private String userAccount;
    /**
     * 角色类型（管理员，普通用户）
     */
    private Integer roleType;
    /**
     * 用户昵称
     */
    private String nickName;
    /**
     * 密码  MD5(MD5(密码)+校验码)
     */
    private String password;
    /**
     * 校验方式(1：密码，2：手机验证码)
     */
    private Integer checkMode;
    /**
     * 校验码
     */
    private String checkCode;
    /**
     * 真实姓名
     */
    private String realName;
    /**
     * 性别（1：男，2：女）
     */
    private Integer sex;
    /**
     * 用户头像
     */
    private String userImage;
    /**
     * 手机号
     */
    private String mobilePhone;
    /**
     * 电子邮箱
     */
    private String email;
    /**
     * 个性签名
     */
    private String sign;
    /**
     * 个人简介
     */
    private String info;
    /**
     * 链接
     */
    private String url;
    /**
     * 地区id
     */
    private Integer areaId;
    /**
     * 地址
     */
    private String address;
    /**
     * 微信标识
     */
    private String wxId;
    /**
     * qq标识
     */
    private String qqId;
    /**
     * 新浪标识
     */
    private String sinaId;
    /**
     * 用户状态（1：正常，2：审核中，3：冻结中，4：已注销）
     */
    private Integer userStatus;
    /**
     * 状态说明
     */
    private String statusMemo;
    /**
     * 登录ip
     */
    private String loginIp;
    /**
     * 登录日期
     */
    private String loginTime;
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

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public Integer getRoleType() {
        return roleType;
    }

    public void setRoleType(Integer roleType) {
        this.roleType = roleType;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getCheckMode() {
        return checkMode;
    }

    public void setCheckMode(Integer checkMode) {
        this.checkMode = checkMode;
    }

    public String getCheckCode() {
        return checkCode;
    }

    public void setCheckCode(String checkCode) {
        this.checkCode = checkCode;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWxId() {
        return wxId;
    }

    public void setWxId(String wxId) {
        this.wxId = wxId;
    }

    public String getQqId() {
        return qqId;
    }

    public void setQqId(String qqId) {
        this.qqId = qqId;
    }

    public String getSinaId() {
        return sinaId;
    }

    public void setSinaId(String sinaId) {
        this.sinaId = sinaId;
    }

    public Integer getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }

    public String getStatusMemo() {
        return statusMemo;
    }

    public void setStatusMemo(String statusMemo) {
        this.statusMemo = statusMemo;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
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
        return "User{" +
        ", id=" + id +
        ", userAccount=" + userAccount +
        ", roleType=" + roleType +
        ", nickName=" + nickName +
        ", password=" + password +
        ", checkMode=" + checkMode +
        ", checkCode=" + checkCode +
        ", realName=" + realName +
        ", sex=" + sex +
        ", userImage=" + userImage +
        ", mobilePhone=" + mobilePhone +
        ", email=" + email +
        ", sign=" + sign +
        ", info=" + info +
        ", url=" + url +
        ", areaId=" + areaId +
        ", address=" + address +
        ", wxId=" + wxId +
        ", qqId=" + qqId +
        ", sinaId=" + sinaId +
        ", userStatus=" + userStatus +
        ", statusMemo=" + statusMemo +
        ", loginIp=" + loginIp +
        ", loginTime=" + loginTime +
        ", creatTime=" + creatTime +
        ", version=" + version +
        "}";
    }
}
