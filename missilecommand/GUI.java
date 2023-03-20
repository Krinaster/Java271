package missilecommand;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.Timer;


public class GUI {
    
    private JFrame window;
    private Background panel;
    private Timer shooterTimer, missileTimer;
    
    public int missileCount = 20;
    
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
        shooterTimer = new Timer(10, new shotMissileListener());
        missileTimer = new Timer(25, new alienMissileListener());
        
        // Adding Panels
        window.add(panel);
        
        
        // Setting window visible
        window.setVisible(true);
        window.setResizable(false);
        
        // Adding initial creation of Missiles
        panel.createMissiles(missileCount);
        
        // Starting Timer
        shooterTimer.start();
        missileTimer.start();
        
    }

    public class RefillAmmo implements KeyListener{

        @Override
        public void keyTyped(KeyEvent ke) {
           
        }

        @Override
        public void keyPressed(KeyEvent ke) {
            if(ke.getKeyCode() == KeyEvent.VK_SPACE)
                panel.refillAmmo();
        }

        @Override
        public void keyReleased(KeyEvent ke) {
           
        }
    
    }
    
    public class ClickListener implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent me) {
            
        }

        @Override
        public void mousePressed(MouseEvent me) {
            panel.getCoordinates(me.getX(),me.getY());
            panel.shootMissile(me.getX(),me.getY());
            panel.drawRectangle(me.getX(), me.getY());
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
        
        public int getX(MouseEvent me){
            return me.getX();
        }
        
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

        }
    
    }

} // End of GUI Class