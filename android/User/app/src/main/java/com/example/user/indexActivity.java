package com.example.user;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.http.OkHttpClientManager;
import com.example.http.TimestampAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.io.Serializable;
import java.util.Set;

import entity.Ordered;
import entity.Ordering;
import entity.Removeorder;
import entity.Service;
import entity.Servicetype;
import entity.Unorder;
import entity.User;
import entity.Worker;

/**
 * Created by 张广洁 on 2017/2/18.
 */
public class indexActivity extends Activity{

    private List<Unorder> unorder1;
    private List<Ordering> ordering1;
    private List<Ordered> ordered1;
    private List<Removeorder> removeorder1;

    private Unorder test;

    private Servicetype baojie;
    private Servicetype dala;
    private Servicetype shafa;
    private Servicetype dasaochu;
    private List<Servicetype> servicestypelist;


    private User user;
    private String session;
    //private String phoneNum;
    private TextView tv;
    private String name;

    String mBaseUrl="http://114.115.139.178:8080/app_housework/user_login";

    public void onCreate(Bundle savedInstanceBundle) {
        super.onCreate(savedInstanceBundle);
        setContentView(R.layout.index);

        tv=(TextView)findViewById(R.id.mess);
        tv.setText("18181818181");
        //获得用户名
        Intent it=this.getIntent();
        session=it.getStringExtra("session");
      //  phoneNum=it.getStringExtra("number_login");

        //获取用户信息
      /*  OkHttpClient okHttpClient3=new OkHttpClient();
        okHttpClient3.setCookieHandler(new CookieManager(null, CookiePolicy.ACCEPT_ALL));
        final Request request3=new Request.Builder().addHeader("cookie",session).url("http://192.168.191.1:8080/app_housework/user_infor").build();

        Call call3 = okHttpClient3.newCall(request3);
        call3.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {

                final String res = response.body().string();
                // Log.i("userinfo", res);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        Gson gson = new Gson();
                        Type type2 = new TypeToken<User>() {}.getType();
                        user = gson.fromJson(res, type2);
                        Log.i("test------", user.toString());
                        name = user.getUserName();
                        tv.setText(name);
                    }
                });
               // Log.i("name",user.toString());


            }
        });
*/
        //通过okhttp获取所有未接单订单信息
        //通过json方式返回

        //未接单订单信息
        OkHttpClient okHttpClient_un=new OkHttpClient();
        okHttpClient_un.setCookieHandler(new CookieManager(null, CookiePolicy.ACCEPT_ALL));
        final Request request_un=new Request.Builder().addHeader("cookie",session).url("http://114.115.139.178:8080/app_housework/user_unorders").build();

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
        final Request request_ing=new Request.Builder().addHeader("cookie",session).url("http://114.115.139.178:8080/app_housework/user_orderings").build();

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
        final Request request_ed=new Request.Builder().addHeader("cookie",session).url("http://114.115.139.178:8080/app_housework/user_ordereds").build();

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
        final Request request_re=new Request.Builder().addHeader("cookie",session).url("http://114.115.139.178:8080/app_housework/user_removeOrders").build();

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

        //获取服务类型以及阿姨信息
        OkHttpClient okHttpClient2=new OkHttpClient();
        okHttpClient2.setCookieHandler(new CookieManager(null, CookiePolicy.ACCEPT_ALL));
        final Request request2=new Request.Builder().url("http://114.115.139.178:8080/app_housework/service_list").build();

        Call call2 = okHttpClient2.newCall(request2);
        call2.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
            }

