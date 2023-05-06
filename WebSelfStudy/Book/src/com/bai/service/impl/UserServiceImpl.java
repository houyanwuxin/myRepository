package com.bai.service.impl;

import com.bai.Dao.UserDao;
import com.bai.Dao.impl.UserDaoImpl;
import com.bai.pojo.User;
import com.bai.service.UserService;

public class UserServiceImpl implements UserService {
    private UserDao userDao=new UserDaoImpl();

    @Override
    public void registUser(User user) {
        userDao.saveUser(user);
    }

    @Override
    public boolean isExistsUsername(String name) {
        if(userDao.queryUserByUsername(name)==null){
            return false;
        }
        return true;
    }

    @Override
    public User loginUser(User user) {
        return userDao.queryUserByUsernameAndPassword(user.getUsername(),user.getPassword());
    }
}
