package com.arrowedhome.arrowedhome;
public class Any{
   Object a;
   public Any(Object a){
      this.a = a;
   }
   public <T> T data(){
      return (T) a;
   }
   public Class type(){
      return a.getClass();
   }
   public String toString(){
      return a.toString();
   }
   public boolean equals(Object b){
      if(b instanceof Any) return a.equals(((Any)b).data()); 
      return a.equals(b);
   }
   public int hashCode(){
      return a.hashCode();
   }
}