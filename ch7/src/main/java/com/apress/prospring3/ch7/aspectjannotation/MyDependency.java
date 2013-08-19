package com.apress.prospring3.ch7.aspectjannotation;

import org.springframework.stereotype.Component;

@Component("myDependency")
public class MyDependency {
	
	public void foo(int intVal) {
		System.out.println("foo(int): " + intVal);;
	}
	
	public void bar() {
		System.out.println("bar()");
	}

}
