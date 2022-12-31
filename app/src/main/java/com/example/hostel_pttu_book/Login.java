package com.example.hostel_pttu_book;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Login extends AppCompatActivity {

    EditText p1,p2,p3,p4;
    public static String PREFS_NAME="MyPrefsFile";
    private Button login;

    public static final String p1TEXT = "p1Text";
    public static final String p2TEXT = "p2Text";
    public static final String p3TEXT = "p3Text";
    public static final String p4TEXT = "p4Text";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login=findViewById(R.id.buttonL);
        p1=(EditText) findViewById(R.id.tVp1);
        p2=(EditText) findViewById(R.id.tVp2);
        p3=(EditText) findViewById(R.id.tVp3);
        p4=(EditText) findViewById(R.id.tVp4);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sp1 = getSharedPreferences(Login.PREFS_NAME,0);
                SharedPreferences.Editor editor1 = sp1.edit();

                editor1.putString(p1TEXT, p1.getText().toString());
                editor1.putString(p2TEXT, p2.getText().toString());
                editor1.putString(p3TEXT, p3.getText().toString());
                editor1.putString(p4TEXT, p4.getText().toString());

                editor1.putBoolean("hasLoggedIn",true);
                editor1.commit();

                startActivity(new Intent(Login.this,MainActivity.class));
                finish();


            }
        });
    }
}