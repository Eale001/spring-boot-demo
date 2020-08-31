package com.eale.thymeleaf.dao;

import com.eale.thymeleaf.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author Admin
 * @Date 2020/8/28
 * @Description //TODO
 * @Version 1.0
 **/
public interface UserRepository extends JpaRepository<User,Integer> {
}
