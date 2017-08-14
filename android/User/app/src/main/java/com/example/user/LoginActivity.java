package com.example.user;

import android.app.Activity;

import android.content.Intent;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.http.OkHttpClientManager;
import com.example.http.Session;
import com.example.http.TimestampAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.internal.framed.Header;


import java.io.IOException;
import java.lang.reflect.Type;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import entity.Servicetype;
import entity.User;


/**
 * Created by 张广洁 on 2017/2/16.
 */
public class LoginActivity extends Activity{

   // private String phoneNum ;//手机号
   // private String psw;//密码
    private String s;
    private EditText pN;
    private EditText ps;
    private TextView findpsw;

    private int LOGIN_CHANCE=5;
    private int count=LOGIN_CHANCE;
    private float WAIT_TIME=1800000L;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.login);

        Button btn=(Button)findViewById(R.id.login1);

       /* String phoneNum;
        //获得登陆的手机号
        EditText pN = (EditText)findViewById(R.id.loginPhoneNum);
        phoneNum = pN.getText().toString().trim();
        //获得登陆密码
        String psw;
        EditText ps = (EditText)findViewById(R.id.loginPsw);
        psw = ps.getText().toString().trim();*/

        //跳转至注册界面
        TextView textView1 = (TextView) findViewById(R.id.goregister);
        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent();
                intent1.setClass(LoginActivity.this, Register.class);
                LoginActivity.this.startActivity(intent1);
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //此处设置点击login之后的动作
                String phoneNum;
                //获得登陆的手机号
                pN = (EditText)findViewById(R.id.loginPhoneNum);
                phoneNum = pN.getText().toString().trim();

                //获得登陆密码
                String psw;
                ps = (EditText)findViewById(R.id.loginPsw);
                psw = ps.getText().toString().trim();

                    //这里需要使用ok http的post功能，将手机号与密码上传
                    OkHttpClient okHttpClient=new OkHttpClient();
                    okHttpClient.setCookieHandler(new CookieManager(null, CookiePolicy.ACCEPT_ALL));
                    String mBaseUrl="http://114.115.139.178:8080/app_housework/user_login";
                   // String mBaseUrl="http://192.168.191.1:8080/app_housework/user_login";
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
                            //  Gson gson=new Gson();
                            final String res=response.body().string();
                            Log.i("denglu", res);
                            //需捕获异常

                            try{

                                Gson gson=new Gson();
                                Type type = new TypeToken<Map<String, Object>>(){}.getType();
                                Map<String, Object> sList = gson.fromJson(res, type);
                                final String result= sList.get("message").toString();
                                Log.i("res",result);
                                if(res.equals("unexisted")){
                                    toast("该账号尚未注册！");
                                }

                                runOnUiThread(new Runnable() {
                                                  @Override
                                                  public void run() {
                                                      SharedPreferences sp=getSharedPreferences("data", MODE_PRIVATE);
                                                      long errorTime=sp.getLong("errorTime", 0L);//输入错误时的时间
                                                      long recentTime=System.currentTimeMillis();//获取当前时间

                                                      if (result.equals("success")&&recentTime-errorTime>WAIT_TIME) {
                                                          //成功登陆 跳转
                                                          Headers header = response.headers();
                                                          List<String> cookies=header.values("Set-Cookie");
                                                          String session= cookies.get(0);
                                                          s=session.substring(0,session.indexOf(";"));
                                                          //Log.i("session info",s);
                                                          toast("登陆成功");
                                                          Intent intentindex = new Intent();
                                                          intentindex.setClass(LoginActivity.this, indexActivity.class);
                                                         // intentindex.putExtra("number_login", phoneNum);
                                                          intentindex.putExtra("session", s);
                                                          LoginActivity.this.startActivity(intentindex);
                                                          LoginActivity.this.finish();
                                                      } else {
                                                          if(count==1){//已经5次登录失败了
                                                             // pN.setText("");
                                                              ps.setText("");
                                                              count=LOGIN_CHANCE;
                                                              toast("连续5次登陆失败，请您"+WAIT_TIME/60000+"分钟后再登陆！");
                                                              errorTime=System.currentTimeMillis();
                                                              SharedPreferences sp1 = getSharedPreferences("data", MODE_PRIVATE);
                                                              SharedPreferences.Editor editor = sp1.edit();
                                                              editor.putLong("errorTime", errorTime);
                                                              editor.commit();
                                                          }else{//剩余机会大于1次
                                                            count--;
                                                              ps.setText("");
                                                              toast("您还有"+count+"次登陆机会");
                                                          }
                                                      }
                                                  }
                                              }
                                );//线程结束
                            }catch(Exception e){
                                e.printStackTrace();
                                toast("服务器返回错误");
                            }
                        }
                    });//正确返回结束
                }
            }
        );//点击事件结束


        findpsw=(TextView)findViewById(R.id.findpsw);
        findpsw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(LoginActivity.this, FindPswActivity.class);
                LoginActivity.this.startActivity(intent);
            }
        });
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

