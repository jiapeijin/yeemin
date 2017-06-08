package com.champion.framework.context;

import com.champion.framework.domain.TUser;
import com.champion.module.dao.TUserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @Description: tuser connect to db
 * @Auther： william
 * @Date：2017/6/7 0007 14:38
 */
public class TUserStorage {

    private static final Logger logger = LoggerFactory.getLogger(TUserStorage.class);

    private static final TUserDao userDao = ApplicationContextHolder.newInstance().getBean(TUserDao.class);

    private TUserStorage() { }

    private static TUserStorage instance = null;

    public static TUserStorage getInstance() {
        if (instance == null) {
            instance = new TUserStorage();
        }
        return instance;
    }

    /**
     * @Description
     * @MethodName findTUserById
     * @param id
     * @return com.champion.framework.domain.TUser
     * @author william [yeemin_shon@163.com]
     * @Date 2017/6/7 0007 14:40
     */
    public TUser findTUserById(String id) {
        return userDao.getTUserById(id);
    }


}
