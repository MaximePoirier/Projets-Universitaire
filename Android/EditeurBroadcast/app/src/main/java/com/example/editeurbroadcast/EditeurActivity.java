package com.example.editeurbroadcast;

import android.Manifest;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;

public class EditeurActivity extends AppCompatActivity implements LocationListener {

    private TextView editeur_txt;
    private EditText editeur_input;
    private Button editeur_btn;

    private LocationManager locationManager;

    private Double lat;
    private Double lon;

    private Sender sender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editeur);

        editeur_txt = (TextView) findViewById(R.id.activity_editeur_txt);
        editeur_input = (EditText) findViewById(R.id.activity_editeur_input);
        editeur_btn = (Button) findViewById(R.id.activity_editeur_btn);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PERMISSION_GRANTED) {

            Toast.makeText(getBaseContext(), "No permission 1 ", Toast.LENGTH_LONG).show();

            String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION};
            ActivityCompat.requestPermissions(this,permissions,42);

            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                3000,   // 3 sec
                10, this);


        editeur_input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                editeur_btn.setEnabled(s.toString().length() != 0);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        editeur_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sender = new Sender(lat,lon,editeur_input.getText().toString());
                sender.sendNote(EditeurActivity.this);
                editeur_input.setText("");
            }
        });

    }

    @Override
    public void onLocationChanged(Location location) {
        String str = "Latitude: "+location.getLatitude()+"Longitude: "+location.getLongitude();
        lat = location.getLatitude();
        lon = location.getLongitude();

        Log.d("Location",str);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {
        Toast.makeText(getBaseContext(), "Gps turned on ", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onProviderDisabled(String provider) {
        Toast.makeText(getBaseContext(), "Gps turned off ", Toast.LENGTH_LONG).show();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults[0] == PERMISSION_GRANTED )
            try {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                        3000,   // 3 sec
                        10, this);
            }
            catch (SecurityException e) {}
        else
        {
            for (int i=0;i<permissions.length;++i)
                Log.d("Permissions",permissions[i]+" - "+grantResults[i]);
            Toast.makeText(getBaseContext(), "No permission 2 ", Toast.LENGTH_LONG).show();
        }

    }


}
