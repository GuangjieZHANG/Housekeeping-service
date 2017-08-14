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
import com.manage.util.MapDistance;
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

	
	/**
	 * String hql="from RegionUser u where u.lat is not null and u.lng is not null order by ACOS(SIN(("+lat.toString()+" * 3.1415) / 180 ) *SIN((u.lat * 3.1415) / 180 ) +COS(("+lat.toString()+" * 3.1415) / 180 ) * COS((u.lat * 3.1415) / 180 ) *COS(("+lng.toString()+" * 3.1415) / 180 - (u.lng * 3.1415) / 180 ) ) * 6378.140 asc";

	 * select new com.ima.dto.NearByUser(u.id,u.latitude,u.longitude,(2 * 6378.137* ASIN(SQRT(POW(SIN(PI()*(:latitude-latitude)/360),2)+COS(PI()*:longitude/180)* COS(latitude * PI()/180)*POW(SIN(PI()*(:longitude-longitude)/360),2)))) as juli) from User u order by juli asc
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Worker matchWorker(Unorder unorder) {
		// TODO Auto-generated method stub
		List<Criterion> cronlist = new ArrayList<Criterion>();
		List<Order> orders = new ArrayList<Order>();
		List<String> alias = new ArrayList<String>();
		List<Worker> workers = new ArrayList<Worker>();
		Disjunction disjunction = Restrictions.disjunction();
		if(unorder.getWorker() != null)
			cronlist.add(Restrictions.not(Restrictions.eq("workerId",unorder.getWorker().getWorkerId())));
		alias.add("servicetype");
		cronlist.add(Restrictions.eq("servicetype.serviceTypeId",unorder.getServicetype().getServiceTypeId()));
		
		alias.add("ordering");
		disjunction.add(Restrictions.isNull("ordering.worker"));
		disjunction.add(Restrictions.lt("ordering.startTime", new Timestamp(unorder.getPredictTime().getTime() + 5*60*60*1000)));
		cronlist.add(disjunction);
//		orders.add(Order.asc("(2.0 * 6378137.0* ASIN(SQRT(POW(SIN(PI()*(" + unorder.getLatitude() + "-worker.latitude)/360.0),2)+COS(PI()*" + unorder.getLongitude() + "/180.0)* COS(worker.latitude * PI()/180.0)*POW(SIN(PI()*(" + unorder.getLongitude() + "-worker.longitude)/360.0),2))))"));
		workers = baseDao.findPageByCreateAlias(Worker.class, 1, 20, orders, cronlist, alias).getList();
		return minDistance(workers, unorder.getLongitude(), unorder.getLatitude());
	}

	   /**
     * @description 给阿姨排序
     * @param workers
     * @param longi
     * @param lati
     * @return
     */
    public List<Worker> workerSort(List<Worker> workers, double longi, double lati){
    	for(int i=0; i<workers.size() - 1; i++){
			for(int j=i + 1; j<workers.size(); j++){
				if(MapDistance.getDistance(workers.get(i).getLatitude(), workers.get(i).getLongitude(), lati, longi) > MapDistance.getDistance(workers.get(j).getLatitude(), workers.get(i).getLongitude(), lati, longi)){
					Worker part = workers.get(i);
					workers.set(i, workers.get(j));
					workers.set(j, part);
				}
			}
		}
    	return workers;
    }
	   /**
  * @description 给阿姨排序
  * @param workers
  * @param longi
  * @param lati
  * @return
  */
 public Worker minDistance(List<Worker> workers, double longi, double lati){
	 Worker worker = new Worker();
	 if(workers.size() != 0){
		 worker = workers.get(0);
		 for(int i=1; i<workers.size() - 1; i++){
				if(MapDistance.getDistance(workers.get(i).getLatitude(), workers.get(i).getLongitude(), lati, longi) < MapDistance.getDistance(worker.getLatitude(), worker.getLongitude(), lati, longi)){
					worker = workers.get(i);
				}
			}
	 }
 	return worker;
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
