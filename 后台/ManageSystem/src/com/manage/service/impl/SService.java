/**
 * 
 */
package com.manage.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.manage.dao.IBaseDao;
import com.manage.po.Servicetype;
import com.manage.service.ISService;
import com.manage.util.PagerModel;

/**
 * @author 情愿孤独
 *
 */
@Service("serviceTypeService")
@Transactional(propagation = Propagation.REQUIRED)
public class SService implements ISService {
	@Autowired
	private IBaseDao baseDao;
	
	@Override
	public PagerModel getSServiceList(int pageno, int pagesize) {
		// TODO Auto-generated method stub
		return baseDao.findPageByHql("from Servicetype", null, pageno, pagesize);
	}

	@Override
	public Servicetype getServicetypeById(String serviceTypeId) {
		// TODO Auto-generated method stub
		return baseDao.get(Servicetype.class, serviceTypeId);
	}

	@Override
	public List<Servicetype> getServicetypesByIds(String[] serviceIds) {
		// TODO Auto-generated method stub
		Disjunction disjunction = Restrictions.disjunction();
		List<Order> orders = new ArrayList<Order>();
		orders.add(Order.desc("addtime"));
		for (String sId : serviceIds)
			disjunction.add(Restrictions.eq("serviceTypeId", sId));
		return baseDao.findByCriterion(Servicetype.class, orders, disjunction);
	}

}
