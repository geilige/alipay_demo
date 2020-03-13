package com.alipay.sdk.pay.demo.controller;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.util.Log;
import android.widget.Button;

import com.alipay.sdk.app.PayTask;
import com.alipay.sdk.pay.demo.NewPayDemoActivity;
import com.alipay.sdk.pay.demo.PayDemoActivity;
import com.alipay.sdk.pay.demo.R;
import com.yanzhenjie.andserver.annotation.GetMapping;
import com.yanzhenjie.andserver.annotation.PostMapping;
import com.yanzhenjie.andserver.annotation.RequestParam;
import com.yanzhenjie.andserver.annotation.RestController;
import com.yanzhenjie.andserver.http.HttpRequest;
import com.yanzhenjie.andserver.http.HttpResponse;

import java.util.Map;

import static com.alipay.sdk.pay.demo.PayDemoActivity.getContext;

@RestController
public class QunarController {

    @GetMapping("/")
    public String Test(HttpRequest request, HttpResponse response) {
        return "Hello World!";
    }

    @PostMapping("/startAliPay")
    public String StartAliPay(HttpRequest request, HttpResponse response, @RequestParam(name = "req") String req) {
        System.out.println(req);
        String result = "";

        try {
//            方式一
//            Intent intent = new Intent();
//            intent.setComponent(new ComponentName("com.alipay.sdk.pay.demo","com.alipay.sdk.pay.demo.NewPayDemoActivity"));
//            方式二
            Intent intent = new Intent(PayDemoActivity.getContext(), NewPayDemoActivity.class);
            intent.putExtra("payUrl", req);
            getContext().startActivity(intent);
            ((Activity) getContext()).finish();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
