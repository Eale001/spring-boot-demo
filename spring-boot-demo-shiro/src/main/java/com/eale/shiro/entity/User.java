package com.eale.shiro.entity;

import lombok.Data;

/**
 * @Author Admin
 * @Date 2020/9/25
 * @Description
 * @Version 1.0
 **/
@Data
public class User {


    private String username;

    private String password;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password=password;
    }
}
