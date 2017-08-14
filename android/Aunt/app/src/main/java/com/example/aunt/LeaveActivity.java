package com.example.aunt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Created by 张广洁 on 2017/3/14.
 */
public class LeaveActivity extends Activity {

    private Spinner s_year;
    private String session;
    private Button submit;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.leave);

        submit=(Button)findViewById(R.id.leave_submit);

        Intent it=this.getIntent();
        session=it.getStringExtra("session");

        s_year=(Spinner)findViewById(R.id.start_year);

        final String arr[]=new String[]{
                "2017",
                "2018",
                "2019"
        };

       // ArrayAdapter<String> arratAdapter=new  ArrayAdapter<String>(this,);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toast("您已请假成功！");
            }
        });

    }

    private void toast(final String str) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(LeaveActivity.this, str, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
