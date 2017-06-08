package com.champion.service.gyy.controller;

import com.champion.framework.cache.ICache;
import com.champion.framework.cache.SysCache;
import com.champion.framework.context.UserContext;
import com.champion.framework.domain.TUser;
import com.champion.framework.utils.StringUtil;
import com.champion.service.gyy.entity.User;
import com.champion.service.gyy.service.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description:
 * @Auther： william
 * @Date：2017/6/5 0005 13:43
 */
@Controller
public class TestController {

    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private TestService testService;

    @RequestMapping(value = "/")
    public String hello(Model model) {
        model.addAttribute("user", testService.userList());
        model.addAttribute("currentUserId", UserContext.currentUserId());
        return "index";
    }

    @RequestMapping(value = "/addUser")
    public String insertUser(User user, Model model) {
        ICache cache = SysCache.getInstance();
        User newUser = testService.insertUser(user);
        if (StringUtil.isEmpty(UserContext.currentUserId())) {
            logger.info("set current user: " + newUser);
            UserContext.serCurrentUserId(String.valueOf(newUser.getId()));
        }
        TUser tUser = UserContext.currentTUser();
        logger.info("current user: " + tUser);
        cache.put("user_"+user.getId(), newUser);
        return hello(model);
    }

}
