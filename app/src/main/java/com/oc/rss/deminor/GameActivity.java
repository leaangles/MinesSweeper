package com.oc.rss.deminor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class GameActivity extends AppCompatActivity {

    Button[][] buttons;
    Deminor deminor;
    int m;
    int n;
    boolean started;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_game);
        Intent intent = getIntent();
        n = intent.getIntExtra("n", 10);
        m = intent.getIntExtra("m", 10);

        deminor = new Deminor(n, m, 4);
        deminor.init();

        LinearLayout layout = findViewById(R.id.layout);

        LinearLayout table = new LinearLayout(this);
        LinearLayout.LayoutParams paramsTable = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        table.setLayoutParams(paramsTable);
        table.setOrientation(LinearLayout.HORIZONTAL);
        table.setWeightSum(n);
        layout.addView(table);

        LinearLayout[] layouts = new LinearLayout[n];
        LinearLayout.LayoutParams layoutParams1 = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
        for (int i = 0; i < n; i++) {
            layouts[i] = new LinearLayout(this);
            layouts[i].setLayoutParams(layoutParams1);
            layouts[i].setOrientation(LinearLayout.VERTICAL);
            layouts[i].setWeightSum(m);
            table.addView(layouts[i]);
        }
        buttons = new Button[n][m];
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 1);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                buttons[i][j] = new Button(this);
                buttons[i][j].setText(R.string.mine_button);
                buttons[i][j].setBackgroundColor(0xFF4CAF50);
                buttons[i][j].setLayoutParams(layoutParams);
                layouts[i].addView(buttons[i][j]);
            }
        }

        Chronometer chronometer = findViewById(R.id.chronometer1);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                buttons[i][j].setOnClickListener(new MineClickListener(i, j, deminor, buttons, this, chronometer));
            }
        }

        final Button flagButton = findViewById(R.id.flag_button);
        flagButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(deminor.flag);;
                deminor.flag = !deminor.flag;
                if (deminor.flag) {
                    flagButton.setText("Mine");
                } else {
                    flagButton.setText("Flag");
                }
            }
        });
    }

    public void lose() {
        Intent intent = new Intent(GameActivity.this, LostActivity.class);
        intent.putExtra("n", n);
        intent.putExtra("m", m);
        startActivity(intent);
    }
}
