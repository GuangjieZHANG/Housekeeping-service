package com.example.user;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

/**
 * Created by 张广洁 on 2017/3/14.
 */
public class RemarkActivity extends Activity {

    private RatingBar mRatingBar;
    private EditText mEditText;
    private Button mButton;
    private String remark;
    private float stars;

    public void onCreate(Bundle savedInstanceBundle){
        super.onCreate(savedInstanceBundle);
        setContentView(R.layout.remark);

        mRatingBar=(RatingBar)findViewById(R.id.stars);
        mEditText=(EditText)findViewById(R.id.remarks);
        mButton=(Button)findViewById(R.id.submitremark);

      /*  mRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {

            }
        });*/
        remark=mEditText.getText().toString();
        stars=mRatingBar.getRating();
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //这里需要是要okHttp上传评星结果与评价
                           toast("请耐心等待此功能");
            }
        });
    }

    private void toast(final String str) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(RemarkActivity.this, str, Toast.LENGTH_SHORT).show();
            }
        });
    }

}
