package com.example.user;

import com.example.http.AndroidLinkWeb;
import com.example.http.OkHttpClientManager;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;


/**带有短信验证功能的注册界面
 * Created by 张广洁 on 2017/2/17.
 */
public class Register extends Activity {

    private String phoneNumber = "";
    private String code = "";
    private String psw = "";
    private String resp;

    EventHandler eh = new EventHandler() {
        @Override
        public void afterEvent(int event, int result, Object data) {

            switch (event) {
                case SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE:
                    if (result == SMSSDK.RESULT_COMPLETE) {
                        toast("验证成功");
                        //此处要完成对参数的传递，借助工具类AndroidLinkWeb.java
                        reg();
                    } else {
                        toast("验证失败");
                    }
                    break;
                case SMSSDK.EVENT_GET_VERIFICATION_CODE:
                    if (result == SMSSDK.RESULT_COMPLETE) {
                        toast("获取验证码成功");
                        //默认的智能验证是开启的,我已经在后台关闭
                    } else {
                        toast("获取验证码失败");
                    }
                    break;
            }
        }
    };

    private void toast(final String str) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(Register.this, str, Toast.LENGTH_SHORT).show();
            }
        });
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        //初始化SMSSDK
        SMSSDK.initSDK(this, "1b5c56a33b680", "69d31d65bdb3c7d0db61d654765d90a4");

        //获取手机号
        EditText editText1 = (EditText) findViewById(R.id.phoneNum);
        phoneNumber = editText1.getText().toString().trim();

        //获取密码
        EditText editText3 = (EditText) findViewById(R.id.psw);
        psw = editText3.getText().toString().trim();

        //获取验证码
        EditText editText2 = (EditText) findViewById(R.id.code);
        code = editText2.getText().toString().trim();

        //为验证按钮设置监听事件
        Button button1 = (Button) findViewById(R.id.getcode);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //设置点击获取验证码之后发生的事件
                //先验证手机号的正确性
                if (phoneNumber.length() == 11) {
                    SMSSDK.registerEventHandler(eh);
                    SMSSDK.getVerificationCode("86", phoneNumber);

                } else {
                    toast("请输入正确的手机号");
                }

            }
        });
        //为注册按钮设置监听事件
        Button button2 = (Button) findViewById(R.id.register);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击注册按钮，需要判断验证结果，然后再注册
                if (psw.length() == 0) {
                    toast("密码不能为空");
                } else if (psw.length() > 16 || psw.length() < 6) {
                    toast("密码长度为8-16位");
                } else {
                    SMSSDK.submitVerificationCode("86", phoneNumber, code);
                    SMSSDK.registerEventHandler(eh);
                }

            }
        });

        TextView textView = (TextView) findViewById(R.id.gologin);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Register.this, LoginActivity.class);
                Register.this.startActivity(intent);
            }
        });

    }

    public void reg() {
        //这个方法用来完成验证码成功以后的事情

        EditText editText1 = (EditText) findViewById(R.id.phoneNum);
        phoneNumber = editText1.getText().toString().trim();

        EditText editText3 = (EditText) findViewById(R.id.psw);
        psw = editText3.getText().toString().trim();

        //使用okHttp来进行交互

       /* OkHttpClient okHttpClient=new OkHttpClient();
        okHttpClient.setCookieHandler(new CookieManager(null, CookiePolicy.ACCEPT_ALL));
        String mBaseUrl="http://192.168.191.1:8080/app_housework/user_register";
        FormEncodingBuilder requestBodyBuilder=new FormEncodingBuilder();
        RequestBody requestBody=requestBodyBuilder.add("phoneNumber",phoneNumber).add("password",psw).build();
        Request.Builder builder=new Request.Builder();
        Request request = builder.url(mBaseUrl).post(requestBody).post(requestBody).build();
        Call call=okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                //输出出错信息
                L.e("onFailure:"+e.getMessage());
                e.printStackTrace();
            }

            @Override
            public void onResponse(Response response) throws IOException {


                Gson gson=new Gson();
                final String res=response.body().string();
                Log.i("denglu", res);
                Type type = new TypeToken<Map<String, Object>>(){}.getType();
                Map<String, Object> sList = gson.fromJson(res, type);
                resp=(String)sList.get("message");
                System.out.println(">>>>>>>> receive: " + sList.get("result"));

                runOnUiThread(new Runnable() {
                                  @Override
                                  public void run() {
                                      if (resp.equals("success")) {
                                          //成功登陆 跳转
                                          toast("登陆成功");
                                          Intent intentindex = new Intent();
                                          intentindex.setClass(Register.this, LoginActivity.class);
                                          //intentindex.putExtra("number_login",phoneNum);
                                        Register.this.startActivity(intentindex);
                                      } else if (res.equals("existed")) {
                                            toast("此用户已存在，请直接登陆");

                                      } else {
                                            toast("不知名错误，请重试");
                                      }
                                  }
                              }
                );//线程结束

            }
        });//正确返回结束
    }
}
);//回调结束*/






   /* Map<String, String> params = new HashMap<String, String>();
    params.put("phoneNumber", phoneNumber);
    params.put("password", psw);*/
//		params.put("longtitude", "87.12");
//		params.put("latitude", "87.23");
/*    try {
        Gson gson = new Gson();
        String result = AndroidLinkWeb.getURLResponse("user/register.action",params);
        Type type = new TypeToken<Map<String, Object>>(){}.getType();
        Map<String, Object> sList = gson.fromJson(result, type);
        System.out.println(">>>>>>>> receive: "+ sList.get("result"));
        //如果返回注册成功，那么打开index页面
        if(result.equals("success")){
            Intent it=new Intent(Register.this,indexActivity.class);
            Register.this.startActivity(it);
            Register.this.finish();
        }else if(result.equals("existed")){
            //号码已经存在
            toast("号码已经存在，请勿重复注册");
        }else{
            toast("注册失败");
        }

    } catch (Exception e) {
        e.printStackTrace();
    }



}*/

    }
}