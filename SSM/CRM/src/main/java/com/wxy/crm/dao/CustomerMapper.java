package com.wxy.crm.dao;

import com.wxy.crm.pojo.Customer;
import com.wxy.crm.pojo.QueryVo;

import java.util.List;


public interface CustomerMapper {

	List<Customer> getCustomerListWithCond(QueryVo queryVo);

	int getTotalResult(QueryVo queryVo);

	Customer getCustomerById(Integer id);

	void updateCustomer(Customer customer);

	void deleteCustomerById(Integer id);

	

}
