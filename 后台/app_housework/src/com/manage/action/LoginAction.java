package com.manage.action;

import java.sql.Timestamp;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.manage.po.User;
import com.manage.po.Worker;
import com.manage.service.IUserService;
import com.manage.service.IWorkerService;
import com.manage.util.PagerModel;

@Controller
public class LoginAction extends BaseAction {

	private static final long serialVersionUID = 5033459742031477727L;

	@Autowired
	private IUserService userService;
	@Autowired
	private IWorkerService workerService;

	private PagerModel pm;
	private int pageNum = 1;
	private int numPerPage = 20;

	private String phoneNumber;
	private String password;
	private String oldPassword;
	
	private User user;
	private Worker worker;
	
	/**
	 * @description 获取字符串phoneNumber, password, 以及浮点数longtitude, latitude
	 * 				验证phoneNumber是否存在
	 * 					若存在, 则返回{'resulet':'existed'}
	 * 					若不存在,则添加用户,返回添加结果{'resulet':'success'}或者{'resulet':'failure'}
	 * @author 情愿孤独
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Action("/user_register")
	public String register() {
		System.out.println("phoneNumber: "+phoneNumber + ", password: " + password);
		
		//查询该电话号码是否存在
		// 查询该电话号码是否存在，以及其对应的密码
		pm = userService.getUserByPhoneNumber(phoneNumber, pageNum, numPerPage);
		List<User> list = pm.getList();
		if(list.size() > 0){
			return this.ajaxJsonSuccessMessage("existed");
		}else{
			//添加用户
			user = new User();
			Timestamp now = new Timestamp(System.currentTimeMillis());
			user.setAddTime(now);
			user.setUserName(phoneNumber);
			user.setPhoneNumber(phoneNumber);
			user.setPassword(password);
			userService.save(user);
			// 给客户端返回结果
			return this.ajaxJsonSuccessMessage("success");
		}
	}
	
	/**
	 * @description 获取字符串phoneNumber, password 验证phoneNumber是否存在 若不存在,
	 *              则返回{'resulet':'unexisted'}
	 *              若存在,则匹配该用户是否输入正确,返回添加结果{'resulet':'success'}或者{'resulet':'failure'
	 *              }
	 * @author 情愿孤独
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Action("/user_login")
	public String user_login() {
		// 获取参数
		System.out.println("phoneNumber: " + phoneNumber + ", password: "
				+ password);
		// 查询该电话号码是否存在，以及其对应的密码
		pm = userService.getUserByPhoneNumber(phoneNumber, pageNum, numPerPage);
		List<User> list = pm.getList();
		// 判断所输入电话号码是否存在，以及判读密码是否正确
		if (list.size() == 0) {
			// 给客户端返回结果
			return this.ajaxJsonSuccessMessage("unexisted");
		} else if (list.get(0).getPassword().equals(password)) {
			session.put("uid", list.get(0).getUserId());
			session.put("uphone", list.get(0).getPhoneNumber());
			session.put("uname", list.get(0).getUserName());
			return this.ajaxJsonSuccessMessage("success");
		} else {
			return this.ajaxJsonSuccessMessage("failure");
		}
	}
	
	@Action("user_loginOut")
	public String loginout() throws Exception {
		session.remove("uid");
		session.remove("uname");
		session.remove("uphone");
		return this.ajaxJsonSuccessMessage("success");
	}

	@Action("user_updatePassword")
	public String updatepwd() {
		String userId = (String) session.get("uid");
		user = userService.getUserById(userId);
		if(!user.getPassword().equals(oldPassword)){
			return this.ajaxJsonWarningMessage("error");
		}else {
			user.setPassword(password);
			userService.merge(user);
			return this.ajaxJsonSuccessMessage("success");
		}
	}

	/*********************worker********************************/

	/**
	 * @description 获取字符串phoneNumber, password, 以及浮点数longtitude, latitude
	 * 				验证phoneNumber是否存在
	 * 					若存在, 则返回{'resulet':'existed'}
	 * 					若不存在,则添加用户,返回添加结果{'resulet':'success'}或者{'resulet':'failure'}
	 * @author 情愿孤独
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Action("/worker_register")
	public String worker_register() {
		System.out.println("phoneNumber: "+phoneNumber + ", password: " + password);
		
		//查询该电话号码是否存在
		// 查询该电话号码是否存在，以及其对应的密码
		pm = workerService.getWorkerByPhoneNumber(phoneNumber, pageNum, numPerPage);
		List<Worker> list = pm.getList();
		if(list.size() > 0){
			return this.ajaxJsonSuccessMessage("existed");
		}else{
			//添加用户
			worker = new Worker();
			Timestamp now = new Timestamp(System.currentTimeMillis());
			worker.setAddtime(now);
			worker.setWorkerName(phoneNumber);
			worker.setPhoneNumber(phoneNumber);
			worker.setPassword(password);
			workerService.save(worker);
			// 给客户端返回结果
			return this.ajaxJsonSuccessMessage("success");
		}
	}
	
	/**
	 * @description 获取字符串phoneNumber, password 验证phoneNumber是否存在 若不存在,
	 *              则返回{'resulet':'unexisted'}
	 *              若存在,则匹配该用户是否输入正确,返回添加结果{'resulet':'success'}或者{'resulet':'failure'
	 *              }
	 * @author 情愿孤独
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Action("/worker_login")
	public String worker_login() {
		// 获取参数
		System.out.println("phoneNumber: " + phoneNumber + ", password: "
				+ password);
		// 查询该电话号码是否存在，以及其对应的密码
		pm = workerService.getWorkerByPhoneNumber(phoneNumber, pageNum, numPerPage);
		List<Worker> list = pm.getList();
		// 判断所输入电话号码是否存在，以及判读密码是否正确
		if (list.size() == 0) {
			// 给客户端返回结果
			return this.ajaxJsonSuccessMessage("unexisted");
		} else if (list.get(0).getPassword().equals(password)) {
			session.put("wid", list.get(0).getWorkerId());
			session.put("wphone", list.get(0).getPhoneNumber());
			session.put("wname", list.get(0).getWorkerName());
			return this.ajaxJsonSuccessMessage("success");
		} else {
			return this.ajaxJsonSuccessMessage("failure");
		}
	}
	
	@Action("worker_loginOut")
	public String worker_loginout() throws Exception {
		session.remove("wid");
		session.remove("wname");
		session.remove("wphone");
		return this.ajaxJsonSuccessMessage("success");
	}

	@Action("worker_updatePassword")
	public String worker_updatepwd() {
		String WorkerId = (String) session.get("wid");
		worker = workerService.getWorkerById(WorkerId);
		if(!worker.getPassword().equals(oldPassword)){
			return this.ajaxJsonWarningMessage("error");
		}else {
			worker.setPassword(password);
			workerService.merge(worker);
			return this.ajaxJsonSuccessMessage("success");
		}
	}
	
	/***********************************************************/
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
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the oldPassword
	 */
	public String getOldPassword() {
		return oldPassword;
	}

	/**
	 * @param oldPassword the oldPassword to set
	 */
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
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

}