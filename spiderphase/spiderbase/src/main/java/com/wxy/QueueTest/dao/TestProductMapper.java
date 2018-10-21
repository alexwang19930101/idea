package com.wxy.QueueTest.dao;

import com.wxy.QueueTest.bean.Product;
import org.apache.ibatis.session.SqlSession;
//import org.junit.Test;
import com.wxy.spider.utils.MybatisUtils;

public class TestProductMapper {
/*    @Test
    public void test(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        ProductMapper productMapper = sqlSession.getMapper(ProductMapper.class);
        Product product = new Product();
        product.setBranch("美的");
        product.setPrice(5000.0);
        product.setAd("真香");
        product.setTitle("美的空调");

        productMapper.insertSelective(product);
        sqlSession.commit();
        sqlSession.close();
        System.out.println(product);
    }*/
}
