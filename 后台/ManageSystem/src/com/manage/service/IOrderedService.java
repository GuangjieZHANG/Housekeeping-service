package com.manage.service;

import com.manage.util.PagerModel;

public interface IOrderedService {

	PagerModel getOtheredList(int pageNum, int numPerPage);

	PagerModel search(String searchOrderedId, String searchServiceTypeId,
			String searchUserName, String searchUserPhoneNumber,
			String searchBeginTime, String searchEndTime,
			String searchWorkerName, String searchWorkerPhoneNumber,
			int pageNum, int numPerPage);
	
//	public Room	getRoomById(String roomID);
//	public void merge(Room room);
//	public void save(Room room);
//	public void update(Room room);
//	public void delete(String roomID);
//	
//	public Room TestHql(String roomID);
//	public Room TestHql(String...param);
//	
//	//批量删除
//	public void batchDelete(String[] ids);
	

}
