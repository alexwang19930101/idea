package com.wxy.spider.utils;

import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisUtils {

	private static SqlSessionFactory sessionFactory;

	static {
		try {
			InputStream is = new FileInputStream("E:/ideaworkspace/spiderphase/spiderbase/src/main/resources/SqlMapConfig.xml");
			sessionFactory = new SqlSessionFactoryBuilder().build(is);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static SqlSession getSqlSession() {
		return sessionFactory.openSession();
	}
}
