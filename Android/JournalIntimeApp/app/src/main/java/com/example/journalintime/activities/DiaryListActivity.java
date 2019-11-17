package com.example.journalintime.activities;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.journalintime.R;
import com.example.journalintime.modele.Comment;
import com.example.journalintime.modele.CommentAdapter;
import com.example.journalintime.services.SQLHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DiaryListActivity extends AppCompatActivity {

    private SQLHelper db;
    private Button add_btn;
    private ListView listeView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diarylist);

        this.db = new SQLHelper(this);

        this.add_btn = (Button) findViewById(R.id.activity_add_comment_btn);

        this.listeView = (ListView) findViewById(R.id.activity_list_lv);

        viewAll();

        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DiaryListActivity.this,AddCommentActivity.class);
                startActivityForResult(intent,2);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 2){
            String comment = data.getStringExtra("Comment");
            boolean isInserted = db.insertData(comment);

            if(isInserted){
                Toast.makeText(DiaryListActivity.this,"Data Inserted",Toast.LENGTH_LONG).show();
            }
            else
                Toast.makeText(DiaryListActivity.this,"Data Not Inserted",Toast.LENGTH_LONG).show();

            viewAll();
        }
    }

    protected void viewAll(){
        Cursor data = db.getAllData();
        if(data.getCount() == 0){
            Toast.makeText(DiaryListActivity.this,"No Data",Toast.LENGTH_LONG).show();
            return;
        }

        List<Comment> comments = new ArrayList<Comment>();

        while(data.moveToNext()){
            String date = data.getString(1);
            String text = data.getString(2);
            Comment com = new Comment(date,text);
            comments.add(com);
        }

        Collections.reverse(comments);

        CommentAdapter adapter = new CommentAdapter(DiaryListActivity.this,comments);
        listeView.setAdapter(adapter);
    }
}
