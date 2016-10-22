package com.example.scontz.khontravel.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.scontz.khontravel.Fonts.TypefaceUti;
import com.example.scontz.khontravel.R;

/**
 * Created by scOnTz on 30/9/2559.
 */
public class ReviewFragment extends Fragment {

    View myView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        TypefaceUti.overrideFont(getActivity(), "SERIF", "bj.ttf");
        myView = inflater.inflate(R.layout.review_layout,container,false);
        return myView;
    }//oncreate
}//main
