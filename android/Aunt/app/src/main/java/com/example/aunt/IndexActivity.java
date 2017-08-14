package com.example.aunt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import entity.Ordered;
import entity.Ordering;
import entity.Removeorder;
import entity.Servicetype;
import entity.Unorder;
import entity.User;
import entity.Worker;
import tools.TimestampAdapter;

/**
 * Created by 张广洁 on 2017/3/22.
 */
public class IndexActivity extends Activity {

    private ImageView dd1;
    private ImageView dd2;
    private ImageView dd3;
    private ImageView dd4;
    private Button bt1;
    private Button bt2;
    private String session;
    private TextView mess;
    private TextView name;
    private TextView age;
    private TextView intro;

    private Worker mine;

    private List<Unorder> unorder1;
    private List<Ordering> ordering1;
    private List<Ordered> ordered1;
    private List<Removeorder> removeorder1;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.index);
        Intent it=this.getIntent();
        session=it.getStringExtra("session");

        dd1=(ImageView)findViewById(R.id.goorder1);
        dd2=(ImageView)findViewById(R.id.goorder2);
        dd3=(ImageView)findViewById(R.id.goorder3);
        dd4=(ImageView)findViewById(R.id.goorder4);

        bt1=(Button)findViewById(R.id.modifyinfo);
        bt2=(Button)findViewById(R.id.out);

        mess=(TextView)findViewById(R.id.mess);
        name=(TextView)findViewById(R.id.myname);
        age=(TextView)findViewById(R.id.myage);
        intro=(TextView)findViewById(R.id.myintro);

        //获得个人信息
        OkHttpClient okHttpClient2=new OkHttpClient();
        okHttpClient2.setCookieHandler(new CookieManager(null, CookiePolicy.ACCEPT_ALL));
        final Request request2=new Request.Builder().addHeader("cookie",session).url("http://114.115.139.178:8080/app_housework/worker_infor").build();

        Call call2 = okHttpClient2.newCall(request2);
        call2.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {

                final String res = response.body().string();
                Log.i("workerinfo", res);

                Gson gson= new GsonBuilder()
                        .registerTypeAdapter(Timestamp.class, new TimestampAdapter())
                        .create();
                Type type2 = new TypeToken<Worker>() {}.getType();
                mine = gson.fromJson(res, type2);

                Log.i("name", mine.getWorkerName());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mess.setText(mine.getWorkerName() + " " + mine.getPhoneNumber());
                        name.setText("姓名："+mine.getWorkerName());
                        age.setText("年龄："+mine.getAge());
                        intro.setText("简介："+mine.getBrief());

                    }
                });
            }
        });


        //未接单订单信息
        OkHttpClient okHttpClient_un=new OkHttpClient();
        okHttpClient_un.setCookieHandler(new CookieManager(null, CookiePolicy.ACCEPT_ALL));
        final Request request_un=new Request.Builder().addHeader("cookie",session).url("http://114.115.139.178:8080/app_housework/worker_unorders").build();

        Call call_un = okHttpClient_un.newCall(request_un);
        call_un.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
            }
            @Override
            public void onResponse(Response response) throws IOException {
                final String res=response.body().string();
                Gson gson= new GsonBuilder()
                        .registerTypeAdapter(Timestamp.class, new TimestampAdapter())
                        .create();
                Type type = new TypeToken<List<Unorder>>(){}.getType();
                unorder1 = gson.fromJson(res, type);
                // Log.i("unorder",res);
            }
        });

        //进行中订单信息
        OkHttpClient okHttpClient_ing=new OkHttpClient();
        okHttpClient_ing.setCookieHandler(new CookieManager(null, CookiePolicy.ACCEPT_ALL));
        final Request request_ing=new Request.Builder().addHeader("cookie",session).url("http://114.115.139.178:8080/app_housework/worker_orderings").build();

        Call call_ing = okHttpClient_ing.newCall(request_ing);
        call_ing.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
            }
            @Override
            public void onResponse(Response response) throws IOException {
                final String res=response.body().string();
                Gson gson= new GsonBuilder()
                        .registerTypeAdapter(Timestamp.class, new TimestampAdapter())
                        .create();
                Type type = new TypeToken<List<Ordering>>(){}.getType();
                ordering1 = gson.fromJson(res, type);

                // Log.i("ing",res);
            }
        });

        //已完成订单信息
        OkHttpClient okHttpClient_ed=new OkHttpClient();
        okHttpClient_ed.setCookieHandler(new CookieManager(null, CookiePolicy.ACCEPT_ALL));
        final Request request_ed=new Request.Builder().addHeader("cookie",session).url("http://114.115.139.178:8080/app_housework/worker_ordereds").build();

        Call call_ed = okHttpClient_ed.newCall(request_ed);
        call_ed.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
            }
            @Override
            public void onResponse(Response response) throws IOException {
                final String res=response.body().string();
                Gson gson= new GsonBuilder()
                        .registerTypeAdapter(Timestamp.class, new TimestampAdapter())
                        .create();
                Type type = new TypeToken<List<Ordered>>(){}.getType();
                ordered1 = gson.fromJson(res, type);
            }
        });

        //已取消订单信息
        OkHttpClient okHttpClient_re=new OkHttpClient();
        okHttpClient_re.setCookieHandler(new CookieManager(null, CookiePolicy.ACCEPT_ALL));
        final Request request_re=new Request.Builder().addHeader("cookie",session).url("http://114.115.139.178:8080/app_housework/worker_removeOrders").build();

        Call call_re = okHttpClient_re.newCall(request_re);
        call_re.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
            }
            @Override
            public void onResponse(Response response) throws IOException {
                final String res=response.body().string();
                // Log.i("remove",res);
                Gson gson= new GsonBuilder()
                        .registerTypeAdapter(Timestamp.class, new TimestampAdapter())
                        .create();
                Type type = new TypeToken<List<Removeorder>>(){}.getType();
                removeorder1 = gson.fromJson(res, type);
            }
        });


        dd1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //去第 1个订单
                if(unorder1.size()==0){
                    toast("您暂无此类型订单信息");
                }else{
                    Intent it_un = new Intent(IndexActivity.this, UnorderListActivity.class);
                    it_un.putExtra("session",session);
                    Bundle bundle1=new Bundle();
                    bundle1.putSerializable("unorder1",(Serializable)unorder1);
                    it_un.putExtras(bundle1);
                    IndexActivity.this.startActivity(it_un);
                }

            }
        });

        dd2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //去第 2个订单
                if(ordering1.size()==0){
                    toast("您暂无此类型订单信息");
                }else{
                    Intent it_ing = new Intent(IndexActivity.this, OrderingListActivity.class);
                    it_ing.putExtra("session",session);
                    Bundle bundle2=new Bundle();
                    bundle2.putSerializable("ordering1",(Serializable)ordering1);
                    it_ing.putExtras(bundle2);
                    IndexActivity.this.startActivity(it_ing);
                }
            }
        });

        dd3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //去第3个订单
                if(ordered1.size()==0){
                    toast("您暂无此类型订单信息");
                }else{
                    Intent it_ed = new Intent(IndexActivity.this, OrderedListActivity.class);
                    it_ed.putExtra("session",session);
                    Bundle bundle3=new Bundle();
                    bundle3.putSerializable("ordered1",(Serializable)ordered1);
                    it_ed.putExtras(bundle3);
                    IndexActivity.this.startActivity(it_ed);
                }

            }
        });

        dd4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //去第4个订单
                if(removeorder1.size()==0){
                    toast("您暂无此类型订单信息");
                }else{
                    Intent it_re = new Intent(IndexActivity.this, RemoveorderListActivity.class);
                    it_re.putExtra("session",session);
                    Bundle bundle4=new Bundle();
                    bundle4.putSerializable("remove1",(Serializable)removeorder1);
                    it_re.putExtras(bundle4);
                    IndexActivity.this.startActivity(it_re);
                }

            }
        });

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent itgo = new Intent(IndexActivity.this,ModifyMineActivity.class);
                IndexActivity.this.startActivity(itgo);
            }
        });

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent itout = new Intent(IndexActivity.this, LeaveActivity.class);
                itout.putExtra("session",session);
                IndexActivity.this.startActivity(itout);
            }
        });

    }
            private void toast(final String str) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(IndexActivity.this, str, Toast.LENGTH_SHORT).show();
                    }
                });
            }

    }
