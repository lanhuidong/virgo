package com.nexusy.virgo.android;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.nexusy.virgo.android.http.DataParser;
import com.nexusy.virgo.android.http.UrlConstants;
import com.nexusy.virgo.android.http.VirgoHttpClient;
import com.nexusy.virgo.android.model.BillItemType;

public class BillActivity extends Activity {

    private String TAG = BillActivity.class.getName();
    
    private TextView menuList;
    private TextView menuUser;

    private BillAddTask billAddTask;

    private EditText date;
    private EditText item;
    private EditText money;
    private EditText remark;
    private RadioGroup type;
    private AtomicInteger lock = new AtomicInteger();

    private Button add;

    private DatePickerDialog dialog;

    private Dialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate");
        setContentView(R.layout.activity_bill);
        
        menuList = (TextView) findViewById(R.id.menu_list);
        menuList.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BillActivity.this, ListActivity.class);
                startActivity(intent);
            }
        });
        menuUser = (TextView) findViewById(R.id.menu_user);
        menuUser.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BillActivity.this, UserActivity.class);
                startActivity(intent);
            }
        });

        date = (EditText) findViewById(R.id.date);
        final Calendar c = Calendar.getInstance();
        date.setText(String.format("%1$tF", c.getTime()));
        date.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (dialog == null) {
                    dialog = new DatePickerDialog(BillActivity.this, new OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                            date.setText(year + "-" + String.format("%02d", (monthOfYear + 1)) + "-"
                                    + String.format("%02d", dayOfMonth));
                        }
                    }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DATE));
                }
                dialog.show();
            }
        });

        item = (EditText) findViewById(R.id.item);
        money = (EditText) findViewById(R.id.money);
        remark = (EditText) findViewById(R.id.remark);
        type = (RadioGroup) findViewById(R.id.type);

        add = (Button) findViewById(R.id.add);
        add.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (!lock.compareAndSet(0, 1)) {
                    return;
                }
                String dateString = date.getText().toString();
                String itemString = item.getText().toString().trim();
                if (itemString == null || "".equals(itemString)) {
                    Toast.makeText(BillActivity.this, R.string.itemempty, Toast.LENGTH_SHORT).show();
                }

                String moneyString = money.getText().toString().trim();
                if (moneyString == null || "".equals(moneyString)) {
                    Toast.makeText(BillActivity.this, R.string.moneyempty, Toast.LENGTH_SHORT).show();
                }
                String remarkString = remark.getText().toString().trim();
                int id = type.getCheckedRadioButtonId();
                String typeString;
                if (id == R.id.pay) {
                    typeString = BillItemType.PAY.toString();
                } else {
                    typeString = BillItemType.INCOME.toString();
                }
                billAddTask = new BillAddTask();
                billAddTask.execute(dateString, itemString, moneyString, typeString, remarkString);
            }
        }); 
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
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
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop");
    }

    private class BillAddTask extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if (loadingDialog == null) {
                loadingDialog = new Dialog(BillActivity.this, R.style.dialog);
                loadingDialog.setContentView(R.layout.loading_dialog);
                TextView tv = (TextView) loadingDialog.findViewById(R.id.dialog_content);
                tv.setText(R.string.adding);
                loadingDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                loadingDialog.setCanceledOnTouchOutside(false);
            }
            loadingDialog.show();
        }

        @Override
        protected String doInBackground(String... arg0) {
            try {
                Thread.sleep(1000*5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Map<String, String> params = new HashMap<String, String>();
            params.put("date", arg0[0]);
            params.put("item", arg0[1]);
            params.put("money", arg0[2]);
            params.put("type", arg0[3]);
            params.put("remark", arg0[4]);
            return new DataParser().parseHttpResponseToString(VirgoHttpClient.post(UrlConstants.ADD_BILL_URL, params));
        }

        @SuppressLint("InlinedApi")
        @Override
        protected void onPostExecute(String result) {
            loadingDialog.dismiss();
            lock.compareAndSet(1, 0);
            if ("timeout".equals(result)) {
                Intent intent = new Intent(BillActivity.this, LoginActivity.class);
                startActivity(intent);
                return;
            }
            if ("true".equals(result)) {
                item.setText("");
                money.setText("");
                Toast.makeText(BillActivity.this, R.string.billsuccess, Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(BillActivity.this, R.string.billfailed, Toast.LENGTH_LONG).show();
            }
        }
    }

}