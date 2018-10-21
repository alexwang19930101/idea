package com.wxy.spider.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.FileInputStream;
import java.io.InputStream;

public class MybatisUtils {

	private static SqlSessionFactory sessionFactory;

	static {
		try {
			sessionFactory = new SqlSessionFactoryBuilder().build(MybatisUtils.class.getClassLoader().getResourceAsStream("SqlMapConfig.xml"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static SqlSession getSqlSession() {
		return sessionFactory.openSession();
	}
}
