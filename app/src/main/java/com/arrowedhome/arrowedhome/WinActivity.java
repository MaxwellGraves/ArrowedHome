package com.arrowedhome.arrowedhome;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class WinActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win);

        Intent i = getIntent();
        String level = i.getStringExtra("arrowedhome.level");
        String width = i.getStringExtra("arrowedhome.size");

        TextView t = (TextView) findViewById(R.id.levelsize);
        String add = "( "+width+" x "+width+" "+level+" )";
        t.setText(add);
    }
    public void gohome(View v){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}
