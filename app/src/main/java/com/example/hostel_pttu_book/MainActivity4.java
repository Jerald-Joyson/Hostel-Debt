package com.example.hostel_pttu_book;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.*;
import java.io.IOException;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;

public class MainActivity4 extends AppCompatActivity {
    TextView dTv,aDv,aSv,bAv,jEv,dTv1,aDv1,aSv1,bAv1,jEv1;
    Button btn1,fDbtn;
    SharedPreferences prefs;
    TextView textV5,textV2,textV3,textV4,textV7,textV8,textV9,textV0;

    private static final String filename = ".demoFile.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btn1=(Button) findViewById(R.id.btnView);
        fDbtn=(Button) findViewById(R.id.fDbtn);

        dTv=(TextView) findViewById(R.id.dT);
        aDv=(TextView) findViewById(R.id.aD);
        aSv=(TextView) findViewById(R.id.aS);
        bAv=(TextView) findViewById(R.id.bA);
        jEv=(TextView) findViewById(R.id.jE);
        dTv1=(TextView) findViewById(R.id.dT1);
        aDv1=(TextView) findViewById(R.id.aD1);
        aSv1=(TextView) findViewById(R.id.aS1);
        bAv1=(TextView) findViewById(R.id.bA1);
        jEv1=(TextView) findViewById(R.id.jE1);

        textV2=(TextView) findViewById(R.id.textV2);
        textV3=(TextView) findViewById(R.id.textV3);
        textV4=(TextView) findViewById(R.id.textV4);
        textV5=(TextView) findViewById(R.id.textV5);
        textV7=(TextView) findViewById(R.id.textV7);
        textV8=(TextView) findViewById(R.id.textV8);
        textV9=(TextView) findViewById(R.id.textV9);
        textV0=(TextView) findViewById(R.id.textV0);

        SharedPreferences prefs1 = getSharedPreferences(Login.PREFS_NAME,0);
        String p1Text=prefs1.getString("p1Text","no values");
        String p2Text=prefs1.getString("p2Text","no values");
        String p3Text=prefs1.getString("p3Text","no values");
        String p4Text=prefs1.getString("p4Text","no values");
        textV2.setText(p1Text);
        textV3.setText(p2Text);
        textV4.setText(p3Text);
        textV5.setText(p4Text);
        textV7.setText(p1Text);
        textV8.setText(p2Text);
        textV9.setText(p3Text);
        textV0.setText(p4Text);


        prefs=getSharedPreferences("sharedPrefs",MODE_PRIVATE);
        String adText=prefs.getString("adText","no values");
        String asText=prefs.getString("asText","no values");
        String baText=prefs.getString("baText","no values");
        String jeText=prefs.getString("jeText","no values");
        String curdate=prefs.getString("curdate","no values");
        dTv.setText(curdate);
        aDv.setText(adText);
        aSv.setText(asText);
        bAv.setText(baText);
        jEv.setText(jeText);


        fDbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity4.this);
                builder.setMessage("Do you want to Delete the file ?");
                builder.setTitle("Alert !");
                builder.setCancelable(false);
                builder.setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {
                    try {
                        File f = new File(Environment.getExternalStoragePublicDirectory("Android/media"), filename);
                        if (f.delete()) {
                            printMessage("File Successfully Deleted..!!");
                            finish();
                        } else {
                            printMessage("Error in Deleting..!!");
                        }
                    }catch(Exception e){
                        e.printStackTrace();
                        printMessage("File not found..!!");
                    }
                });// Set the Negative button with No name Lambda OnClickListener method is use of DialogInterface interface.
                builder.setNegativeButton("No", (DialogInterface.OnClickListener) (dialog, which) -> {
                    printMessage("Cancelled..!");
                    dialog.cancel();
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                readData();
            }
        });
    }
    public void printMessage(String m) {
        Toast.makeText(this, m, Toast.LENGTH_LONG).show();
    }

    private void readData() {
        String info=null;
        StringBuilder temp = new StringBuilder();
        StringBuilder da = new StringBuilder();
        StringBuilder ad = new StringBuilder();
        StringBuilder as = new StringBuilder();
        StringBuilder ba = new StringBuilder();
        StringBuilder je = new StringBuilder();
        try {
            File f = new File(Environment.getExternalStoragePublicDirectory("Android/media"), filename);
            FileInputStream fin = new FileInputStream(f);
            InputStreamReader input = new InputStreamReader(fin);
            BufferedReader br =new BufferedReader(input);
            while ((info = br.readLine()) != null) {
                temp.append(info);
            }
            String nl = "\n";
            String temp1 = new String(temp.toString());
            StringTokenizer st1 = new StringTokenizer(temp1, "*");
            while (st1.hasMoreTokens()) {
                String temp2 = new String(st1.nextToken());
                StringTokenizer st2 = new StringTokenizer(temp2, "=");
                while (st2.hasMoreTokens()) {
                    da.append(st2.nextToken());
                    da.append(nl);
                    ad.append(st2.nextToken());
                    ad.append(nl);
                    ad.append(nl);
                    as.append(st2.nextToken());
                    as.append(nl);
                    as.append(nl);
                    ba.append(st2.nextToken());
                    ba.append(nl);
                    ba.append(nl);
                    je.append(st2.nextToken());
                    je.append(nl);
                    je.append(nl);
                }
            }
            dTv1.setText(da.toString());
            aDv1.setText(ad.toString());
            aSv1.setText(as.toString());
            bAv1.setText(ba.toString());
            jEv1.setText(je.toString());
            fin.close();
            printMessage("reading from file completed..");
        }catch(FileNotFoundException e){
            e.printStackTrace();
            printMessage("File Not Found..!!");
        }catch (IOException e) {
            e.printStackTrace();
            printMessage("Error..!!");
        }

    }
}