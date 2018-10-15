package com.wxy.crm.pojo;

public class QueryVo {

	//用于接收页面上的所有的查询信息
	private String custName;
	
	private String custSource;
	
	private String custIndustry;

	private String custLevel;

	//分页条件  每页多少条
	private int size=10;
	
	//当前页
	private int page =1;
	
	// 起始行
	private int start ;

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getCustSource() {
		return custSource;
	}

	public void setCustSource(String custSource) {
		this.custSource = custSource;
	}

	public String getCustIndustry() {
		return custIndustry;
	}

	public void setCustIndustry(String custIndustry) {
		this.custIndustry = custIndustry;
	}

	public String getCustLevel() {
		return custLevel;
	}

	public void setCustLevel(String custLevel) {
		this.custLevel = custLevel;
	}

	@Override
	public String toString() {
		return "QueryVo{" +
				"custName='" + custName + '\'' +
				", custSource='" + custSource + '\'' +
				", custIndustry='" + custIndustry + '\'' +
				", custLevel='" + custLevel + '\'' +
				", size=" + size +
				", page=" + page +
				", start=" + start +
				'}';
	}
}
