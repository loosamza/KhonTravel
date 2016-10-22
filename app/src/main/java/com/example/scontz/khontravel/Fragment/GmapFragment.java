package com.example.scontz.khontravel.Fragment;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.scontz.khontravel.MainActivity;
import com.example.scontz.khontravel.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;

/**
 * Created by scOnTz on 1/10/2559.
 */
public class GmapFragment extends Fragment implements OnMapReadyCallback {
    String[] strMLpName, strMLpType, strMLIMG;
    double[] listLat, listLng;
    View myView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.gmap_layout, container, false);
        initWidget();
        return myView;
    }

    private void initWidget() {
        MainActivity activity = (MainActivity) getActivity();
        strMLpName = activity.getArray("place");
        strMLpType = activity.getArray("typeplace");
        strMLIMG = activity.getArray("img");

        listLat = activity.getLocation("lat");
        listLng = activity.getLocation("lng");

        //Log.i("Test pass data ", Arrays.toString(strMLpName));

    }



    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MapFragment fragment = (MapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        fragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {


        MarkerOptions marker = new MarkerOptions();

        for (int i = 0; i < strMLpName.length; i++) {

            LatLng coordinate = new LatLng(listLat[i], listLng[i]);
            String toolTip = strMLpName[i];
            Bitmap icon = getResizedBitmap(getBitmapFromURL(strMLIMG[i]), 60, 40);

            googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(coordinate, 10));

            marker.position(coordinate);
            marker.title(toolTip);
            marker.icon(BitmapDescriptorFactory.fromBitmap(icon));
            googleMap.addMarker(marker);
        }


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
