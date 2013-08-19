package com.apress.prospring3.ch7.aspectj;

import org.springframework.context.support.GenericXmlApplicationContext;

public class AspectJExample {

	public static void main(String[] args) {
		
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load("classpath:aspectj.xml");
		ctx.refresh();
		
		MessageWriter writer = new MessageWriter();
		writer.writeMessage();
		writer.foo();
		ctx.close();
	}

}
