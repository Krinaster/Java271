
package missilecommand;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class Background extends JPanel {
    
    City[] city = new City[6];
    
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
        
        // Drawing Test City
        // g2.setColor(Color.RED);
        // g2.fill(new City(Base.valley1LeftBound(),Base.valleyHeight()-41,5));
        
        
        // Will Draw Cities will default to 6 cities
        initializeCities(Base.valley1LeftBound(),Base.valley2LeftBound(), Base.valleyHeight());
        

        g2.setColor(Color.RED);
        for(City c: city)
            g2.fill(c);
        
    }
    
    private void initializeCities(int xbound1, int xbound2, int ybound1){
        int w = getWidth();
        int h = getHeight();
        int y = ybound1 - 40;
        
        city[0] = new City(xbound1,y,5);
        city[1] = new City(xbound1+city[0].getCityLength(),y,5);
        city[2] = new City(5,5,5);
        city[3] = new City(5,5,5);
        city[4] = new City(5,5,5);
        city[5] = new City(5,5,5);
    }

} // End of background