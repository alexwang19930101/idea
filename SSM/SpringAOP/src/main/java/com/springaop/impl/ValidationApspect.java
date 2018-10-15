package com.springaop.impl;

import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Order(1)
@Aspect
@Component
public class ValidationApspect {

    @Before(value = "com.springaop.impl.LoggingAspect.decalreJointPointExpression()")
    public void validateArgs(JoinPoint joinPoint) {
        List<Object> args = Arrays.asList(joinPoint.getArgs());

        System.out.println("validate args:" + args);
    }
}
