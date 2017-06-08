package com.champion.module.dao;

import com.champion.framework.annotation.MyBatisDao;
import com.champion.framework.domain.TUser;

/**
 * @Description:
 * @Auther： william
 * @Date：2017/6/7 0007 14:36
 */
@MyBatisDao
public interface TUserDao {

    /**
     * @Description get TUser by id
     * @MethodName getTUserById
     * @param id
     * @return TUser
     * @author william [yeemin_shon@163.com]
     * @Date 2017/6/7 0007 14:36
     */
    TUser getTUserById(String id);
}
