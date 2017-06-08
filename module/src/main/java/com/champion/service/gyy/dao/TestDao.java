package com.champion.service.gyy.dao;

import com.champion.framework.annotation.MyBatisDao;
import com.champion.service.gyy.entity.User;

import java.util.List;

/**
 * @Description:
 * @Auther： william
 * @Date：2017/6/6 0006 10:07
 */
@MyBatisDao
public interface TestDao {

    List<User> findUsers();

    int insertUser(User user);
}
