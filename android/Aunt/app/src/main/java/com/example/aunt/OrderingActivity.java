package com.example.aunt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;

import entity.Ordering;

/**
 * Created by 张广洁 on 2017/3/23.
 */
public class OrderingActivity extends Activity {

    private TextView username;
    private TextView usernumber;
    private TextView auntname;
    private TextView auntnumber;
    private TextView oedertime;
    private TextView ordertype;
    private TextView orderadd;
    private Button gotimer;
    private String session;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order2);

        Intent it=this.getIntent();
        Ordering ording=(Ordering)it.getSerializableExtra("ordering");
        session=it.getStringExtra("session");

        toast(ording.getServicetype().getServiceTypeName());
        username=(TextView)findViewById(R.id.orderingusername);
        usernumber=(TextView)findViewById(R.id.orderingusernumber);
        auntname=(TextView)findViewById(R.id.orderingauntname);
        auntnumber=(TextView)findViewById(R.id.orderingauntnumber);
        oedertime=(TextView)findViewById(R.id.orderingtime);
        ordertype=(TextView)findViewById(R.id.orderingtype);
        orderadd=(TextView)findViewById(R.id.orderingadd);
        gotimer=(Button)findViewById(R.id.gotimer);

        username.setText("用户名："+ording.getUser().getUserName());
        usernumber.setText("用户电话"+ording.getUser().getPhoneNumber());
        auntname.setText("阿姨姓名："+ording.getWorker().getWorkerName());
        auntnumber.setText("阿姨电话："+ording.getWorker().getPhoneNumber());
        oedertime.setText("订单时间："+ording.getPredictTime().toString());
        ordertype.setText("服务类型："+ording.getServicetype().getServiceTypeName());
        orderadd.setText("服务地址："+"陕西省西安市西北工业大学25舍五号楼405");

        gotimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(OrderingActivity.this, TimerActivity.class);
                it.putExtra("session", session);
                OrderingActivity.this.startActivity(it);

            }
        });
    }
    private void toast(final String str) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(OrderingActivity.this, str, Toast.LENGTH_SHORT).show();
            }
        });
    }

}
