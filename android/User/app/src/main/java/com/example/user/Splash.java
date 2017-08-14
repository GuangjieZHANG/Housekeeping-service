package com.example.user;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import entity.Ordered;

/** 这个类用来展示启动界面
 * Created by 张广洁 on 2017/2/16.
 */
public class Splash extends Activity{

    private final int SPLASH_DISPLAY_LENGTH=3000;//延迟三秒

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mainIntent=new Intent(Splash.this, UnindexActivity.class);
                Splash.this.startActivity(mainIntent);
                Splash.this.finish();
            }
        },SPLASH_DISPLAY_LENGTH);
    }
}
