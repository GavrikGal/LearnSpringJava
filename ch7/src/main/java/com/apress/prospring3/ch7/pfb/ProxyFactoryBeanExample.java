package com.apress.prospring3.ch7.pfb;

import org.springframework.context.support.GenericXmlApplicationContext;

public class ProxyFactoryBeanExample {

	public static void main(String[] args) {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load("classpath:pfb.xml");
		ctx.refresh();
		
		MyBean bean1 = (MyBean)ctx.getBean("myBean1");
		MyBean bean2 = (MyBean)ctx.getBean("myBean2");
		
		System.out.println("Bean 1");
		bean1.execute();
		System.out.println("Bean 2");
		bean2.execute();
		ctx.close();
		
	}

}
