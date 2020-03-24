package com.oc.rss.deminor;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;

import androidx.appcompat.app.AppCompatActivity;

public class MineClickListener implements View.OnClickListener {
    public int i;
    public int j;
    public Deminor deminor;
    public Button[][] buttons;
    public GameActivity activity;
    public Chronometer chronometer;

    public MineClickListener(int i, int j, Deminor deminor, Button[][] buttons, GameActivity activity, Chronometer chronometer) {
        this.i = i;
        this.j = j;
        this.deminor = deminor;
        this.buttons = buttons;
        this.activity = activity;
        this.chronometer = chronometer;
    }

    @Override
    public void onClick(View v) {
        if (!activity.started) {
            chronometer.start();
            activity.started = true;
        }
        System.out.println(i + " " + j);
        System.out.println("flag : "+ deminor.table[i][j].flaged);
        System.out.println("pushed : "+ deminor.table[i][j].pushed);
        System.out.println("mine : "+ deminor.table[i][j].bomb);
        boolean result = deminor.click(i, j);

        if (deminor.flag) {
            if (!deminor.table[i][j].pushed) {
                if (deminor.table[i][j].flaged) {
                    buttons[i][j].setBackgroundColor(0xFFF44336);
                } else {
                    buttons[i][j].setBackgroundColor(0xFF4CAF50);
                }
            }

        } else {
            if (!deminor.table[i][j].flaged) {
                if (!result) {
                    activity.lose();
                    return;
                }
                int nbNeighbours = deminor.getNeighbours(i, j);
                if (nbNeighbours != 0) {
                    buttons[i][j].setText(Integer.toString(nbNeighbours));
                } else {
                    buttons[i][j].setBackgroundColor(0xFF00897B);
                    int[][] neighbours = {{i + 1, j - 1}, {i + 1, j}, {i + 1, j + 1}, {i, j - 1}, {i, j + 1},
                            {i - 1, j - 1}, {i - 1, j}, {i - 1, j + 1}};
                    for (int[] pos : neighbours) {
                        int x = pos[0];
                        int y = pos[1];
                        if (0 <= x && x < deminor.n && 0 <= y && y < deminor.m && !deminor.table[x][y].flaged && !deminor.table[x][y].pushed) {
                            MineClickListener listener = new MineClickListener(x, y, deminor, buttons, activity, chronometer);
                            listener.onClick(v);
                        }
                    }
                }
            }
        }
    }
}
