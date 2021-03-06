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

import com.example.scontz.khontravel.Control_Database;
import com.example.scontz.khontravel.MainActivity;
import com.example.scontz.khontravel.Method.MyAlert;
import com.example.scontz.khontravel.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
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
    String[] strMLpName, strMLpType, strMLIMG, strMLdes, strMLTravel, strMLOpen, strMLContact;
    double[] listLat, listLng;
    View myView;
    private Control_Database objControl_Database;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.gmap_layout, container, false);
        objControl_Database = new Control_Database(getActivity());
        initWidget();
        return myView;
    }

    private void initWidget() {
        MainActivity activity = (MainActivity) getActivity();
        strMLpName = activity.getArray("place");
        strMLpType = activity.getArray("typeplace");
        strMLdes = activity.getArray("des");
        strMLOpen = activity.getArray("open");
        strMLTravel = activity.getArray("travel");
        strMLContact = activity.getArray("contact");
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
            googleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                @Override
                public void onInfoWindowClick(Marker marker) {
                    int _id = Integer.parseInt(marker.getId().replace("m", ""));
                    LatLng t = new LatLng(listLat[_id], listLng[_id]);
                    String[][] te = objControl_Database.getDistance(t, objControl_Database.Location(), strMLpName[_id], objControl_Database.AllList("p_name"));
                    final String[] strPassing = {strMLpType[_id], strMLpName[_id], strMLdes[_id]
                            , strMLTravel[_id], strMLOpen[_id], strMLContact[_id], strMLIMG[_id]};
                    MyAlert myAlert = new MyAlert();
                    myAlert.myDialog(getActivity(), strMLpName[_id], strPassing, te,t);
                    //Log.d("mapLog", ">>>" + Arrays.deepToString(te));


                }
            });


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
