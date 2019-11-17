package com.example.receiverbroadcast.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.receiverbroadcast.activities.ListNotesActivity;
import com.example.receiverbroadcast.activities.MainActivity;

public class ListenerBrodcast extends BroadcastReceiver {

    private SQLHelper db;
    @Override
    public void onReceive(Context context, Intent intent) {

        if(intent.getAction().equals("NOTES")){
            Log.d("Listener","Listened");
            context.startActivity(new Intent(null, MainActivity.class));
        }

        Log.d("Listener","Listened");
        context.startActivity(new Intent(null, MainActivity.class));
        this.db = new SQLHelper(context);
        String note = intent.getStringExtra("note");
        Double lat = intent.getDoubleExtra("lat",0);
        Double lon = intent.getDoubleExtra("lon",0);

        boolean isInserted = db.insertData(note,lat,lon);
        if(isInserted){
            Log.d("Data","Data Inserted !");
        }
        else
            Log.d("Data","Fuck off !");
    }
}
