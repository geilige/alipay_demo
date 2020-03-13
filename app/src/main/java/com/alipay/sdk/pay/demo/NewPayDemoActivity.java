package com.alipay.sdk.pay.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.alipay.sdk.pay.demo.util.NetUtils;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;

public class NewPayDemoActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        newPayV2();
    }

    public void newPayV2() {
        try {
            Intent intent = getIntent();
            String payUrl = intent.getStringExtra("payUrl");
            if (payUrl == null) {
                Toast.makeText(this, "payUrl为空", Toast.LENGTH_LONG).show();
                return;
            }
            final String orderInfo = payUrl;

            final Runnable payRunnable = new Runnable() {

                @Override
                public void run() {
                    PayTask alipay = new PayTask(NewPayDemoActivity.this);
                    Map<String, String> result = alipay.payV2(orderInfo, true);
                    Log.i("msp", result.toString());
                }
            };

            // 必须异步调用
            Thread payThread = new Thread(payRunnable);
            payThread.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
