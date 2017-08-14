package com.manage.action;



import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.manage.po.Ordered;
import com.manage.po.Servicetype;
import com.manage.service.IOrderedService;
import com.manage.service.ISService;
import com.manage.service.IUserService;
import com.manage.service.IWorkerService;
import com.manage.util.DateFormat;
import com.manage.util.PagerModel;


@Controller
@Results({
		@Result(name = "ordered_list", location = "systemAdmin/ordered/orderedManage.jsp"),
		@Result(name = "ordered_edit", location = "systemAdmin/ordered/orderedEdit.jsp"),
		@Result(name = "ordered_list_lookup", location = "systemAdmin/ordered/ordered_list_lookup.jsp"),
		@Result(name = "error", location = "error.jsp") })
public class OrderedAction extends BaseAction{
	
	private static final long serialVersionUID = 6718838822334455667L;
	@Autowired
	private IOrderedService orderedService;
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
	private String searchOrderedId;
	private String searchServiceTypeId;
	private String searchUserName;
	private String searchUserPhoneNumber;
	private String searchBeginTime;
	private String searchEndTime;
	private String searchWorkerName;
	private String searchWorkerPhoneNumber;
	
	private String ordered_id;   //修改id 
	private String editorType;
	
	private Servicetype servicetype;
	private Ordered ordered;


    ////////////////////////////////////////////////////////////////////////////////
		
	@Action("orderedmanage")
	public String getOrderedList(){
		pm=orderedService.getOtheredList(pageNum, numPerPage);
		for(int i=0; i<pm.getList().size(); i++){
			Ordered orderedT = (Ordered)pm.getList().get(i);
			orderedT.setCost(DateFormat.formatDouble(DateFormat.Hours(orderedT.getStartTime(), orderedT.getFinishTime()) * orderedT.getServicetype().getUserPrice(), 2));
		}
		servicePM=sService.getSServiceList(pageNum, numPerPage);
		return "ordered_list";
	}
	
	@Action("SearchOrdered_List")
	public String search(){
		pm = orderedService.search(searchOrderedId, searchServiceTypeId, searchUserName,searchUserPhoneNumber,
				searchBeginTime, searchEndTime, searchWorkerName, searchWorkerPhoneNumber, pageNum, numPerPage);
		for(int i=0; i<pm.getList().size(); i++){
			Ordered orderedT = (Ordered)pm.getList().get(i);
			orderedT.setCost(DateFormat.formatDouble(DateFormat.Hours(orderedT.getStartTime(), orderedT.getFinishTime()) * orderedT.getServicetype().getUserPrice(), 2));
		}
		servicePM=sService.getSServiceList(pageNum, numPerPage);
		return "ordered_list";
	}
	
	
	// 用于查找带回
//	@Action("Room_List_LookUp")
//	public String room_list_lookup(){
//		pm = roomService.search(searchId, searchName, pageNum, numPerPage);
//		return "room_list_lookup";
//	}
	
	
	//////////////////////////////////////////////////////////
//	@Action("Room_Add")
//	public String toAddRoomInfo() {
//		editorType="add";
//		return "room_edit";
//	}
//		
//	@Action("Room_Modify")
//	public String showModify(){
//		System.out.println("---------777777777----------");
//		editorType="modify";
//		room = roomService.getRoomById(room_ID);
//		return "room_edit";	
//	}
//
//	@Action("Room_Save")
//	public String save(){
//		if(editorType.equals("add")){
//			roomService.save(room);
//			return ajaxJsonSuccessMessage("您已经添加成功!","ROOMMANAGE","closeCurrent");
//		}else{
//			roomService.merge(room);
//			return ajaxJsonSuccessMessage("您已经修改成功!","ROOMMANAGE","closeCurrent");
//		}
//	}
//	
//	///////////////////////////////////////////////////////////////	
//	@Action("Room_BatchDelete")
//	public String delete(){
//		roomService.batchDelete(ids);
//		return ajaxJsonSuccessMessage("删除成功","","");
//	}

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
	 * @return the orderedService
	 */
	public IOrderedService getOrderedService() {
		return orderedService;
	}

	/**
	 * @param orderedService the orderedService to set
	 */
	public void setOrderedService(IOrderedService orderedService) {
		this.orderedService = orderedService;
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
	 * @return the searchOrderedId
	 */
	public String getSearchOrderedId() {
		return searchOrderedId;
	}

	/**
	 * @param searchOrderedId the searchOrderedId to set
	 */
	public void setSearchOrderedId(String searchOrderedId) {
		this.searchOrderedId = searchOrderedId;
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
	 * @return the ordered
	 */
	public Ordered getOrdered() {
		return ordered;
	}

	/**
	 * @param ordered the ordered to set
	 */
	public void setOrdered(Ordered ordered) {
		this.ordered = ordered;
	}

	/**
	 * @return the ordered_id
	 */
	public String getOrdered_id() {
		return ordered_id;
	}

	/**
	 * @param ordered_id the ordered_id to set
	 */
	public void setOrdered_id(String ordered_id) {
		this.ordered_id = ordered_id;
	}
	
}
	
	