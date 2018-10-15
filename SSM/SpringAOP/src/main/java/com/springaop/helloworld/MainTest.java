package com.springaop.helloworld;

/**
 * create by pinkill on ${date}
 */
public class MainTest {
    public static void main(String[] args) {
        ArithmeticCalculator arithmeticCalculator = new ArithmeticCalculatorLoggingProxy(new ArithmeticCalculatorImpl()).getProxy();
        int addResult = arithmeticCalculator.add(1, 2);
        System.out.println(addResult);
    }
}
