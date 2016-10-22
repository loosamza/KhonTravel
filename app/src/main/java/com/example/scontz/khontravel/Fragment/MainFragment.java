package com.example.scontz.khontravel.Fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.scontz.khontravel.R;
import com.example.scontz.khontravel.ShowSelect;

/**
 * Created by scOnTz on 1/10/2559.
 */
public class MainFragment extends Fragment {
    Button b, w, m, c, k;
    View myView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.main_layout, container, false);
        initWidget(myView);

        return myView;
    }

    public void initWidget(View view) {
        b = (Button) view.findViewById(R.id.b);
        w = (Button) view.findViewById(R.id.w);
        m = (Button) view.findViewById(R.id.m);
        c = (Button) view.findViewById(R.id.c);
        k = (Button) view.findViewById(R.id.k);

        b.setOnClickListener(new View.OnClickListener() {
            Intent intent = new Intent(getActivity().getBaseContext(), ShowSelect.class);

            @Override
            public void onClick(View v) {
                intent.putExtra("key", "ทะเล");

                getActivity().startActivity(intent);

            }
        });
        w.setOnClickListener(new View.OnClickListener() {
            Intent intent = new Intent(getActivity().getBaseContext(), ShowSelect.class);

            @Override
            public void onClick(View v) {
                intent.putExtra("key", "น้ำตก");
                getActivity().startActivity(intent);

            }
        });
        m.setOnClickListener(new View.OnClickListener() {
            Intent intent = new Intent(getActivity().getBaseContext(), ShowSelect.class);

            @Override
            public void onClick(View v) {
                intent.putExtra("key", "ภูเขา");
                getActivity().startActivity(intent);

            }
        });
        c.setOnClickListener(new View.OnClickListener() {
            Intent intent = new Intent(getActivity().getBaseContext(), ShowSelect.class);

            @Override
            public void onClick(View v) {
                intent.putExtra("key", "ถ้ำ");
                getActivity().startActivity(intent);

            }
        });
        k.setOnClickListener(new View.OnClickListener() {
            Intent intent = new Intent(getActivity().getBaseContext(), ShowSelect.class);

            @Override
            public void onClick(View v) {
                intent.putExtra("key", "แก่ง");
                getActivity().startActivity(intent);

            }
        });


    }

    public void goSelect(View view) {
        Intent intent = new Intent(getActivity(), ShowSelect.class);
        switch (view.getId()) {
            case R.id.b:
                intent.putExtra("key", "ทะเล");
                startActivity(intent);
                break;
            case R.id.w:
                intent.putExtra("key", "น้ำตก");
                startActivity(intent);
                break;
            case R.id.m:
                intent.putExtra("key", "ภูเขา");
                startActivity(intent);
                break;
            case R.id.c:
                intent.putExtra("key", "ถ้ำ");
                startActivity(intent);
                break;
            case R.id.k:
                intent.putExtra("key", "แก่ง");
                startActivity(intent);
                break;

        }
    }


}
