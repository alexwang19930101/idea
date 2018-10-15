package cn.itcast.mybatis.e_one2multi;

import cn.itcast.mybatis.pojo.one2multi.Teacher;

/**
 * create by pinkill on ${date}
 */
public interface TeacherMapper {
    Teacher selectTeacherWithStudent(Integer integer);
}
