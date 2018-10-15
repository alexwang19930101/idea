package com.springdemo.bean.generic.di;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainTest {
    public static void main(String[] args) {
        ApplicationContext ctx =  new ClassPathXmlApplicationContext("beans-generic-di.xml");
        UserService userService = (UserService) ctx.getBean("userService");
        userService.add();
    }
}
