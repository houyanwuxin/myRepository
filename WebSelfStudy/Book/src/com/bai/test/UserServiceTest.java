package com.bai.test;

import com.bai.pojo.User;
import com.bai.service.UserService;
import com.bai.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    UserService userService=new UserServiceImpl();
    @Test
    void registUser() {
        userService.registUser(new User("hi","123456","hi@qq.com"));
    }

    @Test
    void isExistsUsername() {
        if(userService.isExistsUsername("hi"))
            System.out.println("用户存在");
        else
            System.out.println("用户不存在，可用");
    }

    @Test
    void loginUser() {
        System.out.println(userService.loginUser(new User("admin", "admin", null)));
    }
}