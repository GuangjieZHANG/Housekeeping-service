package com.example.http;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.user.R;

import java.util.List;

/**
 * Created by 张广洁 on 2017/3/2.
 */
public class ImageBannerFrameLayout extends FrameLayout implements ImageBarnnerViewGroup.ImageBarnnerViewGroupListenner{

    private ImageBarnnerViewGroup imageBarnnerViewGroup;
    private LinearLayout linearLayout;

    public ImageBannerFrameLayout(Context context){
        super(context);
        initImageBannerViewGroup();
        initDotLinearLayout();
    }
    public ImageBannerFrameLayout(Context context,AttributeSet attrs){
        super(context,attrs);
        initImageBannerViewGroup();
        initDotLinearLayout();
    }
    public ImageBannerFrameLayout(Context context,AttributeSet attrs,int defStyleAttrs){
        super(context,attrs,defStyleAttrs);
        initImageBannerViewGroup();
        initDotLinearLayout();
    }

    public void addBitmaps(List<Bitmap> list){

        for(int i=0;i<list.size();i++){
            Bitmap bitmap=list.get(i);
            addBitmapToImageBarnnerViewGroup(bitmap);
            addDotToLinearLayout();
        }

    }

    private void addDotToLinearLayout(){
        ImageView iv=new ImageView(getContext());
        LinearLayout.LayoutParams lp=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
       lp.setMargins(5,5,5,5);
        iv.setLayoutParams(lp);
        iv.setImageResource(R.drawable.dot_normal);
        linearLayout.addView(iv);
    }

    private void addBitmapToImageBarnnerViewGroup( Bitmap bitmap){
        ImageView iv=new ImageView(getContext());
        iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
        iv.setLayoutParams(new ViewGroup.LayoutParams(C.WIDTH,ViewGroup.LayoutParams.WRAP_CONTENT));
        iv.setImageBitmap(bitmap);
        imageBarnnerViewGroup.addView(iv);

    }

    //初始化轮播核心类
    private void initImageBannerViewGroup(){
        imageBarnnerViewGroup=new ImageBarnnerViewGroup(getContext());
        FrameLayout.LayoutParams lp=new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT);
        imageBarnnerViewGroup.setLayoutParams(lp);
        imageBarnnerViewGroup.setBarnnerViewGroupListenner(this);
        addView(imageBarnnerViewGroup);
    }

    //初始化底部圆点布局
    private void initDotLinearLayout(){
        linearLayout=new LinearLayout(getContext());
        FrameLayout.LayoutParams lp=new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
               40);
        linearLayout.setLayoutParams(lp);
        linearLayout.setOrientation(linearLayout.HORIZONTAL);
        linearLayout.setGravity(Gravity.CENTER);

        linearLayout.setBackgroundColor(Color.rgb(0,130,228));

        addView(linearLayout);
        FrameLayout.LayoutParams layoutParams=(FrameLayout.LayoutParams)linearLayout.getLayoutParams();
        layoutParams.gravity=Gravity.BOTTOM;

        linearLayout.setLayoutParams(layoutParams);

        //3.0之后使用setAlpha（），调用不同
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.HONEYCOMB){
            linearLayout.setAlpha(0.5f);
        }else{
            linearLayout.getBackground().setAlpha(100);
        }

    }

    @Override
    public void selectImage(int index) {
        int count=linearLayout.getChildCount();
        for (int i=0;i<count;i++){
            ImageView iv=(ImageView)linearLayout.getChildAt(i);
            if(i==index){
                iv.setImageResource(R.drawable.dot_select);
            }else {
                iv.setImageResource(R.drawable.dot_normal);
            }
        }
    }
}
