package com.nexusy.virgo.android;

import java.util.Date;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

public class BillAddActivity extends Activity {

    private EditText dateEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_add);
        dateEt = (EditText) findViewById(R.id.date);
        dateEt.setText(String.format("%1$tF", new Date()));
        dateEt.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(BillAddActivity.this, R.string.date, Toast.LENGTH_LONG).show();
            }
        });
    }

}
