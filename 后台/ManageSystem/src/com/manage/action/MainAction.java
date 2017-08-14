package com.manage.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.manage.po.Account;
import com.manage.po.Menu;
import com.manage.service.IAccountService;
import com.manage.service.IRoleService;


@Controller
@Results({ @Result(name = "admin_main", location = "admin/main.jsp") })
public class MainAction extends BaseAction {

	private static final long serialVersionUID = -6388663777202488265L;

	@Autowired
	private IAccountService accountService;
	@Autowired
	private IRoleService roleService;
	private String menuString;

	@Action("/Main")
	public String index() {
		String accountId = (String) session.get("uid");
		Account acc = accountService.getAccountById(accountId);
		menuString = "";
		List<Menu> menus = roleService.listAllMenus(acc.getRole().getRoleId());
		if (acc.getAccountId() != null
				&& roleService.listAllMenus(acc.getRole().getRoleId()).size() > 0) {
			menuString += getMenuString(menus, 0);
		}
		return "admin_main";
	}

	public String getMenuString(List<Menu> menuList, int parentNo) {
		String res = "";
		for (Menu m : menuList) {
			if (m.getParentno() == parentNo) {
				if (m.getIsLeaf() == 0) {
					res += "<li><a>" + m.getName() + "</a>" + "<ul>";
					res += getMenuString(menuList, m.getMenuId());
					res += "</ul>" + "</li>";
				} else {
					res += "<li><a href=\"" + m.getUrl()
							+ "\" target=\"navTab\" rel=\"" + m.getPageno()
							+ "\">" + m.getName() + "</a></li>";
				}
			}
		}
		return res;
	}

	public String getMenuString() {
		return menuString;
	}

	public void setMenuString(String menuString) {
		this.menuString = menuString;
	}

}