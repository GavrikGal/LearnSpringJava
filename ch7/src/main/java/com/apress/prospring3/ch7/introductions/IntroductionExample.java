package com.apress.prospring3.ch7.introductions;

import org.springframework.aop.IntroductionAdvisor;
import org.springframework.aop.framework.ProxyFactory;

public class IntroductionExample {

	public static void main(String[] args) {
		TargetBean target = new TargetBean();
		target.setName("Clarence Ho");

		IntroductionAdvisor advisor = new IsModifiedAdvisor();

		ProxyFactory pf = new ProxyFactory();
		pf.setTarget(target);
		pf.addAdvisor(advisor);
		pf.setOptimize(true);
		TargetBean proxy = (TargetBean) pf.getProxy();
		IsModified proxyInterface = (IsModified) proxy;

		System.out.println("Is TargetBean?: " + (proxy instanceof TargetBean));
		System.out.println("Is IsModified?: " + (proxy instanceof IsModified));

		System.out
				.println("Has been modified?: " + proxyInterface.isModified());
		proxy.setName("Clarence Ho");
		System.out
				.println("Has been modified?: " + proxyInterface.isModified());
		proxy.setName("Rob Harrop");
		System.out
				.println("Has been modified?: " + proxyInterface.isModified());
	}

}
