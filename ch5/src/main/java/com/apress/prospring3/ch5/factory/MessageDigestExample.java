package com.apress.prospring3.ch5.factory;

import org.springframework.context.support.GenericXmlApplicationContext;

public class MessageDigestExample {

	public static void main(String[] args) {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load("classpath:factory/factory.xml");
		ctx.refresh();
		
		MessageDigester digester = (MessageDigester) ctx.getBean("digester");
		digester.digest("cbcmrb12");
	}

}
