package cn.itcast.mybatis.e_one2multi;

import cn.itcast.mybatis.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

/**
 * create by pinkill on ${date}
 */
public class _TestTeacherMapper {
    @Test
    public void testSelectTeacherWithStudent(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        TeacherMapper teacherMapper = sqlSession.getMapper(TeacherMapper.class);

        System.out.println(teacherMapper.selectTeacherWithStudent(1));
        sqlSession.close();

    }

}
