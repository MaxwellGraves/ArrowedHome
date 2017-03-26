package com.arrowedhome.arrowedhome;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;

public class GameActivity extends AppCompatActivity {
    int hi, lo, width;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Intent i = getIntent();
        String[] dif = i.getStringExtra("arrowedhome.dif").split(" ");
        lo = Integer.parseInt(dif[0]);
        hi = Integer.parseInt(dif[1]);
        width = Integer.parseInt(i.getStringExtra("arrowedhome.size"));

        Tuple t = findPuzzle(lo, hi, width);
        ArrowGrid g = t.get(0);
        Vector puzz = t.get(1);
        Tuple start = posToRCPair(puzz.x, g);
        Tuple end = posToRCPair(puzz.y, g);
        Vector s1 = start.get(0);
        Vector s2 = start.get(1);
        Vector e1 = end.get(0);
        Vector e2 = end.get(1);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int DHeight = displayMetrics.heightPixels;
        int DWidth = displayMetrics.widthPixels;

        GridLayout gv = (GridLayout) findViewById(R.id.arrowgridview);
        gv.setRowCount(width);
        gv.setColumnCount(width);
        for(int r = 0; r < width; r++){
            for(int c = 0; c < width; c++){
                ImageView img = new ImageView(this);
                img.setImageResource(R.drawable.arrow);
                ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(DWidth/width-30, DWidth/width-30);
                img.setLayoutParams(params);
                gv.addView(img);
            }
        }
    }
    public static Tuple findPuzzle(int lo, int hi, int w){
        Vector puzz = new Vector(-1, -1);
        ArrowGrid v;
        do{
            v = new ArrowGrid(w);
            puzz = v.findPath(lo, hi);
        }
        while(puzz.x == -1);
        return new Tuple(v, puzz);
    }
    public static Tuple posToRCPair(int x, ArrowGrid g){
        return new Tuple(new Vector((x/g.cells)/g.w, (x/g.cells)%g.w), new Vector((x%g.cells)/g.w, (x%g.cells)%g.w));
    }
}
