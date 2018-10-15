package cn.itcast.mybatis.c_dynamic;

import cn.itcast.mybatis.pojo.Product;

import java.util.List;
import java.util.Map;

/**
 * create by pinkill on ${date}
 */
public interface ProductMapper {
    //trainning "where" and "if"
    List<Product> selectList1(Product product);

    //training "foreach list"
    List<Product> selectList2(List list);

    //training "foreach list"
    List<Product> selectList3(Map<String,Object> map);
}
