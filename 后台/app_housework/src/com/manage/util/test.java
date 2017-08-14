/**
 * @Title test.java
 * @Package com.manage.util
 * @Description TODO
 * @author 情愿孤独
 * @date 2017年4月25日 上午12:01:17 
 * @version V1.0
 */
package com.manage.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import com.manage.action.BaseAction;
import com.manage.po.User;

/**
 * @ClassName test
 * @Description TODO
 * @author 情愿孤独
 * @date 2017年4月25日 上午12:01:17
 */
public class test {

	/**
	 * 
	 * @param path
	 * 			调用的方法相对路径
	 * @param params
	 * 			需要发送的相关数据
	 * @return
	 * 			JSON字符串
	 * @throws Exception
	 */
	public static String getURLResponse(String path, Map<String, String> params)
			throws Exception {
		//路径处理
		String uploadUrl = "http://114.115.139.178:8080/app_housework/";
		path = uploadUrl + path;
		// 获取参数String
		StringBuilder sb = new StringBuilder();
		if (params != null && !params.isEmpty()) {
			for (Map.Entry<String, String> entry : params.entrySet())
				sb.append(entry.getKey())
						.append('=')
						.append(URLEncoder.encode(new String(entry.getValue()
								.getBytes(), "utf-8"), "utf-8")).append('&');
			sb.deleteCharAt(sb.length() - 1);
		}
		System.out.println(path + "?" + sb.toString());
		// 将参数转化为byte[]
		byte[] entitydata = sb.toString().getBytes();

		// URL对象
		URL url = new URL(path);
		// 使用URL打开一个链接
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		// 使用POST请求
		conn.setRequestMethod("POST");
		// 5秒钟读数据超时
//		conn.setReadTimeout(5 * 1000);
		// 允许输出流，即允许上传
		conn.setDoOutput(true);
		// 允许输入流，即允许下载
		conn.setDoInput(true);
		// 设置发送内容属性
		conn.setRequestProperty("Content-Type",
				"application/x-www-form-urlencoded; charset=utf-8"); 
		// 设置发送内容长度
		conn.setRequestProperty("Content-Length",
				String.valueOf(entitydata.length));
		// 建立HTTP连接
		conn.connect();

		// 创建输入输出流
		OutputStream outPutStream = null;
		InputStream in = null;
		String line, lines = "";
		try {
			// 获取输出流
			outPutStream = conn.getOutputStream();
			// 发送请求
			outPutStream.write(entitydata);
			outPutStream.flush();
			// 关闭输出流
			outPutStream.close();

			// 获取输入流
			in = conn.getInputStream();
			// 取得输入流，并使用Reader读取
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(in));
			while ((line = reader.readLine()) != null) {
				lines += line;
			}
			reader.close();
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			if (outPutStream != null) {
				// 断开输出流
				outPutStream.close();
			}
			if (in != null) {
				// 断开输入流连接
				in.close();
			}
			if (conn != null) {
				// 断开网络连接
				conn.disconnect();
			}
		}
		return lines;
	}
	
	/**
	 * @Title main
	 * @Description TODO
	 * @author 情愿孤独
	 * @date 2017年4月25日 上午12:01:17
	 * @param args void
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Map<String, String> params = new HashMap<String, String>();
		Timestamp now = new Timestamp(System.currentTimeMillis());
		User user = new User("test1", "15929800188", "123456",now);
		String userString = BaseAction.Object2json(user);
		System.out.println(userString);
		params.put("userJson", userString);
		params.put("editorType", "add");
		System.out.println(getURLResponse("User_Save.action", params));
	}

}
