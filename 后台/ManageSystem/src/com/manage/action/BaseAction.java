package com.manage.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport implements SessionAware {

	private static final long serialVersionUID = 6718838822334455667L;

	public static final String VIEW = "view";
	public static final String LIST = "list";
	public static final String STATUS = "status";
	public static final String WARN = "warn";
	public static final String SUCCESS = "success";
	
	public static final String ERROR = "error";
	public static final String MESSAGE = "message";
	public static final String CONTENT = "content";
	public static final String STATUSCODE = "statusCode";
	public static final String FORWARDURL = "forwardUrl";

	protected Map<String, Object> session;

	public String ajax(String content, String type) {
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType(type + ";charset=UTF-8");
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			response.getWriter().write(content);
			response.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String restr(String content, String type) {
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType(type + ";charset=UTF-8");
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			response.getWriter().write(content);
			response.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String map2json(Map<String, String> map) {
		Gson gson = new Gson();
		return gson.toJson(map);
//		String res = "{";
//		boolean first = true;
//		Iterator<String> it = map.keySet().iterator();
//		while (it.hasNext()) {
//			String key = it.next();
//			if (first)
//				first = false;
//			else
//				res += ",";
//			res += "\"" + key + "\":\"" + map.get(key) + "\"";
//		}
//		res += "}";
//		return res;
	}

	public String outAjaxJsonSuccessMessage() {
		return null;
	}

	public String ajaxJsonMap(Map<String, String> jsonMap) {
		return ajax(map2json(jsonMap), "text/html");
	}

	// 输出JSON成功消息，返回null
	public String ajaxJsonSuccessMessage(String message, String navTabId,
			String callbackType) {
		Map<String, String> jsonMap = new HashMap<String, String>();
		// jsonMap.put(STATUS, SUCCESS);
		jsonMap.put(STATUSCODE, "200");
		jsonMap.put(MESSAGE, message);
		jsonMap.put("navTabId", navTabId);
		jsonMap.put("callbackType", callbackType);
		return ajax(map2json(jsonMap), "text/html");
	}

	public String ajaxJsonErrorMessage(String message, String navTabId,
			String callbackType) {
		Map<String, String> jsonMap = new HashMap<String, String>();
		jsonMap.put(STATUSCODE, "500");
		jsonMap.put(MESSAGE, message);
		jsonMap.put("navTabId", navTabId);
		jsonMap.put("callbackType", callbackType);
		return ajax(map2json(jsonMap), "text/html");
	}

	public String ajaxJsonWarningMessage(String message) {
		Map<String, String> jsonMap = new HashMap<String, String>();
		jsonMap.put(STATUSCODE, "300");
		jsonMap.put(MESSAGE, message);
		return ajax(map2json(jsonMap), "text/html");
	}



	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

}
