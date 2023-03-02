
package searchdemo;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.Scanner;

public class SearchDemo {

    private static final int SIZE = 10000;
    
    public static void main(String[] args) {
    
    // Load the numbers in th efile into an array
    Scanner fromFile = null;
    Scanner keyboard = new Scanner(System.in);
    DecimalFormat sixPlaces = new DecimalFormat("0.00000000#");
    
    try{
        fromFile = new Scanner(new File("sorted10k.txt"));
        }
    catch(FileNotFoundException e){
            System.out.println("PLEASE SAVE THE FILE IN THE RIGHT PLACE IDIOT");
            System.exit(0);
        } // End of Catch
    
    // Declaring Number array and getting the numbers from the file
    int[] number = new int[SIZE];
    for(int i =0; i<number.length; i++){
        number[i] = fromFile.nextInt();
    }
    
    int searchValue = 0;
    
    System.out.print("Enter search value : ");
    searchValue = keyboard.nextInt();
    
    while(searchValue > 0){
        long start = System.nanoTime();
        int index = bFind(searchValue, number);
        long end = System.nanoTime();
        
        if(index >=0)
            System.out.println(searchValue + " was found at index " + index + ".");
        else
            System.out.println(searchValue + " is not in the list.");
        
        System.out.println("Search Length " + sixPlaces.format((end-start)/Math.pow(10, 9)) + " seconds.");
        System.out.print("Enter search value : ");
        searchValue = keyboard.nextInt();
        
        } // End of While Loop
    
    } // End of Main
    
   public static void print(int[] a){
       for(int i =0; i<a.length; i++)
           System.out.print(a[i] + " ");
       System.out.println("");
   }
   
   public static int find(int num, int[] a){
       for(int i=0; i<a.length; i++)
           if(num ==a[i])
               return i;
       return -1;
   }
    
   private static int bFind(int num, int[] a){
       int mid = a.length/2, lo =0, hi = a.length-1;
       
        while(lo < hi && num != a[mid]){
           if(num > a[mid])
               lo = mid+1;
           else
               hi = mid-1;
           mid = (hi + lo)/2;
       }
       
        return (num == a[mid])? mid: -1;
        
//        if(lo < hi)
//            return mid;
//        else
//            return -1;
//       
  
   }

} // End of SearchDemo Class
