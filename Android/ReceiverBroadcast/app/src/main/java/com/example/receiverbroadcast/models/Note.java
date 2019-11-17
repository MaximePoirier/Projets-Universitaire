package com.example.receiverbroadcast.models;

public class Note {

    private String note;
    private Double lat;
    private Double lon;

    public Note(String note, Double lat, Double lon){
        this.note = note;
        this.lat = lat;
        this.lon = lon;
    }

    public String getNote() {
        return note;
    }

    public Double getLat() {
        return lat;
    }

    public Double getLon(){
        return lon;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }
}
