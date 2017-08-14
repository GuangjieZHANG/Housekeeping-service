package com.example.aunt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import entity.Removeorder;

/**
 * Created by 张广洁 on 2017/3/23.
 */
public class RemoveorderActivity extends Activity {

    private TextView username;
    private TextView usernumber;
    private TextView starttime;
    private TextView removetime;
    private TextView type;
    private TextView removeadd;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order4);

        username=(TextView)findViewById(R.id.removeusername);
        usernumber=(TextView)findViewById(R.id.removeusernumber);
        starttime=(TextView)findViewById(R.id.removestarttime);
        removetime=(TextView)findViewById(R.id.removetime);
        type=(TextView)findViewById(R.id.removetype);
        removeadd=(TextView)findViewById(R.id.removeadd);

        Intent it4=this.getIntent();
        Removeorder removeorder = (Removeorder)it4.getSerializableExtra("removeorder");

        toast(removeorder.getServicetype().getServiceTypeName());

        username.setText("用户名："+removeorder.getUser().getUserName());
        usernumber.setText("用户电话："+removeorder.getUser().getPhoneNumber());
        starttime.setText("订单开始时间："+removeorder.getStartTime().toString());
        removetime.setText("订单取消时间："+removeorder.getRemoveTime().toString());
        type.setText("服务类型："+removeorder.getServicetype().getServiceTypeName());
        removeadd.setText("订单地址："+ "陕西省西安市西北工业大学25舍五号楼405");

    }
    private void toast(final String str) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText( RemoveorderActivity.this, str, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
