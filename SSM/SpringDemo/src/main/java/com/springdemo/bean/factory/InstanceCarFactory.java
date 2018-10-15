package com.springdemo.bean.factory;

import java.util.HashMap;
import java.util.Map;

/**
 * 实例工厂方法：先创建工厂本身再调用方法
 */
public class InstanceCarFactory {
    private Map<String, Car> cars;

    public InstanceCarFactory() {
        this.cars = new HashMap<>();
        cars.put("audi", new Car("audi", 300000));
        cars.put("benz", new Car("benz", 500000));
    }

    public Car getCar(String brand){
        return cars.get(brand);
    }
}
