package com.manage.action;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.manage.po.Account;
import com.manage.po.User;
import com.manage.service.IAccountService;
import com.manage.service.IUserService;
import com.manage.util.PagerModel;
import com.manage.util.VerificationCodeUtil;

@Controller
@Results({ @Result(name = "input", location = "../login.jsp"),
		@Result(name = "pwd_edit", location = "admin/pwd_modify.jsp"),
		@Result(name = "succ", location = "main/main.jsp") })
public class LoginAction extends BaseAction {

	private static final long serialVersionUID = 5033459742031477727L;

	@Autowired
	private IAccountService accountService;
	@Autowired
	private IUserService userService;

	private String accountId;
	private String password;
	private String checkcode;

	private PagerModel pm;
	private int pageNum = 1;
	private int numPerPage = 20;
	private User user;
	private String phoneNumber;

	private String oldpassword;
	private String newpassword;
	private String confirmpassword;
	private String pwd;

	private ByteArrayInputStream imageStream;

	@Action("Login_Index")
	public String index() {
		return "input";
	}

	@Action(value = "SecurityCodeImage", results = { @Result(name = "success", type = "stream", params = {
			"contentType", "image/jpeg", "inputName", "imageStream",
			"bufferSize", "2048" }) })
	public String securityCodeImage() {
		VerificationCodeUtil vcu = VerificationCodeUtil.Instance();
		imageStream = vcu.getImage();
		session.put("random", vcu.getVerificationCodeValue());
		return SUCCESS;
	}

	@Action("Login_Login")
	public String login() throws Exception {
		String yzm = this.checkcode.toLowerCase();// 将验证码字符串全部转换成小写
		String random = session.get("random").toString().toLowerCase();
		if (!yzm.equals(random)) {
			Map<String, String> jsonMap = new HashMap<String, String>();
			jsonMap.put("success", "false");
			jsonMap.put("msg", "验证码错误，请核实后重新输入!");
			return this.ajaxJsonMap(jsonMap);
		} else {
			Account acc = accountService.getAccountById(accountId);
			if (acc != null) {
				// String pwd=DESPlus.encrypt("0", password);

				String pwd = password;
				if (!acc.getPassword().equals(pwd)) {
					Map<String, String> jsonMap = new HashMap<String, String>();
					jsonMap.put("success", "false");
					jsonMap.put("msg", "密码错误，请核实后重新输入!");
					return this.ajaxJsonMap(jsonMap);
				}
				// else if(user.getIsUse().equals("0"))
				// OutJson.outString("{success:false,msg:'该用户已停用!'}");
				// else if(!user.getType().equals("管理员"))
				// {
				// OutJson.outString("{success:false,msg:'非管理员不允许登录!'}");
				// }
				else {
					session.put("uid", acc.getAccountId());
					session.put("uname", acc.getName());
					Map<String, String> jsonMap = new HashMap<String, String>();
					jsonMap.put("success", "true");
					jsonMap.put("msg", "登录成功!");
					return this.ajaxJsonMap(jsonMap);
				}
			} else {
				Map<String, String> jsonMap = new HashMap<String, String>();
				jsonMap.put("success", "false");
				jsonMap.put("msg", "登录失败，用户名或者密码不正确!");
				return this.ajaxJsonMap(jsonMap);
			}
		}
	}

	@Action("Login_LoginOut")
	public String loginout() throws Exception {
		session.remove("uid");
		session.remove("uname");
		return "input";
	}

	@Action("Look_PWD")
	public String retpwd() {
		return "pwd_edit";
	}

	@Action("Login_UpdatePwd")
	public String updatepwd() {

		String accountId = (String) session.get("uid");

		if (!confirmpassword.equals(newpassword)) {
			return this.ajaxJsonWarningMessage("输入密码和确认密码不一致!");
		} else {
			Account admin = accountService.getAccountById(accountId);
			String pwd = admin.getPassword();
			try {
				// pwd = DESPlus.encrypt("0", mypwd);
				// oldpassword = DESPlus.encrypt("0", oldpassword);
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println(accountId + "----*********----" + pwd
					+ "---*********---" + oldpassword);
			if (!oldpassword.equals(pwd)) {
				return this.ajaxJsonWarningMessage("原密码输入错误!");
			} else {
				try {
					// newpassword = DESPlus.encrypt("0", newpassword);
				} catch (Exception e) {
					e.printStackTrace();
				}
				admin.setPassword(newpassword);
				accountService.merge(admin);
				return this
						.ajaxJsonSuccessMessage("修改成功!", "1", "closeCurrent");
			}
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
		Map<String, String> result = new HashMap<String, String>();
		result.put("result", "failure");
		// 获取参数
		System.out.println("phoneNumber: " + phoneNumber + ", password: "
				+ password);
		// 查询该电话号码是否存在，以及其对应的密码
		pm = userService.getUserByPhoneNumber(phoneNumber, pageNum, numPerPage);
		List<User> list = pm.getList();
		// 判断所输入电话号码是否存在，以及判读密码是否正确
		if (list.size() == 0) {
			result.put("result", "unexisted");
		} else if (list.get(0).getPassword().equals(password)) {
			result.put("result", "success");
			session.put("uid", list.get(0).getUserId());
			session.put("uname", list.get(0).getUserName());
		} else {
			result.put("result", "failure");
		}
		// 给客户端返回结果
		return this.ajaxJsonMap(result);
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCheckcode() {
		return checkcode;
	}

	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}

	public String getOldpassword() {
		return oldpassword;
	}

	public void setOldpassword(String oldpassword) {
		this.oldpassword = oldpassword;
	}

	public String getNewpassword() {
		return newpassword;
	}

	public void setNewpassword(String newpassword) {
		this.newpassword = newpassword;
	}

	public String getConfirmpassword() {
		return confirmpassword;
	}

	public void setConfirmpassword(String confirmpassword) {
		this.confirmpassword = confirmpassword;
	}

	public ByteArrayInputStream getImageStream() {
		return imageStream;
	}

	public void setImageStream(ByteArrayInputStream imageStream) {
		this.imageStream = imageStream;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the accountService
	 */
	public IAccountService getAccountService() {
		return accountService;
	}

	/**
	 * @param accountService
	 *            the accountService to set
	 */
	public void setAccountService(IAccountService accountService) {
		this.accountService = accountService;
	}

	/**
	 * @return the pm
	 */
	public PagerModel getPm() {
		return pm;
	}

	/**
	 * @param pm
	 *            the pm to set
	 */
	public void setPm(PagerModel pm) {
		this.pm = pm;
	}

	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber
	 *            the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @return the userService
	 */
	public IUserService getUserService() {
		return userService;
	}

	/**
	 * @param userService
	 *            the userService to set
	 */
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	/**
	 * @return the pageNum
	 */
	public int getPageNum() {
		return pageNum;
	}

	/**
	 * @param pageNum
	 *            the pageNum to set
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
	 * @param numPerPage
	 *            the numPerPage to set
	 */
	public void setNumPerPage(int numPerPage) {
		this.numPerPage = numPerPage;
	}

}