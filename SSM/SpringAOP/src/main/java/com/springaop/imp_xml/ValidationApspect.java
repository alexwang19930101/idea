package com.springaop.imp_xml;

import org.aspectj.lang.JoinPoint;

import java.util.Arrays;
import java.util.List;


public class ValidationApspect {

    public void validateArgs(JoinPoint joinPoint) {
        List<Object> args = Arrays.asList(joinPoint.getArgs());

        System.out.println("-->validate args:" + args);
    }
}
