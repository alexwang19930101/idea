package com.springaop.imp_xml;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class MainTest {
    public static void main(String[] args) {
        //创建IOC容器
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext-xml.xml");
        //获取bean
        ArithmeticCalculator arithmeticCalculator = (ArithmeticCalculator) ctx.getBean("arithmeticCalculator");
        //使用bean
        int addResult = arithmeticCalculator.add(3, 5);
        System.out.println(addResult);

        int divResult = arithmeticCalculator.div(3, 2);
        System.out.println(divResult);
    }
}
