package com.example.journalintime.services;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SQLHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Diary.db";
    private static final String TABLE_NAME = "diary_table";
    private static final String COL_1 = "ID";
    private static final String COL_2 = "DATE";
    private static final String COL_3 = "COMMENT";


    public SQLHelper(Context context){
        super(context, DATABASE_NAME,null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " +
                    TABLE_NAME +
                    " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    " DATE TEXT, " +
                    " COMMENT TEXT) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }


    public boolean insertData(String comment){
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy hh:mm:ss");
        String strDate = dateFormat.format(date);

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,strDate);
        contentValues.put(COL_3,comment);
        long result = db.insert(TABLE_NAME,null,contentValues);
        if(result == -1){
            return false;
        }
        else
            return true;
    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("select * from " + TABLE_NAME,null);
        return result;
    }
}
