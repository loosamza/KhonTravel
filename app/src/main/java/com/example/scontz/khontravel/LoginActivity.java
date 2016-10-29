package com.example.scontz.khontravel;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
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
import android.widget.TextView;
import android.widget.Toast;

import com.example.scontz.khontravel.Fonts.TypefaceUti;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookAuthorizationException;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;

public class LoginActivity extends AppCompatActivity {


    final String LOGIN = "App_Login";
    SharedPreferences sp;
    SharedPreferences.Editor editor;

    TextView goMain;
    EditText username, password;
    Button signin;
    String txtUsername, txtPassword;
    String[] uidList, usernameList, passwordList, firstnameList, lastnameList;



    Control_Database objControl_Database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TypefaceUti.overrideFont(getApplicationContext(), "SERIF", "bj.ttf");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        objControl_Database = new Control_Database(this);


        initWidget();

        setOnClick();


    }

    private void setOnClick() {
        goMain.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                sp = getSharedPreferences(LOGIN, Context.MODE_PRIVATE);
                editor = sp.edit();
                editor.putInt("uid", 0);
                editor.commit();
                startActivity(intent);
            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                txtUsername = username.getText().toString().trim();
                txtPassword = password.getText().toString().trim();
                if (txtUsername.trim().length() == 0 || txtPassword.trim().length() == 0) {
                    Toast.makeText(getApplicationContext(), "กรุณากรอกให้ครบ !!", Toast.LENGTH_SHORT).show();
                } else {
                    checkLogin(txtUsername, txtPassword);
                }

            }
        });

    } // setOnClick

    private void checkLogin(String user, String pass) {
        for (int i = 0; i < uidList.length; i++) {
            if (user.equals(usernameList[i]) && pass.equals(passwordList[i])) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                int id = Integer.parseInt(uidList[i]);
               /*
                intent.putExtra("uid", uidList[i]);
                Log.d("Login", uidList[i]);
                intent.putExtra("username", usernameList[i]);
                intent.putExtra("fname", firstnameList[i]);
                intent.putExtra("lname", lastnameList[i]);
                startActivity(intent);
                */
                sp = getSharedPreferences(LOGIN, Context.MODE_PRIVATE);
                editor = sp.edit();
                editor.putInt("uid", id);
                editor.putString("username", usernameList[i]);
                editor.putString("fname", firstnameList[i]);
                editor.putString("lname", lastnameList[i]);
                editor.commit();
                startActivity(intent);


            } else {
                if (i == (uidList.length)) {
                    Log.d("Login", "รหัสผิด");
                }

            }
        }


    }// checkLogin

    private void initWidget() {
        goMain = (TextView) findViewById(R.id.gotoMain);
        signin = (Button) findViewById(R.id.login);
        username = (EditText) findViewById(R.id.user);
        password = (EditText) findViewById(R.id.pass);
        deleteAllData();
        synJSon();


    }// initWidget

    private void synJSon() {

        //setUp Policy
        if (Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy myPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(myPolicy);
        } //if

        InputStream inputStream = null;
        String strJSON = "";

        //  create inputStream
        try {
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost("http://csclub.ssru.ac.th/s56122201021/end/get_user.php");
            HttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            inputStream = httpEntity.getContent();

            // change inputStream to String

        } catch (Exception e) {
            Log.d("DB", "Error from InputSteam : " + e.toString());

        }

        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            StringBuilder stringBuilder = new StringBuilder();
            String strLine = null;

            while ((strLine = bufferedReader.readLine()) != null) {
                stringBuilder.append(strLine);


            } //while
            inputStream.close();
            strJSON = stringBuilder.toString();

        } catch (Exception e) {
            Log.d("DB", "Error Create String " + e.toString());
        }

        try {
            final JSONArray jsonArray = new JSONArray(strJSON);
            int size = jsonArray.length();
            uidList = new String[size];
            usernameList = new String[size];
            passwordList = new String[size];
            firstnameList = new String[size];
            lastnameList = new String[size];
            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String j_uid = jsonObject.getString("uid");
                String j_username = jsonObject.getString("username");
                String j_password = jsonObject.getString("password");
                String j_firstname = jsonObject.getString("firstname");
                String j_lastname = jsonObject.getString("lastname");


                //Put to Array
                uidList[i] = j_uid;
                usernameList[i] = j_username;
                passwordList[i] = j_password;
                firstnameList[i] = j_firstname;
                lastnameList[i] = j_lastname;

                long inserValue = objControl_Database.addValueToUserTable(Integer.parseInt(j_uid), j_username, j_password, j_firstname, j_lastname);
                Log.d("DB", "Result ===> " + "SUCCESS");

            }//for
        } catch (Exception e) {
            Log.d("DB", "Error Up Value ===> " + e.toString());
        }
    } // SynJson

    private void deleteAllData() {
        SQLiteDatabase objSQLiteDataase = openOrCreateDatabase("endproject.db", MODE_PRIVATE, null);
        objSQLiteDataase.delete("tb_user", null, null);
    } // Delete


}
