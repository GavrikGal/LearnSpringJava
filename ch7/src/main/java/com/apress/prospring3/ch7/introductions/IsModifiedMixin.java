package com.apress.prospring3.ch7.introductions;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.support.DelegatingIntroductionInterceptor;

public class IsModifiedMixin extends DelegatingIntroductionInterceptor
		implements IsModified {

	private static final long serialVersionUID = 1L;
	private boolean isModified = false;
	private Map<Method, Method> methodCache = new HashMap<Method, Method>();

	public boolean isModified() {
		return isModified;
	}

	public Object invoke(MethodInvocation invocation) throws Throwable {
		if (!isModified) {
			if ((invocation.getMethod().getName().startsWith("set"))
					&& (invocation.getArguments().length == 1)) {

				Method getter = getGetter(invocation.getMethod());

				if (getter != null) {
					Object newVal = invocation.getArguments()[0];
					Object oldVal = getter.invoke(invocation.getThis(), null);

					if ((newVal == null) && (oldVal == null)) {
						isModified = false;
					} else if ((newVal == null) && oldVal != null) {
						isModified = true;
					} else if ((newVal != null) && (oldVal == null)) {
						isModified = true;
					} else {
						isModified = (!newVal.equals(oldVal));
					}
				}
			}
		}
		return super.invoke(invocation);
	}

	private Method getGetter(Method setter) {
		Method getter = null;
		getter = (Method) methodCache.get(setter);

		if (getter != null) {
			return getter;
		}

		String getterName = setter.getName().replace("set", "get");
		try {
			getter = setter.getDeclaringClass().getMethod(getterName, null);

			synchronized (methodCache) {
				methodCache.put(setter, getter);
			}
			return getter;
		} catch (NoSuchMethodException ex) {
			return null;
		}
	}

}
