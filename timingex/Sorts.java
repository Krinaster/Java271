
package timingex;


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
        
    }
    


}
