package com.example.hostel_pttu_book;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button saveButton;
    private List<String>list;
    String[] permission = {READ_EXTERNAL_STORAGE,WRITE_EXTERNAL_STORAGE};
    ActivityResultLauncher<Intent> activityResultLauncher;

    EditText amtValue,adInd,asInd,baInd,jeInd;
    Button hist_button,note_button,clrButton,calcButton,btnPlsAmt,btnMinsAmt,adPlsBtn,asPlsBtn,adMinsBtn,asMinsBtn,baPlsBtn,jePlsBtn,baMinsBtn,jeMinsBtn;
    TextView adAmt,asAmt,baAmt,jeAmt,textViewP1,textViewP2,textViewP3,textViewP4,textViewPA1,textViewPA2,textViewPA3,textViewPA4;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String adTEXT = "adText";
    public static final String asTEXT = "asText";
    public static final String baTEXT = "baText";
    public static final String jeTEXT = "jeText";
    public static final String curdate = "curdate";

    private String filename = ".HostelDebtBook.hpb";

    private String adText;
    private String asText;
    private String baText;
    private String jeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        amtValue=(EditText) findViewById(R.id.amtValue);

        adInd=(EditText) findViewById(R.id.adInd);
        asInd=(EditText) findViewById(R.id.asInd);
        baInd=(EditText) findViewById(R.id.baInd);
        jeInd=(EditText) findViewById(R.id.jeInd);

        btnPlsAmt=(Button) findViewById(R.id.btnPlsAmt);
        btnMinsAmt=(Button) findViewById(R.id.btnMinsAmt);

        adPlsBtn=(Button) findViewById(R.id.adPlsBtn);
        adMinsBtn=(Button) findViewById(R.id.adMinsBtn);

        asPlsBtn=(Button) findViewById(R.id.asPlsBtn);
        asMinsBtn=(Button) findViewById(R.id.asMinsBtn);

        baPlsBtn=(Button) findViewById(R.id.baPlsBtn);
        baMinsBtn=(Button) findViewById(R.id.baMinsBtn);

        jePlsBtn=(Button) findViewById(R.id.jePlsBtn);
        jeMinsBtn=(Button) findViewById(R.id.jeMinsBtn);

        saveButton = (Button) findViewById(R.id.save_button);
        clrButton = (Button) findViewById(R.id.clr_button);
        calcButton = (Button) findViewById(R.id.calc_button);
        note_button = (Button) findViewById(R.id.note_button);
        hist_button = (Button) findViewById(R.id.hist_button);

        adAmt=(TextView) findViewById(R.id.adAmt);
        asAmt=(TextView) findViewById(R.id.asAmt);
        baAmt=(TextView) findViewById(R.id.baAmt);
        jeAmt=(TextView) findViewById(R.id.jeAmt);

        textViewP1=(TextView) findViewById(R.id.textViewP1);
        textViewP2=(TextView) findViewById(R.id.textViewP2);
        textViewP3=(TextView) findViewById(R.id.textViewP3);
        textViewP4=(TextView) findViewById(R.id.textViewP4);

        textViewPA1=(TextView) findViewById(R.id.textViewPA1);
        textViewPA2=(TextView) findViewById(R.id.textViewPA2);
        textViewPA3=(TextView) findViewById(R.id.textViewPA3);
        textViewPA4=(TextView) findViewById(R.id.textViewPA4);

        SharedPreferences prefs1 = getSharedPreferences(Login.PREFS_NAME,0);
        String p1Text=prefs1.getString("p1Text","no values");
        String p2Text=prefs1.getString("p2Text","no values");
        String p3Text=prefs1.getString("p3Text","no values");
        String p4Text=prefs1.getString("p4Text","no values");

        textViewP1.setText(p1Text);
        textViewP2.setText(p2Text);
        textViewP3.setText(p3Text);
        textViewP4.setText(p4Text);
        textViewPA1.setText(p1Text);
        textViewPA2.setText(p2Text);
        textViewPA3.setText(p3Text);
        textViewPA4.setText(p4Text);


        adPlsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ad1=addition(adInd.getText().toString(),adAmt.getText().toString());
                adAmt.setText(ad1);
                adInd.setText("0");
            }
        });
        adMinsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ad2=substraction(adInd.getText().toString(),adAmt.getText().toString());
                adAmt.setText(ad2);
                adInd.setText("0");
            }
        });

        asPlsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ad1=addition(asInd.getText().toString(),asAmt.getText().toString());
                asAmt.setText(ad1);
                asInd.setText("0");
            }
        });
        asMinsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ad2=substraction(asInd.getText().toString(),asAmt.getText().toString());
                asAmt.setText(ad2);
                asInd.setText("0");
            }
        });

        baPlsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ad1=addition(baInd.getText().toString(),baAmt.getText().toString());
                baAmt.setText(ad1);
                baInd.setText("0");
            }
        });
        baMinsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ad2=substraction(baInd.getText().toString(),baAmt.getText().toString());
                baAmt.setText(ad2);
                baInd.setText("0");
            }
        });

        jePlsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ad1=addition(jeInd.getText().toString(),jeAmt.getText().toString());
                jeAmt.setText(ad1);
                jeInd.setText("0");
            }
        });
        jeMinsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ad2=substraction(jeInd.getText().toString(),jeAmt.getText().toString());
                jeAmt.setText(ad2);
                jeInd.setText("0");
            }
        });

        btnPlsAmt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double amt=0,n1;
                n1=Double.parseDouble(amtValue.getText().toString());
                amt=n1/4;
                amtValue.setText("0");

                String ad1=addition(adAmt.getText().toString(),amt);
                adAmt.setText(ad1);

                String as1=addition(asAmt.getText().toString(),amt);
                asAmt.setText(as1);

                String ba1=addition(baAmt.getText().toString(),amt);
                baAmt.setText(ba1);

                String je1=addition(jeAmt.getText().toString(),amt);
                jeAmt.setText(je1);
            }
        });
        btnMinsAmt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double amt=0,n1;
                n1=Double.parseDouble(amtValue.getText().toString());
                amt=n1;
                amtValue.setText("0");

                String ad1=substraction(adAmt.getText().toString(),amt);
                adAmt.setText(ad1);

                String as1=substraction(asAmt.getText().toString(),amt);
                asAmt.setText(as1);

                String ba1=substraction(baAmt.getText().toString(),amt);
                baAmt.setText(ba1);

                String je1=substraction(jeAmt.getText().toString(),amt);
                jeAmt.setText(je1);
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkPermission()){
                    saveData();
                }else{
                    requestPermission();
                }
            }
        });
        activityResultLauncher=registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
               if (result.getResultCode()== Activity.RESULT_OK){
                   if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R){
                       if (Environment.isExternalStorageManager()){
                           printMessage("Permission Granted");
                       }else{
                           printMessage("permission Denied");
                       }
                   }
               }
            }
        });
        calcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MainActivity2.class));
            }
        });
        clrButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createAlertDialoge();
            }
        });
        note_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MainActivity3.class));
            }
        });
        hist_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MainActivity4.class));
            }
        });

        loadData();
        updateViews();
    }

    void requestPermission(){
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.R){
            try {
                Intent intent = new Intent(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION);
                intent.addCategory("android.intent.category.DEFAULT");
                intent.setData(Uri.parse(String.format("package:%s",new Object[]{getApplicationContext().getPackageName()})));
                activityResultLauncher.launch(intent);
            }catch(Exception e){
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION);
                activityResultLauncher.launch(intent);
            }
        }else{
            ActivityCompat.requestPermissions(MainActivity.this,permission,30);

        }
    }
    boolean checkPermission(){

        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.R){
            return Environment.isExternalStorageManager();
        }else{
            int readcheck= ContextCompat.checkSelfPermission(getApplicationContext(),READ_EXTERNAL_STORAGE);
            int writecheck= ContextCompat.checkSelfPermission(getApplicationContext(),WRITE_EXTERNAL_STORAGE);
            return readcheck ==PackageManager.PERMISSION_GRANTED && writecheck ==PackageManager.PERMISSION_GRANTED;
        }
    }
    public void onRequestPermissionsResult(int requestCode, String[] permissions,int [] grantResults){
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch(requestCode){
            case 30:
                if (grantResults.length>0){
                    boolean readper = grantResults[0]==PackageManager.PERMISSION_GRANTED;
                    boolean writeper=grantResults[1]==PackageManager.PERMISSION_GRANTED;
                    if (readper && writeper){
                        printMessage("Permission Granted..!");
                    }else{
                        printMessage("Permission Denied");
                    }
                }else{
                    printMessage("You Denied Permision");
                }
                break;
        }

    }
    int fg=0;
    private void createAlertDialoge() {
        list=new ArrayList<>();
        AlertDialog.Builder myAlert = new AlertDialog.Builder(MainActivity.this);
        myAlert.setTitle("Are you sure you want to delete the amounts?");
        //myAlert.setMessage("Are you sure you want to delete the amounts ?");
        myAlert.setMultiChoiceItems(R.array.OS_name, null, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i, boolean isChecked) {
                String arr[]=getResources().getStringArray(R.array.OS_name);

                if (isChecked){
                    list.add(arr[i]);
                    fg=1;
                }
                else if (list.contains(arr[i])){
                    list.remove(arr[i]);
                    fg=0;
                }
            }
        });

        myAlert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String data="";
                for (String item:list){
                    data=item;
                }
                if (fg==1){
                    adAmt.setText("0");
                    asAmt.setText("0");
                    baAmt.setText("0");
                    jeAmt.setText("0");
                    saveData();
                    printMessage("Amount Deleted..! please wait..!");
                    finish();
                    startActivity(getIntent());
                }
                else{
                    printMessage("Sorry you haven't checked..!");
                }
            }
        });
        myAlert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                printMessage("Sorry an error occur..!");
            }
        });
        myAlert.show();
    }
    public void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        adText = sharedPreferences.getString(adTEXT, "0");
        asText = sharedPreferences.getString(asTEXT, "0");
        baText = sharedPreferences.getString(baTEXT, "0");
        jeText = sharedPreferences.getString(jeTEXT, "0");
    }
    public void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(adTEXT, adAmt.getText().toString());
        editor.putString(asTEXT, asAmt.getText().toString());
        editor.putString(baTEXT, baAmt.getText().toString());
        editor.putString(jeTEXT, jeAmt.getText().toString());

        Date currentTime = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
        String strDate = dateFormat.format(currentTime);
        editor.putString(curdate, strDate);

        editor.apply();

        Toast.makeText(this, "Data saved", Toast.LENGTH_SHORT).show();
        String adData = adAmt.getText().toString();
        String asData = asAmt.getText().toString();
        String baData = baAmt.getText().toString();
        String jeData = jeAmt.getText().toString();

        String wData= strDate+"="+adData+"="+asData+"="+baData+"="+jeData+"*\n";
        writeData(wData);

    }
    public void updateViews() {
        adAmt.setText(adText);
        asAmt.setText(asText);
        baAmt.setText(baText);
        jeAmt.setText(jeText);
    }
    private void writeData(String data) {
        try {
            File f1 = new File(Environment.getExternalStoragePublicDirectory("Android"), ".HostelDebtBook");
            f1.mkdir();
            File f =new File(Environment.getExternalStoragePublicDirectory("Android/.HostelDebtBook"),filename);
            FileWriter fos = new FileWriter(f,true);
            fos.write(data);
            fos.close();
            //printMessage("File Saved to" + getFilesDir() + "/" + filename +" completed...");
        }catch (FileNotFoundException e){
            e.printStackTrace();
            printMessage("File not found..!!!");
        }catch (IOException e) {
            e.printStackTrace();
            printMessage("Error in saving..!!");
        }
    }
    public void printMessage(String m) {
        Toast.makeText(this, m, Toast.LENGTH_LONG).show();
    }

    public String addition(String a, String b){
        double a1=0,b1=0,c=0;
        a1=Double.parseDouble(a);
        b1=Double.parseDouble(b);
        c = b1+a1;
        String i =Double.toString(c);

        return i;
    }
    public String addition(String a, double b){
        double a1=0.0,c=0.0;
        a1=Double.parseDouble(a);
        c=a1+b;
        String j =Double.toString(c);

        return j;
    }
    public String substraction(String a, String b){
        double a1=0,b1=0,c=0;
        a1=Double.parseDouble(a);
        b1=Double.parseDouble(b);
        c = b1-a1;
        String h =Double.toString(c);

        return h;
    }
    public String substraction(String a, double b){
        double a1=0.0,c=0.0;
        a1=Double.parseDouble(a);
        c=a1-b;
        String k =Double.toString(c);

        return k;
    }
}