package com.oc.rss.deminor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {
    public int n;
    public int m;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RadioGroup radioN = findViewById(R.id.n);
        radioN.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.n10) {
                    n = 10;
                } else if(checkedId == R.id.n50) {
                    n = 20;
                } else {
                    n = 100;
                }
            }
        });

        RadioGroup radioM = findViewById(R.id.m);
        radioM.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.m10) {
                    m = 10;
                } else if(checkedId == R.id.m50) {
                    m = 20;
                } else {
                    m = 100;
                }
            }
        });

        Button playButton = findViewById(R.id.button);
        playButton.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  Intent mainToGameIntent = new Intent(MainActivity.this, GameActivity.class);
                  mainToGameIntent.putExtra("n", n);
                  mainToGameIntent.putExtra("m", m);
                  startActivity(mainToGameIntent);
              }
          }
        );
    }

}
