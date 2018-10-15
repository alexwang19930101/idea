package com.springaop.impl;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * create by pinkill on ${date}
 */
public class MainTest {
    public static void main(String[] args) {
        //创建IOC容器
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        //获取bean
        ArithmeticCalculator arithmeticCalculator = ctx.getBean(ArithmeticCalculator.class);
        //使用bean
        int addResult = arithmeticCalculator.add(3, 5);
        System.out.println(addResult);

        int divResult = arithmeticCalculator.div(3, 2);
        System.out.println(divResult);
    }
}
