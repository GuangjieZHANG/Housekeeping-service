package com.example.user;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import entity.Worker;

/**
 * Created by 张广洁 on 2017/3/22.
 */
public class WorksAdapter extends ArrayAdapter {

    private int resourcedID;

    public WorksAdapter(Context context,int textViewResourceID,List<Worker> objects){

        super(context,textViewResourceID,objects);
        resourcedID=textViewResourceID;
    }

    @Override
    public View getView(int position,View convertView,ViewGroup parent){

        Worker worker=(Worker)getItem(position);
        View view;
        if(convertView==null){
            view= LayoutInflater.from(getContext()).inflate(resourcedID,null);
        }else{
            view=convertView;
        }
        ImageView workImage=(ImageView)view.findViewById(R.id.img);
        TextView workName=(TextView)view.findViewById(R.id.name);
        TextView workNum=(TextView)view.findViewById(R.id.level);

        workImage.setImageResource(R.drawable.touxiang);
        workName.setText("姓名：" + worker.getWorkerName());
        workNum.setText("电话："+worker.getPhoneNumber());
        return  view;
    }
}
