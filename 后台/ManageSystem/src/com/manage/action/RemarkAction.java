/**
 * 
 */
package com.manage.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.manage.service.IRemarkService;
import com.manage.util.PagerModel;

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
	private static final long serialVersionUID = 8103972093323202146L;
	/**
	 * 
	 */
	private PagerModel pm;
	private int pageNum = 1;
	private int numPerPage = 20;
	private String[] ids;   //删除ID
	
	@Autowired
	private IRemarkService remarkService;
	
	private String remarkId;
	
	@Action("remarkmanage")
	public String getLeaveList(){
		pm = remarkService.getRemarkList(pageNum, numPerPage);
		return "remark_list";
	}

	/**
	 * @return the pm
	 */
	public PagerModel getPm() {
		return pm;
	}

	/**
	 * @param pm the pm to set
	 */
	public void setPm(PagerModel pm) {
		this.pm = pm;
	}

	/**
	 * @return the pageNum
	 */
	public int getPageNum() {
		return pageNum;
	}

	/**
	 * @param pageNum the pageNum to set
	 */
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	/**
	 * @return the numPerPage
	 */
	public int getNumPerPage() {
		return numPerPage;
	}

	/**
	 * @param numPerPage the numPerPage to set
	 */
	public void setNumPerPage(int numPerPage) {
		this.numPerPage = numPerPage;
	}

	/**
	 * @return the ids
	 */
	public String[] getIds() {
		return ids;
	}

	/**
	 * @param ids the ids to set
	 */
	public void setIds(String[] ids) {
		this.ids = ids;
	}

	/**
	 * @return the remarkId
	 */
	public String getRemarkId() {
		return remarkId;
	}

	/**
	 * @param remarkId the remarkId to set
	 */
	public void setRemarkId(String remarkId) {
		this.remarkId = remarkId;
	}
	
}
