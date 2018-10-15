package cn.itcast.mybatis.b_order;

import cn.itcast.mybatis.pojo.Orders;

import java.util.List;

/**
 * create by pinkill on ${date}
 */
public interface OrderMapper {
   List<Orders> selectList1();

   List<Orders> selectList2();
}
