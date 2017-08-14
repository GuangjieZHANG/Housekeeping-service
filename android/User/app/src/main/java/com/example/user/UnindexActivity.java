package com.example.user;

import android.app.Activity;
import android.content.Intent;;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.example.http.C;
import com.example.http.ImageBannerFrameLayout;
import com.example.http.ImageBarnnerViewGroup;
import com.example.http.TimestampAdapter;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import entity.Servicetype;
import entity.Worker;

/**
 * Created by 张广洁 on 2017/2/18.
 */
public class UnindexActivity extends Activity {

   private List<Servicetype> servicestypelist;
   private Servicetype dasaochu;
   private Servicetype dala;
   private Servicetype shafa;
   private Servicetype baojie;
  // private String session;

   private ImageBannerFrameLayout mGroup;
   private int[] ids=new int[]{
           R.drawable.baojie,
           R.drawable.dala,
           R.drawable.dasaochu,
           R.drawable.shafa
   };

    public void onCreate(Bundle savedInstanceBundle) {
        super.onCreate(savedInstanceBundle);
        setContentView(R.layout.unindex);
        TextView textView = (TextView) findViewById(R.id.gologin1);

        //计算当前手机宽度
        DisplayMetrics dm=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        C.WIDTH=dm.widthPixels;

        mGroup=(ImageBannerFrameLayout)findViewById(R.id.unindex);

        List<Bitmap> list = new ArrayList<>();

        for(int i=0;i<ids.length;i++){
            Bitmap bitmap= BitmapFactory.decodeResource(getResources(),ids[i]);
            list.add(bitmap);
        }
        mGroup.addBitmaps(list);
        /*for(int i=0;i<ids.length;i++){
            ImageView iv=new ImageView(this);
            iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
            iv.setLayoutParams(new ViewGroup.LayoutParams(width,ViewGroup.LayoutParams.WRAP_CONTENT));
            iv.setImageResource(ids[i]);
            mGroup.addView(iv);
        }*/

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
                //Log.i("serviceslist", res);
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
                Log.i("servicetype",servicestypelist.toString());
            }
        });



        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent itent = new Intent(UnindexActivity.this, LoginActivity.class);
                UnindexActivity.this.startActivity(itent);
                // UnindexActivity.this.finish();保留返回键功能
            }
        });

        TextView tv1=(TextView)findViewById(R.id.gobaojie);
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(baojie.getWorkers().size()>0){
                    Intent itent1 = new Intent(UnindexActivity.this, Baojie.class);
                   // itent1.putExtra("session",session);
                    Bundle bundle5=new Bundle();
                    bundle5.putSerializable("baojie",(Serializable)baojie);
                    itent1.putExtras(bundle5);
                    UnindexActivity.this.startActivity(itent1);
                }else{
                    toast("尚无此类型阿姨信息！");
                }

            }
        });

        TextView tv2=(TextView)findViewById(R.id.godala);
        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dala.getWorkers().size()>0){
                    Intent itent2 = new Intent(UnindexActivity.this, Dala.class);
                   // itent2.putExtra("session",session);
                    Bundle bundle6=new Bundle();
                    bundle6.putSerializable("dala",(Serializable)dala);
                    itent2.putExtras(bundle6);
                    UnindexActivity.this.startActivity(itent2);
                }else{
                    toast("尚无此类型阿姨信息！");
                }
            }
        });

        TextView tv3=(TextView)findViewById(R.id.godasaochu);
        tv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dasaochu.getWorkers().size()>0){
                    Intent itent3 = new Intent(UnindexActivity.this,Dasaochu.class);
                   // itent3.putExtra("session",session);
                    Bundle bundle7=new Bundle();
                    bundle7.putSerializable("dasaochu",(Serializable)dasaochu);
                    itent3.putExtras(bundle7);
                    UnindexActivity.this.startActivity(itent3);
                }else{
                    toast("尚无此类型阿姨信息！");
                }
            }
        });

        TextView tv4=(TextView)findViewById(R.id.goshafa);
        tv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(shafa.getWorkers().size()>0){
                    Intent itent4 = new Intent(UnindexActivity.this, Shafa.class);
                   // itent4.putExtra("session",session);
                    Bundle bundle8=new Bundle();
                    bundle8.putSerializable("shafa",(Serializable)shafa);
                    itent4.putExtras(bundle8);
                    UnindexActivity.this.startActivity(itent4);
                }else{
                    toast("尚无此类型阿姨信息！");
                }
            }
        });


}
    private void toast(final String str) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(UnindexActivity.this, str, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
