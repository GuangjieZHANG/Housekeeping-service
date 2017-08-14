package com.example.aunt;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import entity.Ordered;


/**
 * Created by 张广洁 on 2017/3/22.
 */
public class OrderedAdapter extends ArrayAdapter {

    private int resourcedID;

    public OrderedAdapter(Context context,int textViewResourceID,List<Ordered> objects){

        super(context,textViewResourceID,objects);
        resourcedID=textViewResourceID;
    }
    //SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public View getView(int position,View convertView,ViewGroup parent){

        Ordered ordered=(Ordered)getItem(position);
        View view;
        if(convertView==null){
            view= LayoutInflater.from(getContext()).inflate(resourcedID,null);
        }else{
            view=convertView;
        }
        ImageView orderImage=(ImageView)view.findViewById(R.id.orderimg);
        TextView orderType=(TextView)view.findViewById(R.id.orderlisttype);
        TextView orderTime=(TextView)view.findViewById(R.id.orderlisttime);

        orderImage.setImageResource(R.drawable.dd3);
        orderType.setText("订单类型：" + ordered.getServicetype().getServiceTypeName());
        orderTime.setText("服务价格：" + ordered.getServicetype().getUserPrice()+"元/小时");
        return  view;
    }
}
