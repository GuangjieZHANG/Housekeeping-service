package com.example.aunt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import entity.Unorder;

/**
 * Created by 张广洁 on 2017/3/22.
 */
public class UnorderListActivity extends Activity {

    //private ArrayList<Unorder> unorder1=new ArrayList<>();
    private List<Unorder> unorder1;

    private String session;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.unorderlist);

        Intent it=this.getIntent();
        session=it.getStringExtra("session");
        unorder1=(List<Unorder>)it.getSerializableExtra("unorder1");

        ListView lv1=(ListView)findViewById(R.id.unorderlist1);
        UnorderAdapter unorderAdapter=new UnorderAdapter(UnorderListActivity.this,R.layout.orderitem,unorder1);




       lv1.setAdapter(unorderAdapter);

        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Unorder pass = (Unorder)unorder1.get(position);
                Intent it = new Intent(UnorderListActivity.this, UnorderActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("unorder", pass);
                it.putExtras(bundle);
                it.putExtra("session",session);
                UnorderListActivity.this.startActivity(it);


            }
        });

    }

    private void toast(final String str) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(UnorderListActivity.this, str, Toast.LENGTH_SHORT).show();
            }
        });
    }
}

