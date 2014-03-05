package com.nexusy.virgo.android;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.nexusy.virgo.android.http.UrlConstants;
import com.nexusy.virgo.android.http.VirgoHttpClient;
import com.nexusy.virgo.android.model.BillItemType;

public class BillAddActivity extends Activity {

    private EditText date;
    private EditText item;
    private EditText money;
    private RadioGroup type;

    private Button add;

    private DatePickerDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_add);

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
                            date.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
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

    private class BillAddTask extends AsyncTask<String, Void, Boolean> {

        @Override
        protected Boolean doInBackground(String... arg0) {
            Map<String, String> params = new HashMap<String, String>();
            params.put("date", arg0[0]);
            params.put("item", arg0[1]);
            params.put("money", arg0[2]);
            params.put("type", arg0[3]);
            Boolean result = Boolean.valueOf(VirgoHttpClient.parseHttpResponseToString(VirgoHttpClient.post(
                    UrlConstants.ADD_BILL_URL, params)));
            return result;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if (result) {
                item.setText("");
                money.setText("");
                Toast.makeText(BillAddActivity.this, R.string.billsuccess, Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(BillAddActivity.this, R.string.billfailed, Toast.LENGTH_LONG).show();
            }
        }

    }

}
