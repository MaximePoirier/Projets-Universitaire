package com.example.receiverbroadcast.activities;

import android.Manifest;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.receiverbroadcast.R;
import com.example.receiverbroadcast.models.Note;
import com.example.receiverbroadcast.models.NoteAdapter;
import com.example.receiverbroadcast.services.SQLHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;

public class ListNotesActivity extends AppCompatActivity {

    private SQLHelper db;
    private ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listnote);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PERMISSION_GRANTED) {

            Toast.makeText(getBaseContext(), "No permission 1 ", Toast.LENGTH_LONG).show();

            String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION};
            ActivityCompat.requestPermissions(this,permissions,42);

            return;
        }


        this.db = new SQLHelper(this);
        this.list = (ListView) findViewById(R.id.activity_list_lv);

        viewAll();
    }

    protected void viewAll(){
        Cursor data = db.getAllData();
        if(data.getCount() == 0){
            Toast.makeText(ListNotesActivity.this,"No Data",Toast.LENGTH_LONG).show();
            return;
        }

        List<Note> notes = new ArrayList<Note>();

        while(data.moveToNext()){
            String not = data.getString(1);
            Double lat = data.getDouble(2);
            Double lon = data.getDouble(3);

            Note note = new Note(not,lat,lon);
            notes.add(note);
        }

        Collections.reverse(notes);

        NoteAdapter adapter = new NoteAdapter(ListNotesActivity.this,notes);
        list.setAdapter(adapter);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        viewAll();
    }
}
