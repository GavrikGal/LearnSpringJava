package com.apress.prospring3.ch6.annotationpc;

import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;

import com.apress.prospring3.ch6.staticpc.SimpleAdvice;

public class AnnotationPointcutExample {

	public static void main(String[] args) {
		SampleAnnotationBean target = new SampleAnnotationBean();

		AnnotationMatchingPointcut pc = AnnotationMatchingPointcut
				.forMethodAnnotation(AdviceRequired.class);
		Advisor advisor = new DefaultPointcutAdvisor(pc, new SimpleAdvice());
		
		ProxyFactory pf = new ProxyFactory();
		pf.setTarget(target);
		pf.addAdvisor(advisor);
		
		SampleAnnotationBean proxy = (SampleAnnotationBean) pf.getProxy();
		
		proxy.foo(100);
		proxy.bar();
		
	}

}
