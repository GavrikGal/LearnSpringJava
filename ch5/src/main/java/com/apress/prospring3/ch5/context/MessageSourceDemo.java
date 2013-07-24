package com.apress.prospring3.ch5.context;

import java.util.Locale;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MessageSourceDemo {

	public static void main(String[] args) {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load("classpath:appContext/messageSource.xml");
		ctx.refresh();
		
		Locale english = Locale.ENGLISH;
		Locale russian = new Locale("ru","RU");
		
		
		System.out.println(ctx.getMessage("msg", null, english));
		System.out.println(ctx.getMessage("msg", null, russian));
		
		System.out.println(ctx.getMessage("nameMsg", new Object[] {"Clarence", "Ho"} , english));
		
		ctx.close();
	}

}
