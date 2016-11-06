package com.example.scontz.khontravel.Method;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.scontz.khontravel.Result;
import com.google.android.gms.maps.model.LatLng;

/**
 * Created by scOnTz on 6/11/2559.
 */
public class MyAlert {
    public void myDialog(final Context context,
                         String strTitle,
                         final String[] strPassing,
                         final String[][] strPassing2,
                         final LatLng location) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(strTitle);
        builder.setMessage("คุณต้องการดูรายละเอียด " + strTitle + " ใช่หรือไม่?");
        builder.setCancelable(true);
        builder.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                //Toast.makeText(context, "OK Active", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, Result.class);
                intent.putExtra("strType", strPassing[0]);
                intent.putExtra("strPlaceName", strPassing[1]);
                intent.putExtra("strDes", strPassing[2]);
                intent.putExtra("strTravel", strPassing[3]);
                intent.putExtra("strOpen", strPassing[4]);
                intent.putExtra("strImg", strPassing[6]);
                intent.putExtra("strContact", strPassing[5]);
                intent.putExtra("doubLat", location.latitude);
                intent.putExtra("doubLng", location.longitude);
                Bundle b = new Bundle();
                b.putSerializable("nearLocation", strPassing2);
                intent.putExtras(b);
                context.startActivity(intent);


            }
        });
        builder.setNegativeButton("ยกเลิก", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }
} // Main Class
