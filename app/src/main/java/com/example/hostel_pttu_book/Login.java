package com.example.hostel_pttu_book;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Login extends AppCompatActivity {

    public static String PREFS_NAME="MyPrefsFile";
    private Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login=findViewById(R.id.buttonL);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sp1 = getSharedPreferences(Login.PREFS_NAME,0);
                SharedPreferences.Editor editor1 = sp1.edit();

                editor1.putBoolean("hasLoggedIn",true);
                editor1.commit();

                startActivity(new Intent(Login.this,MainActivity.class));
                finish();


            }
        });
    }
}