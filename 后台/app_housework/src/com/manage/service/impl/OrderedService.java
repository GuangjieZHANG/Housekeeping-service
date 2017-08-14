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
import com.manage.po.Ordered;
import com.manage.po.User;
import com.manage.po.Worker;
import com.manage.service.IOrderedService;
import com.manage.util.PagerModel;


@Service("OrderedService")
@Transactional(propagation = Propagation.REQUIRED)
public class OrderedService implements IOrderedService{

	@Autowired
	private IBaseDao baseDao;

	@Override
	public PagerModel getOtheredList(int pageNum, int numPerPage) {
		// TODO Auto-generated method stub
		return baseDao.findPageBySql(Ordered.class, "select * from Ordered", null, pageNum, numPerPage);
	}

	@Override
	public PagerModel search(String searchOrderedId,
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
		if (searchOrderedId != null && !searchOrderedId.equals(""))
			cronlist.add(Restrictions.like("orderId", searchOrderedId,
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
		return baseDao.findPageByCriterion(Ordered.class, pageNum, numPerPage,
				orders, cronlist);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Ordered> getOrderedByUser(User user) {
		// TODO Auto-generated method stub
		List<Criterion> criterions = new ArrayList<Criterion>();
		List<Order> orders =  new ArrayList<Order>();
		criterions.add(Restrictions.eq("user", user));
		orders.add(Order.desc("addTime"));
		return (List<Ordered>) baseDao.findPageByCriterion(Ordered.class, 1, 20, orders, criterions).getList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Ordered> getOrderedByWorker(Worker worker) {
		// TODO Auto-generated method stub
		List<Criterion> criterions = new ArrayList<Criterion>();
		List<Order> orders =  new ArrayList<Order>();
		criterions.add(Restrictions.eq("worker", worker));
		orders.add(Order.desc("addTime"));
		return (List<Ordered>) baseDao.findPageByCriterion(Ordered.class, 1, 20, orders, criterions).getList();
	}
//	@Override
//	public Room getRoomById(String roomID) {
//		// TODO Auto-generated method stub
//		return baseDao.get(Room.class, roomID);
//	}
//
//	@Override
//	public void merge(Room room) {
//		// TODO Auto-generated method stub
//		baseDao.merge(room);
//	}
//
//	@Override
//	public void save(Room room) {
//		// TODO Auto-generated method stub
//		baseDao.save(room);
//	}
//
//	@Override
//	public void update(Room room) {
//		// TODO Auto-generated method stub
//		baseDao.update(room);
//	}
//
//	@Override
//	public void delete(String roomID) {
//		// TODO Auto-generated method stub
//		Room room = getRoomById(roomID);
//		baseDao.delete(room);
//	}
//
//	@Override
//	public Room TestHql(String roomID) {
//		// TODO Auto-generated method stub
//		String hql="from Room where roomID=?";//HQL语句中表名应该是ORM映射的类名,查询結果是实体类，而不是sql的结果集
//		List<Room> list=baseDao.findByHql(hql, roomID);
//		return list.get(0);
//	}
//
//	@Override
//	public Room TestHql(String... param) {
//		// TODO Auto-generated method stub
//		String hql="from Room where roomID=?";
//		List<Room> list=baseDao.findByHql(hql, param);
//		return list.get(0);
//	}
//
//	@Override
//	public void batchDelete(String[] ids) {
//		// TODO Auto-generated method stub
//		for(int i=0;i<ids.length;i++)
//			delete(ids[i]);	
//	}
		
	

}
