package com.manage.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.manage.dao.IBaseDao;
import com.manage.po.Leaverecord;
import com.manage.po.Worker;
import com.manage.service.ILeaveService;
import com.manage.util.PagerModel;



@Service("LeaveService")
@Transactional(propagation = Propagation.REQUIRED)
public class LeaveService implements ILeaveService{

	@Autowired
	private IBaseDao baseDao;
		
	@Override
	public Leaverecord getLeaveById(String leave_id) {
		// TODO Auto-generated method stub
		return baseDao.get(Leaverecord.class, leave_id);
	}

	@Override
	public void merge(Leaverecord leave) {
		// TODO Auto-generated method stub
		baseDao.merge(leave);
	}

	@Override
	public PagerModel getLeaveById(String leaveId,int pageno,int pagesize) {
		// TODO Auto-generated method stub
		return baseDao.findPageBySql(Leaverecord.class, "select * from Leaverecord ORDER BY Leaverecord.addTime DESC", null, pageno, pagesize);
	}

	@Override
	public void save(Leaverecord leave) {
		// TODO Auto-generated method stub
		baseDao.save(leave);
	}

	@Override
	public void update(Leaverecord leave) {
		// TODO Auto-generated method stub
		baseDao.update(leave);
	}

	@Override
	public void delete(String leave_id) {
		// TODO Auto-generated method stub
		Leaverecord leaverecord = getLeaveById(leave_id);
		baseDao.delete(leaverecord);
	}

	@Override
	public void batchDelete(String[] ids) {
		// TODO Auto-generated method stub
		for(int i=0;i<ids.length;i++)
			delete(ids[i]);	
	}
	
	@Override
	public PagerModel search(String searchWorkerName,String searchPhoneNumber,String searchBeginTime,String searchEndTime,int pageno,int pagesize){
		List<Criterion> cronlist = new ArrayList<Criterion>();
		List<Order> orders = new ArrayList<Order>();
		List<String> workerIdsByN = new ArrayList<String>();
		List<String> workerIdsByp = new ArrayList<String>();
		List<String> workerIds = new ArrayList<String>();
		if(searchWorkerName!=null && !searchWorkerName.equals(""))
			workerIdsByN = baseDao.findBySql("select w.workerId from Worker w where w.workerName like '%" + searchWorkerName + "%'");
		if(searchPhoneNumber!=null && !searchPhoneNumber.equals(""))
			workerIdsByp = baseDao.findBySql("select w.workerId from Worker w where w.phoneNumber like '%" + searchPhoneNumber + "%'");
		if(searchBeginTime!=null && !searchBeginTime.equals("") && searchEndTime!=null && !searchEndTime.equals("")){
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//小写的mm表示的是分钟  
			try {
				Date startDate = sdf.parse(searchBeginTime);
				Date endDate = sdf.parse(searchEndTime);
				cronlist.add(Restrictions.between("beginTime",startDate,endDate));
				cronlist.add(Restrictions.between("endTime",startDate,endDate));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if (!searchPhoneNumber.isEmpty() && !searchWorkerName.isEmpty()) {
			// 交集
			workerIdsByN.retainAll(workerIdsByp);
			workerIds = workerIdsByN;
		} else if ((workerIdsByN.isEmpty() || workerIdsByN==null) && !workerIdsByp.isEmpty() && workerIdsByp!=null) {
			workerIds = workerIdsByp;
		} else if (!workerIdsByN.isEmpty() && workerIdsByN!=null && (workerIdsByp.isEmpty() || workerIdsByp==null)) {
			workerIds = workerIdsByN;
		}
		if ((!searchPhoneNumber.isEmpty() || !searchWorkerName.isEmpty())
				&& (workerIds.isEmpty() || workerIds==null)) {
			return null;
		}
		Disjunction disjunction = Restrictions.disjunction();
		for (String wId : workerIds)
			disjunction.add(Restrictions.eq("worker", baseDao.get(Worker.class, wId)));
		cronlist.add(disjunction);
		orders.add(Order.desc("addTime"));
		return baseDao.findPageByCriterion(Leaverecord.class, pageno, pagesize, orders, cronlist);
	}
	
	@Override
	public Leaverecord TestHql(String leaveId) {
		// TODO Auto-generated method stub
		String hql="from Leaverecord where leaveId=?";//HQL语句中表名应该是ORM映射的类名,查询結果是实体类，而不是sql的结果集
		List<Leaverecord> list=baseDao.findByHql(hql, leaveId);
		return list.get(0);
	}

	@Override
	public Leaverecord TestHql(String... param) {
		String hql="from Leaverecord where leaveId=?";
		List<Leaverecord> list=baseDao.findByHql(hql, param);
		return list.get(0);
	}

}
