package com.apress.prospring3.ch6.proxies;

public class SimpleBean implements ISimpleBean {
	
	private long dummy = 0;
	
	@Override
	public void advised() {
		dummy = System.currentTimeMillis();
	}

	@Override
	public void unadvised() {
		dummy  = System.currentTimeMillis();
	}

}
