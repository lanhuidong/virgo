package com.nexusy.virgo.android;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.nexusy.virgo.android.http.JSONToBean;
import com.nexusy.virgo.android.http.UrlConstants;
import com.nexusy.virgo.android.http.VirgoHttpClient;
import com.nexusy.virgo.android.model.Bill;
import com.nexusy.virgo.android.model.BillItem;
import com.nexusy.virgo.android.model.BillItemType;

public class MainActivity extends Activity {

    private Button add;

    private ListView lv;

    private SimpleAdapter adapter;

    private List<Map<String, Object>> billsMap = new ArrayList<Map<String, Object>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.activity_main);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.bill_title_bar);

        add = (Button) findViewById(R.id.add_button);
        add.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BillAddActivity.class);
                startActivity(intent);
            }
        });

        lv = (ListView) findViewById(R.id.bills);
        Map<String, Object> header = new HashMap<String, Object>();
        header.put("date", "日期");
        header.put("income", "收入");
        header.put("pay", "支出");
        billsMap.add(header);
        adapter = new SimpleAdapter(MainActivity.this, billsMap, R.layout.bills,
                new String[] { "date", "income", "pay" }, new int[] { R.id.bill_date, R.id.bill_income, R.id.bill_pay });
        lv.setAdapter(adapter);
        new QueryBillTask().execute();
    }

    private class QueryBillTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            Map<String, String> parameters = new HashMap<String, String>();
            List<Bill> bills = new JSONToBean().parseHttpResponse(VirgoHttpClient.post(UrlConstants.QUERY_BILL_URL,
                    parameters));
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
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            adapter.notifyDataSetChanged();
        }

    }

}