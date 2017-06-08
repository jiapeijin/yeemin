package com.champion.framework.context;

import com.champion.framework.cache.ICache;
import com.champion.framework.cache.UserCache;
import com.champion.framework.constant.Global;
import com.champion.framework.domain.TUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpSession;
import java.util.Locale;

/**
 * @Description: user in session and cache
 * @Auther： william
 * @Date：2017/6/7 0007 12:38
 */
public class UserContext {

    private final static Logger logger = LoggerFactory.getLogger(UserContext.class);

    private static final ICache userCache = UserCache.getInstance();

    private static final TUserStorage userStorage = TUserStorage.getInstance();

    private static final String USER_CACHE_PREFIX = "user_";

    /**
     * cannot instantiate
     */
    private UserContext() {
        Locale.setDefault(Locale.PRC);
    }

    /**
     * @Description set loginUserId in session
     * @MethodName serUserId
     * @param id
     * @return void
     * @author william [yeemin_shon@163.com]
     * @Date 2017/6/7 0007 12:50
     */
    public static void serCurrentUserId(String id) {
        HttpSession httpSession = HttpServletUtil.getSession();
        httpSession.setAttribute(Global.LocaleConfig.SESSION_UID_KEY, id);
    }

    /**
     * @Description set loginUserId in session
     * @MethodName setUserId
     * @param httpSession
     * @param id
     * @return void
     * @author william [yeemin_shon@163.com]
     * @Date 2017/6/7 0007 12:51
     */
    public static void setCurrentUserId(HttpSession httpSession, String id) {
        httpSession.setAttribute(Global.LocaleConfig.SESSION_UID_KEY, id);
    }

    /**
     * @Description get current user's id
     * @MethodName currentUserId
     * @param
     * @return java.lang.String
     * @author william [yeemin_shon@163.com]
     * @Date 2017/6/7 0007 13:27
     */
    public static String currentUserId() {
        return (String) HttpServletUtil.getSession().getAttribute(Global.LocaleConfig.SESSION_UID_KEY);
    }

    /**
     * @Description get current user
     * @MethodName currentUser
     * @param
     * @return com.champion.framework.domain.TUser
     * @author william [yeemin_shon@163.com]
     * @Date 2017/6/7 0007 14:33
     */
    public static TUser currentTUser() {
        return getTUserById(currentUserId());
    }

    /**
     * @Description put tuser into cache
     * @MethodName addUserCache
     * @param tUser
     * @return void
     * @author william [yeemin_shon@163.com]
     * @Date 2017/6/7 0007 14:31
     */
    public static void addUserCache(TUser tUser) {
        userCache.put(USER_CACHE_PREFIX + tUser.getId(), tUser);
    }

    /**
     * @Description get tuser by id in cache
     * @MethodName getTUserById
     * @param id
     * @return com.champion.framework.domain.TUser
     * @author william [yeemin_shon@163.com]
     * @Date 2017/6/7 0007 14:32
     */
    public static TUser getTUserById(String id) {
        Object obj = userCache.get(USER_CACHE_PREFIX + id);
        if (obj == null) {
            //get user from db
            obj = userStorage.findTUserById(id);
            if (obj != null) {
                addUserCache((TUser) obj);
            }
        }
        return (TUser) obj;
    }
}
