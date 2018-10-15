package cn.itcast.mybatis.c_dynamic;

import cn.itcast.mybatis.pojo.Product;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * create by pinkill on ${date}
 */
public class _TestProductMapper {
    static SqlSessionFactory build;

    static {
        try {
            build = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("SqlMapConfig.xml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSelectList1() {
        SqlSession sqlSession = build.openSession();
        ProductMapper productMapper = sqlSession.getMapper(ProductMapper.class);
        Product product = new Product();
        product.setPid(3);
        product.setPname("a");
        System.out.println(productMapper.selectList1(product));
        sqlSession.close();
    }

    @Test
    public void testSelectList2() {
        SqlSession sqlSession = build.openSession();
        ProductMapper productMapper = sqlSession.getMapper(ProductMapper.class);

        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(3);
        System.out.println(productMapper.selectList2(list));

        sqlSession.close();
    }

    @Test
    public void testSelectList3() {
        SqlSession sqlSession = build.openSession();
        ProductMapper productMapper = sqlSession.getMapper(ProductMapper.class);

        Map<String, Object> map = new HashMap<>();
        map.put("pid", 3);
        map.put("pname", "苹果");
        System.out.println(productMapper.selectList3(map));

        sqlSession.close();
    }
}
