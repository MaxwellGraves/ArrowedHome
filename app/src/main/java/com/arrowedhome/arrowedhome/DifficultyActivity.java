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
            case 4: lo = 10; hi = 15; break;
            case 5: lo = 15; hi = 20; break;
        }
        i.putExtra("arrowedhome.dif", lo+" "+hi);
        i.putExtra("arrowedhome.level", "Easy");
        startActivity(i);
    }
    public void medium(View v){
        Intent i = new Intent(this, GameActivity.class);
        i.putExtra("arrowedhome.size", width+"");
        int lo, hi;
        lo = hi = -1;
        switch(width){
            case 3: lo = 12; hi = 25; break;
            case 4: lo = 15; hi = 30; break;
            case 5: lo = 20; hi = 35; break;
        }
        i.putExtra("arrowedhome.dif", lo+" "+hi);
        i.putExtra("arrowedhome.level", "Medium");
        startActivity(i);
    }
    public void hard(View v){
        Intent i = new Intent(this, GameActivity.class);
        i.putExtra("arrowedhome.size", width+"");
        int lo, hi;
        lo = hi = -1;
        switch(width){
            case 3: lo = 25; hi = 35; break;
            case 4: lo = 30; hi = 45; break;
            case 5: lo = 35; hi = 50; break;
        }
        i.putExtra("arrowedhome.dif", lo+" "+hi);
        i.putExtra("arrowedhome.level", "Hard");
        startActivity(i);
    }
}
