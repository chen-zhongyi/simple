package com.chen.model.person;

import com.chen.enums.PersonType;
import com.chen.enums.Sex;
import com.chen.model.BaseModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
public class Person extends BaseModel {

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = STRING + "'用户类型'")
    private PersonType type;
    @Column(columnDefinition = STRING + "'用户名'")
    private String username;
    @Column(columnDefinition = STRING + "'密码'")
    private String password;
    @Column(columnDefinition = STRING + "'地区'")
    private String zone;
    @Column(columnDefinition = STRING + "'手机'")
    private String phone;
    @Column(columnDefinition = STRING + "'邮箱'")
    private String email;
    @Column(columnDefinition = STRING + "'姓名'")
    private String name;
    @Column(columnDefinition = STRING + "'昵称'")
    private String nickname;
    @Column(columnDefinition = STRING + "'拼音'")
    private String pinyin;
    @Column(columnDefinition = STRING + "'头像'")
    private String avatar;
    @Column(columnDefinition = LONG + "'出生日期'")
    private Long birthday;
    @Column(columnDefinition = STRING_1000 + "'简介'")
    private String intro;
    @Column(columnDefinition = STRING_1000 + "'备注'")
    private String remark;
    @Column(columnDefinition = STRING + "'身份证号'")
    private String idCard;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = STRING + "'性别'")
    private Sex sex = Sex.NO_POINT;
    @Column(columnDefinition = LONG + "'首次登录时间'")
    private Long firstLoginTime;
    @Column(columnDefinition = LONG + "'最后登录时间'")
    private Long lastLoginTime;
    @Column(columnDefinition = LONG + "'登录次数'")
    private Integer loginAmount;

    transient private String token; // jwt

    public PersonType getType() {
        return type;
    }

    public void setType(PersonType type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Long getBirthday() {
        return birthday;
    }

    public void setBirthday(Long birthday) {
        this.birthday = birthday;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public Long getFirstLoginTime() {
        return firstLoginTime;
    }

    public void setFirstLoginTime(Long firstLoginTime) {
        this.firstLoginTime = firstLoginTime;
    }

    public Long getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Long lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Integer getLoginAmount() {
        return loginAmount;
    }

    public void setLoginAmount(Integer loginAmount) {
        this.loginAmount = loginAmount;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "Person{" +
                "type=" + type +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", zone='" + zone + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                ", pinyin='" + pinyin + '\'' +
                ", avatar='" + avatar + '\'' +
                ", birthday=" + birthday +
                ", intro='" + intro + '\'' +
                ", remark='" + remark + '\'' +
                ", idCard='" + idCard + '\'' +
                ", sex=" + sex +
                ", firstLoginTime=" + firstLoginTime +
                ", lastLoginTime=" + lastLoginTime +
                ", loginAmount=" + loginAmount +
                '}';
    }
}
