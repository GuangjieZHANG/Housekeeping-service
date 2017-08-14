package com.example.http;

/**
 * Created by 张广洁 on 2017/4/4.
 */

//这个类用来维持session 每次调用获取即可
public class Session {

    private static String mysession=null;
    private static final Session session=new Session();

    private Session(){

    }

  public static Session getSession(){
      return session;
  }

    public void setSession(String s){
        session.mysession=s;
    }

}
