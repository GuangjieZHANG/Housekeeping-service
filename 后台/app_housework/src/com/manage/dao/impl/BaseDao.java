package com.manage.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.manage.dao.IBaseDao;
import com.manage.util.PagerModel;


@Repository
public class BaseDao implements IBaseDao {

	@Autowired
	private SessionFactory sessionFactory;

	public Session getSession() {
		// return doGetSession(sessionFactory, null, null, false);
		return sessionFactory.getCurrentSession();
	}


	@SuppressWarnings("unchecked")
	public <T> T get(Class<T> clazz, Serializable id) {
		return (T) getSession().get(clazz, id);
	}

	public <T> void save(T entity) {
		getSession().save(entity);
	}

	public <T> void saveOrUpdate(T entity) {
		getSession().saveOrUpdate(entity);
	}

	public <T> void update(T entity) {
		getSession().merge(entity); // update merge qubie
		getSession().flush();
	}

	public <T> void merge(T entity) {
		getSession().merge(entity);
		getSession().flush();
	}

	public <T> void delete(T entity) {
		getSession().delete(entity);
		getSession().flush();
	}

	public <T> void deleteById(Class<T> clazz, Serializable id) {
		getSession().delete(get(clazz, id)); // get load qubie
		getSession().flush();
	}

	public int batchExecute(final String hql, Object[] params) {
		Query query = getSession().createQuery(hql);
		if (params != null && params.length > 0) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
			}
		}
		return query.executeUpdate();
	}

	public <T> List<T> findByHql(String hql) {
		return findByHql(hql, new Object[] {});
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> findBySql(String sql, Class<T> clazz) {
		Query query = getSession().createSQLQuery(sql).addEntity(clazz);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> findBySql(String sql) {
		Query query = getSession().createSQLQuery(sql);
		return query.list();
	}
 
	public <T> List<T> findByHql(String hql, Object param) {
		return findByHql(hql, new Object[] { param });
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> findByHql(String hql, Object[] params) {
		Query query = getSession().createQuery(hql);
		for (int i = 0; i < params.length; i++) {
			query.setParameter(i, params[i]);
		}
		return query.list();
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> findAll(Class<T> clazz) {
		return getSession().createCriteria(clazz).list();
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> findByCriterion(Class<T> clazz, Order order) {
		return getSession().createCriteria(clazz).addOrder(order).list();
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> findByCriterion(Class<T> clazz, List<Order> orderlist,
			List<Criterion> cronlist) {
		Criteria criteria = getSession().createCriteria(clazz);
		if (cronlist != null) {
			for (Criterion c : cronlist)
				criteria.add(c);
		}
		if (orderlist != null) {
			for (Order o : orderlist)
				criteria.addOrder(o);
		}
		return criteria.list();
	}

	public int count(String hql, Object[] params) {
		Query query = getSession().createQuery(hql);
		if (params != null && params.length > 0) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
			}
		}
		return query.list().size();
	}

	@SuppressWarnings("unchecked")
	public <T> PagerModel findPageByCriterion(Class<T> clazz,int pageno,int pagesize,List<Order> orderlist,List<Criterion> cronlist)
	{
		Criteria criteria = getSession().createCriteria(clazz);
		if (cronlist!=null) {
			for (Criterion c : cronlist) {
				criteria.add(c);
			}
		}		
		if(orderlist!=null)
    	{
    		for (Order o : orderlist)
    			criteria.addOrder(o);
    	}
		int total = criteria.list().size();
 		criteria.setFirstResult((pageno-1)*pagesize);
		criteria.setMaxResults(pagesize);
		List<T> list = criteria.list(); 
		PagerModel pm = new PagerModel();
		pm.setPageNum(pageno);
		pm.setNumPerPage(pagesize);
		pm.setTotalCount(total);
		pm.setList(list);
		
		return pm;
	}

	@SuppressWarnings("rawtypes")
	public PagerModel findPageByHql(String hql, Object[] params, int pageno,
			int pagesize) {
		int total = this.count(hql, params);

		Query query = getSession().createQuery(hql);
		if (query != null && params != null && params.length > 0) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
			}
		}
		query.setFirstResult((pageno - 1) * pagesize);
		query.setMaxResults(pagesize);
		List list = query.list();
		PagerModel pm = new PagerModel();
		pm.setHql(hql);
		pm.setPageNum(pageno);
		pm.setNumPerPage(pagesize);
		pm.setTotalCount(total);
		pm.setList(list);
		return pm;
	}

	@SuppressWarnings("unchecked")
	public <T>PagerModel findPageBySql(Class<T> clazz,String sql, Object[] params, int pageno,
			int pagesize) {
		int total = this.sqlCount(sql, params);

		Query query = getSession().createSQLQuery(sql).addEntity(clazz);
		if (query != null && params != null && params.length > 0) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
			}
		}

		query.setFirstResult((pageno - 1) * pagesize);
		query.setMaxResults(pagesize);
		List<T> list = query.list();
		PagerModel pm = new PagerModel();
		pm.setHql(sql);
		pm.setPageNum(pageno);
		pm.setNumPerPage(pagesize);
		pm.setTotalCount(total);
		pm.setList(list);

		return pm;
	}

	public int sqlCount(String sql, Object[] params) {
		Query query = getSession().createSQLQuery(sql);
		if (params != null && params.length > 0) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
			}
		}
		return query.list().size();
	}
	
	@SuppressWarnings("unchecked")
	public <T> List<T> findByHql(String hql, Map<String, String> params, Class<T> clazz) {
		Query query = getSession().createSQLQuery(hql).addEntity(clazz);
		for(String key : params.keySet()){
			query.setParameter(key, params.get(key));
		}
		
		return query.list();
	}
	public void deleteByHql(String hql) {
		Query query = getSession().createQuery(hql);
		query.executeUpdate();
	}


	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> findByCriterion(Class<T> clazz, List<Order> orderlist,
			Disjunction disjunction) {
		Criteria criteria = getSession().createCriteria(clazz);
		criteria.add(disjunction);
		if (orderlist != null) {
			for (Order o : orderlist)
				criteria.addOrder(o);
		}
		return criteria.list();
	}
}
