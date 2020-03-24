package com.oc.rss.deminor;

import android.content.Context;
import android.service.autofill.OnClickAction;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.widget.AppCompatButton;

public class Mine {
    Case mine;
    Button button;

    public Mine(Context context) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);

        this.mine = new Case();
        this.button = new Button(context);

        button.setText(R.string.mine_button);
        button.setBackgroundColor(0xFF99D6D6);
        button.setLayoutParams(layoutParams);
    }

    public boolean OnClickAction(boolean flag, int neighbours){
        if (flag) {
            mine.flaged = !mine.flaged;
        } else {
            if (mine.bomb) {
                return false;
            } else {
                mine.pushed = true;
            }
        }
        button.setText(neighbours);
        return true;
    }


}
