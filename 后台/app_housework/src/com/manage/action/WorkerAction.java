package com.manage.action;

import java.sql.Timestamp;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.manage.po.Worker;
import com.manage.service.ISService;
import com.manage.service.IWorkerService;
import com.manage.util.PagerModel;

@Controller
public class WorkerAction extends BaseAction{
	
	private static final long serialVersionUID = 6718838822334455667L;
	@Autowired
	private IWorkerService workerService;
	@Autowired
	private ISService sService;
	
	private PagerModel pm;
	private int pageNum = 1;
	private int numPerPage = 20;
	private String[] ids;   //删除ID
	
	
	private String workerId;
	//查找内容
	private String searchWorkerName;
	private String searchPhoneNumber;
	private String searchCardId;
	private String searchWokerSex;
		
	private String worker_id;   //修改id 
	private String editorType;
	
	private Worker worker;

    ////////////////////////////////////////////////////////////////////////////////

	@Action("/worker_infor")
	public String getWorkerInfor(){
		workerId = (String) session.get("wid");
		worker = workerService.getWorkerById(workerId);
		return this.ajax(Object2json(worker), "application/json");
	}
	
	@Action("/worker_list")
	public String getWorkerList(){
		List<Worker> result = workerService.getWorkerList();
		return ajax(List2json(result), "application/json");
	}
	
	@Action("SearchWorker_List")
	public String search(){
		System.out.println(searchWorkerName + "***" + searchPhoneNumber + "***" + searchCardId + "***" + searchWokerSex);	
		pm = workerService.search(searchWorkerName, searchPhoneNumber, searchCardId,searchWokerSex, pageNum, numPerPage);
		return "worker_list";
	}
	
	
	// 用于查找带回
	@Action("Worker_List_LookUp")
	public String worker_list_lookup(){
		pm = workerService.search(searchWorkerName, searchPhoneNumber, searchCardId,searchWokerSex, pageNum, numPerPage);
		return "worker_list_lookup";
	}
	
	//////////////////////////////////////////////////////////
	@Action("Worker_Add")
	public String toAddWorkerInfo() {
		editorType="add";
		return "worker_Add";
	}
		
	@Action("Worker_Modify")
	public String showModify(){
		editorType="modify";
		worker = workerService.getWorkerById(worker_id);
		return "worker_edit";	
	}

	@Action("Worker_Save")
	public String save(){
		if(editorType.equals("add")){
			worker.setPassword(worker.getCardId().substring(12));
			Timestamp now = new Timestamp(System.currentTimeMillis());
			worker.setAddtime(now);
			worker.setModifyTime(now);
			workerService.save(worker);
			return ajaxJsonSuccessMessage("您已经添加成功!");
		}else{
			workerService.merge(worker);
			return ajaxJsonSuccessMessage("您已经修改成功!");
		}
	}
	
	///////////////////////////////////////////////////////////////	
	@Action("Worker_BatchDelete")
	public String delete(){
		workerService.batchDelete(ids);
		return ajaxJsonSuccessMessage("您已经删除成功!");
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

	public String getWorker_id() {
		return worker_id;
	}

	public void setWorker_id(String worker_id) {
		this.worker_id = worker_id;
	}

	public String getEditorType() {
		return editorType;
	}

	public void setEditorType(String editorType) {
		this.editorType = editorType;
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
	 * @return the searchCardId
	 */
	public String getSearchCardId() {
		return searchCardId;
	}

	/**
	 * @param searchCardId the searchCardId to set
	 */
	public void setSearchCardId(String searchCardId) {
		this.searchCardId = searchCardId;
	}

	/**
	 * @return the searchWokerSex
	 */
	public String getSearchWokerSex() {
		return searchWokerSex;
	}

	/**
	 * @param searchWokerSex the searchWokerSex to set
	 */
	public void setSearchWokerSex(String searchWokerSex) {
		this.searchWokerSex = searchWokerSex;
	}
	


}
