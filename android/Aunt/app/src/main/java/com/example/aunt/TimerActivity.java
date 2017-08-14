package com.example.aunt;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;

/**
 * Created by 张广洁 on 2017/4/10.
 */
public class TimerActivity extends Activity {

    private Button startTimer;
    private Button stopTimer;
    private Button restartTimer;

    private Chronometer chronometer;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timer);

        chronometer=(Chronometer)findViewById(R.id.timer);

        startTimer=(Button)findViewById( R.id.start);
        stopTimer=(Button)findViewById( R.id.stop);
        restartTimer=(Button)findViewById( R.id.restart);

        startTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chronometer.start();
                startTimer.setText("正在计时");
            }
        });
        stopTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chronometer.stop();
                stopTimer.setText("继续计时");
            }
        });
        restartTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chronometer.setBase(SystemClock.elapsedRealtime());
                chronometer.start();
                startTimer.setText("正在计时");
            }
        });

    }

}
