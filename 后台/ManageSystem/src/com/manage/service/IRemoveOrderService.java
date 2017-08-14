/**
 * 
 */
package com.manage.service;

import com.manage.po.Removeorder;
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


}
