package com.apress.prospring3.ch6.regexppc;

import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.JdkRegexpMethodPointcut;

import com.apress.prospring3.ch6.staticpc.SimpleAdvice;

public class RegexpPointcutExample {

	public static void main(String[] args) {
		RegexpBean target = new RegexpBean();

		JdkRegexpMethodPointcut pc = new JdkRegexpMethodPointcut();
		pc.setPattern(".*foo.*");
		Advisor advisor = new DefaultPointcutAdvisor(pc, new SimpleAdvice());
		
		ProxyFactory pf = new ProxyFactory();
		pf.setTarget(target);
		pf.addAdvisor(advisor);
		RegexpBean proxy = (RegexpBean) pf.getProxy();
		
		proxy.foo1();
		proxy.foo2();
		proxy.bar();
		System.exit(0);
	}
}