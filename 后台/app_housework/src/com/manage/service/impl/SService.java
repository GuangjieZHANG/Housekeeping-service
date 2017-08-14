/**
 * 
 */
package com.manage.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.manage.dao.IBaseDao;
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
		return baseDao.findPageByHql("from Servicetype s order by s.addtime", null, pageno, pagesize);
	}

}
