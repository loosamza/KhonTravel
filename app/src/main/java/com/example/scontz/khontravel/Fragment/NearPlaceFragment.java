package com.example.scontz.khontravel.Fragment;

import android.app.ActionBar;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.scontz.khontravel.Control_Database;
import com.example.scontz.khontravel.Fonts.TypefaceUti;
import com.example.scontz.khontravel.R;
import com.example.scontz.khontravel.Result;
import com.example.scontz.khontravel.ShowSelect;
import com.google.android.gms.maps.model.LatLng;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.Random;

/**
 * Created by scOnTz on 3/10/2559.
 */
public class NearPlaceFragment extends Fragment {

    TextView p1, p2, p3;
    String[][] location = null;
    String[] strPlaceName = new String[5], strDes = new String[5], strTravel = new String[5], strOpen = new String[5], strContact = new String[5], strImg = new String[5]; //ตัวโยนค่า
    Control_Database objControl_Database;
    double[] doubLat = new double[5], doubLng = new double[5];
    View myView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        TypefaceUti.overrideFont(getActivity(), "SERIF", "bj.ttf");
        myView = inflater.inflate(R.layout.nearplace_layout, container, false);
        initWidget(myView);
        setAll(myView);
        return myView;
    }

    private void initWidget(View view) {


        Object[] objectArray = (Object[]) getActivity().getIntent().getExtras().getSerializable("nearLocation");
        if (objectArray != null) {
            location = new String[objectArray.length][];
            for (int i = 0; i < objectArray.length; i++) {
                location[i] = (String[]) objectArray[i];
            }
        }

    }

    private void setAll(View view) {
        objControl_Database = new Control_Database(getActivity());
        LinearLayout linearLayout = (LinearLayout) myView.findViewById(R.id.llnear);

        Random rand = new Random();

        //  p1.setText("");

        for (int i = 0; i < 5; i++) {
            int r = rand.nextInt(255);
            int g = rand.nextInt(255);
            int b = rand.nextInt(255);


            final TextView textView = new TextView(getActivity());
            final ImageView imageView = new ImageView(getActivity());

            //p1.append(location[i][0] + "\t" + "\t");
            textView.setId(i);
            imageView.setId(i);
            final String place = location[i][0];
            strImg[i] = objControl_Database.getImg(place);
            final String url = strImg[i];
            imageView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            textView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            Picasso.with(getActivity()).load(url).resize(200, 150).into(imageView);
            textView.setText(place + "\t");
            textView.setTypeface(Typeface.SERIF);
            textView.setTextSize(18);
            textView.setTextColor(Color.rgb(r, g, b));
            imageView.setPadding(0, 20, 0, 10);
            textView.setPadding(0, 0, 0, 20);
            textView.setPaintFlags(textView.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

            strPlaceName[i] = place;
            strDes[i] = objControl_Database.getDes(place);
            strTravel[i] = objControl_Database.getTravel(place);
            strOpen[i] = objControl_Database.getOpen(place);
            strContact[i] = objControl_Database.getContact(place);

            doubLat[i] = objControl_Database.getLat(place);
            doubLng[i] = objControl_Database.getLng(place);

            try {

                linearLayout.addView(imageView);
                linearLayout.addView(textView);
            } catch (Exception e) {
                e.printStackTrace();
            }
            textView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    switch (v.getId()) {
                        case 0:
                            //Toast.makeText(getActivity(), strPlaceName[0] + " id = " + textView.getId(), Toast.LENGTH_SHORT).show();
                            setIntent(0);
                            break;
                        case 1:
                            setIntent(1);

                            //Toast.makeText(getActivity(), strPlaceName[1] + " id = " + textView.getId(), Toast.LENGTH_SHORT).show();
                            break;
                        case 2:
                            setIntent(2);
                            //Toast.makeText(getActivity(), strPlaceName[1] + " id = " + textView.getId(), Toast.LENGTH_SHORT).show();
                            break;
                        case 3:

                            setIntent(3);
                            //Toast.makeText(getActivity(), strPlaceName[1] + " id = " + textView.getId(), Toast.LENGTH_SHORT).show();
                            break;
                        case 4:
                            setIntent(4);

                            //Toast.makeText(getActivity(), strPlaceName[1] + " id = " + textView.getId(), Toast.LENGTH_SHORT).show();
                            break;
                    }




/*

                   // Toast.makeText(getActivity(), place + " id " + textView.getId(), Toast.LENGTH_SHORT).show();
*/

                }
            });


            for (int j = 1; j < 2; j++) {
                textView.append("\n" + "ระยะทาง " + location[i][j].toString() + " กิโลเมตร \n");
            }


        }
    }

    public void setIntent(int i) {
        Intent intent = new Intent(getActivity(), Result.class);
        LatLng t;
        String[][] te;
        Bundle b = new Bundle();
        intent.putExtra("strPlaceName", strPlaceName[i]);
        intent.putExtra("strDes", strDes[i]);
        intent.putExtra("strTravel", strTravel[i]);
        intent.putExtra("strOpen", strOpen[i]);
        intent.putExtra("strContact", strContact[i]);
        intent.putExtra("strImg", strImg[i]);
        t = new LatLng(doubLat[i], doubLng[i]);

        te = objControl_Database.getDistance(t, objControl_Database.Location()
                , strPlaceName[i], objControl_Database.AllList("p_name"));

        b.putSerializable("nearLocation", te);
        // b.putStringArray("nearLocation",te);
        intent.putExtras(b);

        intent.putExtra("doubLat", doubLat[i]);
        intent.putExtra("doubLng", doubLng[i]);
        startActivity(intent);

    }
}
