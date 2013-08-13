package com.apress.prospring3.ch7.introductions;

import org.springframework.aop.support.DefaultIntroductionAdvisor;

public class IsModifiedAdvisor extends DefaultIntroductionAdvisor {

	public IsModifiedAdvisor() {
		super (new IsModifiedMixin());
	}
}
