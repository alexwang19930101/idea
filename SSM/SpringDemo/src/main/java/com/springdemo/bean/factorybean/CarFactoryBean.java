package com.springdemo.bean.factorybean;

import org.springframework.beans.factory.FactoryBean;

//需要实现FactoryBean接口
public class CarFactoryBean implements FactoryBean<Car> {

    String brand;
    float price;

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public Car getObject() throws Exception {
        return new Car(brand,price);
    }

    @Override
    public Class<?> getObjectType() {
        return Car.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
