
package missilecommand;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.Timer;


public class GUI {

    private JFrame window;
    private Background panel;
    
    public GUI(){
        
        // Preseting Window
        window = new JFrame("Missile Command");
        window.setSize(1600,900);
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        // Creating Panels
        panel = new Background();
       
        
        // Adding Panels
        window.add(panel);
        

        
        // Setting window visible
        window.setVisible(true);
        
    }


} // End of GUI Class
