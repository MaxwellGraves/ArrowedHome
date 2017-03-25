package com.arrowedhome.arrowedhome;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SizeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_size);

        Intent i = getIntent();
    }
    public void three(View v){
        Intent i = new Intent(this,DifficultyActivity.class);
        i.putExtra("arrowedhome.size", "3");
        startActivity(i);
    }
    public void four(View v){
        Intent i = new Intent(this,DifficultyActivity.class);
        i.putExtra("arrowedhome.size", "4");
        startActivity(i);
    }
    public void five(View v){
        Intent i = new Intent(this,DifficultyActivity.class);
        i.putExtra("arrowedhome.size", "5");
        startActivity(i);
    }
}
