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
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.SimpleExpandableListAdapter;

import com.nexusy.virgo.android.adapter.BillAdapter;
import com.nexusy.virgo.android.http.DataParser;
import com.nexusy.virgo.android.http.UrlConstants;
import com.nexusy.virgo.android.http.VirgoHttpClient;
import com.nexusy.virgo.android.model.Bill;
import com.nexusy.virgo.android.model.BillItem;
import com.nexusy.virgo.android.model.BillItemType;

public class MainActivity extends Activity {
    
    private String TAG = MainActivity.class.getName();

    private Button add;
    private Button query;
    private LinearLayout queryPanel;
    private Button search;

    private EditText from;
    private EditText to;

    private DatePickerDialog dialogFrom;
    private DatePickerDialog dialogTo;

    private ExpandableListView elv;

    private SimpleExpandableListAdapter adapter;

    private List<Map<String, Object>> billsMap = new ArrayList<Map<String, Object>>();
    private List<List<Map<String, Object>>> billMap = new ArrayList<List<Map<String, Object>>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate");
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.activity_main);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
            VirgoApplication app = (VirgoApplication) getApplication();
            app.addActivity(this);
        }
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.bill_title_bar);

        add = (Button) findViewById(R.id.add_button);
        add.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BillAddActivity.class);
                startActivity(intent);
            }
        });

        queryPanel = (LinearLayout) findViewById(R.id.query_panel);

        query = (Button) findViewById(R.id.query_button);
        query.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                queryPanel.setVisibility(View.VISIBLE);
            }
        });

        final Calendar cFrom = Calendar.getInstance();
        final Calendar cTo = Calendar.getInstance();
        cFrom.set(Calendar.DATE, 1);

        from = (EditText) findViewById(R.id.from);
        to = (EditText) findViewById(R.id.to);

        from.setText(String.format("%1$tF", cFrom.getTime()));
        to.setText(String.format("%1$tF", cTo.getTime()));

        from.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (dialogFrom == null) {
                    String[] fromString = from.getText().toString().split("-");
                    dialogFrom = new DatePickerDialog(MainActivity.this, new OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                            from.setText(year + "-" + String.format("%02d", (monthOfYear + 1)) + "-"
                                    + String.format("%02d", dayOfMonth));
                        }
                    }, Integer.valueOf(fromString[0]), Integer.valueOf(fromString[1]) - 1, Integer
                            .valueOf(fromString[2]));
                }
                dialogFrom.show();
            }
        });

        to.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (dialogTo == null) {
                    String[] toString = to.getText().toString().split("-");
                    dialogTo = new DatePickerDialog(MainActivity.this, new OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                            to.setText(year + "-" + String.format("%02d", (monthOfYear + 1)) + "-"
                                    + String.format("%02d", dayOfMonth));
                        }
                    }, Integer.valueOf(toString[0]), Integer.valueOf(toString[1]) - 1, Integer.valueOf(toString[2]));
                }
                dialogTo.show();
            }
        });

        search = (Button) findViewById(R.id.search);
        search.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                queryPanel.setVisibility(View.GONE);
                String fromDate = from.getText().toString();
                String toDate = to.getText().toString();
                new QueryBillTask().execute(fromDate, toDate);
            }
        });

        elv = (ExpandableListView) findViewById(R.id.bills);
        adapter = new BillAdapter(MainActivity.this, billsMap, R.layout.bills,
                new String[] { "date", "income", "pay" },
                new int[] { R.id.bill_date, R.id.bill_income, R.id.bill_pay }, billMap, R.layout.bill, new String[] {
                        "item", "money" }, new int[] { R.id.bill_item, R.id.bill_money });
        elv.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart");
        String fromDate = from.getText().toString();
        String toDate = to.getText().toString();
        new QueryBillTask().execute(fromDate, toDate);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
            VirgoApplication app = (VirgoApplication) getApplication();
            app.addActivity(this);
        }
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

    private class QueryBillTask extends AsyncTask<String, Void, Boolean> {

        @Override
        protected Boolean doInBackground(String... params) {
            Map<String, String> parameters = new HashMap<String, String>();
            parameters.put("from", params[0]);
            parameters.put("to", params[1]);
            HttpResponse response = VirgoHttpClient.post(UrlConstants.QUERY_BILL_URL, parameters);
            List<Bill> bills = new DataParser().parseHttpResponse(response);
            if(bills == null){
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
            if(result){
                adapter.notifyDataSetChanged();
            } else {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB){
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                }
                startActivity(intent);
                finish();
            }
        }

    }

}