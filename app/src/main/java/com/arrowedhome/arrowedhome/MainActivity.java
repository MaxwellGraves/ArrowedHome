package com.arrowedhome.arrowedhome;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    public static boolean timeOn = true;
    public static boolean moveOn = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void play(View v){
        Intent i = new Intent(this,SizeActivity.class);
        startActivity(i);
    }
    public void settings(){
        Intent i = new Intent(this,SettingsActivity.class);
        startActivity(i);
    }
}
