package com.bai.service;

import com.bai.pojo.User;

public interface UserService {

    /**
     * 注册用户
     * */
    public void registUser(User user);
    /**
     * 查看用户名是否存在
     * 若存在返回true
     * 不存咋返回false，可用
     * */
    public boolean isExistsUsername(String name);

    /**
     * 返回null登录失败
    * */
    public User loginUser(User user);
}
