/**
 * 
 */
package com.manage.service;

import java.util.List;

import com.manage.po.Removeorder;
import com.manage.po.User;
import com.manage.po.Worker;
import com.manage.util.PagerModel;

/**
 * @author 情愿孤独
 *
 */
public interface IRemoveOrderService {

	public Removeorder getRemoveOrderById(String removeOrderId, int pageNum, int numPerPage);

	public PagerModel getRemoveOrderList(int pageNum, int numPerPage);

	public PagerModel search(String searchRemoveOrderId,
			String searchServiceTypeId, String searchUserName,
			String searchUserPhoneNumber, String searchBeginTime,
			String searchEndTime, String searchWorkerName,
			String searchWorkerPhoneNumber, int pageNum, int numPerPage);
	public List<Removeorder> getRemoveOrderByUser(User user);

	public List<Removeorder> getRemoveOrderByWorker(Worker worker);

}
