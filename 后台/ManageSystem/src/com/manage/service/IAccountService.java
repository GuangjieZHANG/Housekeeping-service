package com.manage.service;

import com.manage.po.Account;
import com.manage.util.PagerModel;

public interface IAccountService {
	public Account getAccountById(String accountId);
	public void merge(Account account);

	public PagerModel getAccountByRoleId(int id, int pageno, int pagesize);
	
	public Account TestHql(String accountId);
	public Account TestHql(String...param);
	
	//批量删除
	public void batchDelete(String[] ids);
	
	public void save(Account account);	
	public void update(Account account);	
	//查找
	public PagerModel listAccountByCondition(int roleId,String searchAccountId,String searchTeamId,int pageno,int pagesize);	
	
}
