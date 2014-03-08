package com.nexusy.virgo.android;

import java.util.HashMap;
import java.util.Map;

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

import com.nexusy.virgo.android.http.UrlConstants;
import com.nexusy.virgo.android.http.VirgoHttpClient;
import com.nexusy.virgo.android.util.NetworkUtil;

public class LoginActivity extends Activity {

    private Button loginButton;
    private Button signupButton;
    private EditText usernameEditText;
    private EditText passwordEditText;

    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginButton = (Button) findViewById(R.id.login);
        loginButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();
                if (username == null || "".equals(username)) {
                    Toast.makeText(LoginActivity.this, R.string.username, Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password == null || "".equals(password)) {
                    Toast.makeText(LoginActivity.this, R.string.password, Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!NetworkUtil.isConnected(LoginActivity.this)){
                    Toast.makeText(LoginActivity.this, R.string.networkunavailable, Toast.LENGTH_SHORT).show();
                    return;
                }
                dialog = new Dialog(LoginActivity.this, R.style.dialog);
                dialog.setContentView(R.layout.loading_dialog);
                TextView tv = (TextView) dialog.findViewById(R.id.dialog_content);
                tv.setText(R.string.logining);
                dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();
                new LoginTask().execute(username, password);
            }
        });

        signupButton = (Button) findViewById(R.id.signup);
        signupButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();
                if (username == null || "".equals(username)) {
                    Toast.makeText(LoginActivity.this, R.string.username, Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password == null || "".equals(password)) {
                    Toast.makeText(LoginActivity.this, R.string.password, Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!NetworkUtil.isConnected(LoginActivity.this)){
                    Toast.makeText(LoginActivity.this, R.string.networkunavailable, Toast.LENGTH_SHORT).show();
                    return;
                }
                dialog = new Dialog(LoginActivity.this, R.style.dialog);
                dialog.setContentView(R.layout.loading_dialog);
                TextView tv = (TextView) dialog.findViewById(R.id.dialog_content);
                tv.setText(R.string.logining);
                dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();
                new SignupTask().execute(username, password);
            }
        });

        usernameEditText = (EditText) findViewById(R.id.username);
        passwordEditText = (EditText) findViewById(R.id.password);
    }

    private class LoginTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            Map<String, String> parameters = new HashMap<String, String>();
            parameters.put("j_username", params[0]);
            parameters.put("j_password", params[1]);
            return VirgoHttpClient.parseHttpResponseToString(VirgoHttpClient.post(UrlConstants.LOGIN_URL, parameters));
        }

        @Override
        protected void onPostExecute(String result) {
            dialog.dismiss();
            if ("0".equals(result)) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                finish();
                startActivity(intent);
            } else {
                Toast.makeText(LoginActivity.this, R.string.loginfailed, Toast.LENGTH_LONG).show();
            }
        }

    }
    
    private class SignupTask extends AsyncTask<String, Void, String> {
        
        @Override
        protected String doInBackground(String... params) {
            Map<String, String> parameters = new HashMap<String, String>();
            parameters.put("j_username", params[0]);
            parameters.put("j_password", params[1]);
            return VirgoHttpClient.parseHttpResponseToString(VirgoHttpClient.post(UrlConstants.SIGN_URL, parameters));
        }
        
        @Override
        protected void onPostExecute(String result) {
            dialog.dismiss();
            if ("0".equals(result)) {
                String username = usernameEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();
                new LoginTask().execute(username, password);
            } else if("2".equals(result)){
                Toast.makeText(LoginActivity.this, R.string.samename, Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(LoginActivity.this, R.string.signupfailed, Toast.LENGTH_LONG).show();
            }
        }
        
    }

}