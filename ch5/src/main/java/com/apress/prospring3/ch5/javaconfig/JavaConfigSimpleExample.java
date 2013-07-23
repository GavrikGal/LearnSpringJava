package com.apress.prospring3.ch5.javaconfig;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;

public class JavaConfigSimpleExample {

	public static void main(String[] args) {
		//ApplicationContext ctx = new ClassPathXmlApplicationContext(
		//		"classpath:app-context.xml");
		ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
		MessageRenderer renderer = ctx.getBean("messageRenderer",
				MessageRenderer.class);

		renderer.render();
	}
}
