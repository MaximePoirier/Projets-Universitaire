package com.example.journalintime.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.journalintime.R;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LoginActivity extends AppCompatActivity {

    private TextView login_textView;
    private EditText login_input;
    private Button login_btn;

    private String filename = "securedMDP";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login_textView = (TextView) findViewById(R.id.activity_login_txt);
        login_input = (EditText) findViewById(R.id.activity_login_input);
        login_btn = (Button) findViewById(R.id.activity_login_btn);

        if(!isFirstLaunch()){
            login_textView.setText("Entrez votre Mot de passe : ");
        }

        login_input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                login_btn.setEnabled(s.toString().length() != 0);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isFirstLaunch()){
                    saveMDP();
                }
                else{
                    String passSaved = getMdp();
                    String mdp = login_input.getText().toString();
                    String mdpHashe = hashage(mdp);
                    if(mdpHashe.equals(passSaved)){
                        login_textView.setText("Logged in !");
                        Intent i = new Intent(LoginActivity.this, DiaryListActivity.class);
                        startActivity(i);
                    }
                    else{
                        login_textView.setText("Mauvais mot de passe !");
                    }

                }
            }
        });
    }

    protected boolean isFirstLaunch(){
        try {
            FileInputStream in = this.openFileInput(filename);
            return false;
        }
        catch (Exception e){
            return true;
        }
    }

    protected void saveMDP(){
        String mdp = login_input.getText().toString();
        String mdpHashe = hashage(mdp);
        try{
            FileOutputStream out = this.openFileOutput(filename,MODE_PRIVATE);
            out.write(mdpHashe.getBytes());
            out.close();
            Toast.makeText(this,"Password saved!",Toast.LENGTH_SHORT).show();

        }
        catch (Exception e){
            Toast.makeText(this,"Error:"+ e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    protected String hashage(String mdp){
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            String mdpSale = mdp + "UnBonGrosSalage";
            md.update(mdpSale.getBytes(StandardCharsets.UTF_8));
            byte[] digest = md.digest();
            return String.format("%064x", new BigInteger(1, digest));
        }
        catch (NoSuchAlgorithmException e){
            return null;
        }
    }

    protected String getMdp(){
        try {
            FileInputStream in = this.openFileInput(filename);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            StringBuilder sb = new StringBuilder();
            String s = null;
            while((s = br.readLine()) != null){
                sb.append(s);
            }
            return sb.toString();
        }
        catch (Exception e){
            Toast.makeText(this,"Error:"+ e.getMessage(),Toast.LENGTH_SHORT).show();
            return null;
        }
    }
}
