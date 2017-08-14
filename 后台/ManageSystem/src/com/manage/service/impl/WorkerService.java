package com.manage.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.manage.dao.IBaseDao;
import com.manage.po.Worker;
import com.manage.service.IWorkerService;
import com.manage.util.PagerModel;



@Service("WorkerService")
@Transactional(propagation = Propagation.REQUIRED)
public class WorkerService implements IWorkerService{

	@Autowired
	private IBaseDao baseDao;
		
	@Override
	public Worker getWorkerById(String workerId) {
		// TODO Auto-generated method stub
		return baseDao.get(Worker.class, workerId);
	}

	@Override
	public void merge(Worker worker) {
		// TODO Auto-generated method stub
		baseDao.merge(worker);
	}

	@Override
	public PagerModel getWorkerList(int pageno, int pagesize) {	
		String sql = "select * from worker";
		System.out.println("********" + sql);
		return baseDao.findPageBySql(Worker.class, sql, null, pageno, pagesize);
	}

	@Override
	public void save(Worker worker) {
		// TODO Auto-generated method stub
		baseDao.save(worker);
	}

	@Override
	public void update(Worker worker) {
		// TODO Auto-generated method stub
		baseDao.update(worker);
	}

	@Override
	public void delete(String workerId) {
		// TODO Auto-generated method stub
		Worker worker = getWorkerById(workerId);
		baseDao.delete(worker);
	}

	@Override
	public void batchDelete(String[] ids) {
		// TODO Auto-generated method stub
		for(int i=0;i<ids.length;i++)
			delete(ids[i]);	
	}
	
	@Override
	public PagerModel search(String searchWorkerName,String searchPhoneNumber,String searchCardId,String searchWokerSex, String searchServiceTypeId, int pageno,int pagesize) {
		List<Criterion> cronlist = new ArrayList<Criterion>();
		List<Order> orders = new ArrayList<Order>();
		List<String> alias = new ArrayList<String>();
		if(searchWorkerName!=null && !searchWorkerName.equals(""))
			cronlist.add(Restrictions.like("workerName",searchWorkerName,MatchMode.ANYWHERE));
		if(searchPhoneNumber!=null && !searchPhoneNumber.equals(""))
			cronlist.add(Restrictions.like("phoneNumber",searchPhoneNumber,MatchMode.ANYWHERE));
		if(searchCardId!=null && !searchCardId.equals(""))
			cronlist.add(Restrictions.like("cardId",searchCardId,MatchMode.ANYWHERE));
		if(searchWokerSex!=null && !searchWokerSex.equals(""))
			cronlist.add(Restrictions.like("sex",searchWokerSex,MatchMode.ANYWHERE));
		if(searchServiceTypeId!=null && !searchServiceTypeId.equals("")){
			alias.add("servicetype");
			cronlist.add(Restrictions.eq("servicetype.serviceTypeId",searchServiceTypeId));
		}
		orders.add(Order.asc("addtime"));
		return baseDao.findPageByCreateAlias(Worker.class, pageno, pagesize, orders, cronlist, alias);
	}

	@Override
	public Worker TestHql(String workerId) {
		// TODO Auto-generated method stub
		String hql="from Team where teamid=?";//HQL语句中表名应该是ORM映射的类名,查询結果是实体类，而不是sql的结果集
		List<Worker> list=baseDao.findByHql(hql, workerId);
		return list.get(0);
	}

	@Override
	public Worker TestHql(String... param) {
		String hql="from Team where teamid=?";
		List<Worker> list=baseDao.findByHql(hql, param);
		return list.get(0);
	}

}
