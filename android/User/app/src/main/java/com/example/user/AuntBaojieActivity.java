package com.example.user;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import entity.Servicetype;
import entity.Worker;

/**
 * Created by 张广洁 on 2017/4/3.
 */
public class AuntBaojieActivity extends Activity {

    private List<Worker> worksList=new ArrayList<>();
    //private EditText search;
    private ImageView delete;
    private List<Worker> searchresult=new ArrayList<>();//存储搜索结果
   // private String[] autoString=new String[7];
    private EditText auto;
    private String session;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aunt_baojie);

        auto=(EditText)findViewById(R.id.baojiesearch);
        delete=(ImageView)findViewById(R.id.baojieclear);

        Intent it=this.getIntent();
        session=it.getStringExtra("session");
        worksList = (List<Worker>)it.getSerializableExtra("baojieaunts");
        //worksList=(List<Worker>)it.getSerializableExtra("baojie");
        //initWorks();

       /* String[] autoString=new String[7];

        for(int i=0;i<worksList.size();i++){
           autoString[i]= worksList.get(i).getWorkerName();
           Log.i("test",autoString[i]);
        }*/

   /*     final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,autoString);
        auto=(AutoCompleteTextView)findViewById(R.id.baojiesearch);
        auto.setThreshold(1);
        auto.setAdapter(adapter);*/


        final ListView lv1=(ListView)findViewById(R.id.listviewbaojie);
        WorksAdapter worksAdapter=new WorksAdapter(AuntBaojieActivity.this,R.layout.listitem,worksList);

        lv1.setAdapter(worksAdapter);

        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Worker pass = (Worker) worksList.get(position);
                Intent it = new Intent(AuntBaojieActivity.this, AuntActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("worker", pass);
                it.putExtras(bundle);
                it.putExtra("session",session);
                AuntBaojieActivity.this.startActivity(it);
            }
        });

        auto.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                 searchresult.clear();
                if(auto.getText()!=null){
                    String sea=auto.getText().toString();
                    searchresult=getNewData(sea);
                    WorksAdapter  newadapter=new WorksAdapter(AuntBaojieActivity.this,R.layout.listitem,searchresult);
                    lv1.setAdapter(newadapter);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              auto.setText("");
            }
        });
        //搜索的内容改变之后触发的事件

    }

    private List<Worker> getNewData(String input){
        for(int i=0;i<worksList.size();i++){
            Worker find=worksList.get(i);
            if(find.getWorkerName().contains(input)){
                Worker add=new Worker();
                add.setWorkerName(find.getWorkerName());
                add.setPhoneNumber(find.getPhoneNumber());
                add.setAge(find.getAge());
                add.setBrief(find.getBrief());
                searchresult.add(add);
            }
        }
        return searchresult;
    }

    private void initWorks(){
        Worker worker1=new Worker("李凤年","11211211212","日常保洁");
        worksList.add(worker1);
        Worker worker2=new Worker("张晚晚","15908876890","日常保洁");
        worksList.add(worker2);
        Worker worker3=new Worker("杜若涛","11240987635","日常保洁");
        worksList.add(worker3);
        Worker worker4=new Worker("曲直","18809876384","日常保洁");
        worksList.add(worker4);
        Worker worker5=new Worker("吴向晚","15290781753","日常保洁");
        worksList.add(worker5);
        Worker worker6=new Worker("秦九勺","15938985905","日常保洁");
        worksList.add(worker6);
    }
    }
