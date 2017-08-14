package com.manage.service;

import com.manage.po.User;
import com.manage.util.PagerModel;


public interface IUserService {
	
	public User	getUserById(String userId);
	public void merge(User user);
	public PagerModel getUserList(String userId,int pageno,int pagesize);
	public void save(User user);
	public void update(User user);
	public void delete(String userId);
	public PagerModel search(String searchUserName,String searchPhoneNumber,int pageno,int pagesize);
	
	public User TestHql(String userId);
	public User TestHql(String...param);
	
	//批量删除
	public void batchDelete(String[] ids);
	public PagerModel getUserByPhoneNumber(String phoneNumber, int pageno, int pagesize);
	
	
	

}
