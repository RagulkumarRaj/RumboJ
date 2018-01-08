package com.rumboj.services.beanCreationService;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanFactory{
	private static ClassPathXmlApplicationContext context;
	static{
		context = new ClassPathXmlApplicationContext("beans.xml");
	}
	public static Object getInstance(String className){
		Object obj = context.getBean(className);
		return obj;
	}
	public static void shutdwnHook(){
		context.registerShutdownHook();
	}
}