package com.apress.prospring3.ch6;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class MessageDecorator implements MethodInterceptor {

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		System.out.print("Hello ");
		Object retVal = invocation.proceed();
		System.out.print("!");
		return retVal;
	}

}
