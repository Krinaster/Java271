
package timingex;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Scanner;

public class TimingEx {

    private static final int SIZE = 11;
    
    public static void main(String[] args) {
        
        int[] value = new int[SIZE];
        DecimalFormat timerFormat = new DecimalFormat("0.000000#");
        
        Scanner fromFile = null;
        try{
            fromFile = new Scanner(new File("data.txt"));
        }
        catch(FileNotFoundException error_e){
            System.out.println("YOU MESSED UP");
            System.exit(0);
        }
        
        // load the array
        for(int i =0; i < value.length; i++)
            value[i] = fromFile.nextInt();
        
        // time the sort
        long start = System.nanoTime();
         Sorts.BogoSort(value);
        //Arrays.sort(value);
        long end = System.nanoTime();
        
        // Check the sort visually and with method
        for(int i: value)
            System.out.print(i + " ");
        System.out.println("SORTED? " + checkSort(value));
        
        System.out.println("Time taken: " + timerFormat.format((end-start)/Math.pow(10, 9)));
    }
    
    private static boolean checkSort(int[] a){
        for(int i=0; i<a.length-1; i++)
           if(a[i] > a[i+1])
               return false;
        return true;
    }

}
