package com.champion;

import com.champion.framework.utils.ArrayUtil;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * @Description dao层测试类基类，所有dao层测试类必须继承此类
 *              测试类中，数据层实例的获取方法：
 *                  getResource(Class<T> requiredType);
 * @author william [yeemin_shon@163.com]
 * @Date 2017/6/7 0007 16:13
 */
public abstract class DaoBaseTest {

    protected Logger logger = Logger.getLogger(DaoBaseTest.class);

    public static ClassPathXmlApplicationContext context;

    static {
        context = new ClassPathXmlApplicationContext("/spring/spring-dao.xml");
        context.start();
    }

    public static <T> T getResource(Class<T> requiredType) {
        return context.getBean(requiredType);
    }

    @Test
    public void testContext() {
        logger.info("开始dao层测试。。。");
        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        if (ArrayUtil.isNotEmpty(beanDefinitionNames)) {
            int length = beanDefinitionNames.length;
            for (int i=0; i<length; i++) {
                logger.info(beanDefinitionNames[i]);
            }
        }
        Assert.assertNotNull(beanDefinitionNames);
    }
}