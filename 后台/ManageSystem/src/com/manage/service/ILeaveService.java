package com.manage.service;

import com.manage.po.Leaverecord;
import com.manage.util.PagerModel;


public interface ILeaveService {
	
	public Leaverecord getLeaveById(String leave_id);
	public void merge(Leaverecord leave);
	public PagerModel getLeaveById(String leaveId,int pageno,int pagesize);
	public void save(Leaverecord leave);
	public void update(Leaverecord leave);
	public void delete(String leaveId);
	public PagerModel search(String searchWorkerName,String searchPhoneNumber,String searchBeginTime,String searchEndTime,int pageno,int pagesize);
	
	
	public Leaverecord TestHql(String leaveId);
	public Leaverecord TestHql(String...param);
	
	//批量删除
	public void batchDelete(String[] ids);

	/*
	public OrganInfo TestHql(String orgid);
	
	public OrganInfo TestHql(String...param);
	*/
	
	

}
