package com.example.journalintime.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.journalintime.R;

public class AddCommentActivity extends AppCompatActivity {

    private TextView add_txt;
    private EditText add_input;
    private Button add_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcomment);

        add_txt = (TextView) findViewById(R.id.activity_add_txt);
        add_input = (EditText) findViewById(R.id.activity_add_input);
        add_btn = (Button) findViewById(R.id.activity_add_btn);

        add_input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                add_btn.setEnabled(s.toString().length() != 0);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String comment = add_input.getText().toString();
                Intent intent = new Intent();
                intent.putExtra("Comment",comment);
                setResult(2, intent);
                finish();
            }
        });
    }
}
