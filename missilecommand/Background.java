
package missilecommand;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;


public class Background extends JPanel{
    
    public Background(){
        super();
        setBackground(Color.BLACK);
        
    }
    
    // Window is 1600x900
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        
        
        // Drawing Rectange
        g2.setColor(Color.WHITE);
        g2.fillRect(100, 100, 50, 50); // Creates filled in Rectangle
        g2.drawRect(100,200,50,50); // Creates hollow Rectange
        
        // Drawing Polygon
        g2.setColor(Color.CYAN);
        g2.drawPolygon(new int[]{5,100,200,300,400,500},new int[]{850,750,750,850,850,850},6);
    }

} // End of Background

