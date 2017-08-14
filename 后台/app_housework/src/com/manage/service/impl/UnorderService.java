package com.manage.service.impl;

import java.sql.Timestamp;
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
import com.manage.po.Unorder;
import com.manage.po.User;
import com.manage.po.Worker;
import com.manage.service.IUnorderService;
import com.manage.util.PagerModel;

@Service("UnorderService")
@Transactional(propagation = Propagation.REQUIRED)

public class UnorderService implements IUnorderService{

	@Autowired
	private IBaseDao baseDao;
	
	@Override
	public Unorder getUnorderById(String unorderId) {
		// TODO Auto-generated method stub
		return baseDao.get(Unorder.class, unorderId);
	}

	@Override
	public void merge(Unorder unorder) {
		// TODO Auto-generated method stub
		baseDao.merge(unorder);
	}

	@Override
	public void save(Unorder unorder) {
		// TODO Auto-generated method stub
		baseDao.save(unorder);
	}

	@Override
	public void update(Unorder unorder) {
		// TODO Auto-generated method stub
		baseDao.update(unorder);
	}

	@Override
	public void delete(String unorderId) {
		// TODO Auto-generated method stub
		Unorder unorder = getUnorderById(unorderId);
		baseDao.delete(unorder);
	}
	
	@Override
	public void batchDelete(String[] ids) {
		// TODO Auto-generated method stub
		Timestamp now = new Timestamp(System.currentTimeMillis());
		for (int i = 0; i < ids.length; i++) {
			Unorder unorder = baseDao.get(Unorder.class, ids[i]);
			Removeorder removeorder = new Removeorder(unorder.getUser(), unorder.getServicetype(), unorder.getAddress(), unorder.getLongitude(), unorder.getLatitude(), unorder.getPredictTime(), now, now);
			removeorder.setAddTime(unorder.getAddTime());
			removeorder.setWorker(unorder.getWorker());
			baseDao.save(removeorder);
			delete(ids[i]);
		}
	}

	@Override
	public PagerModel search(String searchUnorderId,
			String searchServiceTypeId, String searchUserName,
			String searchUserPhoneNumber, String searchBeginTime,
			String searchEndTime, String searchWorkerName,
			String searchWorkerPhoneNumber, int pageno, int pagesize) {
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
		if (searchUnorderId != null && !searchUnorderId.equals(""))
			cronlist.add(Restrictions.like("orderId", searchUnorderId,
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
				cronlist.add(Restrictions.between("predictTime", startDate,
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
		return baseDao.findPageByCriterion(Unorder.class, pageno, pagesize,
				orders, cronlist);
	}

	@Override
	public PagerModel getUnorderList(int pageno,int pagesize) {
		// TODO Auto-generated method stub
		return baseDao.findPageBySql(Unorder.class, "select * from Unorder", null, pageno, pagesize);
	}

	@Override
	public Unorder TestHql(String Unorderid) {
		// TODO Auto-generated method stub
		String hql="from Unorder where Unorderid=?";//HQL语句中表名应该是ORM映射的类名,查询結果是实体类，而不是sql的结果集
		List<Unorder> list=baseDao.findByHql(hql, Unorderid);
		return list.get(0);
	}

	@Override
	public Unorder TestHql(String... param) {
		// TODO Auto-generated method stub
		String hql="from Unorder where Unorderid=?";
		List<Unorder> list=baseDao.findByHql(hql, param);
		return list.get(0);
	}

	@Override
	public boolean saveFileToDB(Unorder file) {
		// TODO Auto-generated method stub
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Unorder> getUnordersByUser(User user) {
		// TODO Auto-generated method stub
		List<Criterion> criterions = new ArrayList<Criterion>();
		List<Order> orders =  new ArrayList<Order>();
		criterions.add(Restrictions.eq("user", user));
		orders.add(Order.desc("addTime"));
		return (List<Unorder>) baseDao.findPageByCriterion(Unorder.class, 1, 20, orders, criterions).getList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Unorder> getUnordersByWorker(Worker worker) {
		// TODO Auto-generated method stub
		List<Criterion> criterions = new ArrayList<Criterion>();
		List<Order> orders =  new ArrayList<Order>();
		criterions.add(Restrictions.eq("worker", worker));
		orders.add(Order.desc("addTime"));
		return (List<Unorder>) baseDao.findPageByCriterion(Unorder.class, 1, 20, orders, criterions).getList();
	}

//	@SuppressWarnings("deprecation")
//	@Override
//	public boolean saveFileToDB(Unorder file) {
//		// TODO Auto-generated method stub
//		boolean flag = false;
//		Connection conn = null;
//		ResultSet rs = null;
//		Statement st = null;
//		try {
//			String fileid = file.getUnorderid();
//			conn = DBUtil.getConn();
//			conn.setAutoCommit(false);
//			st = conn.createStatement();
//			String sql = "insert into Unorder(Unorderid,Unordername,Unordertype,Unordervarieties,submittime,Unorderobj,note) "
//					+ " values('"+ file.getUnorderid()+ "','"+ file.getUnordername()+ "',"+ file.getUnordertype()+ ",'"+ file.getUnordervarieties()
//					+ "',"+ file.getSubmittime()+ ",'"+ file.getUnorderobj()+ "',"+ file.getNote()+ ")";
//
//			st.executeUpdate(sql);
//			rs = st.executeQuery("select Unorderobj from Unorder where Unorderid='"+ fileid + "' for update");
//
//			if (rs.next()) {
//				BLOB blob = (BLOB) rs.getBlob(1);
//				OutputStream outStream = blob.getBinaryOutputStream();
//				File f = new File(file.getUnorderobj());
//				InputStream fin = new FileInputStream(f);
//				byte[] b = new byte[blob.getBufferSize()];
//				int len = 0;
//				while ((len = fin.read(b)) != -1) {
//					outStream.write(b, 0, len);
//				}
//				fin.close();
//				outStream.flush();
//				outStream.close();
//			}
//			conn.commit();
//			flag = true;
//		} catch (Exception e) {
//			try {
//				conn.rollback();
//			} catch (SQLException e1) {
//				e1.printStackTrace();
//			}
//			e.printStackTrace();
//		} finally {
//			DBUtil.closeAll(rs, st, conn);
//		}
//		return flag;
//	}
	
	
	

	
}
