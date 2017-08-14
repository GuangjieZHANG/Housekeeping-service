package com.manage.action;

import java.sql.Timestamp;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.manage.po.Account;
import com.manage.po.Leaverecord;
import com.manage.service.IAccountService;
import com.manage.service.ILeaveService;
import com.manage.service.IWorkerService;
import com.manage.util.PagerModel;


@Controller
@Results({
		@Result(name = "leave_list", location = "systemAdmin/leave/leaveManage.jsp"),
		@Result(name = "leave_edit", location = "systemAdmin/leave/leaveEdit.jsp"),
		@Result(name = "leave_add", location = "systemAdmin/leave/leaveAdd.jsp"),
		@Result(name = "leave_list_lookup", location = "systemAdmin/leave/leave_list_lookup.jsp"),
		@Result(name = "error", location = "error.jsp") })
public class LeaveAction extends BaseAction{
	
	private static final long serialVersionUID = 6718838822334455667L;
	@Autowired
	private ILeaveService leaveService;
	@Autowired
	private IWorkerService workerService;
	@Autowired
	private IAccountService accountService;

	private int roleid;
	
	
	private PagerModel pm;
	private int pageNum = 1;
	private int numPerPage = 20;
	private String[] ids;   //删除ID
	
	private String leaveId;
	private String workerId;
	//查找内容
	private String searchEndTime;
	private String searchWorkerName;
	private String searchPhoneNumber;
	private String searchBeginTime;
		
	private String leave_id;   //修改id 
	private String editorType;
	
	private Leaverecord leave;
	
    ////////////////////////////////////////////////////////////////////////////////
		
	@Action("leavemanage")
	public String getLeaveList(){
		pm=leaveService.getLeaveById(leaveId, pageNum, numPerPage);
		return "leave_list";
	}
	
	@Action("SearchLeave_List")
	public String search(){
		pm = leaveService.search(searchWorkerName, searchPhoneNumber, searchBeginTime,searchEndTime, pageNum, numPerPage);
		System.out.println("***********--------************-****-*-*-*-*-*-*-");
		return "leave_list";
	}
	
	
	// 用于查找带回
	@Action("Leave_List_LookUp")
	public String leave_list_lookup(){
//		pm = leaveService.search(searchId, searchName, searchStart,searchEnd, pageNum, numPerPage);
		return "leave_list_lookup";
	}
	
	//////////////////////////////////////////////////////////
	@Action("Leave_Add")
	public String toAddLeaveInfo() {
		editorType="add";
		return "leave_add";
	}
		
		
	@Action("Leave_Modify")
	public String showModify(){
		editorType="modify";
		leave = leaveService.getLeaveById(leave_id);
		return "leave_edit";	
	}

	@Action("Leave_Save")
	public String save(){
		if(editorType.equals("add")){
			leave.setWorker(workerService.getWorkerById(workerId));
			Timestamp now = new Timestamp(System.currentTimeMillis());
			leave.setAddTime(now);
			leave.setModifyTime(now);
			leaveService.save(leave);
			return ajaxJsonSuccessMessage("您已经添加成功!","LEAVEMANAGE","closeCurrent");
		}else{
			leave.setWorker(workerService.getWorkerById(workerId));
			leaveService.merge(leave);
			return ajaxJsonSuccessMessage("您已经修改成功!","LEAVEMANAGE","closeCurrent");
		}
	}
	
	///////////////////////////////////////////////////////////////	
	@Action("Leave_BatchDelete")
	public String delete(){
		leaveService.batchDelete(ids);
		return ajaxJsonSuccessMessage("删除成功","","");
	}

	///////////////////////////////////////////////////
	

	///////////////////////////////////////////////////
	
	@SuppressWarnings("unused")
	private void getAccountRole() {
		String accid = session.get("uid").toString();
		Account acc = accountService.getAccountById(accid);
		if(acc != null)
			roleid = acc.getRole().getRoleId();
	}

	////////////////////////////////////////////////////////////////////////////////

	public PagerModel getPm() {
		return pm;
	}

	public void setPm(PagerModel pm) {
		this.pm = pm;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getNumPerPage() {
		return numPerPage;
	}

	public void setNumPerPage(int numPerPage) {
		this.numPerPage = numPerPage;
	}

	public String[] getIds() {
		return ids;
	}

	public void setIds(String[] ids) {
		this.ids = ids;
	}

	public String getEditorType() {
		return editorType;
	}

	public void setEditorType(String editorType) {
		this.editorType = editorType;
	}
	
	public IAccountService getAccountService() {
		return accountService;
	}

	public void setAccountService(IAccountService accountService) {
		this.accountService = accountService;
	}

	public int getRoleid() {
		return roleid;
	}

	public void setRoleid(int roleid) {
		this.roleid = roleid;
	}

	/**
	 * @return the leaveService
	 */
	public ILeaveService getLeaveService() {
		return leaveService;
	}

	/**
	 * @param leaveService the leaveService to set
	 */
	public void setLeaveService(ILeaveService leaveService) {
		this.leaveService = leaveService;
	}

	/**
	 * @return the workerService
	 */
	public IWorkerService getWorkerService() {
		return workerService;
	}

	/**
	 * @param workerService the workerService to set
	 */
	public void setWorkerService(IWorkerService workerService) {
		this.workerService = workerService;
	}

	/**
	 * @return the leaveId
	 */
	public String getLeaveId() {
		return leaveId;
	}

	/**
	 * @param leaveId the leaveId to set
	 */
	public void setLeaveId(String leaveId) {
		this.leaveId = leaveId;
	}

	/**
	 * @return the leave_id
	 */
	public String getLeave_id() {
		return leave_id;
	}

	/**
	 * @param leave_id the leave_id to set
	 */
	public void setLeave_id(String leave_id) {
		this.leave_id = leave_id;
	}

	/**
	 * @return the leave
	 */
	public Leaverecord getLeave() {
		return leave;
	}

	/**
	 * @param leave the leave to set
	 */
	public void setLeave(Leaverecord leave) {
		this.leave = leave;
	}

	/**
	 * @return the workerId
	 */
	public String getWorkerId() {
		return workerId;
	}

	/**
	 * @param workerId the workerId to set
	 */
	public void setWorkerId(String workerId) {
		this.workerId = workerId;
	}

	/**
	 * @return the searchEndTime
	 */
	public String getSearchEndTime() {
		return searchEndTime;
	}

	/**
	 * @param searchEndTime the searchEndTime to set
	 */
	public void setSearchEndTime(String searchEndTime) {
		this.searchEndTime = searchEndTime;
	}

	/**
	 * @return the searchWorkerName
	 */
	public String getSearchWorkerName() {
		return searchWorkerName;
	}

	/**
	 * @param searchWorkerName the searchWorkerName to set
	 */
	public void setSearchWorkerName(String searchWorkerName) {
		this.searchWorkerName = searchWorkerName;
	}

	/**
	 * @return the searchPhoneNumber
	 */
	public String getSearchPhoneNumber() {
		return searchPhoneNumber;
	}

	/**
	 * @param searchPhoneNumber the searchPhoneNumber to set
	 */
	public void setSearchPhoneNumber(String searchPhoneNumber) {
		this.searchPhoneNumber = searchPhoneNumber;
	}

	/**
	 * @return the searchBeginTime
	 */
	public String getSearchBeginTime() {
		return searchBeginTime;
	}

	/**
	 * @param searchBeginTime the searchBeginTime to set
	 */
	public void setSearchBeginTime(String searchBeginTime) {
		this.searchBeginTime = searchBeginTime;
	}
	
}
