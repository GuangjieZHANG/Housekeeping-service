package com.example.user;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

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
import java.lang.reflect.Type;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import entity.Service;
import entity.Servicetype;
import entity.Unorder;
import entity.User;

/**
 * Created by 张广洁 on 2017/3/22.
 */
public class UnorderListActivity extends Activity {

    //private ArrayList<Unorder> unorder1=new ArrayList<>();
    private List<Unorder> unorder1;

    private String session;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.unorderlist);

        Intent it=this.getIntent();
        session=it.getStringExtra("session");
        unorder1=(List<Unorder>)it.getSerializableExtra("unorder1");

        ListView lv1=(ListView)findViewById(R.id.unorderlist1);
        UnorderAdapter unorderAdapter=new UnorderAdapter(UnorderListActivity.this,R.layout.orderitem,unorder1);




       lv1.setAdapter(unorderAdapter);

        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Unorder pass = (Unorder)unorder1.get(position);
                Intent it = new Intent(UnorderListActivity.this, UnorderActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("unorder", pass);
                it.putExtras(bundle);
                it.putExtra("session",session);
                UnorderListActivity.this.startActivity(it);


            }
        });

    }

    private void toast(final String str) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(UnorderListActivity.this, str, Toast.LENGTH_SHORT).show();
            }
        });
    }
}

