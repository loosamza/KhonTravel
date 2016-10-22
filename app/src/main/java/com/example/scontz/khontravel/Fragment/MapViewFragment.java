package com.example.scontz.khontravel.Fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.example.scontz.khontravel.Fonts.TypefaceUti;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.example.scontz.khontravel.R;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by scOnTz on 30/9/2559.
 */
public class MapViewFragment extends Fragment implements OnMapReadyCallback {
    View myView;
    MapView mMapView;
    private GoogleMap googleMap;
    private Double Latitude = 0.00;
    private Double Longitude = 0.00;
    String placeName, imgURL;
    Bitmap bitmapIcon;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        TypefaceUti.overrideFont(getActivity(), "SERIF", "bj.ttf");
        myView = inflater.inflate(R.layout.mapview_layout, container, false);

        initWidget();

        touchListener(myView);


        bitmapIcon = getResizedBitmap(getBitmapFromURL(imgURL), 80, 50);

        return myView;


    }

    private void initWidget() {
        Latitude = getActivity().getIntent().getExtras().getDouble("doubLat");
        Longitude = getActivity().getIntent().getExtras().getDouble("doubLng");
        placeName = getActivity().getIntent().getExtras().getString("strPlaceName");
        imgURL = getActivity().getIntent().getExtras().getString("strImg");


    }


    public void touchListener(View view) {
        final ScrollView mainScrollView = (ScrollView) view.findViewById(R.id.sv_container);
        ImageView transparentImageView = (ImageView) view.findViewById(R.id.transparent_image);

        transparentImageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        mainScrollView.requestDisallowInterceptTouchEvent(true);
                        return false;
                    case MotionEvent.ACTION_UP:
                        mainScrollView.requestDisallowInterceptTouchEvent(false);
                        return true;
                    case MotionEvent.ACTION_MOVE:
                        mainScrollView.requestDisallowInterceptTouchEvent(true);
                        return false;
                    default:
                        return true;
                }
            }
        });
    }


       /* mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                googleMap = mMap;

                if (Latitude > 0 && Longitude > 0) {
                    //*** Display Google Map

                    // googleMap = mMapFragment.getMap();


                    googleMap.getUiSettings().setZoomControlsEnabled(true);

                    //*** Focus & Zoom

                    LatLng coordinate = new LatLng(Latitude, Longitude);
                    googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                    googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(coordinate, 20));

                    //*** Marker
                    String toolTip = placeName;
                    MarkerOptions marker = new MarkerOptions().position(coordinate).title(toolTip);
                    googleMap.addMarker(marker);

                }


            }
        });*/


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MapFragment fragment = (MapFragment) getChildFragmentManager().findFragmentById(R.id.MapView);

        fragment.getMapAsync(this);
    }

    public MapViewFragment() {
        super();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng coordinate = new LatLng(Latitude, Longitude);
        googleMap.getUiSettings().setZoomControlsEnabled(true);

        //*** Focus & Zoom


        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(coordinate, 13));


        //*** Marker
        String toolTip = placeName;
        MarkerOptions marker = new MarkerOptions().position(coordinate).title(toolTip).icon(BitmapDescriptorFactory.fromBitmap(bitmapIcon));
        googleMap.addMarker(marker);

    }

    public Bitmap getBitmapFromURL(String imageUrl) {
        try {
            URL url = new URL(imageUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            Log.d("url", "url get Done!!");
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            Log.d("url", "url get error!!");
            return null;
        }
    }

    public Bitmap getResizedBitmap(Bitmap bm, int newWidth, int newHeight) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // CREATE A MATRIX FOR THE MANIPULATION
        Matrix matrix = new Matrix();
        // RESIZE THE BIT MAP
        matrix.postScale(scaleWidth, scaleHeight);

        // "RECREATE" THE NEW BITMAP
        Bitmap resizedBitmap = Bitmap.createBitmap(
                bm, 0, 0, width, height, matrix, false);
        bm.recycle();
        return resizedBitmap;
    }
}
