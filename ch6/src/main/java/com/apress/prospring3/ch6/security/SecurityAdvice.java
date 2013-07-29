package com.apress.prospring3.ch6.security;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;

public class SecurityAdvice implements MethodBeforeAdvice {

	private SecurityManager securityManager;

	public SecurityAdvice() {
		this.securityManager = new SecurityManager();
	}

	@Override
	public void before(Method method, Object[] args, Object terget)
			throws Throwable {

		UserInfo user = securityManager.getLoggedOnUser();

		if (user == null) {
			System.out.println("No user authenticated");
			throw new SecurityException(
					"You must login before attempting to invoke the method: "
							+ method.getName());
		} else if ("clarence".equals(user.getUserName())) {
			System.out.println("Logged in user is clarance - OKAY!");
		} else {
			System.out.println("Logged in user is " + user.getUserName()
					+ " NOT GOOD :(");
			throw new SecurityException("User " + user.getUserName()
					+ " is not allowed access to method " + method.getName());
		}
	}
}
