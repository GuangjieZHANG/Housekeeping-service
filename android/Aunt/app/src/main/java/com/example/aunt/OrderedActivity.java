package com.example.aunt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import entity.Ordered;


/**
 * Created by 张广洁 on 2017/3/23.
 */
public class OrderedActivity extends Activity {

    private Button gomark;
    private Button gopay;
    private TextView orderedusername;
    private TextView orderedusernumber;
    private TextView orderedauntname;
    private TextView orderedauntnumber;
    private TextView orderedfinishtime;
    private TextView orderedtype;
    private TextView orderedprice;
    private TextView orderedadd;


    //支付宝商户私钥  公钥已配置在后台
   // private String rsa_private="MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAIXGlq+JAi5PbiR/p0DYwQS4YXkAEOzl91dPWP9jYdqfA0iTpg4sLWzE3V2PB9t5QxRUHR5WevdiNr60ejVJqFKCwRed+jcpMo5QMJSh4cQex3ULa7ZdZDWfTp/wyiVacRjzMMHKhKbJDkX5xHi/TFIls3Vsig8ZZAIz1OJro2MZAgMBAAECgYBfPEGKNb+XuHRxB2/KeoTvF1RND/78p7quLY7uQZIoUYvE31gYPtsX1A7DzE+Lr4hRsJAKSQbBluniWhmmVgm9p2NFCuMsHLTb58O6IVt1LKSByvZHbwm7Y5U5FeA0dTgcsceivId0HstNg3AtJqW3A0Mr1ATBkKdl8vfIpFjxAQJBAOCkwhO9/xtPzJhdUVXXi5xJ0I7Ll376fj68tEy9BNoobNJDhDUVt/Y5EWIn3ix8RHIRRr0TiwpYNRibq9tUd8MCQQCYctQOwVw5mcE1uGNmnC6h9G7ZYHVm7B4FLPqBJabhT/LVk+6MbDUytE3LmNNWTwNLBJoD0869Wc/FYDd2fSfzAkADr1ArqLsthIKToTw7TzzQBHnyTa6fe5Zkiw3eEF+rAGzAijD5+fA5jrgVRxgi3Kr2wAfMBC0Mr2O3kp4gC+OnAkA6d7fK3O/xC34li01cOkRMSwIEjtEYIzC0TXbZ90XUniQFM9JGuu2AP9SiZD+Rl4YszFwBYcelqX9dNLSw1flvAkEArkPKeXm1M6dqfqNOdSTIgq0fHYGWyqKndqq7+XNxyTvU8IEkLvezGPkGf2tztliqKVYMLIS2ek2mnxK7AH2aTg==";
    private String rsa_private="MIIEuwIBADANBgkqhkiG9w0BAQEFAASCBKUwggShAgEAAoIBAQCKC2J+984aZBS6KvMT4BSZ76tfeY5FNeUivt3NE/JheslhD2LhfYWc2gKt8qtpPO+u3XDNk2VpuwAtpxAPIwSBCJsNVaqKBmUvXVPRe30R9PvE17nTQggNC8v1OW7iY/ilAn90l5ZWXadlM8GOPpcjVe8JYtL+dw6enCzBbVpEBenEt6RJQFYP5VoeAjiet/LPo8K2YoZ7Vj1tcvZy10c9Rt6QkWYyd4dpSNz/MRb+m48XOyfMoNuA1toNh6KuANZJJkKxISxvMqAxaZ/YpINVVFBFjO6lMm2YHqQ4BN1KxsRTkBd0IEvJk3Hiu8DH8obohhx7nQr9NM7LoFs0/9wZAgMBAAECggEALLltivdcVh6QvJRHtuvFoO4Olts6JeBFQZGXKOdKgxyV/BVREv9o952GercBpBHzEbN65Y/Yrn3AnAnhYRYWUnvoSFS18QwLYmIZUuhwaA34OBkwgmqFUzy8MtINw9lh6Iw7oRmvwoGLR3WSQzSyIOepj1/uPjVyjh7JPU3hjeHlopGiHQzJjmRz3wTpn1NNRl+YR7KAdCVY00wAV6UOGXtZckYkBsQGLAWqiofZ15l6Ie7aKsyEYZe6fk1Fy4EIWvjxb9Q44ujtGk4OrbZhhk/emyTzKx+lbXOFgs0t4Faa4SPqDotJAKZZlL04rR61NxjINKwhUqJxMWnYIOgHJQKBgQDXNtpjs3jNoHYXGSaPBvf9RVc6y/jvq7nY09/y+mn9qR6zY5ouO0URB38c2MXWKRHZ/sPgGulECbckXs4UQc+cZOWaLcibrTDa5vXKDwXEhdPupQ63XHBRPN/aN2qCyCdfIPvKCzk8MTjhnKn7Oj+ZHYeCEKlJVowAj+VNywCWcwKBgQCkNKEeBbcOYhNf3W9r9L5+OAy4DfhmIViAg/Vsus5rV9TxLOX5UfKDsSjJa1eoGCjPl1zpLIGl1emP31DwbBX6zH9MydVkVt+QziJFjlYpQDcBV4f9e8W7slfwl4toOXsaHIrJikJlWvHZKt8zbBFzC8Z2qgdfbYmd8q28OiqUQwJ/SEwlEVk5O9qr0Rah3Wp55JtmslnTRrBpvm1uzjFRaeVmat0K7nz6tr3N9eLckxU46gKryp0/v5uD6rPuht+U3vUGLMU0nxePUfpEl5x0U5ChoYnRZ8el86R/js8O+OBEWbC65STgFVhzw1bdw4fN1aIV45u1Qu3QGmA8p+WKTQKBgH9PGpeHQMzS90h9CxT9FPARDrNoiWzNb6t8I7zf/Ig2+a7e65JomYjGwuOe6MA3svR32x4hVoc3mTTlpAAzHLts70metmZsADtpxiJ9X4nt8neE4LeDzeNfO1UaeYgeALZW6LenDSE96IJB/fkFUWYL/BwmaT7wUyD1dQOZaUe3AoGBAJRO9u7JrH4iAmCU1IexzdrUQP13QY7PGrJMuJopH4unQCSYLzen6G2VrDO9GvBUT++9g93OjVVqNF6EjIvvzWsxUllCwNDpYa/DaT7vKSeYqH1rgBOEVQIk7Ijmnuj2peeWoyc3mzWy/+EFmyPWf8hD05xI7cl0y5NtPAvY9Xey";

