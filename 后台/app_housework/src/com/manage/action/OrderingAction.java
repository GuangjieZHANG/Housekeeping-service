package com.manage.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.manage.po.Ordering;
import com.manage.po.Servicetype;
import com.manage.po.User;
import com.manage.po.Worker;
import com.manage.service.IOrderingService;
import com.manage.service.ISService;
import com.manage.service.IUserService;
import com.manage.service.IWorkerService;
import com.manage.util.PagerModel;

@Controller
@Results({
		@Result(name = "ordering_list", location = "systemAdmin/ordering/orderingManage.jsp"),
		@Result(name = "ordering_edit", location = "systemAdmin/ordering/orderingEdit.jsp"),
		@Result(name = "ordering_add", location = "systemAdmin/ordering/orderingAdd.jsp"),
		@Result(name = "error", location = "error.jsp") })

public class OrderingAction extends BaseAction{
	
	private static final long serialVersionUID = 6718838822334455667L;
	@Autowired
	private IOrderingService orderingService;
	@Autowired
	private IUserService userService;
	@Autowired
	private IWorkerService workerService;
	@Autowired
	private ISService sService;
	
	private PagerModel pm;
	private PagerModel servicePM;
	private int pageNum = 1;
	private int numPerPage = 20;
	private String[] ids;   //删除ID
	
	private String userId;
	private String workerId;
	private String orderId;
	private String serviceTypeId;
	//查找内容
	private String searchOrderingId;
	private String searchServiceTypeId;
	private String searchUserName;
	private String searchUserPhoneNumber;
	private String searchBeginTime;
	private String searchEndTime;
	private String searchWorkerName;
	private String searchWorkerPhoneNumber;
	
	private String ordering_id;   //修改id 
	private String editorType;
	
	private Servicetype servicetype;
    ////////////////////////////////////////////////////////////////////////////////
		
	@Action("orderingmanage")
	public String getOrderingList(){
		pm=orderingService.getOrderingList(pageNum, numPerPage);
		servicePM=sService.getSServiceList(pageNum, numPerPage);
		return "ordering_list";
	}
	
	@Action("/user_orderings")
	public String getUserOrderings(){
		User user = userService.getUserById((String)session.get("uid"));
		List<Ordering> orderings = new ArrayList<Ordering>(user.getOrderings());
		return this.ajax(List2json(orderings), "application/json");
	}
	
	@Action("/worker_orderings")
	public String getWorkerOrderings(){
		Worker worker = workerService.getWorkerById((String)session.get("wid"));
		List<Ordering> orderings = new ArrayList<Ordering>(worker.getOrderings());
		return this.ajax(List2json(orderings), "application/json");
	}
	
	@Action("SearchOrdering_List")
	public String search(){
		pm = orderingService.search(searchOrderingId, searchServiceTypeId, searchUserName,searchUserPhoneNumber,
				searchBeginTime, searchEndTime, searchWorkerName, searchWorkerPhoneNumber, pageNum, numPerPage);
		servicePM=sService.getSServiceList(pageNum, numPerPage);
		return "ordering_list";
	}
	
	
//	//////////////////////////////////////////////////////////
//	@Action("Ordering_Add")
//	public String toAddOrderingInfo() {
//		editorType="add";
//		if(projectid!=null && projectid.equals("")){
//			System.out.println("---------123456789----------");
////			setProject(projectService.getProjectById(projectid));//由项目进入添加奖项页面
//			System.out.println("---------888888888----------");
//		}
//		return "ordering_add";		
//	}
//	
//		
//	@Action("Ordering_Modify")
//	public String showModify(){
//		System.out.println("---------777777777----------");
//		editorType="modify";
//		ordering = orderingService.getOrderingById(ordering_id);
//		return "ordering_edit";	
//	}
//
//	@Action("Ordering_Save")
//	public String save(){
//		if(editorType.equals("add")){
////			ordering.setProject(projectService.getProjectById(projectid));
//			orderingService.save(ordering);
//			return ajaxJsonSuccessMessage("您已经添加成功!","orderingMANAGE","closeCurrent");
//		}else{
////			ordering.setProject(projectService.getProjectById(projectid));
//			orderingService.merge(ordering);
//			return ajaxJsonSuccessMessage("您已经修改成功!","orderingMANAGE","closeCurrent");
//		}
//	}
//		
//	///////////////////////////////////////////////////////////////
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	///////////////////////////////////////////////////////////////	
//	@Action("Ordering_BatchDelete")
//	public String delete(){
//		orderingService.batchDelete(ids);         
//		return ajaxJsonSuccessMessage("删除成功","","");
//	}

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
	 * @return the orderingService
	 */
	public IOrderingService getOrderingService() {
		return orderingService;
	}

