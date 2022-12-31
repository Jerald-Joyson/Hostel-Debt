package com.example.hostel_pttu_book;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class LogoActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT=3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences sharedPre = getSharedPreferences(Login.PREFS_NAME,0);
                boolean hasLoggedIn= sharedPre.getBoolean("hasLoggedIn",false);

                if (hasLoggedIn){
                    Intent int1 = new Intent(LogoActivity.this,MainActivity.class);
                    startActivity(int1);
                    finish();
                }
                else {
                    Intent int1=new Intent(LogoActivity.this,Login.class);
                    startActivity(int1);
                    finish();
                }
            }
        },SPLASH_TIME_OUT);
    }
}