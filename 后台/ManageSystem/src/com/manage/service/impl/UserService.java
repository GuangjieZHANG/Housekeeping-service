package com.manage.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.manage.dao.IBaseDao;
import com.manage.po.User;
import com.manage.service.IUserService;
import com.manage.util.PagerModel;


@Service("UserService")
@Transactional(propagation = Propagation.REQUIRED)
public class UserService implements IUserService{

	@Autowired
	private IBaseDao baseDao;
		
	@Override
	public User getUserById(String userId) {
		// TODO Auto-generated method stub
		System.out.println(userId);
		return baseDao.get(User.class, userId);
	}

	@Override
	public void merge(User user) {
		// TODO Auto-generated method stub
		baseDao.merge(user);
	}

	@Override
	public PagerModel getUserList(String userId, int pageno, int pagesize) {
		String sql = "select * from user Order by user.userName";
		return baseDao.findPageBySql(User.class, sql, null, pageno, pagesize);
	}

	@Override
	public void save(User user) {
		// TODO Auto-generated method stub
		baseDao.save(user);
	}

	@Override
	public void update(User user) {
		// TODO Auto-generated method stub
		baseDao.update(user);
	}

	@Override
	public void delete(String userId) {
		// TODO Auto-generated method stub
		User user = getUserById(userId);
		baseDao.delete(user);
	}

	@Override
	public void batchDelete(String[] ids) {
		// TODO Auto-generated method stub
		for(int i=0;i<ids.length;i++)
			delete(ids[i]);	
	}
	
	
	@Override
	public User TestHql(String userId) {
		// TODO Auto-generated method stub
		String hql="from User where userId=?";//HQL语句中表名应该是ORM映射的类名,查询結果是实体类，而不是sql的结果集
		List<User> list=baseDao.findByHql(hql, userId);
		return list.get(0);
	}

	@Override
	public User TestHql(String... param) {
		String hql="from User where userId=?"; 
		List<User> list=baseDao.findByHql(hql, param);
		return list.get(0);
	}

	@Override
	public PagerModel search(String searchUserName, String searchPhoneNumber, int pageno, int pagesize) {
		// TODO Auto-generated method stub
		List<Criterion> cronlist = new ArrayList<Criterion>();
		List<Order> orders = new ArrayList<Order>();
		if(searchUserName!=null && !searchUserName.equals(""))
			cronlist.add(Restrictions.like("userName",searchUserName,MatchMode.ANYWHERE));
		if(searchPhoneNumber!=null && !searchPhoneNumber.equals(""))
			cronlist.add(Restrictions.like("phoneNumber",searchPhoneNumber,MatchMode.ANYWHERE));
/*	
		if(starttime!=null && !starttime.equals(""))
			date = starttime+"";
			System.out.println("*-*-*-*-*-*-*-" + date);
			cronlist.add(Restrictions.like("starttime",date,MatchMode.ANYWHERE));
		if(endtime!=null && !endtime.equals(""))
			date = endtime+"";
			System.out.println("*****-------*********" + date);
			cronlist.add(Restrictions.like("endtime",date,MatchMode.ANYWHERE));
*/
		
		orders.add(Order.asc("userName"));
		return baseDao.findPageByCriterion(User.class, pageno, pagesize, orders, cronlist);
	}
	
	@Override
	public PagerModel getUserByPhoneNumber(String phoneNumber, int pageno, int pagesize) {
		// TODO Auto-generated method stub
		String sql = "select * from user where phoneNumber=" + phoneNumber;
		return baseDao.findPageBySql(User.class, sql, null, pageno, pagesize);
	}
	
}
