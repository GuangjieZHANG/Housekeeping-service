package com.example.user;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.List;
import java.util.Map;

import entity.User;
import entity.Worker;

/**
 * Created by 张广洁 on 2017/3/21.
 */
public class MineActivity extends Activity {

    private Button gomodify;
    private TextView name;
    private TextView add;
    private TextView phone;
    private String myname="";
    private String mynum="";
    private String myadd="";
    private String session;
    private User user=new User();

    public void onCreate(Bundle savedInstanceBundle) {
        super.onCreate(savedInstanceBundle);
        setContentView(R.layout.mine);

        Intent it=this.getIntent();

        session=it.getStringExtra("session");
       // newname=it.getStringExtra("name");
       // newadd=it.getStringExtra("add");

        gomodify=(Button)findViewById(R.id.modifymine);
        name=(TextView)findViewById(R.id.minename);
        add=(TextView)findViewById(R.id.mineadd);
        phone=(TextView)findViewById(R.id.minephone);

        //此时请求获取本用户个人信息
        OkHttpClient okHttpClient2=new OkHttpClient();
        okHttpClient2.setCookieHandler(new CookieManager(null, CookiePolicy.ACCEPT_ALL));
        final Request request2=new Request.Builder().addHeader("cookie",session).url("http://114.115.139.178:8080/app_housework/user_infor").build();

        Call call2 = okHttpClient2.newCall(request2);
        call2.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {

                final String res = response.body().string();
                Log.i("userinfo", res);

                Gson gson = new Gson();
                Type type2 = new TypeToken<User>() {
                }.getType();
                user = gson.fromJson(res, type2);

                Log.i("name", user.getUserName());

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        myname = user.getUserName();
                        mynum = user.getPhoneNumber();
                        myadd=user.getAddress();
                        Log.i("name______________",user.getUserName());
                        Log.i("name_________________",myname);
                        name.setText("用户名："+myname);
                        phone.setText("手机号码：" + mynum);
                        add.setText("家庭住址：" + myadd);
                                  }
                              });


                 }
            });





        gomodify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent itent = new Intent(MineActivity.this, ModifyMineActivity.class);
                MineActivity.this.startActivity(itent);

            }
        });


    }
    private void toast(final String str) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MineActivity.this, str, Toast.LENGTH_SHORT).show();
            }
        });
    }
    }
