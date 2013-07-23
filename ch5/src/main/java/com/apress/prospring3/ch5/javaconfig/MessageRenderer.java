package com.apress.prospring3.ch5.javaconfig;

public interface MessageRenderer {
	public void render();

	public void setMessageProvider(MessageProvider provider);

	public MessageProvider getMessageProvider();
}
