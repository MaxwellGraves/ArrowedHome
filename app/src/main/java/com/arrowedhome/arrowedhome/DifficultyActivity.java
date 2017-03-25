package com.arrowedhome.arrowedhome;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class DifficultyActivity extends AppCompatActivity {
    private int width;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_difficulty);

        Intent i = getIntent();
        width = Integer.parseInt(i.getStringExtra("arrowedhome.size"));
    }
    public void easy(View v){
        Intent i = new Intent(this, GameActivity.class);
        i.putExtra("arrowedhome.size", width+"");
        int lo, hi;
        lo = hi = -1;
        switch(width){
            case 3: lo = 7; hi = 12; break;
            case 4: lo = 15; hi = 20; break;
            case 5: lo = 30; hi = 35; break;
        }
        i.putExtra("arrowedhome.dif", lo+" "+hi);
        startActivity(i);
    }
    public void medium(View v){
        Intent i = new Intent(this, GameActivity.class);
        i.putExtra("arrowedhome.size", width+"");
        int lo, hi;
        lo = hi = -1;
        switch(width){
            case 3: lo = 15; hi = 20; break;
            case 4: lo = 22; hi = 27; break;
            case 5: lo = 35; hi = 40; break;
        }
        i.putExtra("arrowedhome.dif", lo+" "+hi);
        startActivity(i);
    }
    public void hard(View v){
        Intent i = new Intent(this, GameActivity.class);
        i.putExtra("arrowedhome.size", width+"");
        int lo, hi;
        lo = hi = -1;
        switch(width){
            case 3: lo = 20; hi = 25; break;
            case 4: lo = 30; hi = 35; break;
            case 5: lo = 40; hi = 45; break;
        }
        i.putExtra("arrowedhome.dif", lo+" "+hi);
        startActivity(i);
    }
}
