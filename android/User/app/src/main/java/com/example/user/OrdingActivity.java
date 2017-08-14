package com.example.user;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.inner.Point;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.*;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.sql.Timestamp;

import entity.Servicetype;
import entity.Unorder;
import entity.User;
import entity.Worker;

/**
 * Created by 张广洁 on 2017/3/18.
 */
public class OrdingActivity extends Activity {

    private Button gomap;
    private Button submitorder;
    private RadioGroup ordertype;
    private String serviceName;
    private TextView jingdu;
    private TextView weidu;

    private Spinner mstart_year;
    private Spinner mstart_month;
    private Spinner mstart_day;
    private Spinner mstart_time;
    private Spinner mend_time;

    private double longtitu;
    private double latitu;

    private User user;
    private Worker worker;
    private String address="陕西省西安市西北工业大学毅字楼";
    private String session;

    private Timestamp start;
    private Timestamp finish;

    //这句话是为了不让JSON报错 虽然我也不知道为什么 ╮(╯▽╰)╭
    public static final MediaType JSON= MediaType.parse("application/json; charset=utf-8");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 在使用SDK各组件之前初始化context信息，传入ApplicationContext
        // 注意该方法要再setContentView方法之前实现
       // SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.ording);

        gomap=(Button)findViewById(R.id.gomap);

        submitorder=(Button)findViewById(R.id.submitorder);

        ordertype=(RadioGroup)findViewById(R.id.ordertype);

        jingdu=(TextView)findViewById(R.id.orderjingdu);

        weidu=(TextView)findViewById(R.id.orderweidu);