    /** 商户私钥，pkcs8格式 */
    /** 如下私钥，RSA2_PRIVATE 或者 RSA_PRIVATE 只需要填入一个 */
    /** 如果商户两个都设置了，优先使用 RSA2_PRIVATE */
    /** RSA2_PRIVATE 可以保证商户交易在更加安全的环境下进行，建议使用 RSA2_PRIVATE */
    /** 获取 RSA2_PRIVATE，建议使用支付宝提供的公私钥生成工具生成， */
    /** 工具地址：https://doc.open.alipay.com/docs/doc.htm?treeId=291&articleId=106097&docType=1 */


/*
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    */
/**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     *//*

                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        Toast.makeText(OrderedActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(OrderedActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                    }
                    break;
                }
                case SDK_AUTH_FLAG: {
                    @SuppressWarnings("unchecked")
                    AuthResult authResult = new AuthResult((Map<String, String>) msg.obj, true);
                    String resultStatus = authResult.getResultStatus();

                    // 判断resultStatus 为“9000”且result_code
                    // 为“200”则代表授权成功，具体状态码代表含义可参考授权接口文档
                    if (TextUtils.equals(resultStatus, "9000") && TextUtils.equals(authResult.getResultCode(), "200")) {
                        // 获取alipay_open_id，调支付时作为参数extern_token 的value
                        // 传入，则支付账户为该授权账户
                        Toast.makeText(OrderedActivity.this,
                                "授权成功\n" + String.format("authCode:%s", authResult.getAuthCode()), Toast.LENGTH_SHORT)
                                .show();
                    } else {
                        // 其他状态值则为授权失败
                        Toast.makeText(OrderedActivity.this,
                                "授权失败" + String.format("authCode:%s", authResult.getAuthCode()), Toast.LENGTH_SHORT).show();

                    }
                    break;
                }
                default:
                    break;
            }
        };
    };

*/

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order3);

        orderedusername=(TextView)findViewById(R.id.orderedusername);
        orderedusernumber=(TextView)findViewById(R.id.orderedusernumber);
        orderedauntname=(TextView)findViewById(R.id.orderedauntname);
        orderedauntnumber=(TextView)findViewById(R.id.orderedauntnumber);
      //  orderedfinishtime=(TextView)findViewById(R.id.orderedfinishtime);
        orderedtype=(TextView)findViewById(R.id.orderedtype);
        orderedprice=(TextView)findViewById(R.id.orderedprice);
        orderedadd=(TextView)findViewById(R.id.orderedadd);

        Intent it=this.getIntent();

       Ordered orded = (Ordered)it.getSerializableExtra("ordered");
      //  Ordered orded=new Ordered();
//        toast(orded.getServicetype().getServiceTypeName());

        orderedusername.setText("用户名：" + orded.getUser().getUserName());
        orderedusernumber.setText("用户电话："+orded.getUser().getPhoneNumber());
        orderedauntname.setText("阿姨姓名："+orded.getWorker().getWorkerName());
        orderedauntnumber.setText("阿姨电话："+orded.getWorker().getPhoneNumber());
//        orderedfinishtime.setText("订单结束时间："+orded.getFinishTime().toString());
        orderedtype.setText("服务类型："+orded.getServicetype().getServiceTypeName());
        orderedprice.setText("订单价格："+orded.getCost().toString());
        orderedadd.setText("订单地址："+"陕西省西安市西北工业大学25舍五号楼405");

        gomark=(Button)findViewById(R.id.goremark);
        gopay=(Button)findViewById(R.id.gopay);

        gomark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Intent itgoremark = new Intent();
               // itgoremark.setClass(OrderedActivity.this, RemarkActivity.class);
                //intentindex.putExtra("number_login",phoneNum);
              //  OrderedActivity.this.startActivity(itgoremark);
            }
        });

    }
    private void toast(final String str) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(OrderedActivity.this, str, Toast.LENGTH_SHORT).show();
            }
        });
    }

}
