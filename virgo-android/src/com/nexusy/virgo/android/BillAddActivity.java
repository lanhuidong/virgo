package com.nexusy.virgo.android;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import android.annotation.SuppressLint;
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
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.nexusy.virgo.android.http.DataParser;
import com.nexusy.virgo.android.http.UrlConstants;
import com.nexusy.virgo.android.http.VirgoHttpClient;
import com.nexusy.virgo.android.model.BillItemType;

public class BillAddActivity extends Activity {

    private String TAG = BillAddActivity.class.getName();

    private EditText date;
    private EditText item;
    private EditText money;
    private RadioGroup type;

    private Button add;

    private DatePickerDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate");
        setContentView(R.layout.activity_bill_add);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
            VirgoApplication app = (VirgoApplication) getApplication();
            app.addActivity(this);
        }

        date = (EditText) findViewById(R.id.date);
        final Calendar c = Calendar.getInstance();
        date.setText(String.format("%1$tF", c.getTime()));
        date.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (dialog == null) {
                    dialog = new DatePickerDialog(BillAddActivity.this, new OnDateSetListener() {

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
        type = (RadioGroup) findViewById(R.id.type);

        add = (Button) findViewById(R.id.add);
        add.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                String dateString = date.getText().toString();
                String itemString = item.getText().toString().trim();
                String moneyString = money.getText().toString().trim();
                int id = type.getCheckedRadioButtonId();
                String typeString;
                if (id == R.id.pay) {
                    typeString = BillItemType.PAY.toString();
                } else {
                    typeString = BillItemType.INCOME.toString();
                }
                new BillAddTask().execute(dateString, itemString, moneyString, typeString);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
            VirgoApplication app = (VirgoApplication) getApplication();
            app.removeActivity(this);
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
        protected String doInBackground(String... arg0) {
            Map<String, String> params = new HashMap<String, String>();
            params.put("date", arg0[0]);
            params.put("item", arg0[1]);
            params.put("money", arg0[2]);
            params.put("type", arg0[3]);
            return new DataParser().parseHttpResponseToString(VirgoHttpClient.post(UrlConstants.ADD_BILL_URL, params));
        }

        @SuppressLint("InlinedApi")
        @Override
        protected void onPostExecute(String result) {
            if ("timeout".equals(result)) {
                Intent intent = new Intent(BillAddActivity.this, LoginActivity.class);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                }
                startActivity(intent);
                return;
            }
            if ("true".equals(result)) {
                item.setText("");
                money.setText("");
                Toast.makeText(BillAddActivity.this, R.string.billsuccess, Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(BillAddActivity.this, R.string.billfailed, Toast.LENGTH_LONG).show();
            }
        }
    }

}
