
package blocks;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

public class Blocks {

    public static int[] pos;
    public static Stack<Integer>[] blocks;
    public static Stack<Integer> temp;
    
    public static void main(String[] args) {

        /*Scanner stdin = new Scanner(System.in);
		int numOfBlocks = stdin.nextInt();
		stdin.nextLine();
		String input = " ";
		input = stdin.nextLine();
		System.out.println(input);
    */

	Scanner stdin = new Scanner(System.in);
	int numOfBlocks = stdin.nextInt();
	stdin.nextLine();
	blocks = new Stack[numOfBlocks];
       	temp = new Stack<>();
        pos = new int[numOfBlocks];
    	String command = null, keyword = null;
    	int num1 = 0, num2 = 0;

    	// Initializing initial positions of position array and block location
	for(int i =0; i<blocks.length; i++){
		blocks[i] = new Stack<>();
		blocks[i].push(i);
		pos[i] = i;
	}
        
	
	while(!stdin.hasNext("quit")){
		String s = stdin.nextLine();
		String[] splinter = s.split(" ", 0);
		command = splinter[0].trim();
		keyword = splinter[2].trim();
		num1 = Integer.parseInt(splinter[1]);
		num2 = Integer.parseInt(splinter[3]);

		if(command.equals("move")){
                    if(keyword.equals("onto"))
			moveOnto(num1, num2);
                    else
			moveOver(num1, num2);
		}
		else if(command.equals("pile")){
                    if(keyword.equals("onto"))
			pileOnto(num1, num2);
                    else
			pileOver(num1, num2);
		}
		else 
                    throw new Error("You messed up");
            }
            
            printBlocks();
        
            System.exit(0);
            
	} // End of Main

	public static void moveOnto(int num1, int num2){
		// First return all the blocks ontop of a and b
		int positionA = blocks[pos[num1]].indexOf(num1);
                int positionB = blocks[pos[num2]].indexOf(num2);
                
                System.out.println("blocks size " + blocks[num1].size() );
                // Cycling through array after index of num1
                System.out.println("index of " + blocks[pos[num1]].indexOf(num1));
                System.out.println("size of " + blocks[pos[num1]].size());
                System.out.println("index of pt 2" + blocks[pos[num1]].indexOf(positionA));
                while(blocks[pos[num1]].indexOf(num1) < blocks[pos[num1]].size()){
                    moveBack(blocks[pos[num1]].indexOf(++positionA));
                }
                
                // Cycling through array after index of num2
                while(blocks[pos[num2]].indexOf(num2) < blocks[pos[num2]].size()){
                    moveBack(blocks[pos[num1]].indexOf(++positionB));
                }
                
                // Pushing num1 onto num2 stack, removing it from the original stack 
                // and setting position to block 2 stack
		blocks[pos[num2]].push(num1);
                blocks[pos[num1]].remove(num1);
                pos[num1] = pos[num2];
	}

	public static void moveOver(int num1, int num2){
            // First return all block ontop of a
            int positionA = blocks[pos[num1]].indexOf(num1);
            
            // Cycling through array after index of num1
            while(blocks[pos[num1]].indexOf(num1) < blocks[pos[num1]].size()){
                moveBack(blocks[pos[num1]].indexOf(++positionA));
            }
            
            blocks[pos[num2]].push(num1);
            blocks[pos[num1]].remove(num1);
            pos[num1] = pos[num2];
            
	}

	public static void pileOnto(int num1, int num2){
            
            // Pushing every element ontop of the stack until we reach a
            for(int i =blocks[pos[num1]].size(); i>blocks[pos[num1]].indexOf(num1); i--){
                temp.push(blocks[pos[num1]].pop());
            }

            // First add all blocks and a onto temporary stack
            int positionB = blocks[pos[num2]].indexOf(num2);
            
            // Returning all blocks on top of B
            while(blocks[pos[num2]].indexOf(num2) < blocks[pos[num2]].size()){
                moveBack(blocks[pos[num1]].indexOf(++positionB));
            }
            
            // Adding temp stack to b
            while(temp.size() > 0){
                pos[temp.peek()] = pos[num2];
                blocks[pos[num2]].push(temp.pop());
            }
            
	}

	public static void pileOver(int num1, int num2){
            // Pushing every element ontop of the stack until we reach a
            for(int i =blocks[pos[num1]].size(); i>blocks[pos[num1]].indexOf(num1); i--){
                temp.push(blocks[pos[num1]].pop());
            }
            
            // Adding temp stack to b
            while(temp.size() > 0){
                pos[temp.peek()] = pos[num2];
                blocks[pos[num2]].push(temp.pop());
            }// Adding temp stack to b
            
	}
        
        
        // Method to call to move numbers back to original block location
        public static void moveBack(int num){
            //pos[num] = num;
            System.out.println("blocks size " + blocks[num].size());
            //blocks[num].push(blocks[pos[num]].pop());
            //pos[num] = num;
        }
        
        public static void printBlocks(){
            for(int i =0; i<blocks.length; i++){
                System.out.println(i + ": ");
                for(int j=0; j<blocks[i].size(); j++){
                    System.out.print(blocks[i].pop() + " ");
                }
                
            }
        
        }

} // End of class Blocks

