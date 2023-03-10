
package graphicsdemo;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class RedYard extends JPanel{
    
    private City[] city = new City[2];
    private Point strLoc;
    
    private final int DELTA = 8;
    private double deltaX = -5;
    private double deltaY = -4;
    private int deltaM = 7;
    private double theta = .1;
    private ImageIcon Plane;
    private ImageIcon Missile;
    private int curLevel = 1, curMissileNum = 1;
    private ArrayList<Missile> activeMissile = new ArrayList<>();
    private Random rand = new Random();
    
    private final int MISSILE_WIDTH = 10;
    private final int MISSILE_CHANGE_PER_LEVEL = 2;
    private final int INITIAL_NUMBER_OF_MISSILES = 3;
    
    public RedYard() {
        super();
        setBackground(Color.RED);
        initializeCities();
        strLoc = new Point(25,300);
        Plane = new ImageIcon("Plane.png");
        Missile = new ImageIcon("Missile2.png");
        
          
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        
        // Draw the background
        g2.drawImage(Missile.getImage(), 0, 0, getWidth(), getHeight() , null);
        
        // Draw a square
        g2.setColor(Color.black);
        g2.fillRect(50,50,50,50);
        g2.setColor(Color.WHITE);
        g2.drawRect(50, 50, 50, 50);
        
        // Draw a rectangle
        g2.setColor(Color.black);
        g2.fillRect(300,50,50,10);
        g2.setColor(Color.WHITE);
        g2.drawRect(50, 50, 50, 50);
        
        // Draw Circle
        g2.setColor(Color.black);
        g2.fillOval(50,150,100,100);
        g2.setColor(Color.WHITE);
        g2.drawOval(50, 150, 100, 100);
        
        // Draw an ellipse
        g2.setColor(Color.black);
        g2.fillOval(200,200,100,40);
        g2.setColor(Color.WHITE);
        g2.drawOval(200, 200, 100, 40);
        
        // Draw Line
        g2.setColor(Color.BLUE);
        g2.drawLine(0,0,500,469);
        
        // Draw Arc
        g2.setColor(Color.BLACK);
        g2.drawArc(10, 300, 100, 100, 50, 270);
        
        // Draw Smaller Arc
        g2.setColor(Color.BLACK);
        g2.drawArc(25, 315, 75, 75, 50, 270);
        
        // Topline to close C
        g2.setColor(Color.BLACK);
        g2.drawLine(87, 322, 92, 312);
        
        // Bottom arc to close C
        g2.setColor(Color.BLACK);
        g2.drawArc(83,377,16,16,30,60);
        
        // Drawing String
        g2.setColor(Color.GREEN);
        g2.setFont(new Font("Comic Sans", Font.BOLD,44));
        g2.drawString("PacMan", strLoc.x, strLoc.y);
        //g2.drawGlyphVector(gv, TOP_ALIGNMENT, TOP_ALIGNMENT);
        
        
        // Drawing the cities and filling them
        //g2.setColor(Color.BLUE);
        //for(City c: city)
        //    g2.fill(c);
        
        g2.setColor(city[1].getCityColor());
        g2.fill(city[1]);
        // Rotate to draw the small city
        
        g2.rotate(theta, (int)(city[0].getRight() + city[0].getLeft())/2, (int)(city[0].getBottom() + city[0].getTop())/2);
        g2.setColor(city[0].getCityColor());
        g2.fill(city[0]);
        g2.rotate(-theta, (int)(city[0].getRight() + city[0].getLeft())/2, (int)(city[0].getBottom() + city[0].getTop())/2);
        theta += .1;
        
        g2.drawImage(Plane.getImage() , 100, 100, 100, 50, null);
        
        // Draw the missiles
        for(int i =0; i<activeMissile.size(); i++){
            g2.setColor(activeMissile.get(i).getCurColor());
            g2.draw(activeMissile.get(i));
                   
        }
        
        // Test for any collisions
        for(int i =0; i<activeMissile.size(); i++){
            if(activeMissile.get(i).intersects(city[0].getBounds()))
                city[0].setCityColor(new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256)));
        }
    
    }
    private void initializeCities(){
        city[0] = new City(25, 325, 6);
        city[1] = new City(200, 325, 4);
    }
    
    public void updateTextLocation(GUI.Direction d){
        
        if(d == GUI.Direction.DOWN)
            strLoc.y +=  DELTA;
        else if(d == GUI.Direction.UP)
            strLoc.y -=  DELTA;
        else if(d == GUI.Direction.LEFT)
            strLoc.x -=  DELTA;
        else
            strLoc.x +=  DELTA;
        System.out.println(strLoc.x + " " + strLoc.y);
        
        repaint();
    }

    public void updateLargeCity(int x, int y){
        city[1].translate(x-city[1].xpoints[0], y-city[1].ypoints[0]);
        // Or create an accessor/getter for the City class to return the x value
        repaint();
    }
    
    public void update(){
        city[0].translate((int)deltaX,(int)deltaY);
        repaint();
        
        // Change direction if city reaches boundaries
        if(city[0].getTop() < 0 || city[0].getBottom() > getHeight())
            deltaY *= -1.001;
        if(city[0].getRight() > getWidth() || city[0].getLeft() < 0)
            deltaX *= -1.001;
        System.out.println("Left: " + city[0].getLeft() + " Top : " + city[0].getTop());
        
        // How to update the missiles
        // Fine tuning needed to move toward city position
      
    }
    
     public void createMissiles(){
        for(int i =0; i<INITIAL_NUMBER_OF_MISSILES+MISSILE_CHANGE_PER_LEVEL*curLevel; i++){
            int x = rand.nextInt(getWidth());
            int target = rand.nextInt(city.length);
            activeMissile.add(new Missile( x , 5, MISSILE_WIDTH, MISSILE_WIDTH,
                    Math.atan2(city[0].getCenterX()-x+MISSILE_WIDTH/2,
                            city[0].getBottom()+MISSILE_WIDTH/2),
                        curMissileNum++, target));
        }
    }
    
    public void updateMissiles() {
        for(int i=0; i<activeMissile.size(); i++) {
            activeMissile.get(i).translate((int)(deltaM*Math.sin(activeMissile.get(i).getAngle())),
                                           (int)(deltaM*Math.cos(activeMissile.get(i).getAngle())));
            activeMissile.get(i).setAngle(Math.atan2(city[activeMissile.get(i).getTarget()].getCenterX() - activeMissile.get(i).getX() + MISSILE_WIDTH/2,
                                       city[activeMissile.get(i).getTarget()].getBottom() -
                                              activeMissile.get(i).getY() + MISSILE_WIDTH/2));
            
            // Turns rotating city into discoball
            city[0].setCityColor(new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256)));
        }
        
        System.out.println("CITYX: " + city[activeMissile.get(0).getTarget()].getCenterX());
        System.out.println("MISSX: " + activeMissile.get(0).getX());
    }


    /*public void updateMissiles(){
        for(int i =0; i<activeMissile.size(); i++){
            activeMissile.get(i).translate((int)(deltaM*Math.sin(activeMissile.get(i).getAngle())),
                    (int)(deltaM*Math.cos(activeMissile.get(i).getAngle())));
                   
            activeMissile.get(i).setAngle(Math.atan2(city[0].getCenterX() - activeMissile.get(i).getX()+MISSILE_WIDTH/2,
                        city[activeMissile.get(i).getTarget()].getBottom() - activeMissile.get(i).getY() + MISSILE_WIDTH/2));
            
            activeMissile.get(i).setTarget(activeMissile.get(i).getTarget());
        }
      */  
        /*if(!activeMissile.isEmpty()){
            activeMissile.get(0).translate((int)(deltaM*Math.sin(activeMissile.get(0).getAngle())),
                    (int)(deltaM*Math.cos(activeMissile.get(0).getAngle())));
            activeMissile.get(0).setAngle(Math.atan2(city[0].getCenterX() - activeMissile.get(0).getX(),
                        city[0].getBottom() - activeMissile.get(0).getY() + MISSILE_WIDTH));
        } // End of if statement
        */
    

} // End of RedYard Class
