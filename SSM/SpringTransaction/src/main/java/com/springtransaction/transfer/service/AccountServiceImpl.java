package com.springtransaction.transfer.service;

import com.springtransaction.transfer.dao.AccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;


@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDao accountDao;

    @Override
    public void transferMoney(String from, String to, double money) {
        accountDao.moneyOut(from, money);
//        System.out.println(1 / 0);
        accountDao.moneyIn(to, money);
    }
}
