package com.nexusy.virgo.android;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Window;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.nexusy.virgo.android.http.JSONToBean;
import com.nexusy.virgo.android.http.UrlConstants;
import com.nexusy.virgo.android.http.VirgoHttpClient;
import com.nexusy.virgo.android.model.Bill;
import com.nexusy.virgo.android.model.BillItem;
import com.nexusy.virgo.android.model.BillItemType;

public class MainActivity extends Activity {

    private ListView lv;

    private SimpleAdapter adapter;

    private List<Map<String, Object>> billsMap = new ArrayList<Map<String, Object>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.activity_main);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.bill_title_bar);
        lv = (ListView) findViewById(R.id.bills);
        Map<String, Object> header = new HashMap<String, Object>();
        header.put("date", "日期");
        header.put("income", "收入");
        header.put("pay", "支出");
        billsMap.add(header);
        adapter = new SimpleAdapter(MainActivity.this, billsMap, R.layout.bills,
                new String[] { "date", "income", "pay" }, new int[] { R.id.bill_date, R.id.bill_income, R.id.bill_pay });
        lv.setAdapter(adapter);
        queryBill();
    }

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            adapter.notifyDataSetChanged();
        }

    };

    private void queryBill() {
        new Thread() {

            @Override
            public void run() {
                Map<String, String> params = new HashMap<String, String>();
                List<Bill> bills = new JSONToBean().parseHttpResponse(VirgoHttpClient.post(UrlConstants.QUERY_BILL_URL,
                        params));
                for (Bill bill : bills) {
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("date", bill.getDate());
                    double income = 0d;
                    double pay = 0d;
                    for (BillItem item : bill.getItems()) {
                        if (item.getType() == BillItemType.INCOME) {
                            income += item.getMoney();
                        } else {
                            pay += item.getMoney();
                        }
                    }
                    map.put("income", income);
                    map.put("pay", pay);
                    billsMap.add(map);
                    Message message = handler.obtainMessage();
                    handler.sendMessage(message);
                }
            }
        }.start();
    }

}
