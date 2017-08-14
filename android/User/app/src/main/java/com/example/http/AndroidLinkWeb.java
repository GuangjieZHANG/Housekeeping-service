package com.example.http;

        import java.io.BufferedReader;
        import java.io.InputStream;
        import java.io.InputStreamReader;
        import java.io.OutputStream;
        import java.lang.reflect.Type;
        import java.net.HttpURLConnection;
        import java.net.URL;
        import java.net.URLEncoder;
        import java.util.HashMap;
        import java.util.Map;

        import com.google.gson.Gson;
        import com.google.gson.reflect.TypeToken;

/**
 * @author 情愿孤独
 *
 */
public class AndroidLinkWeb {

    /**
     *
     */
    public AndroidLinkWeb() {
        // TODO Auto-generated constructor stub
    }

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
        String uploadUrl = "http://127.0.0.1:8080/housework_app/";
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
        conn.setReadTimeout(5 * 1000);
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

    public static void main(String[] args) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("phoneNumber", "15929800186");
        params.put("password", "123");
//		params.put("longtitude", "87.12");
//		params.put("latitude", "87.23");
        try {
            Gson gson = new Gson();
            String result = getURLResponse("user/login.action",params);
            Type type = new TypeToken<Map<String, Object>>(){}.getType();
            Map<String, Object> sList = gson.fromJson(result, type);
            System.out.println(">>>>>>>> receive: "+ sList.get("result"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}