        ordertype.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                    if(checkedId==R.id.ordertypebaojie){
                        toast("您选择了日常保洁服务");
                        serviceName="日常保洁";
                    }else if(checkedId==R.id.ordertypedala){
                        toast("您选择了地板打蜡服务");
                        serviceName="地板打蜡";
                    }else if(checkedId==R.id.ordertypeshafa){
                        toast("您选择了真皮沙发保养服务");
                        serviceName="真皮沙发保养";
                    }else{
                       toast("您选择了大扫除服务");
                        serviceName="大扫除";
                    }
            }
        });

        Intent it=getIntent();
        longtitu=it.getDoubleExtra("jingdu", 00.00);
        latitu=it.getDoubleExtra("weidu", 00.00);
       // add=it.getStringExtra("add");
        session=it.getStringExtra("session");
        worker=(Worker)it.getSerializableExtra("worker");

       jingdu.setText("经度：" + longtitu);
        weidu.setText("纬度：" + latitu);

        gomap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it_gomap = new Intent(OrdingActivity.this, MapActivity.class);
                it_gomap.putExtra("session",session);
                OrdingActivity.this.startActivity(it_gomap);
            }
        });

        mstart_year=(Spinner)findViewById(R.id.start_year);
        mstart_month=(Spinner)findViewById(R.id.start_month);
        mstart_day=(Spinner)findViewById(R.id.start_day);

        mstart_time=(Spinner)findViewById(R.id.start_time1);
        mend_time=(Spinner)findViewById(R.id.start_time2);

        OkHttpClient okHttpClient2=new OkHttpClient();
        okHttpClient2.setCookieHandler(new CookieManager(null, CookiePolicy.ACCEPT_ALL));
        final Request request2=new Request.Builder().addHeader("cookie",session).url("http://192.168.191.1:8080/app_housework/user_infor").build();

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
            }
        });
       //获取订单地址
       /* if(latitu!=00.00&&longtitu!=00.00){
                LatLng latLng=new LatLng(longtitu,latitu);
                GeoCoder gc = GeoCoder.newInstance();
                gc.reverseGeoCode(new ReverseGeoCodeOption().location(latLng));
                gc.setOnGetGeoCodeResultListener(new OnGetGeoCoderResultListener() {

                    @Override
                    public void onGetReverseGeoCodeResult(ReverseGeoCodeResult result) {
                        //pb.setVisibility(View.GONE);
                        if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
                            Log.e("发起反地理编码请求", "未能找到结果");
                        } else {
                            address=result.getAddress();
                            Log.i("address",address);
                        }
                    }
                    @Override
                    public void onGetGeoCodeResult(GeoCodeResult result) {
                    }
                });
        }*/
        submitorder.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View v) {

                                               if(address==null){
                                                   toast("您尚未选择订单地址");
                                               }else{
                                                   String year, month, day, time1, time2;
                                                   // String time_start;
                                                   //String time_finish;
                                                   //提交订单内容
                                                   year = mstart_year.getSelectedItem().toString();

                                                   month = mstart_month.getSelectedItem().toString();

                                                   day = mstart_day.getSelectedItem().toString();

                                                   time1 = mstart_time.getSelectedItem().toString();

                                                   time2 = mend_time.getSelectedItem().toString();

                                                   String time_start = year + "-" + month + "-" + day + " " + time1 + ":00";

                                                   String time_finish = year + "-" + month + "-" + day + " " + time2 + ":00";

                                                   try{

                                                       start = Timestamp.valueOf(time_start);
                                                       finish = Timestamp.valueOf(time_finish);

                                                   }catch(Exception e){
                                                       toast("日期转换失败");
                                                       e.printStackTrace();
                                                   }
                                                   Timestamp modify = new Timestamp(System.currentTimeMillis());

                                                   // Gson gson=new Gson();
                                                   // String start_timestring=gson.toJson()
                                                   Servicetype servicetype = new Servicetype();
                                                   servicetype.setServiceTypeName(serviceName);
                                                   Timestamp now=new Timestamp(System.currentTimeMillis());

                                                   Unorder unorder1=new Unorder();
                                                   unorder1.setOrderId("123456");
                                                   unorder1.setUser(user);
                                                   unorder1.setLongitude(longtitu);
                                                   unorder1.setLatitude(latitu);
                                                   unorder1.setIsReced(false);
                                                   unorder1.setModifyTime(now);
                                                   unorder1.setPredictTime(start);
                                                   unorder1.setAddTime(now);
                                                   unorder1.setTimer(finish);
                                                   unorder1.setAddress(address);
                                                   if(worker!=null){
                                                       unorder1.setWorker(worker);
                                                   }

                                                   Gson gson=new GsonBuilder().setPrettyPrinting()
                                                           .setDateFormat("yyyy-MM-dd HH:mm:ss").create();
                                                   String json=gson.toJson(unorder1);
                                                   //    toast("下单成功");

                                                   Log.i("unorder1", json.toString());

                                                   OkHttpClient okHttpClient=new OkHttpClient();

                                                 //  RequestBody requestBody=RequestBody.create(JSON,json);
                                                   FormEncodingBuilder requestBodyBuilder=new FormEncodingBuilder();
                                                   RequestBody requestBody=requestBodyBuilder.add("unorderJson", json).add("editorType","add").build();
                                                   Request.Builder builder=new Request.Builder();
                                                 String url=" http://114.115.139.178:8080/app_housework/Unorder_Save" ;
                                                   Request request = builder.url(url).post(requestBody).post(requestBody).build();
                                                  /* Request request=new Request.Builder()
                                                           .addHeader("cookie",session)
                                                           .url("http://114.115.139.178:8080/app_housework/Unorder_Save")
                                                           .post(requestBody)
                                                           .build();*/
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
                                                               Log.i("ordering ",response.body().toString());
                                                               Intent it = new Intent(OrdingActivity.this, indexActivity.class);
                                                               it.putExtra("session", session);
                                                               OrdingActivity.this.startActivity(it);
                                                           }
                                                       }
                                                   });

                                                   ////////////////////////////////另一种访问方式



                                                          /* OkHttpClient okHttpClient=new OkHttpClient();
                                                           RequestBody body=RequestBody.create(JSON,json);
                                                           Request request=new Request.Builder()
                                                                   .addHeader("cookie", session)
                                                                   .url("http://114.115.139.178:8080/app_housework/Unorder_Save")
                                                                   .post(body)
                                                                   .build();
                                                           try {
                                                               Response response=okHttpClient.newCall(request).execute();

                                                               if(response.isSuccessful()){
                                                                   toast("success");
                                                                   Log.i("ordering ",response.body().toString());
                                                               }
                                                           } catch (IOException e) {
                                                               e.printStackTrace();
                                                           }

*/


                                               }
                                           }
                                               }

                  );//点击事件结束
            }


    private void toast(final String str) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(OrdingActivity.this, str, Toast.LENGTH_SHORT).show();
            }
        });
    }
  }