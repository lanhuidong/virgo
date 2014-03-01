package com.nexusy.virgo.android;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.os.Looper;

import com.nexusy.virgo.android.http.UrlConstants;
import com.nexusy.virgo.android.http.VirgoHttpClient;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        queryBill();
    }

    private void queryBill() {
        new Thread() {

            @Override
            public void run() {
                Looper.prepare();
                Map<String, String> params = new HashMap<String, String>();
                String result = VirgoHttpClient.parseHttpResponseToString(VirgoHttpClient.post(
                        UrlConstants.QUERY_BILL_URL, params));
                System.out.println(result);
                Looper.myLooper().quit();
            }
        }.start();
    }

}
