package com.manage.action.interceptor;

import java.util.Map;
import java.util.Set;

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
				HttpServletResponse response = ServletActionContext.getResponse();
				response.setContentType("application/json;charset=UTF-8");
				response.setHeader("Pragma", "No-cache");
				response.setHeader("Cache-Control", "no-cache");
				response.setDateHeader("Expires", 0);
				response.getWriter()
						.write("{\"statusCode\":\"301\",\"message\":\"overtime\"}");
				response.getWriter().flush();
				return null;
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