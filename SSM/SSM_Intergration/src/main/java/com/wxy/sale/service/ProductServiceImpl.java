package com.wxy.sale.service;

import com.wxy.bean.Product;
import com.wxy.bean.ProductExample;
import com.wxy.sale.dao.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * create by pinkill on ${date}
 */
@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public int deleteByExample(ProductExample example) {
        return productMapper.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        return productMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Product record) {
        return productMapper.insert(record);
    }

    @Override
    public int insertSelective(Product record) {
        return productMapper.insertSelective(record);
    }

    @Override
    public List<Product> selectByExample(ProductExample example) {
        return productMapper.selectByExample(example);
    }

    @Override
    public Product selectByPrimaryKey(Long id) {
        return productMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByExampleSelective(Product record, ProductExample example) {
        return productMapper.updateByExample(record, example);
    }

    @Override
    public int updateByExample(Product record, ProductExample example) {
        return productMapper.updateByExample(record, example);
    }

    @Override
    public int updateByPrimaryKeySelective(Product record) {
        return productMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Product record) {
        return productMapper.updateByPrimaryKey(record);
    }
}
