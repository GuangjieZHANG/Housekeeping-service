package com.manage.action;

import java.sql.Timestamp;

import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.manage.po.User;
import com.manage.service.IOrderedService;
import com.manage.service.IOrderingService;
import com.manage.service.IRemoveOrderService;
import com.manage.service.IUnorderService;
import com.manage.service.IUserService;
import com.manage.service.IWorkerService;
import com.manage.util.PagerModel;


@Controller
public class UserAction extends BaseAction{
	
	private static final long serialVersionUID = 6718838822334455667L;
	@Autowired
	private IUserService userService;
	@Autowired
	private IWorkerService workerService;

	@Autowired
	private IUnorderService unorderService;
	@Autowired
	private IOrderingService orderingService;
	@Autowired
	private IOrderedService orderedService;
	@Autowired
	private IRemoveOrderService removeOrderService;

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
	private String userJson;
	
	private User user;

    ////////////////////////////////////////////////////////////////////////////////
		
	@Action("/user_infor")
	public String getUserInfor(){
		userId = (String) session.get("uid");
//		userId = "8a8a82c85a50b460015a50b488f70000";
		user = userService.getUserById(userId);
		return this.ajax(Object2json(user), "application/json");
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
		System.out.println(userJson);
		user = gsonToObject.fromJson(userJson, User.class);
		System.out.println(user.getUserId());
		if(editorType.equals("add")){
			Timestamp now = new Timestamp(System.currentTimeMillis());
			user.setAddTime(now);
			user.setModifyTime(now);
			userService.save(user);
			return ajaxJsonSuccessMessage("您已经添加成功!");
		}else{
			userService.merge(user);
			return ajaxJsonSuccessMessage("您已经修改成功!");
		}
	}
	
	///////////////////////////////////////////////////////////////	
	@Action("User_BatchDelete")
	public String delete(){
		userService.batchDelete(ids);
		return ajaxJsonSuccessMessage("删除成功");
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

	/**
	 * @return the userJson
	 */
	public String getUserJson() {
		return userJson;
	}

	/**
	 * @param userJson the userJson to set
	 */
	public void setUserJson(String userJson) {
		this.userJson = userJson;
	}
	
}
