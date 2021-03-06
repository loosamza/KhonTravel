package com.example.scontz.khontravel;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.scontz.khontravel.Fonts.TypefaceUti;
import com.google.android.gms.maps.model.LatLng;

public class ShowSelect extends AppCompatActivity {

    final String LOGIN = "App_Login";
    final String T_IMG = "App_Tile";
    SharedPreferences sp;
    SharedPreferences.Editor editor;

    Control_Database objContrrol_Database;
    TextView txtPlaceType;
    String[] strListPlaceName, strListImg, strListDes, strListTravel, strListOpen, strListContact; //ตัวเก็บค่า
    String strPlaceName, strDes, strTravel, strOpen, strContact, strImg; //ตัวโยนค่า
    double[] douListLat, doubListLng;
    float[] doubListPos, doubListNeg;
    double doubLat, doubLng;
    private double[] pos, neg; // %
    String type;
    private String u, f, l;
    int i;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TypefaceUti.overrideFont(getApplicationContext(), "SERIF", "bj.ttf");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_select);

        objContrrol_Database = new Control_Database(this);

        initWidget();
        setUpAllArray();
        createListView();

    }

    private void createListView() {
        MyAdapter myAdapter = new MyAdapter(ShowSelect.this, strListPlaceName, strListImg, doubListPos, doubListNeg, type);

        ListView listView = (ListView) findViewById(R.id.selectListview);

        listView.setAdapter(myAdapter);

        //ActiveClick
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Keep value for intent to next page
                strPlaceName = strListPlaceName[position];
                strDes = strListDes[position];
                strTravel = strListTravel[position];
                strOpen = strListOpen[position];
                strContact = strListContact[position];
                strImg = strListImg[position];
                doubLat = douListLat[position];
                doubLng = doubListLng[position];

                // Log.d("IMG", "place ===> " + strTravel);

                //Intent
                Intent intent = new Intent(getApplicationContext(), Result.class);


                intent.putExtra("strType", type);
                intent.putExtra("strPlaceName", strPlaceName);
                intent.putExtra("strDes", strDes);
                intent.putExtra("strTravel", strTravel);
                intent.putExtra("strOpen", strOpen);
                intent.putExtra("strContact", strContact);
                intent.putExtra("strImg", strImg);
                LatLng t = new LatLng(doubLat, doubLng);
                String[][] te = objContrrol_Database.getDistance(t, objContrrol_Database.Location(), strPlaceName, objContrrol_Database.AllList("p_name"));
                Bundle b = new Bundle();
                b.putSerializable("nearLocation", te);
                // b.putStringArray("nearLocation",te);
                intent.putExtras(b);

                intent.putExtra("doubLat", doubLat);
                intent.putExtra("doubLng", doubLng);
                startActivity(intent);
            }
        });
    }

    private void setUpAllArray() {
        strListPlaceName = objContrrol_Database.ListPlaceName(type);
        strListDes = objContrrol_Database.ListDes(type);
        strListTravel = objContrrol_Database.ListTravel(type);
        strListOpen = objContrrol_Database.ListOpen(type);
        strListContact = objContrrol_Database.ListContact(type);
        strListImg = objContrrol_Database.ListIMG(type);
        douListLat = objContrrol_Database.ListLat(type);
        doubListLng = objContrrol_Database.ListLng(type);
        doubListPos = objContrrol_Database.ListPos(type);
        doubListNeg = objContrrol_Database.ListNeg(type);


    }

    private void initWidget() {
        type = getIntent().getExtras().getString("key");
        txtPlaceType = (TextView) findViewById(R.id.txtPlaceType);
        txtPlaceType.setText(type);


        setTBG(type);


    }

    private void setTBG(String t) {
        LinearLayout t_bg = (LinearLayout) findViewById(R.id.titleBG);
        LinearLayout l_bg = (LinearLayout) findViewById(R.id.l_titleBG);
        RelativeLayout r_bg = (RelativeLayout) findViewById(R.id.r_bg);

        if (t.equals("ทะเล")) {
            t_bg.setBackgroundResource(R.drawable.title_beach);
            l_bg.setBackgroundColor(Color.parseColor("#b66af4"));
            r_bg.setBackgroundColor(Color.parseColor("#b66af4"));

        } else if (t.equals("น้ำตก")) {
            t_bg.setBackgroundResource(R.drawable.title_waterfall);
            l_bg.setBackgroundColor(Color.parseColor("#ff62a1"));
            r_bg.setBackgroundColor(Color.parseColor("#ff62a1"));

        } else if (t.equals("ถ้ำ")) {
            t_bg.setBackgroundResource(R.drawable.title_cave);
            l_bg.setBackgroundColor(Color.parseColor("#58ffbf"));
            r_bg.setBackgroundColor(Color.parseColor("#58ffbf"));

        } else if (t.equals("แก่ง")) {
            t_bg.setBackgroundResource(R.drawable.title_canu);
            l_bg.setBackgroundColor(Color.parseColor("#f7604f"));
            r_bg.setBackgroundColor(Color.parseColor("#f7604f"));

        } else if (t.equals("ภูเขา")) {
            t_bg.setBackgroundResource(R.drawable.title_mountain);
            l_bg.setBackgroundColor(Color.parseColor("#ffd665"));
            r_bg.setBackgroundColor(Color.parseColor("#ffd665"));

        }


    }

}
