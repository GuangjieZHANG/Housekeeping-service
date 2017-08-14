package com.manage.service;

import com.manage.po.Worker;
import com.manage.util.PagerModel;


public interface IWorkerService {
	
	public Worker getWorkerById(String workerId);
	public void merge(Worker worker);
	public PagerModel getWorkerList(int pageno,int pagesize);
	public void save(Worker worker);
	public void update(Worker worker);
	public void delete(String workerId);
	public PagerModel search(String searchWorkerName,String searchPhoneNumber,String searchCardId,String searchWokerSex,String searchServiceTypeId,int pageno,int pagesize);
	
	public Worker TestHql(String workerId);
	public Worker TestHql(String...param);
	
	//批量删除
	public void batchDelete(String[] ids);

	/*
	public OrganInfo TestHql(String orgid);
	
	public OrganInfo TestHql(String...param);
	*/
	
	

}
