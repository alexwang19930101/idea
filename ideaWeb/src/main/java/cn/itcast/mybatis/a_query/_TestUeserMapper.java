package cn.itcast.mybatis.a_query;

import cn.itcast.mybatis.pojo.QueryVo;
import cn.itcast.mybatis.pojo.User;
import cn.itcast.mybatis.utils.MybatisUtils;
import org.junit.Test;


/**
 * create by pinkill on ${date}
 */
public class _TestUeserMapper {
    private UserMapper userMapper = MybatisUtils.getSqlSession().getMapper(UserMapper.class);

    /**
     * 通过包装类型参数条件查询
     */
    @Test
    public void test1() {
        QueryVo queryVo = new QueryVo();
        User user = new User();
        user.setUsername("zzz");
        queryVo.setUser(user);

        userMapper.selectList(queryVo).forEach(u -> System.out.println(u));
    }
}
