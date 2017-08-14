package com.manage.service;

import java.util.List;

import com.manage.po.Unorder;
import com.manage.po.User;
import com.manage.po.Worker;
import com.manage.util.PagerModel;

public interface IUnorderService {

	public Unorder getUnorderById(String unorderId);
	public void merge(Unorder attach);
	public void save(Unorder attach);
	public void update(Unorder attach);
	public void delete(String unorderId);
	
	public PagerModel getUnorderList(int pageno,int pagesize);
	
	public Unorder TestHql(String unorderId);
	public Unorder TestHql(String...param);
	
	//批量删除
	public void batchDelete(String[] ids);
	//将文件保存到数据库
	public boolean saveFileToDB(Unorder file);
	public PagerModel search(String searchUnorderId,
			String searchServiceTypeId, String searchUserName,
			String searchUserPhoneNumber, String searchBeginTime,
			String searchEndTime, String searchWorkerName,
			String searchWorkerPhoneNumber, int pageno, int pagesize);
	public List<Unorder> getUnordersByUser(User user);
	public List<Unorder> getUnordersByWorker(Worker worker);
	
}
