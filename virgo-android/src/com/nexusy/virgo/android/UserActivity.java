package com.nexusy.virgo.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class UserActivity extends Activity {
    
    private TextView menuList;
    private TextView menuBill;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        
        menuList = (TextView) findViewById(R.id.menu_list);
        menuList.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserActivity.this, ListActivity.class);
                startActivity(intent);
            }
        });
        menuBill = (TextView) findViewById(R.id.menu_bill);
        menuBill.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserActivity.this, BillActivity.class);
                startActivity(intent);
            }
        });
    }

}
