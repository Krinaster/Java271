
package recursivedemo;

import java.util.Scanner;


public class RecursiveDemo {


    public static void main(String[] args) {
        foo(3);
        
        Scanner keyboard = new Scanner(System.in);
        System.out.println("\nEnter n: ");
        int n = keyboard.nextInt();

        System.out.println("F" + n + "  = " + fibR(n));
        System.out.println("F" + n + "  = " + fibI(n));
        
       
        System.out.println(n + "! = " + factR(n));
        System.out.println(n + "! = " + factI(n));
        
    }
    
    private static void foo(int i){
        System.out.print(i + " ");
        if(i < 5000)
            foo(i+1);
        else 
            System.out.println("DONE");
        System.out.print(i + " ");
    }
    
    private static int fibR(int n){
        // Base Case
        if(n == 1 || n == 2)
            return 1;
        else
            return fibR(n-1) + fibR(n-2);
    }
    
    private static int fibI(int n){
        if(n == 1|| n == 2)
            return 1;
        if(n == 3)
            return 2;
        
        int prev = 1, prevprev = 1, cur = 2, count = 1, next = 0;
        
        while(count < n-2){
            next = cur + prev;
            prevprev = prev;
            prev = cur;
            cur = next;
            count++;
        }
        return cur;
    }

    private static long factR(long n){
        if(n == 0)
            return 1;
        return n* factR(n-1);
    }
    
    private static long factI(int n){
        long ans = 1;
        for(int i = 2; i<= n; i++)
            ans*=i;
        return ans;
    }

}
