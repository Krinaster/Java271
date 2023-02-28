// File             : MazeLoader.java
// Author           : David W. Collins Jr.
// Date Created     : 03/01/2016
// Last Modified    : 03/21/2018
// Description      : This is the MazeLoader file for Math 271 where students
//                    will implement the recursive routine to "solve" the maze.
package mazemain;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.filechooser.FileNameExtensionFilter;


/** This is the main class that defines the window to load the maze
 * 
 * @author collindw
 */

public class MazeLoader {
    
    private JFrame window;
    private Scanner fileToRead;
    private JPanel[][] grid;
    private static final Color WALL_COLOR = Color.BLUE.darker();
    private static final Color PATH_COLOR = Color.MAGENTA.brighter();
    private static final Color OPEN_COLOR = Color.WHITE;
    private static final Color BAD_PATH_COLOR  = Color.RED;
    private static int ROW;
    private static int COL;
    private String data;
    private Point start;
    private boolean allowMazeUpdate;
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem[] loadMaze;
    private Timer timer;
    private JFileChooser mazeFile;
    private String lastDirectory = null;
    
    boolean visited[][] = new boolean[ROW][COL];
    /** Default constructor - initializes all private values
     * 
     */
    public MazeLoader() {
        // Intialize other "stuff"
        start = new Point();
        allowMazeUpdate = true;
        timer = new Timer(100, new TimerListener());
        // Create the maze window
        window = new JFrame("Maze Program");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Need to define the layout - as a grid depending on the number
        // of grid squares to use. Open the file and read in the size.
        try {
            
            fileToRead = new Scanner(new File("maze.txt"));
            ROW = fileToRead.nextInt();
            COL = fileToRead.nextInt();
        }
        catch(FileNotFoundException e) {
            JOptionPane.showMessageDialog(window,"Default maze not found. " +
                    "\nSelect a maze to solve from the menu," +
                    "\nor rename maze to maze.txt", "Error", JOptionPane.ERROR_MESSAGE);
            allowMazeUpdate = false;
        }

        if(allowMazeUpdate) {
            // Now establish the Layout - appropriate to the grid size
            window.setLayout(new GridLayout(ROW, COL));
            grid= new JPanel[ROW][COL];
            data = fileToRead.nextLine();
            for(int i=0; i<ROW; i++) {
                data = fileToRead.nextLine();
                for(int j=0; j<COL; j++) {
                    grid[i][j] = new JPanel();
                    grid[i][j].setName("" + i + ":" + j);
                    if(data.charAt(j) == '*') 
                        grid[i][j].setBackground(WALL_COLOR);
					// Do not add a mouse listener to the border square
                    else if(i != 0 && j != 0 && i != COL-1 && j != ROW-1) {
						grid[i][j].setBackground(OPEN_COLOR);
						grid[i][j].addMouseListener(new MazeListener());
                    }
					else // This should be the exit(s) on the maze
						grid[i][j].setBackground(OPEN_COLOR);
					
                    window.add(grid[i][j]);
                }
            }
            fileToRead.close();
            window.pack();
        }

        // Add the menu to the window
        menuBar = new JMenuBar();
        menu = new JMenu("Load Maze...");
        loadMaze = new JMenuItem[2];
        loadMaze[0] = new JMenuItem("Load New Maze from another file...");
        loadMaze[0].addActionListener(new LoadMazeFromFile());
        loadMaze[1] = new JMenuItem("Load New Maze from current maze...");
        loadMaze[1].addActionListener(new ReloadCurrentMaze());
        menu.add(loadMaze[0]);
        menu.add(loadMaze[1]);
        menuBar.add(menu);
        window.setJMenuBar(menuBar);
        
        if(!allowMazeUpdate)
            window.setSize(100,50);
       
        // Finally, show the maze
        window.setResizable(false);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
    
    /** MazeListener class reacts to mouse presses - only when the current
     *  block that is clicked is a valid starting point within the maze.
     */
    private class MazeListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
        }

        /** mousePressed method defines the (x,y) coordinate of the starting
         *  square within the maze. Note: the start Point object does NOT
         *  reference the pixel location, rather the matrix location.
         * @param e - the MouseEvent created upon mouse click.
         */
        @Override
        public void mousePressed(MouseEvent e) {
            if(((JPanel)e.getSource()).getBackground().equals(OPEN_COLOR) &&
                    !timer.isRunning()) {
                data = ((JPanel)e.getSource()).getName();
                start.x = Integer.parseInt(data.substring(0,data.indexOf(":")));
                start.y = Integer.parseInt(data.substring(data.indexOf(":")+1));
              
                // Find the maze solution
                if(!findPath(start))
                    JOptionPane.showMessageDialog(window,"Cannot exit maze.");
                else
                    JOptionPane.showMessageDialog(window, "Maze Exited!");
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }
        
    }
    
