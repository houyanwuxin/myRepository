package com.bai.test;

import com.bai.Dao.UserDao;
import com.bai.Dao.impl.UserDaoImpl;
import com.bai.pojo.User;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoImplTest {

    UserDao userDao = new UserDaoImpl();
    @org.junit.jupiter.api.Test
    void queryUserByUsername() {
        if (userDao.queryUserByUsername("bai") == null ){
            System.out.println("用户名可用！");
        } else {
            System.out.println("用户名已存在！");
        }
    }

    @org.junit.jupiter.api.Test
    void queryUserByUsernameAndPassword() {
        if ( userDao.queryUserByUsernameAndPassword("admin","admin1234") == null) {
            System.out.println("用户名或密码错误，登录失败");
        } else {
            System.out.println("查询成功");
        }
    }

    @org.junit.jupiter.api.Test
    void saveUser() {
        System.out.println( userDao.saveUser(new User("admin", "admin", "admin@qq.com")) );
    }
}