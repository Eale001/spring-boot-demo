package com.eale.shiro.service;

import com.eale.shiro.entity.User;

/**
 * @Author Admin
 * @Date 2020/9/25
 * @Description //TODO
 * @Version 1.0
 **/
public interface UserService {
    User getByName(String username);
}
