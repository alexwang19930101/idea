package com.wxy.sale.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wxy.bean.Product;
import com.wxy.bean.Shop;
import com.wxy.sale.dao.ProductMapper;
import com.wxy.sale.dao.ShopMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = "classpath:applicationContext.xml")
public class SaleTest {
	@Resource
	private SqlSessionFactory sqlSessionFactory;

	private SqlSession sqlSession;
	@Before
	public  void init() throws IOException{
		sqlSession = sqlSessionFactory.openSession();
	}

	//批量插入店铺数据
	@Test
	public void test1() throws Exception {
		ShopMapper shopMapper = sqlSession.getMapper(ShopMapper.class);
		List<Shop> shopList = new ArrayList<Shop>();
		for (int i = 0; i < 5; i++) {
			Shop shop = new Shop();
			shop.setName("店铺"+i);
			shopList.add(shop);
		}
		shopMapper.insertBatch(shopList);
		
		sqlSession.commit();
		sqlSession.close();
	}
	
	
	//商品批量插入
	@Test
	public void test2() throws Exception {
		ProductMapper productMapper = sqlSession.getMapper(ProductMapper.class);
		List<Product> productList = new ArrayList<Product>();
		for (int i = 0; i < 10; i++) {
			Product p = new Product();
			p.setName("大宝剑"+i);
			p.setPrice(100L+i);
			p.setSale(100000L+i);
			p.setStock(10000L+i);
			p.setShopId(11L);
			
			productList.add(p);
		}
		
		productMapper.insertBatch(productList);
		
		sqlSession.commit();
		sqlSession.close();
	}
	
	/*//封装商品和店铺数据
	@Test
	public void test3() throws Exception {
		ProductMapper productMapper = sqlSession.getMapper(ProductMapper.class);
		ShopMapper shopMapper = sqlSession.getMapper(ShopMapper.class);
		ProductExample example = new ProductExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdGreaterThan(290L);
		
		List<Product> productList = productMapper.selectByExample(example);
		List<ProductDTO> productDTOList = new ArrayList<ProductDTO>();
		for (Product product : productList) {
//			System.out.println(product.getId()+" "+product.getName()+" "+product.getShopId());
			Shop shop = shopMapper.selectByPrimaryKey(product.getShopId());
			ProductDTO productDTO = new ProductDTO();
			productDTO.setProduct(product);
			productDTO.setShop(shop);
			productDTOList.add(productDTO);
		}
		
		sqlSession.commit();
		sqlSession.close();
	}
	
	
	@Test
	public void test4() throws Exception {
		ProductMapper productMapper = sqlSession.getMapper(ProductMapper.class);
		ShopMapper shopMapper = sqlSession.getMapper(ShopMapper.class);
		ProductExample example = new ProductExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdGreaterThan(90L);
		
		List<Product> productList = productMapper.selectByExample(example);
		List<ProductDTO> productDTOList = new ArrayList<ProductDTO>();
		List<Long> shopIds = new ArrayList<Long>();
		for (Product product : productList) {
			shopIds.add(product.getShopId());
			ProductDTO productDTO = new ProductDTO();
			productDTO.setProduct(product);
			productDTOList.add(productDTO);
		}
		List<Shop> shopList = shopMapper.selectShopList(shopIds);
		for (int i=0;i<shopList.size();i++) {
			productDTOList.get(i).setShop(shopList.get(i));
		}
		sqlSession.commit();
		sqlSession.close();
	}*/
}








