package com.apress.prospring3.ch6.proxies;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;

public class NoOpBeforeAdvice implements MethodBeforeAdvice{

	@Override
	public void before(Method method, Object[] args, Object target)
			throws Throwable {
		// TODO Автоматически созданная заглушка метода
		
	}

}
