package com.example.scontz.khontravel;


import android.app.FragmentManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;

import com.facebook.AccessToken;
import com.facebook.FacebookSdk;

import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.scontz.khontravel.Fonts.TypefaceUti;
import com.example.scontz.khontravel.Fragment.GmapFragment;
import com.example.scontz.khontravel.Fragment.MainFragment;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import android.content.pm.Signature;

import java.util.List;
import java.util.Map;

import cz.msebera.android.httpclient.extras.Base64;

/* try {
        PackageInfo info = getPackageManager().getPackageInfo(
                "com.example.scontz.khontravel",
                PackageManager.GET_SIGNATURES);
        for (Signature signature : info.signatures) {
            MessageDigest md = MessageDigest.getInstance("SHA");
            md.update(signature.toByteArray());
            Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
        }
    } catch (PackageManager.NameNotFoundException e) {

    } catch (NoSuchAlgorithmException e) {

    }

*/
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    TextView userName;
    ImageView userPic;
    String setuserName, setuserPic;
    ActionBarDrawerToggle toggle;
    Control_Database objControl_Databse;
    private String[] strMLpName, strMLpType, strMLIMG;
    List<LatLng> location;
    LatLng coordinate;
    private double[] listLat, listLng;
    Bundle bundle;
    Profile profile = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TypefaceUti.overrideFont(getApplicationContext(), "SERIF", "bj.ttf");
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());

        setContentView(R.layout.activity_main);


        // Log.i("Token", AccessToken.getCurrentAccessToken().getToken());
        if (AccessToken.getCurrentAccessToken() == null) {
            goLoginScreen();
        } else {


            initWidget();

            if (savedInstanceState == null) {
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container,
                                new MainFragment())
                        .commit();
            }

            setNavigator();
        }


        //Toast.makeText(getApplicationContext(), "Map test", Toast.LENGTH_SHORT).show();


    }

    private void initWidget() {

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(this);
        View header = navigationView.getHeaderView(0);
        userName = (TextView) header.findViewById(R.id.nameUser);
        userPic = (ImageView) header.findViewById(R.id.userPic);


        if (getIntent() != null & getIntent().getExtras() != null) {
            profile = getIntent().getExtras().getParcelable("profile");

            try {

                objControl_Databse.addValueToUID(profile.getId());
            } catch (Exception e) {
                Log.d("UID ADD", "มีข้อมูลแล้ว " + e.toString());
            }

//            Log.i("profile > ", profile.getFirstName() + " ");

            userName.setText(profile.getFirstName() + " " + profile.getLastName());
            Picasso.with(getApplicationContext())
                    .load(profile.getProfilePictureUri(130, 100))
                    .into(userPic);


            //setuserName = getIntent().getStringExtra("nameUser");
            //setuserPic = getIntent().getStringExtra("url");


            objControl_Databse = new Control_Database(getApplicationContext());


            strMLpName = objControl_Databse.ListMapPlaceName();
            strMLpType = objControl_Databse.ListMapPlaceType();
            strMLIMG = objControl_Databse.ListMapIMG();
            location = objControl_Databse.Location();

            listLat = new double[location.size()];
            listLng = new double[location.size()];


            for (int i = 0; i < location.size(); i++) {
                coordinate = location.get(i);
                listLat[i] = coordinate.latitude;
                listLng[i] = coordinate.longitude;
            }

        } else {
            goLoginScreen();
        }
    }

    public void setNavigator() {
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigationView);

        navigationView.setNavigationItemSelectedListener(this);


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        FragmentManager fragmentManager = getFragmentManager();

        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container,
                        new MainFragment())
                .commit();

    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        bundle = new Bundle();
        FragmentManager fragmentManager = getFragmentManager();
        Intent intent = new Intent(getApplicationContext(), ShowSelect.class);
        switch (item.getItemId()) {

            case R.id.nav_b:
                intent.putExtra("key", "ทะเล");
                startActivity(intent);
                // Toast.makeText(getApplicationContext(), "Beach", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_w:
                intent.putExtra("key", "น้ำตก");
                startActivity(intent);
                // Toast.makeText(getApplicationContext(), "Beach", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_m:
                intent.putExtra("key", "ภูเขา");
                startActivity(intent);
                // Toast.makeText(getApplicationContext(), "Beach", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_c:
                intent.putExtra("key", "ถ้ำ");
                startActivity(intent);
                // Toast.makeText(getApplicationContext(), "Beach", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_canu:
                intent.putExtra("key", "แก่ง");
                startActivity(intent);
                // Toast.makeText(getApplicationContext(), "Beach", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_map:

                fragmentManager.beginTransaction().replace(R.id.fragment_container
                        , new GmapFragment()).commit();
                break;
            case R.id.nav_logout:
                logout();
        }

        DrawerLayout dl = (DrawerLayout) findViewById(R.id.drawerlayout);
        if (dl.isDrawerOpen(GravityCompat.START)) {
            dl.closeDrawer(GravityCompat.START);
        }


        return false;
    }

    public String[] getArray(String what) {
        String[] arr = new String[strMLpName.length];
        if (what == "place") {
            arr = strMLpName;
        } else if (what == "typeplace") {
            arr = strMLpType;
        } else if (what == "img") {
            arr = strMLIMG;
        }
        return arr;

    }

    public double[] getLocation(String what) {
        double[] arr = new double[listLat.length];
        if (what == "lat") {
            arr = listLat;
        } else if (what == "lng") {
            arr = listLng;
        }
        return arr;
    }

    private void goLoginScreen() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void logout() {
        LoginManager.getInstance().logOut();
        goLoginScreen();
    }
}

