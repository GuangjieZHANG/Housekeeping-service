package com.example.user;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.Serializable;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import entity.Servicetype;
import entity.Worker;

/**
 * Created by 张广洁 on 2017/2/18.
 */
public class Baojie extends Activity{

    private Button goaunt;
    private Servicetype baojie;
    private TextView intro;
    private TextView price;
    private Set<Worker> baojieaunt=new HashSet<>();
    private List<Worker> baojielist=new ArrayList<>();
    private String session;


    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.baojie);
       // requestWindowFeature(Window.FEATURE_NO_TITLE);

        Intent get=Baojie.this.getIntent();
        session=get.getStringExtra("session");
        baojie = (Servicetype)get.getSerializableExtra("baojie");

//        Log.i("test",baojie.toString());

        goaunt=(Button)findViewById(R.id.goaunt_baojie);
        intro=(TextView)findViewById(R.id.baojieintro);
        price=(TextView)findViewById(R.id.baojieprice);

        intro.setText("服务简介："+baojie.getDescription());
        price.setText("服务价格："+baojie.getUserPrice().toString()+"元/小时");

        baojieaunt=baojie.getWorkers();
        baojielist.addAll(baojieaunt);

        goaunt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it1 = new Intent(Baojie.this, AuntBaojieActivity.class);
                it1.putExtra("session",session);
                Bundle bundle3=new Bundle();
                bundle3.putSerializable("baojieaunts",(Serializable)baojielist);
                it1.putExtras(bundle3);
                Baojie.this.startActivity(it1);
            }
        });



    }

}
