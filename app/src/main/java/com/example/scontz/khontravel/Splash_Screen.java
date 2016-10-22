package com.example.scontz.khontravel;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.os.SystemClock;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.multidex.MultiDex;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.scontz.khontravel.Fonts.TypefaceUti;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;

import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;

public class Splash_Screen extends AppCompatActivity {
    protected static final int TIMER_RUNTIME = 10000;

    protected boolean mActive;
    private Control_Database objControl_Database;

    ProgressBar pgr;
    TextView pgrStatus, loadingStatus;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TypefaceUti.overrideFont(getApplicationContext(), "SERIF", "bj.ttf");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash__screen);
        MultiDex.install(this);

        objControl_Database = new Control_Database(this);
        initWidget();


    }

    public void goToMain() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void initWidget() {
        pgr = (ProgressBar) findViewById(R.id.vertical_progressbar);
        loadingStatus = (TextView) findViewById(R.id.txtLoading);
        pgrStatus = (TextView) findViewById(R.id.pgrStatus);
        new UpdateVerticalProgressBar().execute();


    }

    public class UpdateVerticalProgressBar extends AsyncTask<Void, Integer, Void> {

        int progress;

        @Override
        protected void onPreExecute() {

            progress = 0;
            pgrStatus.setText(progress + " %");
            //pgr.setVisibility(View.VISIBLE);

        }

        @Override
        protected void onProgressUpdate(Integer... values) {

            int current = values[0];


            pgr.setProgress(current);

            pgrStatus.setText(current + " %");
            if (current % 3 == 0) {
                loadingStatus.setText("Loading.");
            } else if (current % 3 == 1) {
                loadingStatus.setText("Loading..");
            } else if (current % 3 == 2) {
                loadingStatus.setText("Loading...");
            }

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            Random random = new Random();


            while (progress < 100) {

                int num = random.nextInt(5) + 1;
                progress = progress + num;

                if (progress > 50 && progress < 60) {
                    deleteAllData();
                    synJSonToSQLite();
                }
                if (progress >= 100) {
                    progress = 100;
                }

                publishProgress(progress);

                SystemClock.sleep(20);


            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            deleteAllData();
            synJSonToSQLite();
            goToMain();
        }
    }


    private void synJSonToSQLite() {

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
            HttpPost httpPost = new HttpPost("http://csclub.ssru.ac.th/s56122201021/end/get_data.php");
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
            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject jsonObject = jsonArray.getJSONObject(i);

                String p_name = jsonObject.getString("p_name");
                String ptype_name = jsonObject.getString("ptype_name");
                String p_des = jsonObject.getString("p_des");
                String p_travel = jsonObject.getString("p_travel");
                String p_open = jsonObject.getString("p_open");
                String p_contact = jsonObject.getString("p_contact");
                String main_pic = jsonObject.getString("main_pic");
                String pic_code = jsonObject.getString("pic_code");
                double latitude = jsonObject.getDouble("latitude");
                double longitude = jsonObject.getDouble("longitude");
                int p_pos = jsonObject.getInt("p_pos");
                int p_neg = jsonObject.getInt("p_neg");


                long insertValue = objControl_Database.addValueToPlaceDetail(p_name, ptype_name, p_des, p_travel, p_open, p_contact, main_pic, pic_code, latitude, longitude, p_pos, p_neg);
            }//for
        } catch (Exception e) {
            Log.d("DB", "Error Up Value ===> " + e.toString());
        }


    }


    private void deleteAllData() {
        SQLiteDatabase objSQLiteDataase = openOrCreateDatabase("endproject.db", MODE_PRIVATE, null);
        objSQLiteDataase.delete("tb_placedetail", null, null);
    }


}
