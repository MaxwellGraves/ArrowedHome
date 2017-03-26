package com.arrowedhome.arrowedhome;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.*;

public class GameActivity extends AppCompatActivity {
    int hi, lo, width;
    ArrayList<Tuple> mem;
    ImageView[][] arrimgs;
    Vector red, blue, e1, e2;
    ImageView redc, bluec, switcher;
    FrameLayout[][] f;
    int DWidth, totalMoves;
    TextView moves;
    ArrowGrid g;
    String top, level;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        totalMoves = -1;
        top = "blue";
        moves = (TextView) findViewById(R.id.moves);
        if(!MainActivity.moveOn) moves.setAlpha(0f);

        Intent i = getIntent();
        String[] dif = i.getStringExtra("arrowedhome.dif").split(" ");
        lo = Integer.parseInt(dif[0]);
        hi = Integer.parseInt(dif[1]);
        width = Integer.parseInt(i.getStringExtra("arrowedhome.size"));
        level = i.getStringExtra("arrowedhome.level");

        Tuple t = findPuzzle(lo, hi, width);
        g = t.get(0);
        Vector puzz = t.get(1);
        Tuple start = posToRCPair(puzz.x, g);
        Tuple end = posToRCPair(puzz.y, g);
        Vector s1 = start.get(0);
        Vector s2 = start.get(1);
        e1 = end.get(0);
        e2 = end.get(1);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int DHeight = displayMetrics.heightPixels;
        DWidth = displayMetrics.widthPixels;

        switcher = (ImageView) findViewById(R.id.switcher);
        switcher.setAlpha(0f);

        GridLayout gv = (GridLayout) findViewById(R.id.arrowgridview);
        gv.setRowCount(width);
        gv.setColumnCount(width);

        f = new FrameLayout[width][width];

        arrimgs = new ImageView[width][width];
        for(int r = 0; r < width; r++){
            for(int c = 0; c < width; c++){

                f[r][c] = new FrameLayout(this);
                arrimgs[r][c] = new ImageView(this);
                arrimgs[r][c].setImageResource(R.drawable.arrow);

                String dir = g.arrows[r][c].toString();
                int rotNum = findRotNum(dir);
                arrimgs[r][c].setRotation((float) 45*rotNum);

                ViewGroup.LayoutParams params = new ViewGroup.LayoutParams((DWidth/width*7/8), (DWidth/width)*7/8);
                int pad = (DWidth/8)/(2*width)*(17/9);
                arrimgs[r][c].setLayoutParams(params);
                arrimgs[r][c].setPadding(pad*2, pad, pad*2, pad);

                if(r == e1.x && c == e1.y)
                    arrimgs[r][c].setColorFilter(Color.argb(255, 255, 0, 0));
                if(r == e2.x && c == e2.y)
                    arrimgs[r][c].setColorFilter(Color.argb(255, 0, 0, 255));

                gv.addView(f[r][c]);
                f[r][c].addView(arrimgs[r][c]);
            }
        }

        red = s1;
        blue = s2;

        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams((DWidth/width*7/8), (DWidth/width)*7/8);

