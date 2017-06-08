package com.champion.dao;

import com.champion.DaoBaseTest;
import com.champion.framework.utils.CollectionUtil;
import com.champion.module.dao.TUserDao;
import com.champion.service.gyy.dao.TestDao;
import com.champion.service.gyy.entity.User;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * @Description:
 * @Auther： william
 * @Date：2017/6/7 0007 15:53
 */
public class UserDaoTest extends DaoBaseTest {

    private TestDao testDao = getResource(TestDao.class);
    private TUserDao userDao = getResource(TUserDao.class);

    @Test
    public void test() {
        List<User> userList = testDao.findUsers();
        Assert.assertNotNull(userList);
        if (CollectionUtil.isNotEmpty(userList)) {
            for (User user : userList) {
                userDao.getTUserById(String.valueOf(user.getId()));
            }
        }
    }


}
