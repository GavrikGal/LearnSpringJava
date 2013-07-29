package com.apress.prospring3.ch6.crypto;

import org.springframework.aop.framework.ProxyFactory;

public class AfterAdviceExample {

	public static void main(String[] args) {
		KeyGenerator keyGen = getKeyGenerator();
		
		for(int x = 0; x < 10; x++) {
			try {
				long key = keyGen.getKey();
				System.out.println("Key: " + key);
			} catch(SecurityException ex) {
				System.out.println("Weak Key Generated!");
				
			}
		}
		System.exit(0);
	}
	
	private static KeyGenerator getKeyGenerator() {
		KeyGenerator target = new KeyGenerator();
		
		ProxyFactory factory = new ProxyFactory();
		factory.setTarget(target);
		factory.addAdvice(new WeakKeyChechAdvice());
		
		return (KeyGenerator) factory.getProxy();
	}

}
