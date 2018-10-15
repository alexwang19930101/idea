package com.springdemo.bean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainTest {
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		Car car = (Car) ctx.getBean("car");
		System.out.println(car);
		
		Car car2 = (Car) ctx.getBean("car2");
		System.out.println(car2);
		
		User user = (User)ctx.getBean("user");
		System.out.println(user);
		
		User user2 = (User)ctx.getBean("user2");
		System.out.println(user2);
		
		User user3 = (User)ctx.getBean("user3");
		System.out.println(user3);
		
		User user5 = (User)ctx.getBean("user5");
		System.out.println(user5);
	}
}
