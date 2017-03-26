package com.arrowedhome.arrowedhome;
public class Vector{ //holds two integer vals
   int x, y;
   public Vector(){
      x = 0; y = 0;
      while(x == y && y == 0){
         x = (int)((Math.random()*3))-1;
         y = (int)((Math.random()*3))-1;
      }
   }
   public Vector(int x, int y){
      this.x = x;
      this.y = y;

   }
   public boolean equals(Vector o){
      return o.x == x && o.y == y;
   }
   public String toString(){
      String str = "";
      switch(y){
         case -1: str += "N"; break;
         case 0: str += ""; break;
         case 1: str += "S"; break;
         default: return toCoords();
      }
      switch(x){
         case -1: str += "W"; break;
         case 0: break;
         case 1: str += "E"; break;
         default: return toCoords();
      }
      if(str == "") return toCoords();
      return str;
   }
   public String toCoords(){
     return "{ "+x+", "+y+"}";
   }
}