    /** findPath is the recursive routine to find the solution through the maze
     * 
     * @param p - the current Point in the maze
     * @return whether or not a solution has been found.
     */
    public boolean findPath(Point p)  {
        boolean foundSolution = false;
        // STUDENTS FINISH CODE HERE
        

        int x = p.x, y= p.y;
        if(grid[x][y].getBackground()==OPEN_COLOR&&(x==0||x==ROW-1||y==0||y==COL-1)){
            grid[x][y].setBackground(PATH_COLOR);
            foundSolution = true;
            }
        if(grid[x][y+1].getBackground() == OPEN_COLOR && foundSolution == false){
                grid[p.x][p.y].setBackground(PATH_COLOR);
                findPath(new Point(p.x,++p.y));
            }
        if(grid[x][y-1].getBackground() == OPEN_COLOR && foundSolution == false){
               grid[p.x][p.y].setBackground(PATH_COLOR); 
               findPath(new Point(p.x,--p.y));
            }
        if(grid[x+1][y].getBackground() == OPEN_COLOR && foundSolution == false){
                grid[p.x][p.y].setBackground(PATH_COLOR);
                findPath(new Point(++p.x, p.y));
            }
        if(grid[x-1][y].getBackground() == OPEN_COLOR && foundSolution == false){
                grid[p.x][p.y].setBackground(PATH_COLOR);
                findPath(new Point(--p.x, p.y));
            }
        if(foundSolution == false){
            grid[x][y].setBackground(BAD_PATH_COLOR);
            return false;
        }      
        // visited[p.x][p.y] = true;
        return foundSolution;
    }

    /** ReloadCurrentMaze class listens to menu clicks - simply
     *  wipes the current state of the maze.
     */
    private class ReloadCurrentMaze implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            for(int i=0; i<ROW; i++)
                for(int j=0; j<COL; j++)
                    if(grid[i][j].getBackground().equals(PATH_COLOR) ||
                       grid[i][j].getBackground().equals(BAD_PATH_COLOR))
                         grid[i][j].setBackground(OPEN_COLOR);
        }
    }
    
    /** LoadMazeFromFile class listens to menu clicks - if the student
     *  wishes to earn extra credit, implement this method by utilizing a
     *  FileChooser to allow the user to choose the maze file, rather than
     *  have it hard-coded in the program as "maze.txt"
     */
    private class LoadMazeFromFile implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e){

            // Removes the old grid
            for(int i =0; i<grid.length; i++)
                for(int j=0; j<grid[i].length; j++)
                    window.remove(grid[i][j]);
            // Basically setting up the JFileChooser
            // And putting a txt file filter on the JFileChooser
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter= new FileNameExtensionFilter("Text Files", "txt");
            chooser.setFileFilter(filter);
            chooser.showOpenDialog(menu);
            
            // Then figure out how to close the old program
            
            // Storing file selected from JFileChooser
            // Then storing the maze.txt file and deleting that while
            // and replacing it with an empty maze.txt file
            // That will copy the selected file's contents into it
            File selectedFile = chooser.getSelectedFile();
            File mazeFile = new File("maze.txt");
            mazeFile.delete();
            
            // The blank copy mazeFile we are copying to
            File newMazeFile = new File("maze.txt");
            
            // Try Catch Block that is trying to copy the selectedFiles
            // Contents into the maze.txt file
            
            try{
            Files.copy(selectedFile.toPath(), newMazeFile.toPath());
                System.out.println("File Copy Successful");
            }
            catch(IOException err){
                err.printStackTrace();
                System.out.println("File Copy Unsuccessful");
            }
        
            // This is copy and pasted, just remakes the JPanel
            // and makes it visually nice
            try {
                fileToRead = new Scanner(new File("maze.txt"));
                ROW = fileToRead.nextInt();
                COL = fileToRead.nextInt();
            }
            catch(FileNotFoundException errr) {
                JOptionPane.showMessageDialog(window,"Default maze not found. " +
                    "\nSelect a maze to solve from the menu," +
                    "\nor rename maze to maze.txt", "Error", JOptionPane.ERROR_MESSAGE);
                allowMazeUpdate = false;
            }

            if(allowMazeUpdate) {
            // Now establish the Layout - appropriate to the grid size
                window.setLayout(new GridLayout(ROW, COL));
                grid= new JPanel[ROW][COL];
                data = fileToRead.nextLine();
                for(int i=0; i<ROW; i++) {
                    data = fileToRead.nextLine();
                    for(int j=0; j<COL; j++) {
                        grid[i][j] = new JPanel();
                        grid[i][j].setName("" + i + ":" + j);
                        if(data.charAt(j) == '*') 
                            grid[i][j].setBackground(WALL_COLOR);
					// Do not add a mouse listener to the border square
                        else if(i != 0 && j != 0 && i != COL-1 && j != ROW-1) {
						grid[i][j].setBackground(OPEN_COLOR);
						grid[i][j].addMouseListener(new MazeListener());
                    }
					else // This should be the exit(s) on the maze
						grid[i][j].setBackground(OPEN_COLOR);
					
                        window.add(grid[i][j]);
                }
            }
                fileToRead.close();
                window.pack();
            }

        }
    
    } // end of LoadMazeFromFile class
    
    /** TimerListener class - Extra credit for students: instead of simply
     *  showing the solution path, show the solution path & any incorrect
     *  paths (and backtracking) by saving the Points in the maze visited
     *  in a "solutionArray", and in this timer method, each time the "timer"
     *  goes off, print the new state of the board according to the 
     *  solution Array. This will give the user a slowed down visualization
     *  of your recursive routine (although it would have finished already)
	 *  Additionally, you're welcome to use a container class to not only 
	 *  track the solution, but all the incorrect paths and display not only 
	 *  the correct path, but all the incorrect path choices made in the
	 *  recursive steps.
     */
    private class TimerListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            
        }
    }
}
