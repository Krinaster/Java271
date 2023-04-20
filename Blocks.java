
package blocks;

import java.util.Scanner;
import java.util.Stack;


public class Blocks {


    public static void main(String[] args) {
        
        Scanner stdin = new Scanner(System.in);
        int numOfBlocks = stdin.nextInt();
        Stack<Integer>[] blocks = new Stack[numOfBlocks];
        int[] pos = new int[numOfBlocks];
        
        // Initializing initial positions of position array and block location
        for(int i =0; i<blocks.length; i++){
            blocks[i] = new Stack<>();
            blocks[i].push(i);
            pos[i] = i;
        }
        
        while(!stdin.next().equals("quit")){
            String s = stdin.nextLine();
            String[] firstOp = s.split(" ", 0);
            for(int i =0; i<firstOp.length; i++)
                System.out.println(firstOp[i]);
        }
        
        
    }
    
}
