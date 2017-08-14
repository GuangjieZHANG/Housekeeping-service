package com.manage.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.manage.dao.IBaseDao;
import com.manage.po.Account;
import com.manage.service.IAccountService;
import com.manage.util.PagerModel;


@Service("AccountService")
@Transactional(propagation = Propagation.REQUIRED)
public class AccountService implements IAccountService {
	@Autowired
	private IBaseDao baseDao;

	@Override
	public Account getAccountById(String accountId) {
		return baseDao.get(Account.class, accountId);			
	}

	@Override
	public void merge(Account account) {
		baseDao.merge(account);
	}

	@Override
	public PagerModel getAccountByRoleId(int id,int pageno,int pagesize) {
		return baseDao.findPageBySql(Account.class, "select * from account where roleid="+id, null, pageno, pagesize);
	}	

	@Override
	public Account TestHql(String accountId){
		String hql="from Account where accountid=?";//HQL语句中表名应该是ORM映射的类名,查询結果是实体类，而不是sql的结果集
		List<Account> list=baseDao.findByHql(hql, accountId);
		return list.get(0);
	}
	
	public Account TestHql(String...param){
		String hql="from Account where accountid=? and password=?";
		List<Account> list=baseDao.findByHql(hql, param);
		return list.get(0);
	}
	@Override
	public void save(Account account){
		baseDao.save(account);
	}
	
	@Override
	public void update(Account account)	{
		baseDao.update(account);
	}
	
	public void delete(String id){
		Account acc = getAccountById(id); 
		baseDao.delete(acc);
	}
	
	@Override
	public void batchDelete(String[] ids){
		
		for(int i=0;i<ids.length;i++)
			delete(ids[i]);		
		
	}
	
	@Override
	public PagerModel listAccountByCondition(int roleId,String searchAccountId,String searchTeamId,int pageno,int pagesize) {
		List<Criterion> cronlist = new ArrayList<Criterion>();
		   cronlist.add(Restrictions.eq("roleid", roleId));
		if(searchAccountId!=null && !searchAccountId.equals(""))
			cronlist.add(Restrictions.like("accountid",searchAccountId,MatchMode.ANYWHERE));	
		if(searchTeamId!=null && !searchTeamId.equals(""))
			cronlist.add(Restrictions.like("teamid",searchTeamId,MatchMode.ANYWHERE));
		return baseDao.findPageByCriterion(Account.class, pageno, pagesize, null, cronlist);		
	}
}
