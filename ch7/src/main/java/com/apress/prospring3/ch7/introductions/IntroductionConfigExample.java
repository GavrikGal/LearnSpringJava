package com.apress.prospring3.ch7.introductions;

import org.springframework.context.support.GenericXmlApplicationContext;

public class IntroductionConfigExample {

	public static void main(String[] args) {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load("classpath:introductions.xml");
		ctx.refresh();
		
		TargetBean bean = (TargetBean) ctx.getBean("bean");
		IsModified mod = (IsModified) bean;
		
		System.out.println("Is TargetBean?: " + (bean instanceof TargetBean));
		System.out.println("Is IsModified?: " + (bean instanceof IsModified));

		System.out
				.println("Has been modified?: " + mod.isModified());
		bean.setName("Clarence Ho");
		System.out
				.println("Has been modified?: " + mod.isModified());
		bean.setName("Rob Harrop");
		System.out
				.println("Has been modified?: " + mod.isModified());
	}

}
