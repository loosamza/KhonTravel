package com.example.scontz.khontravel;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.util.Log;


import com.google.android.gms.maps.model.LatLng;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by scOnTz on 29/9/2559.
 */
public class Control_Database {
    private DatabaseHelper objDB;
    private SQLiteDatabase writeSQLite, readSQLite;

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
    private static final String Col_username = "username";
    private static final String Col_password = "password";
    private static final String Col_firstname = "firstname";
    private static final String Col_lastname = "lastname";

    //Table place review

    private static final String TABLE_tb_review = "tb_review";
    private static final String Col_text_review = "text_review";
    private static final String Col_date = "date";
    private static final String Col_time = "time";
    //Col_p_name
    //Col_uid

    public Control_Database(Context context) {
        objDB = new DatabaseHelper(context);
        writeSQLite = objDB.getWritableDatabase();
        readSQLite = objDB.getReadableDatabase();
    }//Constructor

    //addValue

    public long addValueToPlaceDetail(String p_name, String ptype_name, String p_des, String p_travel, String p_open, String p_contact, String main_pic, String pic_code, double latitude, double longitude, double p_pos, double p_neg) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(Col_p_name, p_name);
        contentValues.put(Col_ptype_name, ptype_name);
        contentValues.put(Col_p_des, p_des);
        contentValues.put(Col_p_travel, p_travel);
        contentValues.put(Col_p_open, p_open);
        contentValues.put(Col_p_contact, p_contact);
        contentValues.put(Col_main_pic, main_pic);
        contentValues.put(Col_pic_code, pic_code);
        contentValues.put(Col_latitude, latitude);
        contentValues.put(Col_longitude, longitude);
        contentValues.put(Col_p_pos, p_pos);
        contentValues.put(Col_p_neg, p_neg);

