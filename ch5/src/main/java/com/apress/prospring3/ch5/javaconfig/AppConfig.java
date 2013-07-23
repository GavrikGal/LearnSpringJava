package com.apress.prospring3.ch5.javaconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
	
	// XML:
	// <bean id="messageProvider"
	// class="com.apress.prospring3.ch5.javaconfig.ConfigurableMessageProvider"/>
	@Bean
	public MessageProvider messageProvider() {
		return new ConfigurableMessageProvider();
	}
	
	// XML:
	// <bean id="messageRenderer"
	// class="com.apress.prospring3.ch5.javaconfig.StandardOutMessageRenderer"
	// p:messageProvider-ref="messageProvider" />
	@Bean
	public MessageRenderer messageRenderer() {
		MessageRenderer renderer = new StandardOutMessageRenderer();
		
		// Внедрение через метод
		renderer.setMessageProvider(messageProvider());
		
		return renderer;
	}

}
