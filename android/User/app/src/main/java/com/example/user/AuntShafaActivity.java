package com.example.user;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import entity.Worker;

/**
 * Created by 张广洁 on 2017/4/3.
 */
public class AuntShafaActivity extends Activity {

    private List<Worker> worksList=new ArrayList<>();
    private EditText search;
    private ImageView delete;
    private List<Worker> searchresult=new ArrayList<>();//存储搜索结果
    private String session;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aunt_shafa);

        Intent it=this.getIntent();
        session=it.getStringExtra("session");
        worksList=(List<Worker>)it.getSerializableExtra("shafaaunts");
        delete=(ImageView)findViewById(R.id.shafaclear);
        search=(EditText)findViewById(R.id.shafasearch);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search.setText("");
            }
        });
        //initWorks();
       /* String[] autoString=new String[]{
                "李凤年",   "张晚晚",  "曲直","吴向晚","秦九勺","eee"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,autoString);
        search.setThreshold(1);
        search.setAdapter(adapter);*/
        final ListView lv1=(ListView)findViewById(R.id.listviewshafa);
        WorksAdapter worksAdapter=new WorksAdapter(AuntShafaActivity.this,R.layout.listitem,worksList);

        lv1.setAdapter(worksAdapter);

        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Worker pass = (Worker) worksList.get(position);
                Intent it = new Intent(AuntShafaActivity.this, AuntActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("worker", pass);
                it.putExtras(bundle);
                it.putExtra("session", session);
                AuntShafaActivity.this.startActivity(it);

            }
        });

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                searchresult.clear();
                if (search.getText() != null) {
                    String sea = search.getText().toString();
                    searchresult = getNewData(sea);
                    WorksAdapter newadapter = new WorksAdapter(AuntShafaActivity.this, R.layout.listitem, searchresult);
                    lv1.setAdapter(newadapter);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

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
        Worker worker1=new Worker("夏语冰","19384762534","真皮沙发保养");
        worksList.add(worker1);
        Worker worker2=new Worker("曲直","18809876384","真皮沙发保养");
        worksList.add(worker2);
        Worker worker3=new Worker("王玲玲","17738493028","真皮沙发保养");
        worksList.add(worker3);
        Worker worker4=new Worker("杜真","18893746254","真皮沙发保养");
        worksList.add(worker4);
        Worker worker5=new Worker("李凤年","11211211212","真皮沙发保养");
        worksList.add(worker5);
        Worker worker6=new Worker("刘夏","18374987635","真皮沙发保养");
        worksList.add(worker6);

    }

}
