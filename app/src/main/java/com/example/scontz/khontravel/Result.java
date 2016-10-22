package com.example.scontz.khontravel;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.view.NestedScrollingChild;
import android.support.v4.view.NestedScrollingParent;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
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

    TextView txtPlaceName, txtPlaceDes, txtPlaceTravel, txtPlaceOpen, txtPlaceContact, txtNearPlace;
    ImageView img;
    String a, b, c, d, e, f;
    double lat, lng;
    String[][] location = null;


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
        Picasso.with(this).load(e).resize(300, 100).into(img);
        txtPlaceName.setText(a);
        txtPlaceDes.setText(b);
        txtPlaceTravel.setText(c);
        txtPlaceOpen.setText(d);
        txtPlaceContact.setText(f);
    } //Set data

    private void initWidget() {
        img = (ImageView) findViewById(R.id.img);
        txtPlaceName = (TextView) findViewById(R.id.txtPlaceName);
        txtPlaceDes = (TextView) findViewById(R.id.txtPlaceDes);
        txtPlaceTravel = (TextView) findViewById(R.id.txtPlaceTravel);
        txtPlaceOpen = (TextView) findViewById(R.id.txtPlaceOpen);
        txtPlaceContact = (TextView) findViewById(R.id.txtPlaceContact);

        a = getIntent().getExtras().getString("strPlaceName");
        b = getIntent().getExtras().getString("strDes");
        c = getIntent().getExtras().getString("strTravel");
        d = getIntent().getExtras().getString("strOpen");
        e = getIntent().getExtras().getString("strImg");
        f = getIntent().getExtras().getString("strContact");


    }// find id get Intent

}//Main
