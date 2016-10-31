package com.example.scontz.khontravel;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.scontz.khontravel.Fonts.TypefaceUti;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import cz.msebera.android.httpclient.NameValuePair;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.entity.UrlEncodedFormEntity;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;
import cz.msebera.android.httpclient.message.BasicNameValuePair;

public class Register extends AppCompatActivity {

    final String LOGIN = "App_Login";

    SharedPreferences sp;

    EditText username, password, cpassword, fname, lname;
    Button regisBtn;
    private String usernametxt, passwordtxt, cpasswordtxt, fnametxt, lnametxt;
    private String[] userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TypefaceUti.overrideFont(getApplicationContext(), "SERIF", "bj.ttf");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initWidget();


    }

    private void checkForm() {
        usernametxt = username.getText().toString().trim();
        passwordtxt = password.getText().toString().trim();
        cpasswordtxt = cpassword.getText().toString().trim();
        fnametxt = fname.getText().toString().trim();
        lnametxt = lname.getText().toString().trim();

        if (usernametxt.length() == 0 || passwordtxt.length() == 0 || cpasswordtxt.length() == 0
                || fnametxt.length() == 0 || lnametxt.length() == 0) {
            Toast.makeText(getApplicationContext(), "กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
        } else if (passwordtxt.equals(cpasswordtxt) == false) {
            Toast.makeText(getApplicationContext(), "รหัสผ่านกับรหัสยืนยันไม่ตรงกัน", Toast.LENGTH_SHORT).show();
        } else {
            checkUser();

        }


    }

    private void checkUser() {
        userList = getIntent().getStringArrayExtra("usernameList");
        //Log.d("CHECKUSER", "===>" + Arrays.asList(userList).contains(usernametxt));
        if (Arrays.asList(userList).contains(usernametxt)) {
            Toast.makeText(getApplicationContext(), "มีคนใช้ Username นี้แล้ว", Toast.LENGTH_SHORT).show();
        } else {
            addUser();

            Toast.makeText(getApplicationContext(), "สมัครสมาชิกเรียบร้อยพร้อมเข้าใช้งานทันที", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
        }


    }

    private void initWidget() {
        username = (EditText) findViewById(R.id.username_edtext);
        password = (EditText) findViewById(R.id.passwd_edtext);
        cpassword = (EditText) findViewById(R.id.cnfpasswd_edtext);
        fname = (EditText) findViewById(R.id.fname_edtext);
        lname = (EditText) findViewById(R.id.lname_edtext);
        regisBtn = (Button) findViewById(R.id.register_button);

    }

    private void addUser() {
        //Setup Policy
        if (Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy myPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(myPolicy);
        }

        //Up Value
        try {
            ArrayList<NameValuePair> objNameValuePairs = new ArrayList<NameValuePair>();
            objNameValuePairs.add(new BasicNameValuePair("isAdd", "true"));
            objNameValuePairs.add(new BasicNameValuePair("username", usernametxt));
            objNameValuePairs.add(new BasicNameValuePair("password", passwordtxt));
            objNameValuePairs.add(new BasicNameValuePair("fname", fnametxt));
            objNameValuePairs.add(new BasicNameValuePair("lname", lnametxt));


            HttpClient objHttpClient = new DefaultHttpClient();
            HttpPost objHttpPost = new HttpPost("http://csclub.ssru.ac.th/s56122201021/End/add_user.php");
            objHttpPost.setEntity(new UrlEncodedFormEntity(objNameValuePairs, "UTF-8"));
            objHttpClient.execute(objHttpPost);
            Log.d("csclub", "Update mySQL addUser ===> ทำแล้ว ");

            /*FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.detach(this).attach(this).commit();*/

           /* Log.d("csclub", "Update mySQL ===> ทำแล้ว ");
            Log.d("csclub", "Update mySQL ===> " + p_code);
            Log.d("csclub", "Update mySQL ===> " + ptype_code);
            Log.d("csclub", "Update mySQL ===> " + text_review);
            Log.d("csclub", "Update mySQL ===> " + date);
            Log.d("csclub", "Update mySQL ===> " + time);*/


        } catch (Exception e) {
            Log.d("csclub", "Update mySQL ===> " + e.toString());
        }


    } //addUser

    public void onRegister(View view) {
        checkForm();
    }


}
