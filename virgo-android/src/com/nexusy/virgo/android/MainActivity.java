package com.nexusy.virgo.android;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
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
    private Button query;
    private LinearLayout queryPanel;
    private Button search;

    private EditText from;
    private EditText to;

    private DatePickerDialog dialogFrom;
    private DatePickerDialog dialogTo;

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

        lv = (ListView) findViewById(R.id.bills);
        adapter = new SimpleAdapter(MainActivity.this, billsMap, R.layout.bills,
                new String[] { "date", "income", "pay" }, new int[] { R.id.bill_date, R.id.bill_income, R.id.bill_pay });
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View view, int arg2, long arg3) {
                Intent intent = new Intent(MainActivity.this, BillOneDayActivity.class);
                intent.putExtra("bill", (Serializable) billsMap.get(arg2).get("bill"));
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        String fromDate = from.getText().toString();
        String toDate = to.getText().toString();
        new QueryBillTask().execute(fromDate, toDate);
    }

    private class QueryBillTask extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... params) {
            Map<String, String> parameters = new HashMap<String, String>();
            parameters.put("from", params[0]);
            parameters.put("to", params[1]);
            List<Bill> bills = new JSONToBean().parseHttpResponse(VirgoHttpClient.post(UrlConstants.QUERY_BILL_URL,
                    parameters));
            billsMap.clear();
            Map<String, Object> header = new HashMap<String, Object>();
            header.put("date", "日期");
            header.put("income", "收入");
            header.put("pay", "支出");
            billsMap.add(header);
            NumberFormat nf = NumberFormat.getCurrencyInstance();
            for (Bill bill : bills) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("date", bill.getDate());
                map.put("bill", bill);
                double income = 0d;
                double pay = 0d;
                for (BillItem item : bill.getItems()) {
                    if (item.getType() == BillItemType.INCOME) {
                        income += item.getMoney();
                    } else {
                        pay += item.getMoney();
                    }
                }
                map.put("income", nf.format(income));
                map.put("pay", nf.format(pay));
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