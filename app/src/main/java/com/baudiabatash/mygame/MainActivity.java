package com.baudiabatash.mygame;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.IOException;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnAni001,btnAni002,btnAni003;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAni001 = (Button) findViewById(R.id.ani001);
        btnAni002 = (Button) findViewById(R.id.ani002);
        btnAni003 = (Button) findViewById(R.id.ani003);
        btnAni001.setOnClickListener(this);
        btnAni002.setOnClickListener(this);
        btnAni003.setOnClickListener(this);

        Geocoder geocoder = new Geocoder(this);
        try {
            Address address = geocoder.getFromLocationName("Haragach,Rangpur",3).get(0);
            Log.d("TTTT",address.getAddressLine(0));
            Log.d("TTTT",address.getAdminArea());
            Log.d("TTTT",address.getFeatureName());
            Log.d("TTTT",address.getLocality());
            Log.d("TTTT",address.getCountryCode());
            Log.d("TTTT",address.getCountryName());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ani001:

                Intent intent = new Intent(this,AnimationActivity001.class);
                startActivity(intent);

                break;

            case R.id.ani002:

                Intent intent2 = new Intent(this,AnimationActivity002.class);
                startActivity(intent2);

                break;

            case R.id.ani003:

                Intent intent3 = new Intent(this,GameLoopActivity.class);
                startActivity(intent3);

                break;
        }
    }
}
