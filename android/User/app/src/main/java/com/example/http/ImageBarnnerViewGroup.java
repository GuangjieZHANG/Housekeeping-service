package com.example.http;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by 张广洁 on 2017/2/28.
 * 实现轮播的核心类
 */
public class ImageBarnnerViewGroup extends ViewGroup {
    private int children;
    private int childheight;
    private int childwidth;

    private int x;//第一次按下位置横坐标，移动过程中，之前位置的横坐标
    private int index=0;//每张图片的索引
    private Scroller scroller;

    public ImageBarnnerViewGroupListenner getBarnnerViewGroupListenner() {
        return barnnerViewGroupListenner;
    }

    public void setBarnnerViewGroupListenner(ImageBarnnerViewGroupListenner barnnerViewGroupListenner) {
        this.barnnerViewGroupListenner = barnnerViewGroupListenner;
    }

    private ImageBarnnerViewGroupListenner barnnerViewGroupListenner;

    private boolean isAuto=true;
    private Timer timer=new Timer();
    private TimerTask task;
    private Handler autoHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                        if(++index>=children){
                            index=0;
                        }

                    scrollTo(childwidth*index,0);
                    barnnerViewGroupListenner.selectImage(index);
                    break;
            }
        }
    };

    private void startAuto(){
        isAuto=true;
    }

    private void stopAuto(){
        isAuto=false;
    }
    //构造方法
    public ImageBarnnerViewGroup(Context context) {
        super(context);
        intiObj();
    }

    public ImageBarnnerViewGroup(Context context, AttributeSet attr) {
        super(context, attr);
        intiObj();
    }

    public ImageBarnnerViewGroup(Context context, AttributeSet attr, int defStyleAttr) {
        super(context, attr, defStyleAttr);
        intiObj();
    }
//必须实现测量，布局，绘制
   private void intiObj(){
       scroller=new Scroller(getContext());
         task=new TimerTask() {
             @Override
             public void run() {
                 if(isAuto){
                     autoHandler.sendEmptyMessage(0);
                 }
             }
         };
       timer.schedule(task,100,3000);
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if(scroller.computeScrollOffset()){
            scrollTo(scroller.getCurrX(), 0);
            invalidate();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //求子视图个数
       children=getChildCount();
        if(0==children){
            setMeasuredDimension(0,0);
        }else{
            //测量子视图宽高
            measureChildren(widthMeasureSpec,heightMeasureSpec);
            View view=getChildAt(0);
            childheight=view.getMeasuredHeight();
            childwidth=view.getMeasuredWidth();
            int width=view.getMeasuredWidth()*children;
            setMeasuredDimension(childwidth,childheight);
        }
    }

    @Override
    public boolean onInterceptHoverEvent(MotionEvent event) {
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
       switch (event.getAction()){
           case MotionEvent.ACTION_DOWN://用户按下一瞬间
               if(!scroller.isFinished()){
                  scroller.abortAnimation();
               }
               x=(int)event.getX();
               stopAuto();
               break;
           case MotionEvent.ACTION_MOVE://用户在屏幕上移动过程
                int moveX=(int)event.getX();
                int distance=moveX - x;
               scrollBy(-distance,0);
               x=moveX;
               break;
           case MotionEvent.ACTION_UP://屏幕抬起一瞬间

                int scrollX=getScrollX();
               index=(scrollX+childwidth/2)/childwidth;
               if(index < 0){
                   index = 0;
               }else if(index>children-1){
                   index=children-1;
               }

               int dx = index * childwidth - scrollX;
               scroller.startScroll(scrollX,0,dx,0);
               postInvalidate();
               barnnerViewGroupListenner.selectImage(index);
               startAuto();
             //  scrollTo(index*childwidth,0);
               break;
           default:

               break;
       }


        return true;//返回真，告诉父View该事件已处理
    }

    /**
     *
     * @param changed 改变为真 不变为假
     * @param l 左
     * @param t 上
     * @param r 右
     * @param b 下
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
       if(changed){
           int leftMargin=0;
           for(int j=0;j<children;j++){
               View view=getChildAt(j);

               view.layout(leftMargin,0,leftMargin+childwidth,childheight);
               leftMargin+=childwidth;
           }
       }
    }

    public interface ImageBarnnerViewGroupListenner{
        void selectImage(int index);
    }
}
