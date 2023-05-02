/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genericdemo;

public class Stuff {

    public static  <T> void print(T v){
        System.out.println(v + " is of " + v.getClass() + " type.");
    }
    
    public <T> int freq(T[] a, T val){
        int count = 0;
        for(T t: a)
            if(t.equals(val))
                count++;
        return count;
    }
    
    public <T extends Number> double sum(T[] a){
        double sum = 0;
        for(T t: a)
            sum += t.doubleValue();
        return sum;
    }

}
