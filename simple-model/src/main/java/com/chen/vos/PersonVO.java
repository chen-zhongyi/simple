package com.chen.vos;

import com.chen.model.person.Person;

public class PersonVO extends OneData {

    private Long personId;
    private Integer type;
    private String username;
    private String password;
    private String zone;
    private String phone;
    private String email;
    private String name;
    private String nickname;
    private String pinyin;
    private String avatar;
    private Long birthday;
    private String intro;
    private String remark;
    private String idCard;
    private Integer sex;
    private Long firstLoginTime;
    private Long lastLoginTime;
    private Integer loginAmount;
    private String token;

    public PersonVO() {}

    public PersonVO(Person person) {
        super(person.getId());
        this.personId = person.getId();
        this.username = person.getUsername();
        this.zone = person.getZone();
        this.phone = person.getPhone();
        this.email = person.getEmail();
        this.name = person.getName();
        this.nickname = person.getNickname();
        this.pinyin = person.getPinyin();
        this.avatar = person.getAvatar();
        this.birthday = person.getBirthday();
        this.intro = person.getIntro();
        this.remark = person.getRemark();
        this.idCard = person.getIdCard();
        this.sex = person.getSex().code();
        this.token = person.getToken();
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
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

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
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
}
