package com.example.user;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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
 * Created by 张广洁 on 2017/2/19.
 */
public class Shafa extends Activity {

    private List<Worker> shafalist=new ArrayList<>();
    private Set<Worker> shafaset=new HashSet<>();
    private Servicetype shafa;
    private TextView intro;
    private TextView price;
    private Button goaunt;
    private String session;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shafa);

        Intent get=Shafa.this.getIntent();
        shafa = (Servicetype)get.getSerializableExtra("shafa");
        session=get.getStringExtra("session");
        goaunt=(Button)findViewById(R.id.goaunt_shafa);
        intro=(TextView)findViewById(R.id.shafaintro);
        price=(TextView)findViewById(R.id.shafaprice);

        intro.setText("服务简介："+shafa.getDescription());
        price.setText("服务价格："+shafa.getUserPrice().toString()+"元/小时");

        shafaset=shafa.getWorkers();
        shafalist.addAll(shafaset);

        goaunt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it1 = new Intent(Shafa.this, AuntShafaActivity.class);
                it1.putExtra("session",session);
                Bundle bundle3=new Bundle();
                bundle3.putSerializable("shafaaunts",(Serializable)shafalist);
                it1.putExtras(bundle3);
                Shafa.this.startActivity(it1);
            }
        });

    }


}
