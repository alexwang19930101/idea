package com.springdemo.bean.factorybean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * create by pinkill on ${date}
 */
public class MainTest {

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("beans-factorybean.xml");
        Car car = (Car) ctx.getBean("car");

        System.out.println(car);
    }
}
