/**
 * 
 */
package com.manage.action;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.stereotype.Controller;

/**
 * @author 情愿孤独
 *
 */
@Controller
@Results({
	@Result(name = "remark_list", location = "systemAdmin/remark/remarkManage.jsp"),
	@Result(name = "remark_edit", location = "systemAdmin/remark/remarkEdit.jsp"),
	@Result(name = "remark_list_lookup", location = "systemAdmin/remark/remark_list_lookup.jsp"),
	@Result(name = "error", location = "error.jsp")
})
public class RemarkAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7713880512491797328L;

}
