package com.example.scontz.khontravel;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by scOnTz on 29/9/2559.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    private DatabaseHelper db;

    private static final String DATABASENAME = "endproject.db";
    private static final int VERSION = 1;


    //TABLE tb_placedetail
    private static final String TABLE_tp_placedetail = "tb_placedetail";
    private static final String Col_p_name = "p_name";
    private static final String Col_ptype_name = "ptype_name";
    private static final String Col_p_des = "p_des";
    private static final String Col_p_travel = "p_travel";
    private static final String Col_p_open = "p_open";
    private static final String Col_p_contact = "p_contact";
    private static final String Col_main_pic = "main_pic";
    private static final String Col_pic_code = "pic_code";
    private static final String Col_latitude = "latitude";
    private static final String Col_longitude = "longitude";
    private static final String Col_p_pos = "p_pos";
    private static final String Col_p_neg = "p_neg";

    //Table userid

    private static final String TABLE_tb_user = "tb_user";
    private static final String Col_uid = "uid";
    private static final String Col_token = "token";

    //Table place review

    private static final String TABLE_tb_review = "tb_review";
    private static final String Col_text_review = "text_review";
    private static final String Col_date = "date";
    private static final String Col_time = "time";
    //Col_p_name
    //Col_uid


    //Create TABLE tb_placedetail

    private static final String Create_tb_placedetail = "create table "
            + TABLE_tp_placedetail
            + " (" + Col_p_name + " text , "
            + Col_ptype_name + " text , "
            + Col_p_des + " text, "
            + Col_p_travel + " text,    "
            + Col_p_open + " text, "
            + Col_p_contact + " text, "
            + Col_main_pic + " text, "
            + Col_pic_code + " text, "
            + Col_latitude + " double, "
            + Col_longitude + " double, "
            + Col_p_pos + " integer, "
            + Col_p_neg + " integer "
            + " ); ";

    //Create TABLE uid

    private static final String Create_tb_user = "CREATE TABLE "
            + TABLE_tb_user
            + " (" + Col_uid + " text PRIMARY KEY "
          //  + Col_token + " text"
            + " );";

    //Create TABLE review

    private static final String Create_tb_review = "CREATE TABLE "
            + TABLE_tb_review
            + " (" + Col_uid + " integer PRIMARY KEY ,"
            + Col_p_name + " text,"
            + Col_text_review + " text,"
            + Col_date + " text,"
            + Col_time + " text"
            + " );";

    public DatabaseHelper(Context context) {

        super(context, DATABASENAME, null, VERSION);

    }//Constructor

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Create_tb_placedetail);
        Log.d("CREATE TABLE", "Create Table 1 Successfully.");
        db.execSQL(Create_tb_review);
        Log.d("CREATE TABLE", "Create Table 2 Successfully.");
        db.execSQL(Create_tb_user);
        Log.d("CREATE TABLE", "Create Table 3 Successfully.");


    }//onCreate

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }//onUpgrade
}//Main

