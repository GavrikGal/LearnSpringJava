package com.apress.prospring3.ch5.profile;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ProfileJavaConfigExample {

	public static void main(String[] args) {

		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.getEnvironment().setActiveProfiles("highschool");
		ctx.register(KindergartenConfig.class, HighschoolConfig.class);
		ctx.refresh();

		FoodProviderService foodProviderService = ctx.getBean(
				"foodProviderService", FoodProviderService.class);
		List<Food> lunchSet = foodProviderService.provideLunchSet();
		
		for(Food food: lunchSet) {
			System.out.println("  Food: " + food.getName());
		}
		
		ctx.close();
	}
}
