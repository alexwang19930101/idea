package cn.itcast.mybatis.b_order;

import cn.itcast.mybatis.utils.MybatisUtils;
import org.junit.Test;

/**
 * create by pinkill on ${date}
 */
public class _TestOrderMapper {
    @Test
    public void test1() {
        OrderMapper orderMapper = MybatisUtils.getSqlSession().getMapper(OrderMapper.class);
        orderMapper.selectList1().forEach(System.out::println);
    }
}