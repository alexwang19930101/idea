package cn.itcast.mybatis.d_one2one;

import cn.itcast.mybatis.pojo.one2one.Wife;

/**
 * create by pinkill on ${date}
 */
public interface WifeMapper {
    Wife selectWifeById(Integer i);

    Wife selectWifeWithHusband(Integer i);
}
