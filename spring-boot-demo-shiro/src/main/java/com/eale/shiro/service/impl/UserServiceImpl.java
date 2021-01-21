package com.eale.shiro.service.impl;

import com.eale.shiro.entity.User;
import com.eale.shiro.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @Author Admin
 * @Date 2020/9/25
 * @Description
 * @Version 1.0
 **/
@Service
public class UserServiceImpl implements UserService {
    @Override
    public User getByName(String username) {
        new User("admin","123456");
        return null;
    }
}
