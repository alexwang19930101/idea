package com.wxy.mysolr;

import org.apache.solr.client.solrj.beans.Field;

public class Product {
	//user中定义的字段名称  === solr core中定义的字段名称 ==》能否直接匹配上？ 不能
	//使用 @Field注解显示声明javaBean中属性所对应的solr core字段
	@Field("id")
	private String id;
	@Field("name")
	private String name;
	@Field("title")
	private String title;
	@Field("price")
	private Long price;

	public Product() {
	}

	public Product(String id, String name, String title, Long price) {
		this.id = id;
		this.name = name;
		this.title = title;
		this.price = price;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getTitle() {
		return title;
	}

	public Long getPrice() {
		return price;
	}

	@Override
	public String toString() {
		return "Product{" +
				"id='" + id + '\'' +
				", name='" + name + '\'' +
				", title='" + title + '\'' +
				", price=" + price +
				'}';
	}
}
