package cn.itcast.mybatis.d_one2one;

import cn.itcast.mybatis.pojo.one2one.Wife;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;

/**
 * create by pinkill on ${date}
 */
public class _TestWifeMapper {
    private static SqlSessionFactory build;

    static{
        try {
            build = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("SqlMapConfig.xml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSelectWifeById(){
        SqlSession sqlSession= build.openSession();
        WifeMapper wifeMapper = sqlSession.getMapper(WifeMapper.class);

        System.out.println(wifeMapper.selectWifeById(1));
        sqlSession.close();
    }

    @Test
    public void testSelectWifeWithHusband(){
        SqlSession sqlSession = build.openSession();
        WifeMapper wifeMapper = sqlSession.getMapper(WifeMapper.class);

        System.out.println(wifeMapper.selectWifeWithHusband(1));
        sqlSession.close();
    }
}
