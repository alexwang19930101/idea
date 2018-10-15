package com.springtransaction.transfer.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AccountDaoImpl implements AccountDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public void moneyOut(String from, double money) {
		jdbcTemplate.update("update account set money = money -? where name = ?", money,from);          
	}

	@Override
	public void moneyIn(String to, double money) {
		jdbcTemplate.update("update account set money = money + ? where name = ?", money,to); 
		
	}
}
