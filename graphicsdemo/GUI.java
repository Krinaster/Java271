
package graphicsdemo;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;


public class GUI {
    
    private JFrame window;
    private JPanel leftPanel;
    private RedYard rightPanel;
    private Timer tictoc;
    
    public enum Direction{UP,LEFT, RIGHT, DOWN};
    
    public GUI(){
    
        window = new JFrame("Animation Demo");
        window.setSize(1000, 500);
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Create Panels
        leftPanel = new JPanel();
        leftPanel.setBackground(Color.WHITE);
        rightPanel = new RedYard();
        
        
        // rightPanel.setBackground(Color.RED);
        
        window.setBackground(Color.MAGENTA);
        window.setLayout(new GridLayout(1,3,2,0));
        window.addKeyListener(new TextListener());
        window.addMouseListener(new CityListener());
        
        // Create the timer
        tictoc = new Timer(1, new TimerListener());

        // Adding Panels and setting Frame visible
        window.add(leftPanel);
        window.add(rightPanel);
        window.setVisible(true);
        
        // Start the timer
        tictoc.start();
        
        rightPanel.createMissiles();
    } // End of GUI class
    
    private class TextListener implements KeyListener {

        @Override
        public void keyTyped(KeyEvent ke) {
            
        }

        @Override
        public void keyPressed(KeyEvent ke) {
            if(ke.getKeyCode() == KeyEvent.VK_W || ke.getKeyCode() == KeyEvent.VK_UP)
                rightPanel.updateTextLocation(Direction.UP);
            else if(ke.getKeyCode() == KeyEvent.VK_D || ke.getKeyCode() == KeyEvent.VK_RIGHT)
                rightPanel.updateTextLocation(Direction.RIGHT);
            else if(ke.getKeyCode() == KeyEvent.VK_A || ke.getKeyCode() == KeyEvent.VK_LEFT)
                rightPanel.updateTextLocation(Direction.LEFT);
            else if(ke.getKeyCode() == KeyEvent.VK_S || ke.getKeyCode() == KeyEvent.VK_DOWN)
                rightPanel.updateTextLocation(Direction.DOWN);
        }

        @Override
        public void keyReleased(KeyEvent ke) {

        }
        
    } // End of Text Listener
    
    private class CityListener implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent me) {
            
        }

        @Override
        public void mousePressed(MouseEvent me) {
           rightPanel.updateLargeCity(me.getX()-leftPanel.getWidth(),me.getY());
        }

        @Override
        public void mouseReleased(MouseEvent me) {
           
        }

        @Override
        public void mouseEntered(MouseEvent me) {
           
        }

        @Override
        public void mouseExited(MouseEvent me) {
            
        }
        
    } // End of CityListener

    private class TimerListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            rightPanel.update();
        }
        
    }
    
} // End of GUI class
