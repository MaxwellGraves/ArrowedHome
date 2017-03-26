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
        TextView time = (TextView)findViewById(R.id.TimeSettings);
        if(MainActivity.timeOn) time.setText("Display Time : On");
            else time.setText("Display Time : Off");
        TextView move = (TextView)findViewById(R.id.MoveSettings);
        if(MainActivity.moveOn) move.setText("Display Moves : On");
            else move.setText("Display Moves : Off");
    }
    public void timeOnOff(View v) {
        TextView temp = (TextView)findViewById(R.id.TimeSettings);
        if(temp.getText().equals("Display Time : On")) {
            temp.setText("Display Time : Off");
            MainActivity.timeOn = false;
        }
        else {
            temp.setText("Display Time : On");
            MainActivity.timeOn = true;
        }
    }
    public void movesOnOff(View v) {
        TextView temp = (TextView)findViewById(R.id.MoveSettings);
        if(temp.getText().equals("Display Moves : On")) {
            temp.setText("Display Moves : Off");
            MainActivity.moveOn = false;
        }
        else {
            temp.setText("Display Moves : On");
            MainActivity.moveOn = true;
        }
    }

}
    