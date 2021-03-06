package com.springdemo.bean.annotation.repository;

import com.springdemo.bean.annotation.TestObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserJdbcRepository implements UserRepository {
    @Autowired(required = false)
    private TestObject testObject;

    @Override
    public void save() {
        System.out.println("jdbcRepository saving...");
        System.out.println(testObject);
    }
}
