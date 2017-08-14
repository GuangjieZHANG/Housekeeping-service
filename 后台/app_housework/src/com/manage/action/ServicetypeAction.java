/**
 * 
 */
package com.manage.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.manage.po.Servicetype;
import com.manage.service.ISService;

/**
 * @author 情愿孤独
 *
 */
@Controller
public class ServicetypeAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1963259578217310468L;
	private int pageNum = 1;
	private int numPerPage = 20;
	@Autowired
	private ISService sService;
	
	@SuppressWarnings("unchecked")
	@Action("/service_list")
	public String getServiceList(){
		List<Servicetype> result = sService.getSServiceList(pageNum, numPerPage).getList();
		return this.ajax(List2json(result), "application/json");
	}
}
