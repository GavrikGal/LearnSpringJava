package com.apress.prospring3.ch5.jsr330;

import javax.inject.Inject;
import javax.inject.Named;

import com.apress.prospring3.ch5.javaconfig.MessageProvider;

@Named("messageProvider")
public class CofigurableMessageProvider implements MessageProvider {

	private String message = "Default message";

	public CofigurableMessageProvider() {
	}

	@Inject
	@Named("message")
	public CofigurableMessageProvider(String message) {
		this.message = message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}

}
