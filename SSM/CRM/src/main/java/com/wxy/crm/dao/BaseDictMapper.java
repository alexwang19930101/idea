package com.wxy.crm.dao;

import com.wxy.crm.pojo.BaseDict;

import java.util.List;

public interface BaseDictMapper {

	List<BaseDict> getCustomerDescInfoByCode(String baseTypeCode);

}
