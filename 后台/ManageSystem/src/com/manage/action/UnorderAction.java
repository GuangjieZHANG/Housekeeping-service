package com.manage.action;

import java.sql.Timestamp;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.manage.po.Servicetype;
import com.manage.po.Unorder;
import com.manage.po.User;
import com.manage.po.Worker;
import com.manage.service.IRemoveOrderService;
import com.manage.service.ISService;
import com.manage.service.IUnorderService;
import com.manage.service.IUserService;
import com.manage.service.IWorkerService;
import com.manage.util.DateFormat;
import com.manage.util.PagerModel;

@Controller
@Results({
		@Result(name = "unorder_list", location = "systemAdmin/unorder/unorderManage.jsp"),
		@Result(name = "unorder_edit", location = "systemAdmin/unorder/unorderEdit.jsp"),
		@Result(name = "unorder_add", location = "systemAdmin/unorder/unorderAdd.jsp"),
		@Result(name = "error", location = "error.jsp") })

public class UnorderAction extends BaseAction{
	
	private static final long serialVersionUID = 6718838822334455667L;
	@Autowired
	private IWorkerService workerService;
	@Autowired
	private IUserService userService;
	@Autowired
	private IUnorderService unorderService;
	@Autowired
	private ISService sService;
	@Autowired
	private IRemoveOrderService removeOrderService;
	
	private PagerModel pm;
	private PagerModel servicePM;
	private int pageNum = 1;
	private int numPerPage = 20;
	private String[] ids;   //删除ID

	
	private String workerId;
	private String userId;
	private String orderId;
	private String serviceTypeId;
	//查找内容
	private String searchUnorderId;
	private String searchServiceTypeId;
	private String searchUserName;
	private String searchUserPhoneNumber;
	private String searchBeginTime;
	private String searchEndTime;
	private String searchWorkerName;
	private String searchWorkerPhoneNumber;
		
	private String unorder_id;   //修改id 
	private String editorType;
	
	private Worker worker;
	private User user;
	private Unorder unorder;
	private Servicetype servicetype;
    ////////////////////////////////////////////////////////////////////////////////		
	@Action("unordermanage")
	public String getUnorderList(){
		pm=unorderService.getUnorderList(pageNum, numPerPage);
		servicePM=sService.getSServiceList(pageNum, numPerPage);
		return "unorder_list";
	}
	
	@Action("SearchUnorder_List")
	public String search(){
		pm = unorderService.search(searchUnorderId, searchServiceTypeId, searchUserName,searchUserPhoneNumber,
				searchBeginTime, searchEndTime, searchWorkerName, searchWorkerPhoneNumber, pageNum, numPerPage);
		servicePM=sService.getSServiceList(pageNum, numPerPage);
		return "unorder_list";
	}
	//////////////////////////////////////////////////////////
	
	
	@Action("Unorder_Add")
	public String toAddUnorderInfo() {
		System.out.println("--------- unorder_Add ----------");
		editorType="add";
        servicePM=sService.getSServiceList(pageNum, numPerPage);
        return "unorder_add";		
	}
	
	@Action("Unorder_Modify")
	public String showModify(){
		System.out.println("--------- Unorder_Modify ----------");
		editorType="modify";
		if(pm == null) 
			pm = new PagerModel();
		unorder = unorderService.getUnorderById(unorder_id);
		user = unorder.getUser();
		worker = unorder.getWorker();
		servicePM=sService.getSServiceList(pageNum, numPerPage);
		return "unorder_edit";	
	}
	
	@Action("Unorder_Save")
	public String save(){
		unorder.setIsReced(false);
		unorder.setUser(user);
		unorder.setServicetype(servicetype);
		Timestamp now = new Timestamp(System.currentTimeMillis());
		unorder.setAddTime(now);
		unorder.setTimer(now);
		if(editorType.equals("add")){
			if(!DateFormat.isEmpty(worker.getWorkerId())){
				unorder.setWorker(worker);
			}else {
				unorder.setWorker(unorderService.matchWorker(unorder));
			}
			System.out.println(unorder.toString());
			unorderService.save(unorder);
			return ajaxJsonSuccessMessage("您已经添加成功!","UNORDERMANAGE","closeCurrent");
		}else{
			System.out.println(editorType);
			worker.getClass().getDeclaredFields();
			if(!DateFormat.isEmpty(worker.getWorkerId())){
				unorder.setWorker(worker);
			}
			unorderService.merge(unorder);
			return ajaxJsonSuccessMessage("您已经修改成功!","UNORDERMANAGE","closeCurrent");
		}
	}
		
	@Action("Unorder_BatchDelete")
	public String delete(){
		unorderService.batchDelete(ids);
		return ajaxJsonSuccessMessage("删除成功","","");
	}

	/********************get AND set***************************/
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

	public String getEditorType() {
		return editorType;
	}

	public void setEditorType(String editorType) {
		this.editorType = editorType;
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
	 * @return the unorderService
	 */
	public IUnorderService getUnorderService() {
		return unorderService;
	}

	/**
	 * @param unorderService the unorderService to set
	 */
	public void setUnorderService(IUnorderService unorderService) {
		this.unorderService = unorderService;
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
	 * @return the unorder_id
	 */
	public String getUnorder_id() {
		return unorder_id;
	}

	/**
	 * @param unorder_id the unorder_id to set
	 */
	public void setUnorder_id(String unorder_id) {
		this.unorder_id = unorder_id;
	}

	/**
	 * @return the worker
	 */
	public Worker getWorker() {
		return worker;
	}

	/**
	 * @param worker the worker to set
	 */
	public void setWorker(Worker worker) {
		this.worker = worker;
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
	 * @return the unorder
	 */
	public Unorder getUnorder() {
		return unorder;
	}

	/**
	 * @param unorder the unorder to set
	 */
	public void setUnorder(Unorder unorder) {
		this.unorder = unorder;
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
	 * @return the searchUnorderId
	 */
	public String getSearchUnorderId() {
		return searchUnorderId;
	}

	/**
	 * @param searchUnorderId the searchUnorderId to set
	 */
	public void setSearchUnorderId(String searchUnorderId) {
		this.searchUnorderId = searchUnorderId;
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

	
}
