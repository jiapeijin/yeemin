package com.champion.service.gyy.service;

import com.champion.service.gyy.entity.User;

import java.util.List;

/**
 * @Description:
 * @Auther： william
 * @Date：2017/6/6 0006 10:06
 */
public interface TestService {

    /**
     * @Description 查询全部用户
     * @MethodName userList
     * @param
     * @return java.util.List<com.champion.service.gyy.entity.User>
     * @author william [yeemin_shon@163.com]
     * @Date 2017/6/6 0006 10:28
     */
    List<User> userList();

    /**
     * @Description 新增一个用户
     * @MethodName insertUser
     * @param user
     * @return int
     * @author william [yeemin_shon@163.com]
     * @Date 2017/6/6 0006 14:10
     */
    User insertUser(User user);
}
