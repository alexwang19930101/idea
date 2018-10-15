package com.springdemo.bean.annotation;

import com.springdemo.bean.annotation.Controller.UserController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainTest {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("beans-annotation.xml");

/*        TestObject to = (TestObject) ctx.getBean("testObject");
        System.out.println(to);



        UserRepositoryImpl ur = (UserRepositoryImpl) ctx.getBean("userRepository");
        System.out.println(ur);

        UserService us = (UserService) ctx.getBean("userService");
        System.out.println(us);*/

        UserController uc = (UserController) ctx.getBean("userController");
        System.out.println(uc);
        uc.execute();
    }
}
