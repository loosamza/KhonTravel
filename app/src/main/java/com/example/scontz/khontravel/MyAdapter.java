package com.example.scontz.khontravel;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.akexorcist.roundcornerprogressbar.IconRoundCornerProgressBar;
import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar;
import com.akexorcist.roundcornerprogressbar.TextRoundCornerProgressBar;
import com.squareup.picasso.Picasso;

/**
 * Created by scOnTz on 29/9/2559.
 */
public class MyAdapter extends BaseAdapter {
    private Context context;
    private String[] strListPlaceName, strListImg;
    private float[] pos, neg;
    private String strGetURL;
    private String type;

    public MyAdapter(Context context, String[] strListPlaceName, String[] strListImg, float[] pos, float[] neg, String type) {
        this.context = context;
        this.strListPlaceName = strListPlaceName;
        this.strListImg = strListImg;
        this.pos = pos;
        this.neg = neg;
        this.type = type;
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

        TextRoundCornerProgressBar posProgressBar = (TextRoundCornerProgressBar) view.findViewById(R.id.pos_progressbar);
        posProgressBar.setProgressColor(Color.parseColor("#00ef7b"));
        posProgressBar.setProgressBackgroundColor(Color.parseColor("#dcede5"));
        posProgressBar.setProgressText("" + pos[position] + "%");
        posProgressBar.setTextProgressColor(Color.BLACK);
        posProgressBar.setTextProgressSize(10);
        posProgressBar.setTextProgressMargin(2);
        posProgressBar.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        posProgressBar.setMax(100);
        posProgressBar.setProgress(pos[position]);
        posProgressBar.setRadius(5);

        TextRoundCornerProgressBar negProgressBar = (TextRoundCornerProgressBar) view.findViewById(R.id.neg_progressbar);
        negProgressBar.setProgressColor(Color.parseColor("#f9584f"));
        negProgressBar.setProgressBackgroundColor(Color.parseColor("#dcede5"));
        negProgressBar.setProgressText("" + neg[position] + "%");
        negProgressBar.setTextProgressColor(Color.BLACK);
        negProgressBar.setTextProgressSize(10);
        negProgressBar.setTextProgressMargin(2);
        negProgressBar.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        negProgressBar.setMax(100);
        negProgressBar.setProgress(neg[position]);

        negProgressBar.setRadius(5);


        Picasso.with(this.context).load(strListImg[position]).resize(200, 150).into(listImg);
        //Picasso.with(context).load("https://upic.me/i/gb/i0b01.png").into(listImg);
        listPlace.setText(strListPlaceName[position]);
        //Log.d("IMG", "IMG >>>> " + strListImg[position].toString());

        LinearLayout bg = (LinearLayout) view.findViewById(R.id.pn_bg);
        LinearLayout show = (LinearLayout) view.findViewById(R.id.show);
        if (type.equals("ทะเล")) {
            bg.setBackgroundColor(Color.parseColor("#b66af4"));
            show.setBackgroundColor(Color.parseColor("#b66af4"));
        } else if (type.equals("น้ำตก")) {
            bg.setBackgroundColor(Color.parseColor("#ff62a1"));
            show.setBackgroundColor(Color.parseColor("#ff62a1"));
        } else if (type.equals("ถ้ำ")) {
            bg.setBackgroundColor(Color.parseColor("#58ffbf"));
            show.setBackgroundColor(Color.parseColor("#58ffbf"));
        } else if (type.equals("แก่ง")) {
            bg.setBackgroundColor(Color.parseColor("#f7604f"));
            show.setBackgroundColor(Color.parseColor("#f7604f"));
        } else if (type.equals("ภูเขา")) {
            bg.setBackgroundColor(Color.parseColor("#ffd665"));
            show.setBackgroundColor(Color.parseColor("#ffd665"));
        }


        return view;
    }


}//Main
