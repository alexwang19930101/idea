package com.springaop.imp_xml;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

import java.util.Arrays;
import java.util.List;

public class LoggingAspect {


    public void beforeMethod(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        List<Object> args = Arrays.asList(joinPoint.getArgs());

        System.out.println("[Before] the method " + methodName + " begins with" + args);
    }
    public void afterMethod(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();

        System.out.println("[After] the method " + methodName + " ends");
    }

    public void afterReturning(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();

        System.out.println("[AfterReturning] the method " + methodName + " ends with" + result);
    }

    public void afterThrowing(JoinPoint joinPoint, Exception ex) {
        String methodName = joinPoint.getSignature().getName();

        System.out.println("[AfterThrowing] the method " + methodName + " occurs exception:" + ex);
    }

    public Object aroundMethod(ProceedingJoinPoint pjp) {
        Object result = null;
        String methodName = pjp.getSignature().getName();
        List<Object> args = Arrays.asList(pjp.getArgs());

        try {
            //前置通知
            System.out.println("the method " + methodName + " begins with " + args);
            result = pjp.proceed();
            //返回通知
            System.out.println("the method " + methodName + " ends with " + result);
        } catch (Throwable throwable) {
            System.out.println("the method " + methodName + " occurs exception:" + throwable);
        }
        //后置通知
        System.out.println("the method " + methodName + " ends");

        return result;
    }
}
