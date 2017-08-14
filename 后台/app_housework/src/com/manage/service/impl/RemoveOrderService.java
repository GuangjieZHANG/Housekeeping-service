/**
 * 
 */
package com.manage.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.manage.dao.IBaseDao;
import com.manage.po.Removeorder;
import com.manage.po.User;
import com.manage.po.Worker;
import com.manage.service.IRemoveOrderService;
import com.manage.util.PagerModel;

/**
 * @author 情愿孤独
 *
 */
@Service("RemoveOrderService")
@Transactional(propagation = Propagation.REQUIRED)
public class RemoveOrderService implements IRemoveOrderService {
	@Autowired
	private IBaseDao baseDao;
	
	@Override
	public Removeorder getRemoveOrderById(String removeOrderId, int pageNum, int numPerPage) {
		// TODO Auto-generated method stub
		return baseDao.get(Removeorder.class, removeOrderId);
	}

	@Override
	public PagerModel getRemoveOrderList(int pageNum, int numPerPage) {
		// TODO Auto-generated method stub
		String sql = "select * from RemoveOrder ORDER BY RemoveOrder.addTime DESC";
		return baseDao.findPageBySql(Worker.class, sql, null, pageNum, numPerPage);
	}

	@Override
	public PagerModel search(String searchRemoveOrderId,
			String searchServiceTypeId, String searchUserName,
			String searchUserPhoneNumber, String searchBeginTime,
			String searchEndTime, String searchWorkerName,
			String searchWorkerPhoneNumber, int pageNum, int numPerPage) {
		// TODO Auto-generated method stub
		List<Criterion> cronlist = new ArrayList<Criterion>();
		List<Order> orders = new ArrayList<Order>();
		List<String> workerIdsByN = new ArrayList<String>();
		List<String> workerIdsByp = new ArrayList<String>();
		List<String> workerIds = new ArrayList<String>();
		List<String> userIdsByN = new ArrayList<String>();
		List<String> userIdsByp = new ArrayList<String>();
		List<String> userIds = new ArrayList<String>();
		Disjunction disjunctionUser = Restrictions.disjunction();
		Disjunction disjunctionWorker = Restrictions.disjunction();
		if (searchRemoveOrderId != null && !searchRemoveOrderId.equals(""))
			cronlist.add(Restrictions.like("orderId", searchRemoveOrderId,
					MatchMode.ANYWHERE));
		if (searchServiceTypeId != null && !searchServiceTypeId.equals(""))
			cronlist.add(Restrictions.like("servicetype.serviceTypeId",
					searchServiceTypeId, MatchMode.ANYWHERE));
		if (searchBeginTime != null && !searchBeginTime.equals("")
				&& searchEndTime != null && !searchEndTime.equals("")) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 小写的mm表示的是分钟  
			try {
				Date startDate = sdf.parse(searchBeginTime);
				Date endDate = sdf.parse(searchEndTime);
				cronlist.add(Restrictions.between("startTime", startDate,
						endDate));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if (searchWorkerName != null && !searchWorkerName.equals(""))
			workerIdsByN = baseDao
					.findBySql("select w.workerId from Worker w where w.workerName like '%"
							+ searchWorkerName + "%'");
		if (searchWorkerPhoneNumber != null
				&& !searchWorkerPhoneNumber.equals(""))
			workerIdsByp = baseDao
					.findBySql("select w.workerId from Worker w where w.phoneNumber like '%"
							+ searchWorkerPhoneNumber + "%'");
		if (searchUserName != null && !searchUserName.equals(""))
			userIdsByN = baseDao
					.findBySql("select u.userId from User u where u.userName like '%"
							+ searchUserName + "%'");
		if (searchUserPhoneNumber != null && !searchUserPhoneNumber.equals(""))
			userIdsByp = baseDao
					.findBySql("select u.userId from User u where u.phoneNumber like '%"
							+ searchUserPhoneNumber + "%'");
		// 阿姨
		if (!searchWorkerPhoneNumber.isEmpty() && !searchWorkerName.isEmpty()) {
			// 交集
			workerIdsByN.retainAll(workerIdsByp);
			workerIds = workerIdsByN;
		} else if ((workerIdsByN.isEmpty() || workerIdsByN==null) && !workerIdsByp.isEmpty() && workerIdsByp!=null) {
			workerIds = workerIdsByp;
		} else if (!workerIdsByN.isEmpty() && workerIdsByN!=null && (workerIdsByp.isEmpty() || workerIdsByp==null)) {
			workerIds = workerIdsByN;
		}
		if ((!searchWorkerPhoneNumber.isEmpty() || !searchWorkerName.isEmpty())
				&& (workerIds.isEmpty() || workerIds==null)) {
			return null;
		}
		// 用户
		if (!searchUserPhoneNumber.isEmpty() && !searchUserName.isEmpty()) {
			// 交集
			userIdsByN.retainAll(userIdsByp);
			userIds = userIdsByN;
		} else if ((userIdsByN.isEmpty() || userIdsByN==null) && !userIdsByp.isEmpty() && userIdsByp!=null) {
			userIds = userIdsByp;
		} else if (!userIdsByN.isEmpty() && userIdsByN!=null && (userIdsByp.isEmpty() || userIdsByp==null)) {
			userIds = userIdsByN;
		}
		if ((!searchUserPhoneNumber.isEmpty() || !searchUserName.isEmpty())
				&& (userIds.isEmpty() || userIds==null)) {
			return null;
		}

		// add or
		for (String wId : workerIds)
			disjunctionWorker.add(Restrictions.eq("worker",
					baseDao.get(Worker.class, wId)));
		cronlist.add(disjunctionWorker);
		
		for (String uId : userIds)
			disjunctionUser.add(Restrictions.eq("user",
					baseDao.get(User.class, uId)));
		cronlist.add(disjunctionUser);
		
		orders.add(Order.desc("addTime"));
		return baseDao.findPageByCriterion(Removeorder.class, pageNum, numPerPage,
				orders, cronlist);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Removeorder> getRemoveOrderByUser(User user) {
		// TODO Auto-generated method stub
		List<Criterion> criterions = new ArrayList<Criterion>();
		List<Order> orders =  new ArrayList<Order>();
		criterions.add(Restrictions.eq("user", user));
		orders.add(Order.desc("addTime"));
		return (List<Removeorder>) baseDao.findPageByCriterion(Removeorder.class, 1, 20, orders, criterions).getList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Removeorder> getRemoveOrderByWorker(Worker worker) {
		// TODO Auto-generated method stub
		List<Criterion> criterions = new ArrayList<Criterion>();
		List<Order> orders =  new ArrayList<Order>();
		criterions.add(Restrictions.eq("worker", worker));
		orders.add(Order.desc("addTime"));
		return (List<Removeorder>) baseDao.findPageByCriterion(Removeorder.class, 1, 20, orders, criterions).getList();
	}
}