        redc = new ImageButton(this);
        redc.setImageResource(R.drawable.circle);
        int pad = (DWidth/8)/(2*width)*(17/9);
        redc.setLayoutParams(params);
        redc.setPadding(pad*2, pad, pad*2, pad);
        redc.setColorFilter(Color.argb(255, 255, 0, 0));
        redc.setAlpha(0.4f);
        redc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(red.y + g.arrows[blue.x][blue.y].x > width-1 || red.x + g.arrows[blue.x][blue.y].y>width-1)
                    return;
                else if(red.y + g.arrows[blue.x][blue.y].x <0 || red.x + g.arrows[blue.x][blue.y].y<0)
                    return;
                else
                {
                    red.y += g.arrows[blue.x][blue.y].x;
                    red.x += g.arrows[blue.x][blue.y].y;
                    if(red.equals(blue)) top = "red";
                    addToMem();
                }
                updatePointers();
            }
        });
        bluec = new ImageButton(this);
        bluec.setImageResource(R.drawable.circle);
        bluec.setLayoutParams(params);
        bluec.setPadding(pad*2, pad, pad*2, pad);
        bluec.setColorFilter(Color.argb(255, 0, 0, 255));
        bluec.setAlpha(0.4f);
        bluec.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v)
            {

                if(blue.y + g.arrows[red.x][red.y].x > width-1 || blue.x + g.arrows[red.x][red.y].y>width-1)
                    return;
                else if(blue.y + g.arrows[red.x][red.y].x <0 || blue.x + g.arrows[red.x][red.y].y<0)
                    return;
                else
                {
                    blue.y += g.arrows[red.x][red.y].x;
                    blue.x += g.arrows[red.x][red.y].y;
                    if(red.equals(blue)) top = "blue";
                    addToMem();
                }
                updatePointers();
            }
        });
        mem = new ArrayList<Tuple>();
        addToMem();
        updatePointers();

    }
    public void updatePointers(){
        Tuple t;
        if(mem.size() >= 2){
            t = mem.get(mem.size()-2);
            Vector redtemp = t.get(0);
            Vector bluetemp = t.get(1);
            f[redtemp.x][redtemp.y].removeView(redc);
            f[bluetemp.x][bluetemp.y].removeView(bluec);
        }
        f[red.x][red.y].addView(redc);
        f[blue.x][blue.y].addView(bluec);
        if(red.equals(blue)){
            switcher.setAlpha(1f);
        }
        else switcher.setAlpha(0f);
        if(red.equals(e1) && blue.equals(e2)){
            Intent i = new Intent(this, WinActivity.class);
            i.putExtra("arrowedhome.size", width+"");
            i.putExtra("arrowedhome.level", level);
            startActivity(i);
        }
        totalMoves++;
        moves.setText("MOVES: "+totalMoves);
    }
    public void addToMem(){
        Vector rednew = new Vector(red.x, red.y);
        Vector bluenew = new Vector(blue.x, blue.y);
        mem.add(new Tuple(rednew, bluenew));
    }
    public void swap(View v){
        if(red.equals(blue)){
            if(top.equals("red")){
                f[red.x][red.y].removeView(bluec);
                f[red.x][red.y].removeView(redc);
                f[red.x][red.y].addView(bluec);
                f[red.x][red.y].addView(redc);
                top = "blue";
            }
            else if(top.equals("blue")){
                f[red.x][red.y].removeView(bluec);
                f[red.x][red.y].removeView(redc);
                f[red.x][red.y].addView(redc);
                f[red.x][red.y].addView(bluec);
                top = "red";
            }
        }
    }
    public void undo(View v){
        Tuple t;
        if(mem.size() >= 2){
            t = mem.get(mem.size()-1);
            Vector redtemp = t.get(0);
            Vector bluetemp = t.get(1);
            f[redtemp.x][redtemp.y].removeView(redc);
            f[bluetemp.x][bluetemp.y].removeView(bluec);
            mem.remove(mem.size()-1);
            t = mem.get(mem.size()-1);
            redtemp = t.get(0);
            bluetemp = t.get(1);
            red = new Vector(redtemp.x, redtemp.y);
            blue = new Vector(bluetemp.x, bluetemp.y);
            f[red.x][red.y].addView(redc);
            f[blue.x][blue.y].addView(bluec);
            if(red.equals(blue)){
                switcher.setAlpha(1f);
            }
            else switcher.setAlpha(0f);
        }
    }
    public void restart(View v){
        Tuple t;
        t = mem.get(mem.size()-1);
        Vector redtemp = t.get(0);
        Vector bluetemp = t.get(1);
        f[redtemp.x][redtemp.y].removeView(redc);
        f[bluetemp.x][bluetemp.y].removeView(bluec);
        t = mem.get(0);
        mem = new ArrayList<Tuple>();
        mem.add(t);
        redtemp = t.get(0);
        bluetemp = t.get(1);
        red = new Vector(redtemp.x, redtemp.y);
        blue = new Vector(bluetemp.x, bluetemp.y);
        f[red.x][red.y].addView(redc);
        f[blue.x][blue.y].addView(bluec);
        if(red.equals(blue)){
            switcher.setAlpha(1f);
        }
        else switcher.setAlpha(0f);
        top = "blue";
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
    public static int findRotNum(String dir){
        if(dir.equals("N")) return 5;
        else if(dir.equals("E")) return 7;
        else if(dir.equals("S")) return 1;
        else if(dir.equals("W")) return 3;
        else if(dir.equals("NE")) return 6;
        else if(dir.equals("NW")) return 4;
        else if(dir.equals("SE")) return 0;
        else if(dir.equals("SW")) return 2;
        return 0;
    }
}
