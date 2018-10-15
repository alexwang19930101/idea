package com.wxy.sale.test;

import com.wxy.bean.Product;
import com.wxy.sale.service.IProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = "classpath:applicationContext.xml")
public class SaleSpringTest {
    @Autowired
    private IProductService productService;

    @Test
    public void testInsert() {
        Product p = new Product();
        p.setName("大宝剑");
        p.setPrice(999L);
        p.setSale(88L);
        p.setStock(77L);
        p.setShopId(11L);
        p.setOnsale(Boolean.TRUE);

        productService.insert(p);
        //手动插入数据到日志表
    }
}
