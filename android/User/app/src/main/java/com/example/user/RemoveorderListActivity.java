package com.example.user;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import entity.Ordered;
import entity.Removeorder;
import entity.Service;
import entity.Servicetype;
import entity.User;

/**
 * Created by 张广洁 on 2017/3/22.
 */
public class RemoveorderListActivity extends Activity {

    private List<Removeorder> reorder1;
    private String session;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.unorderlist);

        Intent it=new Intent();
        reorder1=(List<Removeorder>)it.getSerializableExtra("remove1");
        session=it.getStringExtra("session");

       //  initRemoveorder();
        ListView lv1=(ListView)findViewById(R.id.unorderlist1);
        RemoveorderAdapter orderedAdapter=new  RemoveorderAdapter( RemoveorderListActivity.this,R.layout.orderitem,reorder1);

        lv1.setAdapter(orderedAdapter);

        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Removeorder pass1 = reorder1.get(position);
                Intent it = new Intent(RemoveorderListActivity.this, RemoveorderActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("removeorder", pass1);
                it.putExtras(bundle);
                RemoveorderListActivity.this.startActivity(it);
            }
        });

    }

   /* private void initRemoveorder(){

        Servicetype service=  new Servicetype("大扫除","intro",120.00,180.00);
        Servicetype baojie=new Servicetype("日常保洁","intro",150.00,190.00);
        Servicetype shafa=new Servicetype("真皮沙发保养","intro",190.98,273.09);
        Servicetype dala=new Servicetype("地板打蜡","intro",190.98,273.09);

        Timestamp now=new Timestamp(System.currentTimeMillis());
        User user=new User("张广洁","18189190207","123456",now);
        Removeorder order1=new Removeorder(service,now,user,now,now);
        oderinglist1.add(order1);

        Removeorder order2=new Removeorder(baojie,now,user,now,now);
        oderinglist1.add(order2);

        Removeorder order3=new Removeorder(shafa,now,user,now,now);
        oderinglist1.add(order3);
    }*/


}
