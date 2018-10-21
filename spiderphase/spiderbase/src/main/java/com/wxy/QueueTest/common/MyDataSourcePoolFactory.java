package com.wxy.QueueTest.common;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.datasource.unpooled.UnpooledDataSourceFactory;

public class MyDataSourcePoolFactory extends UnpooledDataSourceFactory {
    public MyDataSourcePoolFactory(){
        this.dataSource = new DruidDataSource();
    }
}
