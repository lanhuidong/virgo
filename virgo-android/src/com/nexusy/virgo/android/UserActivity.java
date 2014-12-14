package com.nexusy.virgo.android;

import java.util.HashMap;
import java.util.Map;

import com.nexusy.virgo.android.http.DataParser;
import com.nexusy.virgo.android.http.UrlConstants;
import com.nexusy.virgo.android.http.VirgoHttpClient;
import com.nexusy.virgo.android.util.StringUtils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UserActivity extends Activity {

    private TextView menuList;
    private TextView menuBill;

    private EditText oldPasswordEt;
    private EditText newPasswordEt;

    private Button modifyBtn;

    private Dialog dialog;

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

        oldPasswordEt = (EditText) findViewById(R.id.old_password);
        newPasswordEt = (EditText) findViewById(R.id.new_password);

        modifyBtn = (Button) findViewById(R.id.modify);
        modifyBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                String oldPassword = oldPasswordEt.getText().toString().trim();
                String newPassword = newPasswordEt.getText().toString().trim();

                if (StringUtils.isBlank(newPassword)) {
                    Toast.makeText(UserActivity.this, R.string.password, Toast.LENGTH_SHORT).show();
                    return;
                }
                if (newPassword.length() < 6 || newPassword.length() > 20) {
                    Toast.makeText(UserActivity.this, R.string.password_length, Toast.LENGTH_SHORT).show();
                    return;
                }
                dialog = new Dialog(UserActivity.this, R.style.dialog);
                dialog.setContentView(R.layout.loading_dialog);
                TextView tv = (TextView) dialog.findViewById(R.id.dialog_content);
                tv.setText(R.string.modifying);
                dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();
                new ModifyPassowrdTask().execute(oldPassword, newPassword);
            }
        });
    }

    private class ModifyPassowrdTask extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if (dialog == null) {
                dialog = new Dialog(UserActivity.this, R.style.dialog);
                dialog.setContentView(R.layout.loading_dialog);
                TextView tv = (TextView) dialog.findViewById(R.id.dialog_content);
                tv.setText(R.string.adding);
                dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                dialog.setCanceledOnTouchOutside(false);
            }
            dialog.show();
        }

        @Override
        protected String doInBackground(String... arg0) {
            Map<String, String> params = new HashMap<String, String>();
            params.put("oldPassword", arg0[0]);
            params.put("newPassword", arg0[1]);
            return new DataParser().parseHttpResponseToString(VirgoHttpClient.post(UrlConstants.USER_MODIFY_PASSWORD,
                    params));
        }

        @Override
        protected void onPostExecute(String result) {
            dialog.dismiss();
            if ("timeout".equals(result)) {
                Intent intent = new Intent(UserActivity.this, LoginActivity.class);
                startActivity(intent);
                return;
            }
            if ("true".equals(result)) {
                Toast.makeText(UserActivity.this, R.string.modify_password_success, Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(UserActivity.this, R.string.modify_password_fail, Toast.LENGTH_LONG).show();
            }
        }
    }

}
