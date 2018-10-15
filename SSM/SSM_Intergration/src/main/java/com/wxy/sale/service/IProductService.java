package com.wxy.sale.service;

import com.wxy.bean.Product;
import com.wxy.bean.ProductExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * create by pinkill on ${date}
 */
public interface IProductService {

    int deleteByExample(ProductExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Product record);

    int insertSelective(Product record);

    List<Product> selectByExample(ProductExample example);

    Product selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Product record, @Param("example") ProductExample example);

    int updateByExample(@Param("record") Product record, @Param("example") ProductExample example);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);
}
