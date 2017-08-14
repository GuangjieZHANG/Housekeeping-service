package com.example.aunt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.List;
import java.util.Map;


/**
 * Created by 张广洁 on 2017/3/13.
 */
public class LoginActivity extends Activity{

    private String resp;
    private String session;
    private EditText pN;
    private EditText ps;


    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.login);

        Button btn=(Button)findViewById(R.id.login1);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //此处设置点击login之后的动作
                String phoneNum = "";
                pN = (EditText) findViewById(R.id.loginPhoneNum);
                phoneNum = pN.getText().toString().trim();

                String psw = "";
                ps = (EditText) findViewById(R.id.loginPsw);
                psw= ps.getText().toString().trim();


                if (phoneNum.length() == 0 || psw.length() == 0) {
                    toast("用户名和密码不能为空");
                } else {

                    OkHttpClient okHttpClient=new OkHttpClient();
                    okHttpClient.setCookieHandler(new CookieManager(null, CookiePolicy.ACCEPT_ALL));
                    String mBaseUrl="http://114.115.139.178:8080/app_housework/worker_login";
                    FormEncodingBuilder requestBodyBuilder=new FormEncodingBuilder();
                    RequestBody requestBody=requestBodyBuilder.add("phoneNumber",phoneNum).add("password",psw).build();
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
                        public void onResponse(final Response response) throws IOException {

                            final String res=response.body().toString();
                            Log.i("denglu", res);
                            runOnUiThread(new Runnable() {
                                              @Override
                                              public void run() {
                                                  if (response.isSuccessful()) {
                                                      //成功登陆 跳转

                                                      Headers header = response.headers();
                                                      List<String> cookies = header.values("Set-Cookie");
                                                      String s = cookies.get(0);
                                                      session = s.substring(0, s.indexOf(";"));
                                                      Log.i("session info", session);
                                                      toast("登陆成功");
                                                      Intent intentindex = new Intent();
                                                      intentindex.setClass(LoginActivity.this, IndexActivity.class);
                                                      //intentindex.putExtra("number_login",phoneNum);
                                                      intentindex.putExtra("session", session);
                                                      LoginActivity.this.startActivity(intentindex);
                                                  } else if (res.equals("unexisted")) {
                                                      toast("该账号尚未注册！");
                                                  } else if(res.equals("failure")){
                                                      toast("密码错误！");
                                                  }
                                              }
                                          }
                            );//线程结束

                        }
                    });//正确返回结束
                }
                }


            }
        );


    }

    private void toast(final String str) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(LoginActivity.this, str, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
