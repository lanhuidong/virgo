package com.nexusy.virgo.android;

import java.util.HashMap;
import java.util.Map;

import com.nexusy.virgo.android.http.UrlConstants;
import com.nexusy.virgo.android.http.VirgoHttpClient;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
                dialog = new Dialog(LoginActivity.this, R.style.dialog);
                dialog.setContentView(R.layout.loading_dialog);
                dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();
                login(username, password);
            }
        });
        
        signupButton = (Button) findViewById(R.id.signup);
        signupButton.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                Toast.makeText(LoginActivity.this, username+password, Toast.LENGTH_LONG).show();
            }
        });
        
        usernameEditText = (EditText) findViewById(R.id.username);
        passwordEditText = (EditText) findViewById(R.id.password);
    }
    
    private void login(final String username, final String password){
        new Thread(){

            @Override
            public void run() {
                Looper.prepare();
                try {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("j_username", username);
                    params.put("j_password", password);
                    params.put("login_agent", "android");
                    String result = VirgoHttpClient.parseHttpResponseToString(VirgoHttpClient.post(UrlConstants.LOGIN_URL, params));
                    dialog.dismiss();
                    if("0".equals(result)){
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        finish();
                        startActivity(intent);
                    } else {
                        Toast.makeText(LoginActivity.this, R.string.loginfailed, Toast.LENGTH_LONG).show();
                        Looper.loop();
                        Looper.myLooper().quit();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    dialog.dismiss();
                    Toast.makeText(LoginActivity.this, R.string.connectionfailed, Toast.LENGTH_LONG).show();
                    Looper.loop();
                    Looper.myLooper().quit();
                }
                Looper.myLooper().quit();
            }
            
        }.start();
    }

}
