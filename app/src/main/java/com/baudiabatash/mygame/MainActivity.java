package com.baudiabatash.mygame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnAni001,btnAni002;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAni001 = (Button) findViewById(R.id.ani001);
        btnAni002 = (Button) findViewById(R.id.ani002);
        btnAni001.setOnClickListener(this);
        btnAni002.setOnClickListener(this);
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
        }
    }
}
