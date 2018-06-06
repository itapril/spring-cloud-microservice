package com.itapril.cloud.oauth.server.domain;

import java.io.Serializable;

/**
 * Created by itapril on 2018/6/4 9:17.
 */
public class User implements Serializable{

    private Long id;
    private String userName;
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
