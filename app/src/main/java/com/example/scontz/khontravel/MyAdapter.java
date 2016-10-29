package com.example.scontz.khontravel;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.akexorcist.roundcornerprogressbar.IconRoundCornerProgressBar;
import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar;
import com.squareup.picasso.Picasso;

/**
 * Created by scOnTz on 29/9/2559.
 */
public class MyAdapter extends BaseAdapter {
    private Context context;
    private String[] strListPlaceName, strListImg;
    private double[] pos, neg;
    private String strGetURL;

    public MyAdapter(Context context, String[] strListPlaceName, String[] strListImg, double[] pos, double[] neg) {
        this.context = context;
        this.strListPlaceName = strListPlaceName;
        this.strListImg = strListImg;
        this.pos = pos;
        this.neg = neg;
        this.strGetURL = strGetURL;
    }//Constructor

    @Override
    public int getCount() {
        return strListPlaceName.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.listview_select, parent, false);


        //Set up Text Place Name
        TextView listPlace = (TextView) view.findViewById(R.id.txtPname);

        //Set up Main Image
        ImageView listImg = (ImageView) view.findViewById(R.id.main_pic);

        //Set up Progress Bar

        IconRoundCornerProgressBar posProgressBar = (IconRoundCornerProgressBar) view.findViewById(R.id.p_progressbar);
        posProgressBar.setProgressColor(Color.parseColor("#00ef7b"));
        posProgressBar.setIconBackgroundColor(Color.parseColor("#005b2f"));
        posProgressBar.setMax(100);
        posProgressBar.setProgress((float) pos[position]);
        posProgressBar.setIconImageResource(R.drawable.icon_pos);
        posProgressBar.setRadius(10);

        IconRoundCornerProgressBar negProgressBar = (IconRoundCornerProgressBar) view.findViewById(R.id.n_progressbar);
        negProgressBar.setProgressColor(Color.parseColor("#f9584f"));
        negProgressBar.setIconBackgroundColor(Color.parseColor("#fc0c00"));
        negProgressBar.setMax(100);
        negProgressBar.setProgress((float) neg[position]);
        negProgressBar.setIconImageResource(R.drawable.icon_neg);
        negProgressBar.setRadius(10);


        Picasso.with(this.context).load(strListImg[position]).resize(200, 150).into(listImg);
        //Picasso.with(context).load("https://upic.me/i/gb/i0b01.png").into(listImg);
        listPlace.setText(strListPlaceName[position]);
        //Log.d("IMG", "IMG >>>> " + strListImg[position].toString());


        return view;
    }
}//Main
