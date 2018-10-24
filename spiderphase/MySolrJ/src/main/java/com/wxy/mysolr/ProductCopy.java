package com.wxy.mysolr;

import org.apache.solr.client.solrj.beans.Field;

import java.util.List;

public class ProductCopy {
	//user中定义的字段名称  === solr core中定义的字段名称 ==》能否直接匹配上？ 不能
	//使用 @Field注解显示声明javaBean中属性所对应的solr core字段
	@Field("id")
	private String id;
	@Field("name")
	private String name;
	@Field("title")
	private List<String> title;
	@Field("price")
	private Float price;

	public ProductCopy() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getTitle() {
		return title;
	}

	public void setTitle(List<String> title) {
		this.title = title;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "ProductCopy{" +
				"id='" + id + '\'' +
				", name='" + name + '\'' +
				", title=" + title +
				", price=" + price +
				'}';
	}
}
