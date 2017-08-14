package com.manage.action.interceptor;

import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.opensymphony.xwork2.util.TextParseUtil;

public class LoginInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 1L;
	private String excludeActionName;// 剔除的拦截方法

	public String intercept(ActionInvocation invocation) throws Exception {

		String actionName = invocation.getProxy().getActionName();// 获取当前访问的action名字

		Set<String> set = TextParseUtil
				.commaDelimitedStringToSet(excludeActionName);

		if (set.contains(actionName)) {
			return invocation.invoke();
		} else {
			Map<String, Object> session = invocation.getInvocationContext()
					.getSession();
			String uid = (String) session.get("uid");
			if (uid == null) {
				HttpServletRequest request = ServletActionContext.getRequest();
				if ("XMLHttpRequest".equalsIgnoreCase(request
						.getHeader("X-Requested-With"))
						|| request.getParameter("ajax") != null) {
					HttpServletResponse response = ServletActionContext
							.getResponse();
					response.setContentType("text/html;charset=UTF-8");
					response.setHeader("Pragma", "No-cache");
					response.setHeader("Cache-Control", "no-cache");
					response.setDateHeader("Expires", 0);
					response.getWriter()
							.write("{\"statusCode\":\"301\",\"message\":\"系统超时,请重新登录!\"}");
					response.getWriter().flush();
					return null;
				} else
					return "tologin";// 没有登录，跳转到登录页
			} else {
				return invocation.invoke();
			}
		}

	}

	public String getExcludeActionName() {
		return excludeActionName;
	}

	public void setExcludeActionName(String excludeActionName) {
		this.excludeActionName = excludeActionName;
	}

}