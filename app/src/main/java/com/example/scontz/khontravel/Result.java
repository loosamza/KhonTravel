package com.example.scontz.khontravel;

import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.view.NestedScrollingChild;
import android.support.v4.view.NestedScrollingParent;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.scontz.khontravel.Fonts.TypefaceUti;
import com.example.scontz.khontravel.Fragment.DescriptionFragment;
import com.example.scontz.khontravel.Fragment.MapViewFragment;
import com.example.scontz.khontravel.Fragment.NearPlaceFragment;
import com.example.scontz.khontravel.Fragment.ReviewFragment;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.BottomBarTab;
import com.roughike.bottombar.OnTabReselectListener;
import com.roughike.bottombar.OnTabSelectListener;
import com.squareup.picasso.Picasso;

public class Result extends AppCompatActivity {

    final String LOGIN = "App_Login";
    SharedPreferences sp;
    SharedPreferences.Editor editor;

    TextView txtPlaceName, txtPlaceDes, txtPlaceTravel, txtPlaceOpen, txtPlaceContact, txtNearPlace;
    ImageView img;
    String strType, strPlaceName, strDes, strTravel, strOpen, strImg, strContact;
    double lat, lng;
    String[][] location = null;
    public String user, fname, lname;
    public int i;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TypefaceUti.overrideFont(getApplicationContext(), "SERIF", "bj.ttf");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);


        initWidget();

        BottomBar bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        BottomBarTab review = bottomBar.getTabWithId(R.id.tab_review);
        review.setBadgeCount(15);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                NestedScrollView parentScroll = (NestedScrollView) findViewById(R.id.myScrollingContent);

                FragmentManager fragmentManager = getFragmentManager();

                if (tabId == R.id.tab_des) {
                    parentScroll.setNestedScrollingEnabled(true);
                    fragmentManager.beginTransaction()
                            .replace(R.id.content_frame,
                                    new DescriptionFragment())
                            .commit();

                } else if (tabId == R.id.tab_review) {
                    parentScroll.setNestedScrollingEnabled(true);
                    fragmentManager.beginTransaction()
                            .replace(R.id.content_frame,
                                    new ReviewFragment())
                            .commit();

                } else if (tabId == R.id.tab_map) {

//                    mainScrollView.requestDisallowInterceptTouchEvent(true);


                    parentScroll.setNestedScrollingEnabled(true);
                    fragmentManager.beginTransaction()
                            .replace(R.id.content_frame,
                                    new MapViewFragment())
                            .commit();
                } else if (tabId == R.id.tab_nearplace) {
                    parentScroll.setNestedScrollingEnabled(true);
                    fragmentManager.beginTransaction()
                            .replace(R.id.content_frame,
                                    new NearPlaceFragment())
                            .commit();
                } else if (tabId == R.id.tab_other) {
                    parentScroll.setNestedScrollingEnabled(true);
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);

                }
            }
        });

        bottomBar.setOnTabReselectListener(new OnTabReselectListener() {
            @Override
            public void onTabReSelected(@IdRes int tabId) {
                Toast.makeText(getApplicationContext(), "Repeat Tab !!", Toast.LENGTH_LONG).show();
            }
        });

        // setAll();

    } // oncreate

    private void setAll() {
        Picasso.with(this).load(strImg).resize(300, 100).into(img);
        txtPlaceName.setText(strPlaceName);
        txtPlaceDes.setText(strDes);
        txtPlaceTravel.setText(strTravel);
        txtPlaceOpen.setText(strOpen);
        txtPlaceContact.setText(strContact);
    } //Set data

    private void initWidget() {
        img = (ImageView) findViewById(R.id.img);
        txtPlaceName = (TextView) findViewById(R.id.txtPlaceName);
        txtPlaceDes = (TextView) findViewById(R.id.txtPlaceDes);
        txtPlaceTravel = (TextView) findViewById(R.id.txtPlaceTravel);
        txtPlaceOpen = (TextView) findViewById(R.id.txtPlaceOpen);
        txtPlaceContact = (TextView) findViewById(R.id.txtPlaceContact);


        strType = getIntent().getExtras().getString("strType");
        strPlaceName = getIntent().getExtras().getString("strPlaceName");
        strDes = getIntent().getExtras().getString("strDes");
        strTravel = getIntent().getExtras().getString("strTravel");
        strOpen = getIntent().getExtras().getString("strOpen");
        strImg = getIntent().getExtras().getString("strImg");
        strContact = getIntent().getExtras().getString("strContact");

        sp = getSharedPreferences(LOGIN, Context.MODE_PRIVATE);
        int id = sp.getInt("uid", -1);

        if (id != 0) {
            user = sp.getString("username", "");

        }


        Log.d("Login", "" + id);

        i = id;


    }// find id get Intent

    public String getStrPlaceName() {
        return strPlaceName;
    }

    public String getStrType() {
        return strType;
    }

    public int getId() {
        return i;
    }

    public String getUser() {
        return user;
    }


}//Main
