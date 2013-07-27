package com.apress.prospring3.ch5.jsr330;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import com.apress.prospring3.ch5.javaconfig.MessageProvider;
import com.apress.prospring3.ch5.javaconfig.MessageRenderer;

@Named("messageRenderer")
@Singleton
public class StandardOutMessageRenderer implements MessageRenderer {

	@Inject
	@Named("messageProvider")
	private MessageProvider messageProvider = null;

	@Override
	public void render() {
		if (messageProvider == null) {
			throw new RuntimeException("You must die!!.. "
					+ StandardOutMessageRenderer.class.getName());
		}
		System.out.println(messageProvider.getMessage());
	}

	@Override
	public void setMessageProvider(MessageProvider provider) {
		this.messageProvider = provider;		
	}

	@Override
	public MessageProvider getMessageProvider() {
		return this.messageProvider;
	}

}
