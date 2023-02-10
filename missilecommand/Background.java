
package missilecommand;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class Background extends JPanel {
    
    public Background(){
        super();
        setBackground(Color.BLACK);
        
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        
        // Drawing Rectangle
        g2.setColor(Color.WHITE);
        g2.fillRect(100,100,50,50);
        g2.drawRect(100,200,50,50);
        
        // Drwaing Base and Coloring
        Base Base = new Base(getWidth(), getHeight());
        g2.setColor(Base.getBaseColor());
        g2.fill(Base);
        
        
        
    }
} // End of background
