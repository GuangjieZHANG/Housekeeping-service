package com.example.user;

import android.util.Log;

/**
 * Created by 张广洁 on 2017/2/28.
 */
public class L {

    private static final String TAG="Alert";
    private static boolean debug=true;
    public static void e(String msg){
        if(debug)
            Log.e(TAG,msg);

    }
}
