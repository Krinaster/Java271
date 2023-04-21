
package blocks;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

public class Blocks {

	public static int[] pos;
	public static Stack<Integer>[] blocks;
    public static void main(String[] args) throws FileNotFoundException{

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

	} // End of Main

	public static void moveOnto(int num1, int num2){
		// First return all the blocks ontop of a and b
		if(blocks[pos[num1]].size() > 1){
			blocks[pos[num1]].indexOf(num1);
		}
			
		//if(blokcs[num2].size() > 1)
	}

	public static void moveOver(int num1, int num2){

	}

	public static void pileOnto(int num1, int num2){

	}

	public static void pileOver(int num1, int num2){

	}

} // End of class Blocks

