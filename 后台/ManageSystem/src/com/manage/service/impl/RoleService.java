package com.manage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.manage.dao.IBaseDao;
import com.manage.po.Menu;
import com.manage.service.IRoleService;



@Service("RoleService")
@Transactional(propagation = Propagation.REQUIRED)
public class RoleService implements IRoleService {
	@Autowired
	private IBaseDao baseDao;

	@Override
	public List<Menu> listAllMenus(int roleid) {
		// select * from menu m where m.id in(select menuid from role_menu where
		// roleid=1);
		return baseDao.findBySql(
				"select m.* from menu m, role_menu rm where rm.menuid=m.menuid and rm.roleid="
						+ roleid, Menu.class);
	}
}
