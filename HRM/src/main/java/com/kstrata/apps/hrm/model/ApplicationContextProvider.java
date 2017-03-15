package com.kstrata.apps.hrm.model;


import org.springframework.beans.BeansException;  
import org.springframework.context.ApplicationContext;  
import org.springframework.context.ApplicationContextAware;  

/** 
* @author Dinesh 
* 
*/  
public class ApplicationContextProvider implements ApplicationContextAware { 
	
	private static ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}  

	

} 