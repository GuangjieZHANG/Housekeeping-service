package com.example.aunt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import entity.Ordered;

/**
 * Created by 张广洁 on 2017/3/22.
 */
public class OrderedListActivity extends Activity {

    private List<Ordered> oderinglist1;
    private String session;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.unorderlist);

        Intent it=this.getIntent();
        oderinglist1=(List<Ordered>)it.getSerializableExtra("ordered1");
        session=it.getStringExtra("session");

       // initOrdered();
        ListView lv1=(ListView)findViewById(R.id.unorderlist1);
        OrderedAdapter orderedAdapter=new OrderedAdapter(OrderedListActivity.this,R.layout.orderitem,oderinglist1);

        lv1.setAdapter(orderedAdapter);

        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Ordered pass1 = oderinglist1.get(position);
                Intent it = new Intent(OrderedListActivity.this, OrderedActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("ordered", pass1);
                it.putExtras(bundle);
                OrderedListActivity.this.startActivity(it);
            }
        });

    }

    /*private void initOrdered(){

        Servicetype service=  new Servicetype("大扫除","intro",120.00,180.00);
        Servicetype baojie=new Servicetype("日常保洁","intro",150.00,190.00);
        Servicetype shafa=new Servicetype("真皮沙发保养","intro",190.98,273.09);
        Servicetype dala=new Servicetype("地板打蜡","intro",190.98,273.09);

        Timestamp now=new Timestamp(System.currentTimeMillis());
        User user=new User("张广洁","18189190207","123456",now);
        Worker worker=new Worker("夏语冰","19384762534","真皮沙发保养");
        Worker worker2=new Worker("王媛媛","10239474929","地板打蜡");
        Ordered order1=new Ordered(service,now,worker,user,980.8);
        oderinglist1.add(order1);

        Ordered order2=new Ordered(baojie,now,worker,user,659.0);
        oderinglist1.add(order2);

        Ordered order3=new Ordered(shafa,now,worker2,user,538.00);
        oderinglist1.add(order3);
    }
*/
}
