package com.example.journalintime.modele;

public class Comment {

    private String date;
    private String comment;

    public Comment(String date, String comment){
        this.date = date;
        this.comment = comment;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDate() {
        return date;
    }

    public String getComment() {
        return comment;
    }
}