	/**
	 * @param orderingService the orderingService to set
	 */
	public void setOrderingService(IOrderingService orderingService) {
		this.orderingService = orderingService;
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
	 * @return the orderId
	 */
	public String getOrderId() {
		return orderId;
	}

	/**
	 * @param orderId the orderId to set
	 */
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	/**
	 * @return the servicePM
	 */
	public PagerModel getServicePM() {
		return servicePM;
	}

	/**
	 * @param servicePM the servicePM to set
	 */
	public void setServicePM(PagerModel servicePM) {
		this.servicePM = servicePM;
	}
	/**
	 * @return the sService
	 */
	public ISService getsService() {
		return sService;
	}
	/**
	 * @param sService the sService to set
	 */
	public void setsService(ISService sService) {
		this.sService = sService;
	}

	/**
	 * @return the searchOrderingId
	 */
	public String getSearchOrderingId() {
		return searchOrderingId;
	}

	/**
	 * @param searchOrderingId the searchOrderingId to set
	 */
	public void setSearchOrderingId(String searchOrderingId) {
		this.searchOrderingId = searchOrderingId;
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
	 * @return the searchUserPhoneNumber
	 */
	public String getSearchUserPhoneNumber() {
		return searchUserPhoneNumber;
	}

	/**
	 * @param searchUserPhoneNumber the searchUserPhoneNumber to set
	 */
	public void setSearchUserPhoneNumber(String searchUserPhoneNumber) {
		this.searchUserPhoneNumber = searchUserPhoneNumber;
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
	 * @return the searchWorkerPhoneNumber
	 */
	public String getSearchWorkerPhoneNumber() {
		return searchWorkerPhoneNumber;
	}

	/**
	 * @param searchWorkerPhoneNumber the searchWorkerPhoneNumber to set
	 */
	public void setSearchWorkerPhoneNumber(String searchWorkerPhoneNumber) {
		this.searchWorkerPhoneNumber = searchWorkerPhoneNumber;
	}

	/**
	 * @return the servicetype
	 */
	public Servicetype getServicetype() {
		return servicetype;
	}

	/**
	 * @param servicetype the servicetype to set
	 */
	public void setServicetype(Servicetype servicetype) {
		this.servicetype = servicetype;
	}

	/**
	 * @return the serviceTypeId
	 */
	public String getServiceTypeId() {
		return serviceTypeId;
	}

	/**
	 * @param serviceTypeId the serviceTypeId to set
	 */
	public void setServiceTypeId(String serviceTypeId) {
		this.serviceTypeId = serviceTypeId;
	}

	/**
	 * @return the searchServiceTypeId
	 */
	public String getSearchServiceTypeId() {
		return searchServiceTypeId;
	}

	/**
	 * @param searchServiceTypeId the searchServiceTypeId to set
	 */
	public void setSearchServiceTypeId(String searchServiceTypeId) {
		this.searchServiceTypeId = searchServiceTypeId;
	}

	/**
	 * @return the ordering_id
	 */
	public String getOrdering_id() {
		return ordering_id;
	}

	/**
	 * @param ordering_id the ordering_id to set
	 */
	public void setOrdering_id(String ordering_id) {
		this.ordering_id = ordering_id;
	}
	
}
