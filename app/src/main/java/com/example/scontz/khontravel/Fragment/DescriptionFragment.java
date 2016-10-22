package com.example.scontz.khontravel.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.scontz.khontravel.Fonts.TypefaceUti;
import com.example.scontz.khontravel.R;

import com.squareup.picasso.Picasso;

/**
 * Created by scOnTz on 30/9/2559.
 */
public class DescriptionFragment extends Fragment {
    TextView txtPlaceName, txtPlaceDes, txtPlaceTravel, txtPlaceOpen, txtPlaceContact, txtNearPlace;
    ImageView img;
    String a, b, c, d, e, f;
    double lat, lng;
    String[][] location = null;
    View myView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        TypefaceUti.overrideFont(getActivity(), "SERIF", "bj.ttf");
        myView = inflater.inflate(R.layout.des_layout, container, false);
        initWidget(myView);

        setAll();


        return myView;
    }

    private void initWidget(View view) {
        img = (ImageView) view.findViewById(R.id.img);
        txtPlaceName = (TextView) view.findViewById(R.id.txtPlaceName);
        txtPlaceDes = (TextView) view.findViewById(R.id.txtPlaceDes);
        txtPlaceTravel = (TextView) view.findViewById(R.id.txtPlaceTravel);
        txtPlaceOpen = (TextView) view.findViewById(R.id.txtPlaceOpen);
        txtPlaceContact = (TextView) view.findViewById(R.id.txtPlaceContact);

        a = getActivity().getIntent().getExtras().getString("strPlaceName");
        b = getActivity().getIntent().getExtras().getString("strDes");
        c = getActivity().getIntent().getExtras().getString("strTravel");
        d = getActivity().getIntent().getExtras().getString("strOpen");
        e = getActivity().getIntent().getExtras().getString("strImg");
        f = getActivity().getIntent().getExtras().getString("strContact");

       // Toast.makeText(getActivity(), a, Toast.LENGTH_SHORT).show();


    }// find id get Intent

    private void setAll() {
        Picasso.with(getActivity()).load(e).resize(300, 100).into(img);
        txtPlaceName.setText(a);
        txtPlaceDes.setText(b);
        txtPlaceTravel.setText(c);
        txtPlaceOpen.setText(d);
        txtPlaceContact.setText(f);
    } //Set data
}
