package com.manage.action;

import java.sql.Timestamp;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.manage.po.Account;
import com.manage.po.Role;
import com.manage.po.User;
import com.manage.service.IAccountService;
import com.manage.service.IUserService;
import com.manage.service.IWorkerService;
import com.manage.util.PagerModel;


@Controller
@Results({
		@Result(name = "user_list", location = "systemAdmin/user/userManage.jsp"),
		@Result(name = "user_edit", location = "systemAdmin/user/userEdit.jsp"),
		@Result(name = "user_add", location = "systemAdmin/user/userAdd.jsp"),
		@Result(name = "user_list_lookup", location = "systemAdmin/user/user_list_lookup.jsp"),
		@Result(name = "error", location = "error.jsp") })
public class UserAction extends BaseAction{
	
	private static final long serialVersionUID = 6718838822334455667L;
	@Autowired
	private IUserService userService;
	@Autowired
	private IWorkerService workerService;

	@Autowired
	private IAccountService accountService;

	private Role role;
	private PagerModel pm;
	private int pageNum = 1;
	private int numPerPage = 20;
	private String[] ids;   //删除ID
	
	private String userId;

	//查找内容
	private String searchUserName;
	private String searchPhoneNumber;
		
	private String user_id;   //修改id 
	private String editorType;
	
	private User user;

    ////////////////////////////////////////////////////////////////////////////////
		
	@Action("usermanage")
	public String getUserList(){
		pm=userService.getUserList(userId, pageNum, numPerPage);
		return "user_list";
	}
	
	@Action("SearchUser_List")
	public String search(){
		pm = userService.search(searchUserName, searchPhoneNumber, pageNum, numPerPage);
		System.out.println("***********--------************-****-*-*-*-*-*-*-");
		return "user_list";
	}
	
	
	// 用于查找带回
	@Action("User_List_LookUp")
	public String user_list_lookup(){
		pm = userService.search(searchUserName, searchPhoneNumber, pageNum, numPerPage);
		return "user_list_lookup";
	}
	
	//////////////////////////////////////////////////////////
	@Action("User_Add")
	public String toAddUserInfo() {
		editorType="add";
		return "user_add";
	}
		
		
	@Action("User_Modify")
	public String showModify(){
		editorType="modify";
		user = userService.getUserById(user_id);
		return "user_edit";	
	}

	@Action("User_Save")
	public String save(){		
		if(editorType.equals("add")){
			Timestamp now = new Timestamp(System.currentTimeMillis());
			user.setAddTime(now);
			user.setModifyTime(now);
			System.out.println(user.getAddTime());
			user.setPassword(user.getPhoneNumber());
			userService.save(user);
			return ajaxJsonSuccessMessage("您已经添加成功!","USERMANAGE","closeCurrent");
		}else{
			userService.merge(user);
			return ajaxJsonSuccessMessage("您已经修改成功!","USERMANAGE","closeCurrent");
		}
	}
	
	///////////////////////////////////////////////////////////////	
	@Action("User_BatchDelete")
	public String delete(){
		userService.batchDelete(ids);
		return ajaxJsonSuccessMessage("删除成功","","");
	}

	///////////////////////////////////////////////////
	

	///////////////////////////////////////////////////
	
	@SuppressWarnings("unused")
	private void getAccountRole() {
		String accid = session.get("uid").toString();
		Account acc = accountService.getAccountById(accid);
		if(acc != null)
			role = acc.getRole();
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

	/**
	 * @return the role
	 */
	public Role getRole() {
		return role;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(Role role) {
		this.role = role;
	}
	/**
	 * @return the userService
	 */
	public IUserService getUserService() {
		return userService;
	}

	/**
	 * @param userService the userService to set
	 */
	public void setUserService(IUserService userService) {
		this.userService = userService;
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
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}
	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the searchUserName
	 */
	public String getSearchUserName() {
		return searchUserName;
	}

	/**
	 * @param searchUserName the searchUserName to set
	 */
	public void setSearchUserName(String searchUserName) {
		this.searchUserName = searchUserName;
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
	 * @return the user_id
	 */
	public String getUser_id() {
		return user_id;
	}

	/**
	 * @param user_id the user_id to set
	 */
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
}
