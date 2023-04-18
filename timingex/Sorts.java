package timingex;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


public class Sorts {
    
    // Create Bubble Sort O(n) = ?
    public static void bubbleSort(int[] a){
        boolean swapMade = true;
        int passNumber = 1;
        
        while(swapMade){
            swapMade = false;
            for(int i =0; i<a.length-passNumber; i++)
                if(a[i] > a[i+1]){
                    a[i]^=a[i+1];
                    a[i+1]^=a[i];
                    a[i]^=a[i+1];
                    swapMade = true;
                }
            
            passNumber++;
        }
    
    } // end of bubble sort
    
    public static void selectionSort(int [] a){
        int minIndex = 0;
        
        for(int i =0; i<a.length - 1; i++){
            minIndex = i;
            for(int j=i+1; j<a.length; j++)
                if(a[j] < a[minIndex])
                    minIndex = j;
            if(i != minIndex){
                a[i] ^= a[minIndex];
                a[minIndex] ^= a[i];
                a[i] ^= a[minIndex];
            }
        }
    } // end of selection sort
    
    // O(n) = n^2
    public static void insertionSort(int [] a){
        int curValue = 0, index = 0;
        
        // a[0] by itself is already in sorted order
        for(int i =1; i<a.length; i++){
            index = i-1;
            // Question: Is saving the two writes "worth more" than excluding the 'if'
            
            // if(a[index] > a[i]){
            curValue = a[i];
            while(index >= 0 && a[index] > curValue){
                a[index+1]= a[index];
                index--;
            }
            
            a[index+1] = curValue;
            }
        //}
    } // end of insertionsort
    
    // O(n) = nlogn
    public static void quickSort(int [] a){
        quick(a, 0, a.length-1);
    }
    
    private static void quick(int a[], int l, int h){
        int pivotIndex = partition(a, l , h);
        if(l < pivotIndex)
            quick(a, l, pivotIndex-1);
        if(h > pivotIndex)
            quick(a, pivotIndex+1, h);
    }
    
    private static int partition(int[] a, int l, int h){
        int pivotValue = a[l];
        
        while(l < h){
            // Start on high side of  the list
            while(a[h] > pivotValue && l<h)
                h--;
            if(l != h){
                a[l] = a[h];
                l++;
            }

            // Reapt from the low side of hte list
            while(a[l] < pivotValue && l<h)
                l++;
            if(l != h){
                a[h] = a[l];
                h--;
            }
            
        }
        
        a[l] = pivotValue;
        return l;
        
    } // End of Quick Sort -------------------------
    
    // Merge Sort O(n) = n lg n
    public static void mergeSort(int[] a){
        int[] b = a.clone();
        order(b, a, 0, a.length-1);
        
    }
    
    // Recursive method to split the array and order the sublists
    // with two lists that are the same and manipulating both to achieve desired
    // outcome, where s is source and d is destination arrays
    // l and h are low and high points in the individuals arrays
    private static void order(int[] s, int[] d, int l, int h){
        int m = -1;
        if(l != h){
            m = (l + h)/2;
            order(d, s, l, m);
            order(d, s, m+1, h);
            merge(s, d, l, m, h);
        }
    
    }
    
    // Recursive method to merge the arrays back together using the source
    // and dest array with the low mid and high points
    // dI = destinationIndex
    private static void merge(int[] s, int[] d, int l, int m, int h){
        int i = l, j=m+1, dI = l;
        
        // Start collecting smaller values to the destination array
        do{
            if(s[i] < s[j])
                d[dI] = s[i++]; // Increments i after setting destination at destinationIndex = i and then increments
            else
                d[dI] = s[j++]; 
            dI++;
                
        } while(i<= m && j<= h);
        // Once the do while loops exits, then one list is exhausted
        // Copy the remaining elements to the destination
        
        if(i > m){
            do{
                d[dI++] = s[j++]; // Incrementing dI and j after setting the elements equal
            }while(j <= h);
        }
        else
            do{
                d[dI++] = s[i++];
            }while(i <= m);
        
        
    }
    
     public static void BogoSort(int[] a){
        while(!checkSort(a)){
            List<Integer> list = Arrays.stream(a)        // IntStream
                                    .boxed()          // Stream<Integer>
                                    .collect(Collectors.toList());
            Collections.shuffle(list);
            for(int i =0; i<list.size(); i++){
                a[i] = list.get(i);
            }
        }
        
     }

     
    private static boolean checkSort(int[] a){
        for(int i=0; i<a.length-1; i++)
           if(a[i] > a[i+1])
               return false;
        return true;
    }
    
    
    // radixSort using 3D array 
    public static void radixSort(int[] a){
        int maxLength = max(a);
        int[][][] bins = new int[maxLength][10][];
        int digit = 1;
        int index = 0;
        // Consider making bins a 3d array so that I can swap the 3d index
        // for when I want to redefine the array
        
        while(digit <= maxLength){
            countDigit(a, bins, digit);
            copyTo(a, bins, digit);
            digit++;
            index++;
        }

    }
    
    private static int max(int[] a){
        int max = a[0];
        int digit = 10;
        int count = 1;
        
        for(int i =1; i<a.length; i++){
            if(max < a[i])
                max = a[i];
        }
        
        while(max != max%digit){
            digit *= 10;
            count++;
        }

        return count;
    }
    
    private static void countDigit(int[] a, int[][][] bins, int digit){
        int[] digitCount = new int[10];
        int digitBin = 0;
        digit--;
        
        for(int i =0; i<a.length; i++){
            digitBin = a[i]/(int)Math.pow(10, digit)%10;
            digitCount[digitBin]++;
        }
        
        for(int i =0; i<digitCount.length; i++){
            bins[digit][i] = new int[digitCount[i]];
        }

    } // End of countDigit
    
    private static void copyTo(int[] a, int[][][] bins, int digit){
        int[] arrayIndex = new int[10];
        int digitBin = 0, index = 0;
        digit--;
        
        for(int i=0; i<a.length; i++){
            digitBin = a[i]/(int)Math.pow(10, digit)%10;
            bins[digit][digitBin][arrayIndex[digitBin]++] = a[i];
        }
        
        for(int i =0; i<bins[digit].length; i++){
            for(int j=0; j<bins[digit][i].length; j++){
                a[index] = bins[digit][i][j];
                index++;
            }
        }
        
    } // End of copyTo
    
}
