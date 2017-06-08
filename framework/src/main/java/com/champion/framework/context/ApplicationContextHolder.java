/**
 * @Description: 
 * @info-yangpu.winchampion.info.yangpu.common.utils
 * @FileName:ApplicationContextHolder.java
 * @Author: William
 * @CreateTime: 2017-05-17 12:56:51
 */
package com.champion.framework.context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @Description: get ApplicationContext
 * @ClassName: ApplicationContextHolder
 * @Auther: William
 * @CreateTime: 2017-05-17 12:56:51
 */
public class ApplicationContextHolder implements ApplicationContextAware, DisposableBean {
	
	private static Logger logger = LoggerFactory.getLogger(ApplicationContextHolder.class);
	
	private static ApplicationContextHolder instance = null;
	
	private ApplicationContextHolder() {}
	
	public static ApplicationContextHolder newInstance() {
		if (instance == null) {
			instance = new ApplicationContextHolder();
		}
		return instance;
	}
	
	public ApplicationContext applicationContext = null;
	
	/**
	 * 
	 * @Description: remove ApplicationContext in ApplicationContextHolder
	 * 				 set applicationContext null
	 * @ReturnType void
	 * @Author: William
	 * @CreateTime: 2017-05-17 04:32:48
	 */
	public void clearHolder() {
		if (logger.isDebugEnabled()){
			logger.debug("remove ApplicationContext in ApplicationContextHolder :" + applicationContext);
		}
		applicationContext = null;
	}
	
	/**
	 * 
	 * @Description: get bean from applicationContext by bean name and cast it to required type
	 * @Param: @param name
	 * @Param: @return
	 * @ReturnType T
	 * @Author: William
	 * @CreateTime: 2017-05-17 04:33:21
	 */
	@SuppressWarnings("unchecked")
	public <T> T getBean(String name) {
		assertContextInjected();
		return (T) applicationContext.getBean(name);
	}

	/**
	 * 
	 * @Description: get bean from applicationContext by class of type and cast it to required type
	 * @Param: @param requiredType
	 * @Param: @return
	 * @ReturnType T
	 * @Author: William
	 * @CreateTime: 2017-05-17 04:32:39
	 */
	public <T> T getBean(Class<T> requiredType) {
		assertContextInjected();
		return applicationContext.getBean(requiredType);
	}
	
	/**
	 * 
	 * @Description: check weather zhe applicationContext is null; 
	 * @ReturnType void
	 * @Author: William
	 * @CreateTime: 2017-05-17 04:34:26
	 */
	private void assertContextInjected() {
		if (instance == null) {
			logger.warn("applicationContext属性未注入， 请配置SpringContextHolder");
		}
	}
	
	@Override
	public void destroy() throws Exception {
		clearHolder();
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		ApplicationContextHolder.newInstance().applicationContext = applicationContext;
	}

	public ApplicationContext getApplicationContext() {
		assertContextInjected();
		return applicationContext;
	}
}
