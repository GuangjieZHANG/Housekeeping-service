package com.example.alipay;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;

import com.alipay.sdk.app.PayTask;
import com.example.user.LoginActivity;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

/**
 * Created by 张广洁 on 2017/3/30.
 */
public class AliPay {
    public static class Builder {
        private Activity mActivity;

        public Builder(Activity activity) {
            this.mActivity = activity;
        }
        /**
         * 设置商户PID
         */
        private String PARTNER = "";
        // 商户收款账号
        private String SELLER = "";
        // 商户私钥，pkcs8格式
        private String RSA_PRIVATE = "";
        // 支付宝公钥
        private String RSA_PUBLIC = "";
        private int SDK_PAY_FLAG = 6406;

        private String orderTitle = "";
        private String subTitle = "";
        private String price = "";
        private String notifyURL = "";

        /**
         * 设置商户PID
         */
        public AliPay.Builder setPARTNER(String PARTNER) {
            this.PARTNER = PARTNER;
            return this;
        }
        /**
         * 设置商户收款账号
         */
        public AliPay.Builder setSELLER(String SELLER) {
            this.SELLER = SELLER;
            return this;
        }
        /**
         * 设置商户私钥，pkcs8格式
         */
        public AliPay.Builder setRSA_PRIVATE(String RSA_PRIVATE) {
            this.RSA_PRIVATE = RSA_PRIVATE;
            return this;
        }

        /**
         * 设置支付宝公钥
         */
        public AliPay.Builder setRSA_PUBLIC(String RSA_PUBLIC) {
            this.RSA_PUBLIC = RSA_PUBLIC;
            return this;
        }

        /**
         * 设置商品名称
         */
        public AliPay.Builder setOrderTitle(String orderTitle) {
            this.orderTitle = orderTitle;
            return this;
        }
        /**
         * 设置商品详情
         */
        public AliPay.Builder setSubTitle(String subTitle) {
            this.subTitle = subTitle;
            return this;
        }
        /**
         * 设置商品价格
         */
        public AliPay.Builder setPrice(String price) {
            this.price = price;
            return this;
        }
        /**
         * 设置支付宝支付成功后通知的地址，可以填写你公司的地址
         */
        public AliPay.Builder setNotifyURL(String notifyURL) {
            this.notifyURL = notifyURL;
            return this;
        }


