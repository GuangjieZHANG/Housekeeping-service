package com.example.aunt;

import android.util.Log;

/**
 * Created by 张广洁 on 2017/3/13.
 */
    public class L {

        private static final String TAG="Alert";
        private static boolean debug=true;
        public static void e(String msg){
            if(debug)
                Log.e(TAG, msg);

        }
}
