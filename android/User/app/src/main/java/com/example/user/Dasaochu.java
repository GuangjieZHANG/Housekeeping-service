package com.example.user;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
import entity.Unorder;
import entity.Worker;

/**
 * Created by 张广洁 on 2017/2/18.
 */
public class Dasaochu extends AppCompatActivity {

    private Servicetype dasaochu;
    private TextView intro;
    private TextView price;
    private Button goaunt;
    private String session;
    private List<Worker> dasaochulist=new ArrayList<>();
    private Set<Worker> dasaochuset=new HashSet<>();

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dasaochu);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);

        Intent get=Dasaochu.this.getIntent();
        session=get.getStringExtra("session");
        dasaochu = (Servicetype)get.getSerializableExtra("dasaochu");
        intro=(TextView)findViewById(R.id.dasaochuintro);
        price=(TextView)findViewById(R.id.dasaochuprice);

        intro.setText("服务简介："+dasaochu.getDescription());
        price.setText("服务价格："+dasaochu.getUserPrice().toString()+"元/小时");

        dasaochuset=dasaochu.getWorkers();
        dasaochulist.addAll(dasaochuset);

        goaunt=(Button)findViewById(R.id.goaunt_dasaochu);
        goaunt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it1 = new Intent(Dasaochu.this, AuntDasaochuActivity.class);
                it1.putExtra("session",session);
                Bundle bundle3=new Bundle();
                bundle3.putSerializable("dasaochuaunts",(Serializable)dasaochulist);
                it1.putExtras(bundle3);
                Dasaochu.this.startActivity(it1);
            }
        });

    }

}
