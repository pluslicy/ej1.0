package com.briup.apps.ej.vm;

/**
 * @program: ej
 * @description: 用户登录信息类
 * @author: charles
 * @create: 2019-10-25 13:27
 **/

public class UserVM {
    private String username;
    private String password;
    private String type;

    public UserVM(String username, String password, String type) {
        this.username = username;
        this.password = password;
        this.type = type;
    }
    public UserVM(){

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
