package com.oc.rss.deminor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class LostActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lost);

        Intent intent = getIntent();
        final int n = intent.getIntExtra("n", 10);
        final int m = intent.getIntExtra("m", 10);

        Button restartButton = findViewById(R.id.restart_button);
        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent restartIntent = new Intent(LostActivity.this, GameActivity.class);
                restartIntent.putExtra("n", n);
                restartIntent.putExtra("m", m);
                startActivity(restartIntent);
            }
        });

        Button menuButton = findViewById(R.id.menu_button);
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent menuIntent = new Intent(LostActivity.this, MainActivity.class);
                startActivity(menuIntent);
            }
        });
    }
}
