package com.manage.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.manage.dao.IBaseDao;
import com.manage.po.Remark;
import com.manage.service.IRemarkService;
import com.manage.util.PagerModel;


@Service("RemarkService")
@Transactional(propagation = Propagation.REQUIRED)
public class RemarkService implements IRemarkService{

	@Autowired
	private IBaseDao baseDao;

	@Override
	public PagerModel getRemarkList(int pageNum, int numPerPage) {
		// TODO Auto-generated method stub
		String sql = "select * from remark ORDER BY remark.addTime DESC";
		return baseDao.findPageBySql(Remark.class, sql, null, pageNum, numPerPage);
	}
		
//	@Override
//	public Device getDeviceById(String deviceid) {
//		// TODO Auto-generated method stub
//		return baseDao.get(Device.class, deviceid);
//	}
//
//	@Override
//	public void merge(Device device) {
//		// TODO Auto-generated method stub
//		baseDao.merge(device);
//	}
//
//	@Override
//	public PagerModel getDeviceById(String deviceid, int pageno, int pagesize) {
//		// TODO Auto-generated method stub
//		//String sql = "select * from device where deviceid = " + "'" + deviceid + "'";
//		String sql = "select * from device";
//		System.out.println("********" + sql);
//		return baseDao.findPageBySql(Device.class, sql, null, pageno, pagesize);
//	}
//
//	@Override
//	public void save(Device device) {
//		// TODO Auto-generated method stub
//		baseDao.save(device);
//	}
//
//	@Override
//	public void update(Device device) {
//		// TODO Auto-generated method stub
//		baseDao.update(device);
//	}
//
//	@Override
//	public void delete(String deviceid) {
//		// TODO Auto-generated method stub
//		Device device = getDeviceById(deviceid);
//		baseDao.delete(device);
//	}
//
//	@Override	public void batchDelete(String[] ids) {
//		// TODO Auto-generated method stub
//		for(int i=0;i<ids.length;i++)
//			delete(ids[i]);	
//	}
//	
//	@Override
//	public PagerModel search(String deviceid,String devicetype,String location,
//			String deviceowner,int pageno,int pagesize) {
//		List<Criterion> cronlist = new ArrayList<Criterion>();
//		if(deviceid!=null && !deviceid.equals(""))
//			cronlist.add(Restrictions.like("deviceid",deviceid,MatchMode.ANYWHERE));
//		if(devicetype!=null && !devicetype.equals(""))
//			cronlist.add(Restrictions.like("devicetype",devicetype,MatchMode.ANYWHERE));
//		if(location!=null && !location.equals(""))
//			cronlist.add(Restrictions.like("location",location,MatchMode.ANYWHERE));
//		if(deviceowner!=null && !deviceowner.equals(""))
//			cronlist.add(Restrictions.like("deviceowner",deviceowner,MatchMode.ANYWHERE));
//		return baseDao.findPageByCriterion(Device.class, pageno, pagesize, null, cronlist);
//	}
//
//	@Override
//	public Device TestHql(String deviceid) {
//		// TODO Auto-generated method stub
//		String hql="from Device where deviceid=?";//HQL语句中表名应该是ORM映射的类名,查询結果是实体类，而不是sql的结果集
//		List<Device> list=baseDao.findByHql(hql, deviceid);
//		return list.get(0);
//	}
//
//	@Override
//	public Device TestHql(String... param) {
//		String hql="from Device where deviceid=?";
//		List<Device> list=baseDao.findByHql(hql, param);
//		return list.get(0);
//	}

}
