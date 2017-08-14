package com.manage.action;

import java.io.IOException;
import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.manage.adapter.TimestampAdapter;
import com.manage.util.HibernateProxyTypeAdapter;
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
	
	protected static Gson gsonTOStr = new GsonBuilder().registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY).registerTypeAdapter(Timestamp.class, new TimestampAdapter()).setPrettyPrinting().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
	protected static Gson gsonToObject = new GsonBuilder().registerTypeAdapter(Timestamp.class, new TimestampAdapter()).setDateFormat("yyyy-MM-dd HH:mm:ss").create();
	
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
	
	/*******************serialize*************************/
	public static String map2json(Map<String, Object> map) {
		return gsonTOStr.toJson(map);
	}
	
	public static String Object2json(Object object) {
		return gsonTOStr.toJson(object);
	}

	public  static <T> String List2json(List<T> list) {
		return gsonTOStr.toJson(list);
	}
	
	/*******************deserialize*************************/
	public static <T> T Json2Object(String jsonStr, Class<T> clazz){
		return (T) gsonToObject.fromJson(jsonStr, clazz);
	}
	
	public static <T> List<T> Json2List(String jsonStr,Class<T> clazz){
		List<T> list = new ArrayList<T>();
		try {
			JsonArray arry = new JsonParser().parse(jsonStr).getAsJsonArray();
			for (JsonElement jsonElement : arry) {
				list.add(gsonToObject.fromJson(jsonElement, clazz));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
//		Type type = new TypeToken<ArrayList<T>>() {}.getType();
//		return gson.fromJson(jsonStr, type);
	}
	
	public static <T> Map<String, T> Json2Map(String jsonStr, Class<T> clazz){
		Type type = new TypeToken<Map<String, T>>() {}.getType();
		return gsonToObject.fromJson(jsonStr, type);
	}
	
	public String outAjaxJsonSuccessMessage() {
		return null;
	}

	public String ajaxJsonMap(Map<String, Object> jsonMap) {
		return ajax(map2json(jsonMap), "application/json");
	}

	// 输出JSON成功消息，返回null
	public String ajaxJsonSuccessMessage(String message) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put(STATUSCODE, "200");
		jsonMap.put(MESSAGE, message);
		return ajax(map2json(jsonMap), "application/json");
	}
	// 输出JSON失败消息，返回null
	public String ajaxJsonErrorMessage(String message) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put(STATUSCODE, "500");
		jsonMap.put(MESSAGE, message);
		return ajax(map2json(jsonMap), "application/json");
	}
	// 输出JSON警告消息，返回null
	public String ajaxJsonWarningMessage(String message) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put(STATUSCODE, "300");
		jsonMap.put(MESSAGE, message);
		return ajax(map2json(jsonMap), "application/json");
	}


	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

}
