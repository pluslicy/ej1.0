package com.briup.apps.ej.vm;

/**
 * @program: ej
 * @description: 用户信息类
 * @author: charles
 * @create: 2019-10-25 14:04
 **/

public class UserInfo {
    private Long id;
    private String name;
    private String avatar;
    private String introduction;
    private String[] roles;

    public UserInfo() {
    }

    public UserInfo(long id,String name, String avatar, String introduction, String[] roles) {
        this.id = id;
        this.name = name;
        this.avatar = avatar;
        this.introduction = introduction;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String[] getRoles() {
        return roles;
    }

    public void setRoles(String[] roles) {
        this.roles = roles;
    }
}
