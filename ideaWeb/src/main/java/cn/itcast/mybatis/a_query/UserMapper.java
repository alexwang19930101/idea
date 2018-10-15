package cn.itcast.mybatis.a_query;

import cn.itcast.mybatis.pojo.QueryVo;
import cn.itcast.mybatis.pojo.User;

import java.util.List;

/**
 * create by pinkill on ${date}
 */
public interface UserMapper {
    List<User> selectList(QueryVo queryVo);

    Integer selectCount();
}
