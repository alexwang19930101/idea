package com.springaop.impl;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

//把该类放入IOC,再加入注解
@Order(2)
@Aspect
@Component
public class LoggingAspect {

    /**
     * 声明一个方法定义切入点表达式，不填入代码
     * 使用@Pointcut来声明切入点表达式
     * 后面其他通知直接使用方法名来引用当前切入点表达式
     */
    @Pointcut("execution(public int com.springaop.impl.ArithmeticCalculator.*(..))")
    public void decalreJointPointExpression(){}

    /**
     * 声明该方法是一个前置通知
     *
     * @param joinPoint
     */
    @Before("decalreJointPointExpression()")
    public void beforeMethod(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        List<Object> args = Arrays.asList(joinPoint.getArgs());

        System.out.println("[Before] the method " + methodName + " begins with" + args);
    }

    /**
     * 声明该方法是一个后置通知，不管是否有异常都执行
     * 后置通知还不能访问目标方法执行的结果
     *
     * @param joinPoint
     */
    @After("decalreJointPointExpression()")
    public void afterMethod(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();

        System.out.println("[After] the method " + methodName + " ends");
    }

    /**
     * 在方法正常结束后执行
     *
     * @param joinPoint
     */
    @AfterReturning(value = "decalreJointPointExpression()", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();

        System.out.println("[AfterReturning] the method " + methodName + " ends with" + result);
    }

    /**
     * 在方法出现异常时执行
     * 可以指定异常类型，在出现特定异常时执行
     *
     * @param joinPoint
     * @param ex
     */
    @AfterThrowing(value = "decalreJointPointExpression()", throwing = "ex")
    public void afterThrowing(JoinPoint joinPoint, Exception ex) {
        String methodName = joinPoint.getSignature().getName();

        System.out.println("[AfterThrowing] the method " + methodName + " occurs exception:" + ex);
    }

    /**
     * 需要带ProceedingJoinPoint
     * 类似于动态代理的全过程：ProceedingJoinPoint可以决定是否执行目标方法
     * 且必须要有返回值（目标方法的返回值）
     *
     * @param pjp
     */
   /* @Around(value = "decalreJointPointExpression()")
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
    }*/
}