        return writeSQLite.insert(TABLE_tp_placedetail, null, contentValues);

    } // add value to table

    public long addValueToUserTable(int uid, String user, String pass, String fname, String lname) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Col_uid, uid);
        contentValues.put(Col_username, user);
        contentValues.put(Col_password, pass);
        contentValues.put(Col_firstname, fname);
        contentValues.put(Col_lastname, lname);


        return writeSQLite.insert(TABLE_tb_user, null, contentValues);
    }


    public long addValueToUID(String uid) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Col_uid, uid);
        //contentValues.put(Col_token, token);
        return writeSQLite.insert(TABLE_tb_user, null, contentValues);
    } // add value to user Table


    public String[] ListPlaceName(String type) {

        String[] strList = null;
        Cursor cursor = readSQLite.rawQuery("SELECT " + Col_p_name + " FROM " + TABLE_tp_placedetail + " WHERE " + Col_ptype_name + " = '" + type + "'", null);
        cursor.moveToFirst();
        strList = new String[cursor.getCount()];
        for (int i = 0; i < cursor.getCount(); i++) {
            strList[i] = cursor.getString(cursor.getColumnIndex(Col_p_name));
            cursor.moveToNext();
        } // for
        cursor.close();
        return strList;
    } // ListPlace

    public String[] ListDes(String type) {

        String[] strList = null;
        Cursor cursor = readSQLite.rawQuery("SELECT " + Col_p_des + " FROM " + TABLE_tp_placedetail + " WHERE " + Col_ptype_name + " = '" + type + "'", null);
        cursor.moveToFirst();
        strList = new String[cursor.getCount()];
        for (int i = 0; i < cursor.getCount(); i++) {
            strList[i] = cursor.getString(cursor.getColumnIndex(Col_p_des));
            cursor.moveToNext();
        } // for
        cursor.close();
        return strList;
    } // ListDes

    public String[] ListTravel(String type) {

        String[] strList = null;
        Cursor cursor = readSQLite.rawQuery("SELECT " + Col_p_travel + " FROM " + TABLE_tp_placedetail + " WHERE " + Col_ptype_name + " = '" + type + "'", null);
        cursor.moveToFirst();
        strList = new String[cursor.getCount()];
        for (int i = 0; i < cursor.getCount(); i++) {
            strList[i] = cursor.getString(cursor.getColumnIndex(Col_p_travel));
            //Log.d("IMG", "place ===> " + strList[i].toString());
            cursor.moveToNext();
        } // for
        cursor.close();
        return strList;
    } // ListTravel

    public String[] ListOpen(String type) {

        String[] strList = null;
        Cursor cursor = readSQLite.rawQuery("SELECT " + Col_p_open + " FROM " + TABLE_tp_placedetail + " WHERE " + Col_ptype_name + " = '" + type + "'", null);
        cursor.moveToFirst();
        strList = new String[cursor.getCount()];
        for (int i = 0; i < cursor.getCount(); i++) {
            strList[i] = cursor.getString(cursor.getColumnIndex(Col_p_open));
            cursor.moveToNext();
        } // for
        cursor.close();
        return strList;
    } // ListOpen

    public String[] ListContact(String type) {

        String[] strList = null;
        Cursor cursor = readSQLite.rawQuery("SELECT " + Col_p_contact + " FROM " + TABLE_tp_placedetail + " WHERE " + Col_ptype_name + " = '" + type + "'", null);
        cursor.moveToFirst();
        strList = new String[cursor.getCount()];
        for (int i = 0; i < cursor.getCount(); i++) {
            strList[i] = cursor.getString(cursor.getColumnIndex(Col_p_contact));
            cursor.moveToNext();
        } // for
        cursor.close();
        return strList;
    } // ListContact

    public String[] ListIMG(String type) {

        String[] strList = null;
        Cursor cursor = readSQLite.rawQuery("SELECT " + Col_main_pic + " FROM " + TABLE_tp_placedetail + " WHERE " + Col_ptype_name + " = '" + type + "'", null);
        cursor.moveToFirst();
        strList = new String[cursor.getCount()];
        for (int i = 0; i < cursor.getCount(); i++) {
            strList[i] = cursor.getString(cursor.getColumnIndex(Col_main_pic));
            cursor.moveToNext();
        } // for
        cursor.close();
        return strList;
    } // ListIMG

    public double[] ListLat(String type) {
        double[] doubList = null;
        Cursor cursor = readSQLite.rawQuery("SELECT " + Col_latitude + " FROM " + TABLE_tp_placedetail + " WHERE " + Col_ptype_name + " = '" + type + "'", null);
        cursor.moveToFirst();
        doubList = new double[cursor.getCount()];
        for (int i = 0; i < cursor.getCount(); i++) {

            doubList[i] = cursor.getDouble(cursor.getColumnIndex(Col_latitude));
            cursor.moveToNext();
        } // for
        cursor.close();
        return doubList;
    } // ListLat

    public double[] ListLng(String type) {
        double[] doubList = null;

        Cursor cursor = readSQLite.rawQuery("SELECT " + Col_longitude + " FROM " + TABLE_tp_placedetail + " WHERE " + Col_ptype_name + " = '" + type + "'", null);
        cursor.moveToFirst();

        doubList = new double[cursor.getCount()];
        for (int i = 0; i < cursor.getCount(); i++) {
            doubList[i] = cursor.getDouble(cursor.getColumnIndex(Col_longitude));
            cursor.moveToNext();

        } // for
        cursor.close();
        return doubList;
    } // ListLng

    public float[] ListPos(String type) {
        float[] doubList = null;

        Cursor cursor = readSQLite.rawQuery("SELECT " + Col_p_pos + " FROM " + TABLE_tp_placedetail + " WHERE " + Col_ptype_name + " = '" + type + "'", null);
        cursor.moveToFirst();

        doubList = new float[cursor.getCount()];
        for (int i = 0; i < cursor.getCount(); i++) {
            doubList[i] = cursor.getFloat(cursor.getColumnIndex(Col_p_pos));
            cursor.moveToNext();

        } // for
        cursor.close();
        return doubList;
    } // ListPos

    public float[] ListNeg(String type) {
        float[] doubList = null;

        Cursor cursor = readSQLite.rawQuery("SELECT " + Col_p_neg + " FROM " + TABLE_tp_placedetail + " WHERE " + Col_ptype_name + " = '" + type + "'", null);
        cursor.moveToFirst();

        doubList = new float[cursor.getCount()];
        for (int i = 0; i < cursor.getCount(); i++) {
            doubList[i] = cursor.getFloat(cursor.getColumnIndex(Col_p_neg));
            cursor.moveToNext();

        } // for
        cursor.close();
        return doubList;
    } // ListNeg


    //////////////////////////// LIST FOR MAP VIEW ////////////////////////////
    public List<LatLng> Location() {
        List<LatLng> coordinates = new ArrayList<LatLng>();
        double[] lat = null, lng = null;

        Cursor cursor = readSQLite.rawQuery("SELECT " + Col_latitude + " , " + Col_longitude + " FROM " + TABLE_tp_placedetail, null);
        cursor.moveToFirst();
        lat = new double[cursor.getCount()];
        lng = new double[cursor.getCount()];
        for (int i = 0; i < lat.length; i++) {
            coordinates.add(new LatLng(cursor.getDouble((cursor.getColumnIndex(Col_latitude))),
                    (cursor.getDouble(cursor.getColumnIndex(Col_longitude)))));

            cursor.moveToNext();
        } // for

        Log.d("Size Location", "Size >>>> " + coordinates.get(0) + " ," + coordinates.get(1));
        return coordinates;
    } // ListLocation

    public String[] ListMapIMG() {

        String[] strList = null;
        Cursor cursor = readSQLite.rawQuery("SELECT " + Col_main_pic +
                " FROM " + TABLE_tp_placedetail, null);
        cursor.moveToFirst();
        strList = new String[cursor.getCount()];
        for (int i = 0; i < cursor.getCount(); i++) {
            strList[i] = cursor.getString(cursor.getColumnIndex(Col_main_pic));
            cursor.moveToNext();
        } // for
        cursor.close();
        return strList;
    } // ListMapIMG

    public String[] ListMapPlaceName() {

        String[] strList = null;
        Cursor cursor = readSQLite.rawQuery("SELECT " + Col_p_name +
                " FROM " + TABLE_tp_placedetail, null);
        cursor.moveToFirst();
        strList = new String[cursor.getCount()];
        for (int i = 0; i < cursor.getCount(); i++) {
            strList[i] = cursor.getString(cursor.getColumnIndex(Col_p_name));
            cursor.moveToNext();
        } // for
        cursor.close();
        return strList;
    } // ListMapPlace

    public String[] ListMapPlaceType() {

        String[] strList = null;
        Cursor cursor = readSQLite.rawQuery("SELECT " + Col_ptype_name +
                " FROM " + TABLE_tp_placedetail, null);
        cursor.moveToFirst();
        strList = new String[cursor.getCount()];
        for (int i = 0; i < cursor.getCount(); i++) {
            strList[i] = cursor.getString(cursor.getColumnIndex(Col_ptype_name));
            cursor.moveToNext();
        } // for
        cursor.close();
        return strList;
    } // ListMapPlaceType

    public String[] ListMapDes() {

        String[] strList = null;
        Cursor cursor = readSQLite.rawQuery("SELECT " + Col_p_des + " FROM " + TABLE_tp_placedetail, null);
        cursor.moveToFirst();
        strList = new String[cursor.getCount()];
        for (int i = 0; i < cursor.getCount(); i++) {
            strList[i] = cursor.getString(cursor.getColumnIndex(Col_p_des));
            cursor.moveToNext();
        } // for
        cursor.close();
        return strList;
    } // ListMapDes

    public String[] ListMapTravel() {

        String[] strList = null;
        Cursor cursor = readSQLite.rawQuery("SELECT " + Col_p_travel + " FROM " + TABLE_tp_placedetail, null);
        cursor.moveToFirst();
        strList = new String[cursor.getCount()];
        for (int i = 0; i < cursor.getCount(); i++) {
            strList[i] = cursor.getString(cursor.getColumnIndex(Col_p_travel));
            //Log.d("IMG", "place ===> " + strList[i].toString());
            cursor.moveToNext();
        } // for
        cursor.close();
        return strList;
    } // ListMapTravel

    public String[] ListMapOpen() {

        String[] strList = null;
        Cursor cursor = readSQLite.rawQuery("SELECT " + Col_p_open + " FROM " + TABLE_tp_placedetail, null);
        cursor.moveToFirst();
        strList = new String[cursor.getCount()];
        for (int i = 0; i < cursor.getCount(); i++) {
            strList[i] = cursor.getString(cursor.getColumnIndex(Col_p_open));
            cursor.moveToNext();
        } // for
        cursor.close();
        return strList;
    } // ListMapOpen

    public String[] ListMapContact() {

        String[] strList = null;
        Cursor cursor = readSQLite.rawQuery("SELECT " + Col_p_contact + " FROM " + TABLE_tp_placedetail, null);
        cursor.moveToFirst();
        strList = new String[cursor.getCount()];
        for (int i = 0; i < cursor.getCount(); i++) {
            strList[i] = cursor.getString(cursor.getColumnIndex(Col_p_contact));
            cursor.moveToNext();
        } // for
        cursor.close();
        return strList;
    } // ListMapContact

    //////////////////////////// EMD LIST FOR MAP VIEW ////////////////////////////
    public String[] AllList(String column) {
        String[] strList = null;
        Cursor cursor = readSQLite.rawQuery("SELECT " + column + " FROM " + TABLE_tp_placedetail, null);
        cursor.moveToFirst();
        strList = new String[cursor.getCount()];
        try {
            for (int i = 0; i < cursor.getCount(); i++) {
                strList[i] = cursor.getString(cursor.getColumnIndex(column));
                cursor.moveToNext();
            } // for
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        cursor.close();
        return strList;
    } //AllList


    public String[][] getDistance(LatLng my_latlong, List<LatLng> frnd_latlong, String myPlaceName, String[] frnd_PlaceName) {

        ArrayList<Float> dist = new ArrayList<Float>();
        // float[] dist = new float[frnd_latlong.size()];
        ArrayList<String> frnd = new ArrayList<String>();
        // String[] frnd = new String[frnd_latlong.size()];


        String recLoc[][] = new String[5][2];
        Location l1 = new Location(myPlaceName);
        l1.setLatitude(my_latlong.latitude);
        l1.setLongitude(my_latlong.longitude);


        for (int i = 0; i < frnd_latlong.size(); i++) {

            if ((myPlaceName.equals(frnd_PlaceName[i]) == false)) {
                Location l2 = new Location(frnd_PlaceName[i]);
                LatLng frnd_Location = frnd_latlong.get(i);
                l2.setLatitude(frnd_Location.latitude);
                l2.setLongitude(frnd_Location.longitude);
                float distance = l1.distanceTo(l2);
                distance = distance / 1000.0f;
                DecimalFormat df = new DecimalFormat("0.000");

                dist.add(Float.parseFloat(df.format(distance)));
                frnd.add(frnd_PlaceName[i]);
            }


        }

        Log.d("nameTag", "เดิม" + Arrays.toString(frnd.toArray()) + "\n " + Arrays.toString(dist.toArray()));
        ArrayList<Float> distsort = (ArrayList<Float>) dist.clone();
        Collections.sort(distsort);
        int[] index = getIndices(dist);

        Log.d("nameTag", "เรียง" + Arrays.toString(index) + "\n " + Arrays.toString(distsort.toArray()));

        // Log.d("check", "ระยะเรียง " + Arrays.toString(distsort) + " ไม่เรียง " + Arrays.toString(dist) + " อินเด็ก " + Arrays.toString(index) + " ชื่อที่ " + Arrays.toString(frnd_PlaceName));

        for (int i = 0; i < 5; i++) {
            recLoc[i][0] = frnd.get(index[i]);
            for (int j = 1; j < 2; j++) {
                recLoc[i][j] = distsort.get(i) + "";

            }
        }

        return recLoc;
    }//get near location

    //////////////////////// get for nearplace //////////

    public String getImg(String place) {
        String url = null;
        Cursor cursor = readSQLite.rawQuery("SELECT " + Col_main_pic + " FROM " + TABLE_tp_placedetail
                + " WHERE " + Col_p_name + " = '" + place + "'", null);
        cursor.moveToFirst();

        try {

            url = cursor.getString(cursor.getColumnIndex(Col_main_pic));
            cursor.moveToNext();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        cursor.close();
        return url;

    }

    public String getDes(String place) {

        String url = null;
        Cursor cursor = readSQLite.rawQuery("SELECT " + Col_p_des + " FROM " + TABLE_tp_placedetail
                + " WHERE " + Col_p_name + " = '" + place + "'", null);
        cursor.moveToFirst();

        try {

            url = cursor.getString(cursor.getColumnIndex(Col_p_des));
            cursor.moveToNext();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        cursor.close();
        return url;
    } // getDes

    public String getTravel(String place) {

        String url = null;
        Cursor cursor = readSQLite.rawQuery("SELECT " + Col_p_travel + " FROM " + TABLE_tp_placedetail
                + " WHERE " + Col_p_name + " = '" + place + "'", null);
        cursor.moveToFirst();

        try {

            url = cursor.getString(cursor.getColumnIndex(Col_p_travel));
            cursor.moveToNext();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        cursor.close();
        return url;
    } // getTravel

    public String getOpen(String place) {

        String url = null;
        Cursor cursor = readSQLite.rawQuery("SELECT " + Col_p_open + " FROM " + TABLE_tp_placedetail
                + " WHERE " + Col_p_name + " = '" + place + "'", null);
        cursor.moveToFirst();

        try {

            url = cursor.getString(cursor.getColumnIndex(Col_p_open));
            cursor.moveToNext();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        cursor.close();
        return url;
    } // getOpen

    public String getContact(String place) {

        String url = null;
        Cursor cursor = readSQLite.rawQuery("SELECT " + Col_p_contact + " FROM " + TABLE_tp_placedetail
                + " WHERE " + Col_p_name + " = '" + place + "'", null);
        cursor.moveToFirst();

        try {

            url = cursor.getString(cursor.getColumnIndex(Col_p_contact));
            cursor.moveToNext();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        cursor.close();
        return url;
    } // getContact


    public double getLat(String place) {
        double lat;
        Cursor cursor = readSQLite.rawQuery("SELECT " + Col_latitude + " FROM " + TABLE_tp_placedetail
                + " WHERE " + Col_p_name + " = '" + place + "'", null);
        cursor.moveToFirst();

        try {

            lat = cursor.getDouble(cursor.getColumnIndex(Col_latitude));
            cursor.moveToNext();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        cursor.close();
        return lat;
    } // getLat

    public double getLng(String place) {
        double lng;
        Cursor cursor = readSQLite.rawQuery("SELECT " + Col_longitude + " FROM " + TABLE_tp_placedetail
                + " WHERE " + Col_p_name + " = '" + place + "'", null);
        cursor.moveToFirst();

        try {

            lng = cursor.getDouble(cursor.getColumnIndex(Col_longitude));
            cursor.moveToNext();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        cursor.close();
        return lng;
    } // getLng

    public static int[] getIndices(ArrayList<Float> originalArray) {
        int len = originalArray.size();

        ArrayList<Float> sortedCopy = (ArrayList<Float>) originalArray.clone();
        int[] indices = new int[len];

        // Sort the copy
        Collections.sort(sortedCopy);

        // Go through the original array: for the same index, fill the position where the
        // corresponding number is in the sorted array in the indices array
        for (int index = 0; index < len; index++) {
            //indices[index] = Collections.binarySearch(sortedCopy, originalArray.get(index));
            indices[index] = originalArray.indexOf(sortedCopy.get(index));
        }

        return indices;
    }//getIndex
}
