package com.springtransaction.transfer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.springtransaction.transfer.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value="classpath:applicationContext2.xml")
public class AccountTransferTest {

	@Autowired
	private AccountService accountService;

	@Test
	public void transferMoney() throws Exception {
		accountService.transferMoney("小张" ,"小凤" ,1000d);
	}
}
