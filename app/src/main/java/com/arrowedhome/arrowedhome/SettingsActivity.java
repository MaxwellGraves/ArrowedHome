package com.arrowedhome.arrowedhome;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }
    public void timeOnOff(View v) {
        TextView temp = (TextView)findViewById(R.id.TimeSettings);
        if(temp.getText().equals("Display Time On/Off : On")) {
            temp.setText("Display Time On/Off : Off");
            MainActivity.timeOn = false;
        }
        else {
            temp.setText("Display Time On/Off : On");
            MainActivity.timeOn = true;
        }
    }
    public void movesOnOff(View v) {
        TextView temp = (TextView)findViewById(R.id.MoveSettings);
        if(temp.getText().equals("Display Move On/Off : On")) {
            temp.setText("Display Move On/Off : Off");
            MainActivity.moveOn = false;
        }
        else {
            temp.setText("Display Move On/Off : On");
            MainActivity.moveOn = true;
        }
    }

}
    