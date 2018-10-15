package com.wxy.crm.service;

import com.wxy.crm.pojo.Customer;
import com.wxy.crm.pojo.QueryVo;
import com.wxy.crm.utils.Page;

public interface CustomerService {

	Page<Customer> getCustomerPage(QueryVo queryVo);
	
	Customer getCustomerById(Integer id);

	void updateCustomer(Customer customer);

	void deleteById(Integer id);

}
