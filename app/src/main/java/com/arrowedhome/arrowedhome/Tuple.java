package com.arrowedhome.arrowedhome;
public class Tuple{
   private Any[] a;
   public Tuple(Object... args){
      a = new Any[args.length];
      for(int i = 0; i < args.length; i++){
         a[i] = new Any(args[i]);
      }
   }
   public <T> T get(int x){
      return a[x].data();
   }
   public Any getAny(int x){
      return a[x];
   }
   public int size(){
      return a.length;
   }
   public String toString(){
      String ret = "(";
      for(Any x : a){
         ret += x+", ";
      }
      return ret.substring(0, ret.length()-2)+")";
   }
   public String toStringWithClass(){
      String ret = "(";
      for(Any x : a){
         ret += ""+x+"<"+x.data().getClass().getSimpleName()+">, ";
      }
      return ret.substring(0, ret.length()-2)+")";
   }
   private String hashString(){
      String ret = "";
      for(Any x : a){
         ret += ""+x+"<"+x.data().getClass().getSimpleName()+">";
      }
      return ret;
   }
   public int hashCode(){
      return hashString().hashCode();
   }
   public boolean equals(Object x){
      return hashCode() == x.hashCode();
   }
}