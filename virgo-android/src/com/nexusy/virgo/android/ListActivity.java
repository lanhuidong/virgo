package com.nexusy.virgo.android;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;
import android.widget.TextView;

import com.nexusy.virgo.android.adapter.BillAdapter;
import com.nexusy.virgo.android.http.DataParser;
import com.nexusy.virgo.android.http.UrlConstants;
import com.nexusy.virgo.android.http.VirgoHttpClient;
import com.nexusy.virgo.android.model.Bill;
import com.nexusy.virgo.android.model.BillItem;
import com.nexusy.virgo.android.model.BillItemType;

public class ListActivity extends Activity {

    private String TAG = ListActivity.class.getName();
    
    private Calendar from;
    private Calendar to;

    private TextView totalIncomeTV;
    private TextView totalPayTV;
    private double totalIncome;
    private double totalPay;

    private float x;

    private Dialog loadingDialog;

    private ExpandableListView elv;

    private SimpleExpandableListAdapter adapter;

    private List<Map<String, Object>> billsMap = new ArrayList<Map<String, Object>>();
    private List<List<Map<String, Object>>> billMap = new ArrayList<List<Map<String, Object>>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate");
        setContentView(R.layout.activity_list);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
            VirgoApplication app = (VirgoApplication) getApplication();
            app.addActivity(this);
        }

        totalIncomeTV = (TextView) findViewById(R.id.total_income);
        totalPayTV = (TextView) findViewById(R.id.total_pay);

        elv = (ExpandableListView) findViewById(R.id.bills);
        adapter = new BillAdapter(ListActivity.this, billsMap, R.layout.bills,
                new String[] { "date", "income", "pay" },
                new int[] { R.id.bill_date, R.id.bill_income, R.id.bill_pay }, billMap, R.layout.bill, new String[] {
                        "item", "money" }, new int[] { R.id.bill_item, R.id.bill_money });
        elv.setAdapter(adapter);
        elv.setOnTouchListener(new OnTouchListener() {
            
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                boolean result = false;
                switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    x = event.getX();
                    break;
                case MotionEvent.ACTION_UP:
                    float tmp = event.getX();
                    if(x - tmp > 200){
                        from.add(Calendar.MONTH, 1);
                        to.set(Calendar.DATE, 1);
                        to.add(Calendar.MONTH, 2);    
                        to.add(Calendar.DATE, -1);
                        String fromString = String.format("%1$tF", from.getTime());
                        String toString = String.format("%1$tF", to.getTime());
                        loadingDialog = new Dialog(ListActivity.this, R.style.dialog);
                        loadingDialog.setContentView(R.layout.loading_dialog);
                        TextView tv = (TextView) loadingDialog.findViewById(R.id.dialog_content);
                        tv.setText(R.string.loading);
                        loadingDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                        loadingDialog.setCanceledOnTouchOutside(false);
                        loadingDialog.show();
                        new QueryBillTask().execute(fromString, toString);
                        result = true;
                    } else if(tmp -x > 200){
                        from.add(Calendar.MONTH, -1);
                        to.set(Calendar.DATE, 1); 
                        to.add(Calendar.DATE, -1);             
                        String fromString = String.format("%1$tF", from.getTime());
                        String toString = String.format("%1$tF", to.getTime());
                        loadingDialog = new Dialog(ListActivity.this, R.style.dialog);
                        loadingDialog.setContentView(R.layout.loading_dialog);
                        TextView tv = (TextView) loadingDialog.findViewById(R.id.dialog_content);
                        tv.setText(R.string.loading);
                        loadingDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                        loadingDialog.setCanceledOnTouchOutside(false);
                        loadingDialog.show();
                        new QueryBillTask().execute(fromString, toString);
                        result = true;
                    }
                    break;
                default:
                    break;
                }
                return result;
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart");
        from = Calendar.getInstance();
        from.set(Calendar.DATE, 1);
        to = Calendar.getInstance();
        to.set(Calendar.DATE, 1);
        to.set(Calendar.MONTH, to.get(Calendar.MONTH)+1);
        to.add(Calendar.DATE, -1);
        String fromString = String.format("%1$tF", from.getTime());
        String toString = String.format("%1$tF", to.getTime());
        loadingDialog = new Dialog(ListActivity.this, R.style.dialog);
        loadingDialog.setContentView(R.layout.loading_dialog);
        TextView tv = (TextView) loadingDialog.findViewById(R.id.dialog_content);
        tv.setText(R.string.loading);
        loadingDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        loadingDialog.setCanceledOnTouchOutside(false);
        loadingDialog.show();
        new QueryBillTask().execute(fromString, toString);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
            VirgoApplication app = (VirgoApplication) getApplication();
            app.addActivity(this);
        }
        loadingDialog.dismiss();
        Log.i(TAG, "onDestroy");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop");
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
        case MotionEvent.ACTION_DOWN:
            x = event.getY();
            break;
        case MotionEvent.ACTION_UP:
            float tmp = event.getY();
            if(x - tmp > 200){
                from.set(Calendar.MONTH, from.get(Calendar.MONTH)-1);
                to.set(Calendar.MONTH, to.get(Calendar.MONTH)-1);                
            } else if(tmp -x > 200){
                from.set(Calendar.MONTH, from.get(Calendar.MONTH)+1);
                to.set(Calendar.MONTH, to.get(Calendar.MONTH)+1);                
            }
            String fromString = String.format("%1$tF", from.getTime());
            String toString = String.format("%1$tF", to.getTime());
            loadingDialog = new Dialog(ListActivity.this, R.style.dialog);
            loadingDialog.setContentView(R.layout.loading_dialog);
            TextView tv = (TextView) loadingDialog.findViewById(R.id.dialog_content);
            tv.setText(R.string.loading);
            loadingDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            loadingDialog.setCanceledOnTouchOutside(false);
            loadingDialog.show();
            System.out.println(fromString+"-"+toString);
            new QueryBillTask().execute(fromString, toString);
            break;
        default:
            break;
        }
        return super.onTouchEvent(event);
    }

    private class QueryBillTask extends AsyncTask<String, Void, Boolean> {

        @Override
        protected Boolean doInBackground(String... params) {
            Map<String, String> parameters = new HashMap<String, String>();
            parameters.put("from", params[0]);
            parameters.put("to", params[1]);
            HttpResponse response = VirgoHttpClient.post(UrlConstants.QUERY_BILL_URL, parameters);
            List<Bill> bills = new DataParser().parseHttpResponse(response);
            if (bills == null) {
                return false;
            }
            billsMap.clear();
            billMap.clear();
            Map<String, Object> header = new HashMap<String, Object>();
            header.put("date", "日期");
            header.put("income", "收入");
            header.put("pay", "支出");
            billsMap.add(header);
            List<Map<String, Object>> headerChild = new ArrayList<Map<String, Object>>();
            billMap.add(headerChild);
            NumberFormat nf = NumberFormat.getCurrencyInstance();
            totalIncome = 0d;
            totalPay = 0d;
            for (Bill bill : bills) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("date", String.format("%1$tF", bill.getDate()));
                map.put("bill", bill);
                double income = 0d;
                double pay = 0d;

                List<Map<String, Object>> child = new ArrayList<Map<String, Object>>();
                for (BillItem item : bill.getItems()) {
                    Map<String, Object> m = new HashMap<String, Object>();
                    m.put("item", item.getItem());
                    m.put("money", nf.format(item.getMoney()));
                    m.put("type", item.getType());
                    m.put("id", item.getId());
                    child.add(m);
                    if (item.getType() == BillItemType.INCOME) {
                        income += item.getMoney();
                    } else {
                        pay += item.getMoney();
                    }
                }
                totalIncome += income;
                totalPay += pay;
                billMap.add(child);
                map.put("income", nf.format(income));
                map.put("pay", nf.format(pay));
                billsMap.add(map);
            }
            return true;
        }

        @TargetApi(Build.VERSION_CODES.HONEYCOMB)
        @Override
        protected void onPostExecute(Boolean result) {
            loadingDialog.dismiss();
            if (result) {
                NumberFormat nf = NumberFormat.getCurrencyInstance();
                adapter.notifyDataSetChanged();
                totalIncomeTV.setText(nf.format(totalIncome));
                totalPayTV.setText(nf.format(totalPay));
            } else {
                Intent intent = new Intent(ListActivity.this, LoginActivity.class);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                }
                startActivity(intent);
                finish();
            }
        }

    }

}