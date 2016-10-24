package com.example.scontz.khontravel.Fragment;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.scontz.khontravel.Fonts.TypefaceUti;
import com.example.scontz.khontravel.Method.MyMethod;
import com.example.scontz.khontravel.R;
import com.example.scontz.khontravel.Result;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;

import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.NameValuePair;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.entity.UrlEncodedFormEntity;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;
import cz.msebera.android.httpclient.message.BasicNameValuePair;

/**
 * Created by scOnTz on 30/9/2559.
 */
public class ReviewFragment extends Fragment {

    View myView;
    String strType, strPlaceName, strDate, strTime;
    String p_code, ptype_code, uid, text_review, date, time;
    private String review;
    EditText inputReview;
    Button send;
    int id;
    int c_review = 1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        TypefaceUti.overrideFont(getActivity(), "SERIF", "bj.ttf");
        myView = inflater.inflate(R.layout.review_layout, container, false);
        initWidget(myView);
        synJSon();
        return myView;
    }//oncreate

    private void initWidget(View view) {
        Result activity = (Result) getActivity();
        strPlaceName = activity.getStrPlaceName();
        strType = activity.getStrType();
        id = activity.getId();
        uid = Integer.toString(id);
        inputReview = (EditText) view.findViewById(R.id.inputReview);
        send = (Button) view.findViewById(R.id.send);



        send.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //เช็คการเป็นสมาชิก
                review = inputReview.getText().toString();
                if (id == 0) {
                    Toast.makeText(getActivity(), "คุณไม่ได้ล๊อคอินไม่ได้สามารถแสดงความคิดเห็นได้", Toast.LENGTH_SHORT).show();
                } else if (c_review < 1) {
                    Toast.makeText(getActivity(), "คุณแสดงความคิดเห็นครบแล้วสำหรับวันนี้", Toast.LENGTH_SHORT).show();
                } else if (review.trim().length() == 0) {
                    Toast.makeText(getActivity(), "คุณไม่ได้กรอกข้อมูล", Toast.LENGTH_SHORT).show();
                } else {

                    Calendar c = Calendar.getInstance(TimeZone.getTimeZone("Asia/Bangkok"));
                    SimpleDateFormat mdformat = new SimpleDateFormat("yyyy-MM-dd");
                    date = mdformat.format(c.getTime());
                    SimpleDateFormat mtformat = new SimpleDateFormat("HH:mm:ss");
                    time = mtformat.format(c.getTime());
                    //Log.d("csclub", "Time ===> " + date);
                    //Log.d("csclub", "Time ===> " + time);
                    text_review = inputReview.getText().toString();
                    MyMethod objMyMethod = new MyMethod();
                    p_code = objMyMethod.changePnameToCode(strPlaceName);
                    ptype_code = objMyMethod.changePtypeToCode(strType);
                    upDateCommentToMySQL();
                }
            }
        });

    }// initWidget

    private void upDateCommentToMySQL() {
        //Setup Policy
        if (Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy myPilicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(myPilicy);
        }

        //Up Value
        try {
            ArrayList<NameValuePair> objNameValuePairs = new ArrayList<NameValuePair>();
            objNameValuePairs.add(new BasicNameValuePair("isAdd", "true"));
            objNameValuePairs.add(new BasicNameValuePair("p_code", p_code));
            objNameValuePairs.add(new BasicNameValuePair("ptype_code", ptype_code));
            objNameValuePairs.add(new BasicNameValuePair("uid", uid));
            objNameValuePairs.add(new BasicNameValuePair("text_review", text_review));
            objNameValuePairs.add(new BasicNameValuePair("date", date));
            objNameValuePairs.add(new BasicNameValuePair("time", time));


            HttpClient objHttpClient = new DefaultHttpClient();
            HttpPost objHttpPost = new HttpPost("http://csclub.ssru.ac.th/s56122201021/End/add_review.php");
            objHttpPost.setEntity(new UrlEncodedFormEntity(objNameValuePairs, "UTF-8"));
            objHttpClient.execute(objHttpPost);

            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.detach(this).attach(this).commit();

           /* Log.d("csclub", "Update mySQL ===> ทำแล้ว ");
            Log.d("csclub", "Update mySQL ===> " + p_code);
            Log.d("csclub", "Update mySQL ===> " + ptype_code);
            Log.d("csclub", "Update mySQL ===> " + text_review);
            Log.d("csclub", "Update mySQL ===> " + date);
            Log.d("csclub", "Update mySQL ===> " + time);*/


        } catch (Exception e) {
            Log.d("csclub", "Update mySQL ===> " + e.toString());
        }


    } //upDateComment

    private void setAll(int i, View view, String username, String text_review, String time, String date) {
        final TextView textView = new TextView(getActivity());
        final LinearLayout linearLayout = new LinearLayout(getActivity());
        LinearLayout mLinearLayout = (LinearLayout) view.findViewById(R.id.linReview);
        linearLayout.setId(i);
        textView.setId(i);
        //กรอบโชว์คอมมเม้น
        LinearLayout.LayoutParams liNearParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        liNearParams.setMargins(15, 10, 15, 0);
        linearLayout.setLayoutParams(liNearParams);
        linearLayout.setBackgroundColor(Color.WHITE);
        //คอมเม้น
        LinearLayout.LayoutParams textViewParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        textViewParams.setMargins(20, 20, 20, 20);

        textView.setLayoutParams(textViewParams);
        textView.setText(username + " : " + "\n" + text_review + "\n" + "วันที่ : " + date + " เวลา : " + time);
        textView.setTypeface(Typeface.SERIF);

        try {


            linearLayout.addView(textView);
            mLinearLayout.addView(linearLayout);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }// SetAll

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
            HttpPost httpPost = new HttpPost("http://csclub.ssru.ac.th/s56122201021/end/get_review_data.php");
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
                String username = jsonObject.getString("username");
                String ptype_name = jsonObject.getString("ptype_name");
                String text_review = jsonObject.getString("text_review");
                String date = jsonObject.getString("date");
                String time = jsonObject.getString("time");

                // Show log
               /* Log.d("csclub", "name >>>" + strPlaceName);
                Log.d("csclub", "tname >>>" + strType);*/

                if (ptype_name.equals(strType) && p_name.equals(strPlaceName)) {
                    setAll(i, myView, username, text_review, time, date);
                    Log.d("csclub", "name >>>" + p_name);
                    Log.d("csclub", "username >>>" + username);
                    Log.d("csclub", "tname >>>" + ptype_name);
                    Log.d("csclub", "t_review >>>" + text_review);
                    Log.d("csclub", "date >>>" + date);
                    Log.d("csclub", "time >>>" + time);
                }


            }//for
        } catch (Exception e) {
            Log.d("DB", "Error Up Value ===> " + e.toString());
        }


    } // SynJson


}//main
