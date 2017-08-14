package com.example.user;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import entity.Servicetype;
import entity.Worker;

/**
 * Created by 张广洁 on 2017/2/18.
 */
public class Dala extends Activity {

    private List<Worker> dalalist=new ArrayList<>();
    private Set<Worker> dalaset=new HashSet<>();
    private Button goaunt;
    private Servicetype dala;
    private TextView intro;
    private TextView price;
    private String session;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dala);
       // requestWindowFeature(Window.FEATURE_NO_TITLE);

        Intent get=Dala.this.getIntent();
        session=get.getStringExtra("session");
        dala = (Servicetype)get.getSerializableExtra("dala");

        goaunt=(Button)findViewById(R.id.goaunt_dala);
        intro=(TextView)findViewById(R.id.dalaintro);
        price=(TextView)findViewById(R.id.dalaprice);

        intro.setText("服务简介：" + dala.getDescription());
        price.setText("服务价格："+dala.getUserPrice().toString()+"元/小时");

        dalaset=dala.getWorkers();
        dalalist.addAll(dalaset);

        goaunt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it1 = new Intent(Dala.this, AuntDalaActivity.class);
                it1.putExtra("session",session);
                Bundle bundle3=new Bundle();
                bundle3.putSerializable("dalaaunts",(Serializable)dalalist);
                it1.putExtras(bundle3);
                Dala.this.startActivity(it1);
            }
        });

    }


}