            @Override
            public void onResponse(Response response) throws IOException {
                final String res = response.body().string();
              //  Log.i("serviceslist", res);
                // Gson gson=new Gson();
                Gson gson = new GsonBuilder()
                        .registerTypeAdapter(Timestamp.class, new TimestampAdapter())
                        .create();
                Type type2 = new TypeToken<List<Servicetype>>() {
                }.getType();
                servicestypelist = gson.fromJson(res, type2);

                for(int i=0;i<servicestypelist.size();i++){
                    if(servicestypelist.get(i).getServiceTypeName().equals("日常保洁")){
                       baojie=servicestypelist.get(i);
                    }else if(servicestypelist.get(i).getServiceTypeName().equals("大扫除")){
                        dasaochu=servicestypelist.get(i);
                    }else if(servicestypelist.get(i).getServiceTypeName().equals("地板打蜡")){
                        dala=servicestypelist.get(i);
                    }else{
                        shafa=servicestypelist.get(i);
                    }
                    ;
                }

               // Log.i("servicetype",servicestypelist.toString());
            }
        });



        ImageView order1=(ImageView)findViewById(R.id.goorder1);

        //每个页面跳转之前 首先判断是否有此类型消息
        order1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //去第 1个订单
              /*  test=unorder1.get(1);
                test.setOrderId("123456");
                Gson gson=new GsonBuilder().setPrettyPrinting()
                        .setDateFormat("yyyy-MM-dd HH:mm:ss").create();
                String json=gson.toJson(test);*/
                //    toast("下单成功");

               // Log.i("test", json.toString());
               // OkHttpClient okHttpClient=new OkHttpClient();

                //  RequestBody requestBody=RequestBody.create(JSON,json);
              /*  FormEncodingBuilder requestBodyBuilder=new FormEncodingBuilder();
                RequestBody requestBody=requestBodyBuilder.add("unorderJson", json).add("editorType","add").build();
                Request.Builder builder=new Request.Builder();
                String url=" http://114.115.139.178:8080/app_housework/Unorder_Save" ;
                Request request = builder.url(url).post(requestBody).post(requestBody).build();
                                                   Request request=new Request.Builder()
                                                           .addHeader("cookie",session)
                                                           .url("http://114.115.139.178:8080/app_housework/Unorder_Save")
                                                           .post(requestBody)
                                                           .build();
                Call call=okHttpClient.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Request request, IOException e) {
                        toast("创建订单失败，请检查网络连接");
                    }

                    @Override
                    public void onResponse(Response response) throws IOException {
                        if (response.isSuccessful()) {
                            //添加成功后 跳转到主页面
                            toast("您已成功下单！");

                        }
                    }
                });*/

                if(unorder1.size()==0){
                    toast("您暂无此类型订单信息");
                }else{
                    Intent it_un = new Intent(indexActivity.this, UnorderListActivity.class);
                    it_un.putExtra("session",session);
                    Bundle bundle1=new Bundle();
                    bundle1.putSerializable("unorder1",(Serializable)unorder1);
                    it_un.putExtras(bundle1);
                    indexActivity.this.startActivity(it_un);
                }

            }
        });

        ImageView order2 = (ImageView) findViewById(R.id.goorder2);
        order2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //去第 2个订单
                if(ordering1.size()==0){
                    toast("您暂无此类型订单信息");
                }else{
                    Intent it_ing = new Intent(indexActivity.this, OrderingListActivity.class);
                    it_ing.putExtra("session",session);
                    Bundle bundle2=new Bundle();
                    bundle2.putSerializable("ordering1",(Serializable)ordering1);
                    it_ing.putExtras(bundle2);
                    indexActivity.this.startActivity(it_ing);
                }

            }
        });

        ImageView order3=(ImageView)findViewById(R.id.goorder3);
        order3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //去第3个订单
                if(ordered1.size()==0){
                    toast("您暂无此类型订单信息");
                }else{
                    Intent it_ed = new Intent(indexActivity.this, OrderedListActivity.class);
                    it_ed.putExtra("session",session);
                    Bundle bundle3=new Bundle();
                    bundle3.putSerializable("ordered1",(Serializable)ordered1);
                    it_ed.putExtras(bundle3);
                    indexActivity.this.startActivity(it_ed);
                }

            }
        });

        ImageView order4=(ImageView)findViewById(R.id.goorder4);
        order4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //去第4个订单
                if(removeorder1.size()==0){
                    toast("您暂无此类型订单信息");
                }else{
                    Intent it_re = new Intent(indexActivity.this, RemoveorderListActivity.class);
                    it_re.putExtra("session",session);
                    Bundle bundle4=new Bundle();
                    bundle4.putSerializable("remove1",(Serializable)removeorder1);
                    it_re.putExtras(bundle4);
                    indexActivity.this.startActivity(it_re);
                }

            }
        });


        Button goording=(Button)findViewById(R.id.goording);
        goording.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //去下单
                Intent it_ording = new Intent(indexActivity.this, OrdingActivity.class);
                it_ording.putExtra("session", session);
                indexActivity.this.startActivity(it_ording);

            }
        });

        Button gomine=(Button)findViewById(R.id.gomine);
        gomine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //去往我的主页
                Intent it_mine = new Intent(indexActivity.this, MineActivity.class);
                it_mine.putExtra("session",session);
                indexActivity.this.startActivity(it_mine);
            }
        });


        TextView tv1=(TextView)findViewById(R.id.gobaojie1);
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(baojie.getWorkers().size()>0){
                    Intent itent1 = new Intent(indexActivity.this, Baojie.class);
                    itent1.putExtra("session",session);
                    Bundle bundle5=new Bundle();
                    bundle5.putSerializable("baojie",(Serializable)baojie);
                    itent1.putExtras(bundle5);
                    indexActivity.this.startActivity(itent1);
                }else{
                    toast("尚无此类型阿姨信息！");
                }

                //  indexActivity.this.finish();
            }
        });

        TextView tv2=(TextView)findViewById(R.id.godala1);
        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(dala.getWorkers().size()>0){
                    Intent itent2 = new Intent(indexActivity.this, Dala.class);
                    itent2.putExtra("session",session);
                    Bundle bundle6=new Bundle();
                    bundle6.putSerializable("dala",(Serializable)dala);
                    itent2.putExtras(bundle6);
                    indexActivity.this.startActivity(itent2);
                }else{
                    toast("尚无此类型阿姨信息！");
                }

                //  indexActivity.this.finish();
            }
        });

        TextView tv3=(TextView)findViewById(R.id.godasaochu1);
        tv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dasaochu.getWorkers().size()>0){
                    Intent itent3 = new Intent(indexActivity.this,Dasaochu.class);
                    itent3.putExtra("session",session);
                    Bundle bundle7=new Bundle();
                    bundle7.putSerializable("dasaochu",(Serializable)dasaochu);
                    itent3.putExtras(bundle7);
                    indexActivity.this.startActivity(itent3);
                }else{
                    toast("尚无此类型阿姨信息！");
                }

               // indexActivity.this.finish();
            }
        });

        TextView tv4=(TextView)findViewById(R.id.goshafa1);
        tv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(shafa.getWorkers().size()>0){
                    Intent itent4 = new Intent(indexActivity.this, Shafa.class);
                    itent4.putExtra("session",session);
                    Bundle bundle8=new Bundle();
                    bundle8.putSerializable("shafa",(Serializable)shafa);
                    itent4.putExtras(bundle8);
                    indexActivity.this.startActivity(itent4);
                }else{
                    toast("尚无此类型阿姨信息！");
                }

                //  indexActivity.this.finish();
            }
        });


        }


    private void toast(final String str) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(indexActivity.this, str, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
