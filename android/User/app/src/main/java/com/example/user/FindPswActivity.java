package com.example.user;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * Created by 张广洁 on 2017/4/20.
 */
public class FindPswActivity extends Activity {

    private TextView gologin;

    public void onCreate(Bundle savedInstanceBundle) {
        super.onCreate(savedInstanceBundle);
        setContentView(R.layout.findpsw);

        gologin=(TextView)findViewById(R.id.gologin2);

        gologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(FindPswActivity.this, LoginActivity.class);
                FindPswActivity.this.startActivity(intent);
            }
        });

    }

}
