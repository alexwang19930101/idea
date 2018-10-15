package com.wxy.sale.service;

import com.wxy.bean.Mylog;
import com.wxy.sale.dao.MylogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class MylogServiceImpl implements IMylogService {
    @Autowired
    private MylogMapper mylogMapper;

    @Override
    public int insert(Mylog record) {
        return mylogMapper.insert(record);
    }
}
