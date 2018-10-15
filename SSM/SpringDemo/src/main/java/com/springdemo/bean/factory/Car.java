package com.springdemo.bean.factory;

public class Car {

	private String brand;
	private float price;

	public Car() {
	}

	public Car(String brand, float price) {
		this.brand = brand;
		this.price = price;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Car{" +
				"brand='" + brand + '\'' +
				", price=" + price +
				'}';
	}
}
