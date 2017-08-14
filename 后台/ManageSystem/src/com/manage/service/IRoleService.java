package com.manage.service;

import java.util.List;
import com.manage.po.Menu;

public interface IRoleService {
	public List<Menu> listAllMenus(int roleid);
}
