package com.springdemo.bean.factory;

import java.util.HashMap;
import java.util.Map;

/**
 * 直接调用某个各类的静态方法返回实例
 */
public class StaticCarFactory {
    private static Map<String,Car> cars = new HashMap<>();

    static{
        cars.put("audi",new Car("audi",300000));
        cars.put("benz",new Car("benz",500000));
    }

    public static Car getCar(String carName){
        return  cars.get(carName);
    }
}
