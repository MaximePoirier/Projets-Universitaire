package com.example.editeurbroadcast;

import android.Manifest;
import android.content.Context;
import android.content.Intent;

public class Sender {


    private Double lat;
    private Double lon;
    private String note;

    public Sender(Double lat, Double lon, String note){
        this.lat = lat;
        this.lon = lon;
        this.note = note;
    }

    public Double getLat() {
        return lat;
    }

    public Double getLon() {
        return lon;
    }

    public String getNote() {
        return note;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void sendNote(Context context){
        Intent intent = new Intent();
        intent.setAction("NOTES");
        intent.putExtra("note",this.note);
        intent.putExtra("latitude",this.lat);
        intent.putExtra("longitude", this.lon);
        context.sendBroadcast(intent, Manifest.permission.ACCESS_FINE_LOCATION);
    }
}
