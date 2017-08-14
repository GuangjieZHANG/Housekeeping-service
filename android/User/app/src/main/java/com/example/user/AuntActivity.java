package com.example.user;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;

import entity.Ordering;
import entity.Worker;

/**
 * Created by 张广洁 on 2017/3/24.
 */
public class AuntActivity extends Activity {

    private TextView auntname;
    private TextView auntnum;
    private TextView aunttype;
    private TextView auntintro;
    private Button goording;
    private Worker worker;
    private String session;


    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aunt);

        auntname=(TextView)findViewById(R.id.auntname);
        auntnum=(TextView)findViewById(R.id.auntnumber);
        //aunttype=(TextView)findViewById(R.id.aunttype);
        auntintro=(TextView)findViewById(R.id.auntintro);

        goording=(Button)findViewById(R.id.goording1);

        Intent it=this.getIntent();
        worker = (Worker) it.getSerializableExtra("worker");
        session=it.getStringExtra("session");

       // toast(worker.getWorkerName());

        auntname.setText("阿姨姓名：" + worker.getWorkerName());
        auntnum.setText("阿姨电话："+worker.getPhoneNumber());
       // aunttype.setText("阿姨类型：");
        auntintro.setText("简要介绍："+worker.getBrief());

        goording.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               //判断是否登陆
                if (session==null){
                    //未登录时弹出警告
                    toast("您尚未登陆，请登陆后下单");
                }else{
                    //已登录后才能下单
                    Intent itent1 = new Intent(AuntActivity.this, OrdingActivity.class);
                    itent1.putExtra("session",session);
                    Bundle bundle5=new Bundle();
                    bundle5.putSerializable("worker",(Serializable)worker);
                    itent1.putExtras(bundle5);
                    AuntActivity.this.startActivity(itent1);

                }
            }
        });

    }

    private void toast(final String str) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(AuntActivity.this, str, Toast.LENGTH_SHORT).show();
            }
        });
    }

}
