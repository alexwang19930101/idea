package com.springdemo.bean;

import java.util.List;
import java.util.Map;

public class User {

	private String userName;
	private List<Car> cars;
	private Map<String, Car> carMap;
	private String wifeName;
	
	public Map<String, Car> getCarMap() {
		return carMap;
	}

	public void setCarMap(Map<String, Car> carMap) {
		this.carMap = carMap;
	}
	
	public String getWifeName() {
		return wifeName;
	}

	public void setWifeName(String wifeName) {
		System.out.println("setWifhName: " + wifeName);
		this.wifeName = wifeName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<Car> getCars() {
		return cars;
	}

	public void setCars(List<Car> cars) {
		this.cars = cars;
	}
	
	public User() {
		System.out.println("User's Construtor...");
	}

	@Override
	public String toString() {
		return "User [userName=" + userName + ", cars=" + cars + ", carMap=" + carMap + ", wifeName=" + wifeName + "]";
	}

	public void init(){
		System.out.println("init method...");
	}
	
	public void destroy(){
		System.out.println("destroy method...");
	}

}
