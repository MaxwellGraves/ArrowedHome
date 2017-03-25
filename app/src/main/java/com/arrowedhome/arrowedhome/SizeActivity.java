package com.arrowedhome.arrowedhome;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SizeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_size);

        Intent i = getIntent();
    }
    public void three(){
        Intent i = new Intent(this,DifficultyActivity.class);
        i.putExtra("arrowedhome.size", "3");
        startActivity(i);
    }
    public void four(){
        Intent i = new Intent(this,DifficultyActivity.class);
        i.putExtra("arrowedhome.size", "4");
        startActivity(i);
    }
    public void five(){
        Intent i = new Intent(this,DifficultyActivity.class);
        i.putExtra("arrowedhome.size", "5");
        startActivity(i);
    }
}
