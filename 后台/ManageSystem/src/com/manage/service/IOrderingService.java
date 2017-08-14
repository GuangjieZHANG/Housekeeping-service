package com.manage.service;

import com.manage.util.PagerModel;

public interface IOrderingService {
	
//	public Awardrecord	getAwardrecordById(String awardid);
//	public void merge(Awardrecord awardrecord);

//	public void save(Awardrecord awardrecord);
//	public void update(Awardrecord awardrecord);
//	public void delete(String awardid);
//	
//	public Awardrecord TestHql(String awardid);
//	public Awardrecord TestHql(String...param);
//	
//	//批量删除
//	public void batchDelete(String[] ids);		
	public PagerModel getOrderingList(int pageno,int pagesize);
	public PagerModel search(String searchOrderingId, String searchServiceId,
			String searchUserName, String searchUserPhoneNumber,
			String searchBeginTime, String searchEndTime,
			String searchWorkerName, String searchWorkerPhoneNumber,int pageno,int pagesize);
	
	
	
}
