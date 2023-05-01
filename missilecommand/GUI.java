package missilecommand;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.Timer;

/**
 *
 * @author student
 */
public class GUI {
    
    private JFrame window;
    private Background panel;
    private Timer shooterTimer, missileTimer, explosionTimer, trailTimer;
    
    /**
     *
     */
    public static int missileCount = 3;
    
    /**
     *
     */
    public GUI(){
        
        
        
        // Preseting Window
        window = new JFrame("Missile Command");
        window.setSize(1600,900);
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Creating Panels
        panel = new Background();
        window.addMouseListener(new ClickListener());
        window.addKeyListener(new RefillAmmo());

        // Creating Timers
        // Looking into condensing timers into the same listening method
        // So that I just have multiple timers based on the same class 
        // Instead of 3 different timing classes
        shooterTimer = new Timer(10, new shotMissileListener());
        missileTimer = new Timer(25, new alienMissileListener());
        explosionTimer = new Timer(50, new explosionTimerListener());
        trailTimer = new Timer(750, new missileTrail());
        
        // Adding Panels
        window.add(panel);
        
        // Setting window visible
        window.setVisible(true);
        window.setResizable(false);
        
        
        
        // Starting Timer
        shooterTimer.start();
        missileTimer.start();
        explosionTimer.start();
        trailTimer.start();
        
        // Adding initial creation of Missiles
        //panel.createMissiles(missileCount);
    }

    /**
     *
     */
    public class RefillAmmo implements KeyListener{

        /**
         *
         * @param ke
         */
        @Override
        public void keyTyped(KeyEvent ke) {
           
        }

        /**
         *
         * @param ke
         */
        @Override
        public void keyPressed(KeyEvent ke) {
            if(ke.getKeyCode() == KeyEvent.VK_SPACE)
                panel.refillAmmo();
            if(ke.getKeyCode() == KeyEvent.VK_A)
                panel.createMissiles(5);
            if(ke.getKeyCode() == KeyEvent.VK_R)
                panel.numberOfMissiles();
            if(ke.getKeyCode() == KeyEvent.VK_Q)
                panel.destroyMissiles();
                
        }

        /**
         *
         * @param ke
         */
        @Override
        public void keyReleased(KeyEvent ke) {
           
        }
    
    }
    
    /**
     *
     */
    public class ClickListener implements MouseListener{

        /**
         *
         * @param me
         */
        @Override
        public void mouseClicked(MouseEvent me) {
            
        }

        /**
         *
         * @param me
         */
        @Override
        public void mousePressed(MouseEvent me) {
            //panel.getCoordinates(me.getX(),me.getY());
            if(!panel.emptyLevel()){
                panel.crossHair(me.getX(), me.getY());
                panel.shootMissile(me.getX(),me.getY());
            }
          
        }

        /**
         *
         * @param me
         */
        @Override
        public void mouseReleased(MouseEvent me) {
            
        }

        /**
         *
         * @param me
         */
        @Override
        public void mouseEntered(MouseEvent me) {
            
        }

        /**
         *
         * @param me
         */
        @Override
        public void mouseExited(MouseEvent me) {
            
        }
        
        /**
         *
         * @param me
         * @return
         */
        public int getX(MouseEvent me){
            return me.getX();
        }
        
        /**
         *
         * @param me
         * @return
         */
        public int getY(MouseEvent me){
            return me.getY();
        }
    }
    
    private class shotMissileListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            panel.updateShotMissile();
        }
    
    }
    
    private class alienMissileListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            panel.updateAlienMissile();
            // panel.createMissiles(5);
        }
    
    }
    
    private class explosionTimerListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            panel.updateExplosion();
        }
    
    }

    private class missileTrail implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            panel.createTrail();
        }

    }

} // End of GUI Class