        @SuppressLint("HandlerLeak")
        private Handler mHandler = new Handler() {
            @SuppressWarnings("unused")
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 6406: {
                        Log.i("payresult",msg.obj.toString());

                        PayResult payResult = new PayResult((Map<String,String>) msg.obj);
                        /**
                         * 同步返回的结果必须放置到服务端进行验证（验证的规则请看https://doc.open.alipay.com/doc2/
                         * detail.htm?spm=0.0.0.0.xdvAU6&treeId=59&articleId=103665&
                         * docType=1) 建议商户依赖异步通知
                         */
                        String resultInfo = payResult.getResult();// 同步返回需要验证的信息

                        String resultStatus = payResult.getResultStatus();
                        // 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
                        if (TextUtils.equals(resultStatus, "9000")) {
                            if (mPayCallBackListener != null) {
                                mPayCallBackListener.onPayCallBack(9000, "9000", "支付成功");
                            }
                        } else {
                            // 判断resultStatus 为非"9000"则代表可能支付失败
                            // "8000"代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                            if (TextUtils.equals(resultStatus, "8000")
                                    ) {
                                if (mPayCallBackListener != null) {
                                    mPayCallBackListener.onPayCallBack(8000, "8000", "支付结果确认中");
                                }

                            } else {
                                // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                                if (mPayCallBackListener != null) {
                                    mPayCallBackListener.onPayCallBack(0, "0", "支付失败");
                                }
                            }
                        }
                        break;
                    }
                    default:
                        break;
                }
            }
        };


        public void pay() {
            if (TextUtils.isEmpty(PARTNER) || TextUtils.isEmpty(RSA_PRIVATE) || TextUtils.isEmpty(SELLER)) {
                new AlertDialog.Builder(mActivity).setTitle("警告").setMessage("需要配置PARTNER | RSA_PRIVATE| SELLER")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialoginterface, int i) {
                                //
                                mActivity.finish();
                            }
                        }).show();
                return;
            }
            String orderInfo = getOrderInfo(orderTitle, subTitle, price, notifyURL);

            /**
             * 特别注意，这里的签名逻辑需要放在服务端，切勿将私钥泄露在代码中！
             */
            String sign = sign(orderInfo);
            try {
                /**
                 * 仅需对sign 做URL编码
                 */
                sign = URLEncoder.encode(sign, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            /**
             * 完整的符合支付宝参数规范的订单信息
             */
            final String payInfo = orderInfo + "&sign=\"" + sign + "\"&" + getSignType();

            Log.i("sign",sign);
            Runnable payRunnable = new Runnable() {

                @Override
                public void run() {
                    // 构造PayTask 对象
                    PayTask alipay = new PayTask(mActivity);
                   // Map<String,String> payInfoMap=OrderInfoUtil2_0.buildAuthInfoMap();
                    //String info=OrderInfoUtil2_0.buildOrderParam(payInfoMap);

                    // 调用支付接口，获取支付结果
                    Map<String,String> result =alipay.payV2(payInfo,true);
                            //= alipay.pay(payInfo, true);

                    Message msg = new Message();
                    msg.what = SDK_PAY_FLAG;
                    msg.obj = result;
                    mHandler.sendMessage(msg);
                }
            };

            // 必须异步调用
            Thread payThread = new Thread(payRunnable);
            payThread.start();

        }

        public void setPayCallBackListener(PayCallBackListener listener) {
            this.mPayCallBackListener = listener;
        }

        private PayCallBackListener mPayCallBackListener;

        public interface PayCallBackListener {
            void onPayCallBack(int status, String resultStatus, String progress);
        }


        /**
         * 创建订单信息
         */
        private String getOrderInfo(String subject, String body, String price, String notifyURL) {

            // 签约合作者身份ID
            String orderInfo = "partner=" + "\"" + PARTNER + "\"";

            // 签约卖家支付宝账号
            orderInfo += "&seller_id=" + "\"" + SELLER + "\"";

            // 商户网站唯一订单号
            orderInfo += "&out_trade_no=" + "\"" + getOutTradeNo() + "\"";

            // 商品名称
            orderInfo += "&subject=" + "\"" + subject + "\"";

            // 商品详情
            orderInfo += "&body=" + "\"" + body + "\"";

            // 商品金额
            orderInfo += "&total_fee=" + "\"" + price + "\"";

            // 服务器异步通知页面路径
            orderInfo += "&notify_url=" + "\"" + notifyURL + "\"";

            // 服务接口名称， 固定值
            orderInfo += "&service=\"mobile.securitypay.pay\"";

            // 支付类型， 固定值
            orderInfo += "&payment_type=\"1\"";

            // 参数编码， 固定值
            orderInfo += "&_input_charset=\"utf-8\"";

            // 设置未付款交易的超时时间
            // 默认30分钟，一旦超时，该笔交易就会自动被关闭。
            // 取值范围：1m～15d。
            // m-分钟，h-小时，d-天，1c-当天（无论交易何时创建，都在0点关闭）。
            // 该参数数值不接受小数点，如1.5h，可转换为90m。
            orderInfo += "&it_b_pay=\"30m\"";

            // extern_token为经过快登授权获取到的alipay_open_id,带上此参数用户将使用授权的账户进行支付
            // orderInfo += "&extern_token=" + "\"" + extern_token + "\"";

            // 支付宝处理完请求后，当前页面跳转到商户指定页面的路径，可空
            orderInfo += "&return_url=\"m.alipay.com\"";

            // 调用银行卡支付，需配置此参数，参与签名， 固定值 （需要签约《无线银行卡快捷支付》才能使用）
            // orderInfo += "&paymethod=\"expressGateway\"";

            return orderInfo;
        }

        /**
         * 生成商户订单号，该值在商户端应保持唯一（可自定义格式规范）
         */
        private String getOutTradeNo() {
            SimpleDateFormat format = new SimpleDateFormat("MMddHHmmss", Locale.getDefault());
            Date date = new Date();
            String key = format.format(date);

            Random r = new Random();
            key = key + r.nextInt();
            key = key.substring(0, 15);
            return key;
        }

        /**
         * sign the order info. 对订单信息进行签名
         *
         * @param content 待签名订单信息
         */
        private String sign(String content) {
             SignUtils.sign(content, RSA_PRIVATE,true);
            return RSA_PRIVATE;
        }

        /**
         * 获取签名方式
         */
        private String getSignType() {
            return "sign_type=\"RSA\"";
        }
    }

    // 商户PID
    private String PARTNER="";
    // 商户收款账号
    private String SELLER="";
    // 商户私钥，pkcs8格式
    private String RSA_PRIVATE="";
    // 支付宝公钥
    private String RSA_PUBLIC="";

    private String orderTitle="";
    private String subTitle="";
    private String price="";
    private String notifyURL="";
    private Builder payData;

    public Builder build(Activity activity) {
        payData = new Builder(activity);
        payData.setPARTNER(PARTNER);
        payData.setSELLER(SELLER);
        payData.setRSA_PRIVATE(RSA_PRIVATE);
        payData.setRSA_PUBLIC(RSA_PUBLIC);
        payData.setOrderTitle(orderTitle);
        payData.setSubTitle(subTitle);
        payData.setPrice(price);
        payData.setNotifyURL(notifyURL);
        return payData;
    }

    public AliPay setPARTNER(String PARTNER) {
        this.PARTNER = PARTNER;
        return this;
    }

    public AliPay setSELLER(String SELLER) {
        this.SELLER = SELLER;
        return this;
    }

    public AliPay setRSA_PRIVATE(String RSA_PRIVATE) {
        this.RSA_PRIVATE = RSA_PRIVATE;
        return this;
    }

    public AliPay setRSA_PUBLIC(String RSA_PUBLIC) {
        this.RSA_PUBLIC = RSA_PUBLIC;
        return this;
    }

    public AliPay setOrderTitle(String orderTitle) {
        this.orderTitle = orderTitle;
        return this;
    }

    public AliPay setSubTitle(String subTitle) {
        this.subTitle = subTitle;
        return this;
    }

    public AliPay setPrice(String price) {
        this.price = price;
        return this;
    }

    public AliPay setNotifyURL(String notifyURL) {
        this.notifyURL = notifyURL;
        return this;
    }

    public void pay() {
        if (payData != null) {
            payData.pay();
        }
    }
}
