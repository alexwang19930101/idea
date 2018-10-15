package com.springaop.helloworld;

public class ArithmeticCalculatorLogImpl implements ArithmeticCalculator {
    @Override
    public int add(int i, int j) {
        System.out.println("The method add begins with[" + i + " , " + j + "]");
        System.out.println("The method add end with" + (i + j));
        return i + j;
    }

    @Override
    public int sub(int i, int j) {
        System.out.println("The method sub begins with[" + i + " , " + j + "]");
        System.out.println("The method sub end with" + (i + j));
        return i - j;
    }

    @Override
    public int mul(int i, int j) {
        System.out.println("The method mul begins with[" + i + " , " + j + "]");
        System.out.println("The method mul end with" + (i + j));
        return i * j;
    }

    @Override
    public int div(int i, int j) {
        System.out.println("The method div begins with[" + i + " , " + j + "]");
        System.out.println("The method div end with" + (i + j));
        return i / j;
    }
}
