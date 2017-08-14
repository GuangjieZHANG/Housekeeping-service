package com.example.aunt;


import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;

/**
 * Created by 张广洁 on 2017/3/14.
 * 这个类用来修改个人信息
 */
public class ModifyMineActivity extends Activity {

    private EditText mNameEdit;
    private EditText mAgeEdit;
    private EditText mIntroEdit;
    private EditText mTypeCheck;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modifymine);

        mNameEdit=(EditText)findViewById(R.id.newname);
        mAgeEdit=(EditText)findViewById(R.id.newage);
        mIntroEdit=(EditText)findViewById(R.id.newintro);

    }
}
