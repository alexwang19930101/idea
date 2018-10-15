package com.wxy.crm.service;

import com.wxy.crm.dao.BaseDictMapper;
import com.wxy.crm.pojo.BaseDict;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BaseDictServiceImpl implements BaseDictService {

	
	@Autowired
	private BaseDictMapper baseDictMapper;
	
	
	@Override
	public List<BaseDict> getCustomerSourceList(String customerSource) {
		List<BaseDict> baseDictList = baseDictMapper.getCustomerDescInfoByCode(customerSource);
		return baseDictList;
	}

	

}
