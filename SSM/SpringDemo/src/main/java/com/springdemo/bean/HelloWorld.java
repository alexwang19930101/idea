package com.springdemo.bean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloWorld {
	private String username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public void hello() {
		System.out.println("hello " + username);
	}
	
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		
//		HelloWorld helloWorld = (HelloWorld) ctx.getBean("helloWorld");
		HelloWorld helloWorld = (HelloWorld) ctx.getBean(HelloWorld.class);
		System.out.println(helloWorld);
		
		helloWorld.hello();
	}
}
