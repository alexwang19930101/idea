package com.springtransaction.transfer.dao;

public interface AccountDao {

	void moneyOut(String from, double money);

	void moneyIn(String to, double money);

}
