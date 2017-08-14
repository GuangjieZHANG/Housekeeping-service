package com.manage.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;

import com.manage.util.PagerModel;



public interface IBaseDao {

	public <T> T get(Class<T> clazz, Serializable id);

	public <T> void save(T entity);

	public <T> void saveOrUpdate(T entity);

	public <T> void update(T entity);

	public <T> void merge(T entity);

	public <T> void delete(T entity);

	public <T> void deleteById(Class<T> clazz, Serializable id);

	public int batchExecute(final String hql, Object[] params);

	public <T> List<T> findByHql(String hql);

	public <T> List<T> findBySql(String sql, Class<T> clazz);
	 
	public <T> List<T> findBySql(String sql);
	
	public <T> List<T> findByHql(String hql, Object param);

	public <T> List<T> findByHql(String hql, Object[] params);

	public <T> List<T> findAll(Class<T> clazz);

	public <T> List<T> findByCriterion(Class<T> clazz, Order order);

	public <T> List<T> findByCriterion(Class<T> clazz, List<Order> orderlist,
			List<Criterion> cronlist);
	
	public <T> List<T> findByCriterion(Class<T> clazz, List<Order> orderlist, Disjunction disjunction);

	public int count(String hql, Object[] params);

	public <T> PagerModel findPageByCriterion(Class<T> clazz, int pageno,
			int pagesize, List<Order> orderlist, List<Criterion> cronlist);

	public PagerModel findPageByHql(String hql, Object[] params, int pageno,
			int pagesize);

	public <T>PagerModel findPageBySql(Class<T> clazz,String sql, Object[] params, int pageno,
			int pagesize);
	public <T> List<T> findByHql(String hql, Map<String, String> params, Class<T> clazz);
	public void deleteByHql(String hql);
}
