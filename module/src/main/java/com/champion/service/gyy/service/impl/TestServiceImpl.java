package com.champion.service.gyy.service.impl;

import com.champion.framework.cache.ICache;
import com.champion.framework.cache.SysCache;
import com.champion.service.gyy.dao.TestDao;
import com.champion.service.gyy.entity.User;
import com.champion.service.gyy.service.TestService;
import com.champion.framework.utils.IdGen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description:
 * @Auther： william
 * @Date：2017/6/6 0006 10:06
 */
@Service
@Transactional(readOnly = true)
public class TestServiceImpl implements TestService{

    private static final Logger logger = LoggerFactory.getLogger(TestServiceImpl.class);

    @Autowired
    private TestDao testDao;

    /**
     * @Description 查询全部用户
     * @MethodName userList
     * @param
     * @return java.util.List<com.champion.service.gyy.entity.User>
     * @author william [yeemin_shon@163.com]
     * @Date 2017/6/6 0006 10:28
     */
    @Override
    public List<User> userList() {
        return testDao.findUsers();
    }

    /**
     * @Description 新增一个用户
     * @MethodName insertUser
     * @param user
     * @return int
     * @author william [yeemin_shon@163.com]
     * @Date 2017/6/6 0006 14:11
     */
    @Override
    @Transactional(readOnly = false)
    public User insertUser(User user) {
        user.setId(IdGen.longId());
        int i = testDao.insertUser(user);
        if (i > 0) {
            logger.info("insert a user:" + user.getUsername());
        }
        return user;
    }

}
