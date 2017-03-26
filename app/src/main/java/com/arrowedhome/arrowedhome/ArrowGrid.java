package com.arrowedhome.arrowedhome;
import java.util.*;
public class ArrowGrid{
  ArrayList<LinkedList<Integer>> paths;
  Vector[][] arrows;
  int w, cells, size;
  public ArrowGrid(int x){
    w = x; //w is width, or the length of one grid-side
    cells = w*w; //cells is the number of squares in the grid
    size = cells*cells; //size is the number of positions possible with two pointers
    arrows = new Vector[w][w]; //arrows hold the arrows which will be used for the grid
    for(int r = 0; r < w; r++){
      for(int c = 0; c < w; c++){
        arrows[r][c] = new Vector(); //default constructor forms random  vector (values -1,0,1 only)
      }
    }
    paths = new ArrayList<LinkedList<Integer>>(); //paths indicated what other positions can be reached from a given pos
    for(int i = 0; i < size; i++){
      paths.add(new LinkedList<Integer>()); //index is (cells)*first position + second position
      Vector firstA = arrows[(i/cells)/w][(i/cells)%w]; //arrow Vect
      Vector secondA = arrows[(i%cells)/w][(i%cells)%w];
      Vector newFirst = new Vector(((i/cells)/w+(secondA.y)), ((i/cells)%w+(secondA.x))); //row and col of new pos
      Vector newSecond = new Vector(((i%cells)/w+(firstA.y)), ((i%cells)%w+(firstA.x)));
      //System.out.println(newFirst.toCoords()+"  "+newSecond.toCoords());
      if(inRg(newSecond)) paths.get(i).add((i/cells)*cells + newSecond.x*w+newSecond.y);
      if(inRg(newFirst)) paths.get(i).add((newFirst.x*w+newFirst.y)*cells + i%cells);
    }
  }
  public Vector findPath(int lo, int hi){
    Set<Integer> used = new HashSet<>();
    used.add(-1);
    Vector res = new Vector(-1, -1);
    while(res.x == -1){
      int startPos = -1;
      if(used.size() >= size/3)
        return new Vector(-1, -1);
      while(used.contains(startPos))
        startPos = (int)(Math.random()*size);
      used.add(startPos);
      res = findPath(lo, hi, startPos);
    }
    return res;
  }
  public Vector findPath(int lo, int hi, int startPos){ //returns two position ints (for use in 'paths')
    Map<Integer, Integer> map = new HashMap<>();
    int curLen = 0;
    map.put(startPos, 0);
    boolean flag;
    do{
      flag = false;
      curLen++;
      Map<Integer, Integer> newvals = new HashMap<>(); //temporary map to avoid ConcurrentModification
      Iterator<Integer> it = map.keySet().iterator();
      while(it.hasNext()){
        int pos = it.next();
        if(map.get(pos) == curLen-1){
          int a, b;
          if(paths.get(pos).size() > 0){ //if at least one move is possible for current pos
            a = paths.get(pos).get(0);
            if(!map.containsKey(a)){ //makes sure pos isnt reachable from shorter path
              newvals.put(a, curLen);
              flag = true; //indicates a new position was reached
            }
          }
          if(paths.get(pos).size() == 2){ //if another move is possible for current pos
            b = paths.get(pos).get(1);
            if(!map.containsKey(b)){
              newvals.put(b, curLen);
              flag = true;
            }
          }
        }
      }
      for(int val : newvals.keySet()) map.put(val, newvals.get(val));
    }
    while(flag == true && curLen < hi); //if no more reachable positions OR the upper bound has been passed
    int endPos;
    if(curLen > lo){
      ArrayList<Integer> options = new ArrayList<>();
      for(int pos : map.keySet())
        if(map.get(pos) >= lo && map.get(pos) <= hi) options.add(pos);
      endPos = options.get((int)(Math.random()*options.size()));
      return new Vector(startPos, endPos);
    }
    else{
      return new Vector(-1, -1);
    }
  }
  public boolean inRg(Vector r){
    return r.x >= 0 && r.x < w && r.y >= 0 && r.y < w;
  }
  public String toString(){
      String s = "";
      for(int x = 0; x < w; x++){
         for(int y = 0; y < w; y++){
            s += arrows[x][y]+"   ";
         }
         s+="\n";
      }
      return s;
   }
}
