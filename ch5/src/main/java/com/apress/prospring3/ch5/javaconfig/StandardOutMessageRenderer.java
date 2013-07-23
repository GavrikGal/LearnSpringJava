package com.apress.prospring3.ch5.javaconfig;

public class StandardOutMessageRenderer implements MessageRenderer {

	private MessageProvider messageProvider = null;

	@Override
	public void render() {
		if (messageProvider == null) {
			throw new RuntimeException("You must set ....."
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
