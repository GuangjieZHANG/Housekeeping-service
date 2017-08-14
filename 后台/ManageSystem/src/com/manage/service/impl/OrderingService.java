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
import com.manage.po.Ordering;
import com.manage.po.User;
import com.manage.po.Worker;
import com.manage.service.IOrderingService;
import com.manage.util.PagerModel;

@Service("OrderingService")
@Transactional(propagation = Propagation.REQUIRED)
public class OrderingService implements IOrderingService {

	@Autowired
	private IBaseDao baseDao;

	// @Override
	// public Awardrecord getAwardrecordById(String awardid) {
	// // TODO Auto-generated method stub
	// return baseDao.get(Awardrecord.class, awardid);
	// }
	//
	// @Override
	// public void merge(Awardrecord awardrecord) {
	// // TODO Auto-generated method stub
	// baseDao.merge(awardrecord);
	// }
	//
	@Override
	public PagerModel getOrderingList(int pageno, int pagesize) {
		// TODO Auto-generated method stub
		return baseDao.findPageBySql(Ordering.class, "select * from Ordering",
				null, pageno, pagesize);
	}

	//
	// @Override
	// public void save(Awardrecord awardrecord) {
	// // TODO Auto-generated method stub
	// baseDao.save(awardrecord);
	// }
	//
	// @Override
	// public void update(Awardrecord awardrecord) {
	// // TODO Auto-generated method stub
	// baseDao.update(awardrecord);
	// }
	//
	// @Override
	// public void delete(String awardid) {
	// // TODO Auto-generated method stub
	// Awardrecord awardrecord = getAwardrecordById(awardid);
	// baseDao.delete(awardrecord);
	// }
	//
	// @Override
	// public void batchDelete(String[] ids) {
	// // TODO Auto-generated method stub
	// for(int i=0;i<ids.length;i++)
	// delete(ids[i]);
	// }

	@Override
	public PagerModel search(String searchOrderingId, String searchServiceId,
			String searchUserName, String searchUserPhoneNumber,
			String searchBeginTime, String searchEndTime,
			String searchWorkerName, String searchWorkerPhoneNumber,
			int pageno, int pagesize) {
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
		if (searchOrderingId != null && !searchOrderingId.equals(""))
			cronlist.add(Restrictions.like("orderId", searchOrderingId,
					MatchMode.ANYWHERE));
		if (searchServiceId != null && !searchServiceId.equals(""))
			cronlist.add(Restrictions.like("servicetype.serviceTypeId",
					searchServiceId, MatchMode.ANYWHERE));
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
		return baseDao.findPageByCriterion(Ordering.class, pageno, pagesize,
				orders, cronlist);
	}

	// @Override
	// public Awardrecord TestHql(String awardid) {
	// // TODO Auto-generated method stub
	// String
	// hql="from Awardrecord where awardid=?";//HQL语句中表名应该是ORM映射的类名,查询結果是实体类，而不是sql的结果集
	// List<Awardrecord> list=baseDao.findByHql(hql, awardid);
	// return list.get(0);
	// }
	//
	// @Override
	// public Awardrecord TestHql(String... param) {
	// String hql="from Awardrecord where awardid=?";
	// List<Awardrecord> list=baseDao.findByHql(hql, param);
	// return list.get(0);
	// }

}
