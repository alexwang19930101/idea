package com.springaop.helloworld;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * 动态代理
 */
public class ArithmeticCalculatorLoggingProxy implements InvocationHandler {
    private ArithmeticCalculator target;

    public ArithmeticCalculatorLoggingProxy(ArithmeticCalculator target){
        this.target = target;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("The method "+method.getName()+" begins with"+Arrays.asList(args));
        Object result = method.invoke(target, args);
        System.out.println("The method "+method.getName()+" end with" + result);

        return result;
    }

    public ArithmeticCalculator getProxy(){
        ArithmeticCalculator proxy = (ArithmeticCalculator) Proxy.newProxyInstance(target.getClass().getClassLoader(),target.getClass().getInterfaces(),this);
        return  proxy;
    }
}
