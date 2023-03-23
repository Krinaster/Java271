package missilecommand;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import javax.swing.JPanel;
import javax.swing.ImageIcon;

public class Background extends JPanel {
    
    City[] city = new City[6];
    private ImageIcon Shooter;
    private ArrayList<Missile> activeMissile = new ArrayList<>();
    private ArrayList<Missile> shotMissile = new ArrayList<>();
    private ArrayList<Explosion> boom = new ArrayList<>();
    private Random rand = new Random();
    private int[] Ammo = new int[3];
    
    private int curMissileNum = 10;
    private int AmmoCount = 10;
    private int explosionSize = 85;
    
    public Background(){
        super();
        setBackground(Color.BLACK);
        Shooter = new ImageIcon("Shooter.png");
        
        initializeCities();
        
        // Initializing Ammo
        refillAmmo();
        
        
    }

    @Override
    public void paintComponent(Graphics g){
        
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        
        // Drwaing Base and Coloring
        Base Base = new Base(getWidth(), getHeight());
        g2.setColor(Base.getBaseColor());
        g2.fill(Base);
        

        // Drawing Shooters
        g2.drawImage(Shooter.getImage(),Base.hill1Center(),getHeight()-220,Base.hillLength(),150, null);
        g2.drawImage(Shooter.getImage(),Base.hill2Center(),getHeight()-220,Base.hillLength(),150, null);
        g2.drawImage(Shooter.getImage(),Base.hill3Center(),getHeight()-220,Base.hillLength(),150, null);
       
        // Test drawing Missile
        // g2.draw(new Missile(0,0,50,50,0,0,0));
        
        // Drawing Test City
        // g2.setColor(Color.RED);
        // g2.fill(new City(Base.valley1LeftBound(),Base.valleyHeight()-41,5));
        
        
        // Will Draw Cities will default to 6 cities
        g2.setColor(city[0].getCityColor());
        for(City c: city)
            g2.fill(c);
        
        
        // Drawing missiles at top
        for(int i =0; i<activeMissile.size(); i++){
            // g2.rotate(activeMissile.get(i).getAngle());
            g2.setColor(activeMissile.get(i).getCurColor());
            g2.draw(activeMissile.get(i));
            repaint();
            // g2.rotate(-activeMissile.get(i).getAngle());
        }
        
        // Drawing clicked missiles
        for(int i =0; i<shotMissile.size(); i++){
            g2.setColor(shotMissile.get(i).getCurColor());
            g2.draw(shotMissile.get(i));
            repaint();
        }
        
        
        // Drawing Ammo
        g2.setFont(new Font("Comic Sans", Font.PLAIN,50));
        g2.setColor(Color.BLACK);
        g2.drawString(" "+Ammo[0], Base.hill1Center()-15, Base.valleyHeight());
        g2.drawString(" "+Ammo[1], Base.hill2Center()-15, Base.valleyHeight());
        g2.drawString(" "+Ammo[2], Base.hill3Center()-15, Base.valleyHeight());
    
        // Testing intersections
        for(int i =0; i<activeMissile.size(); i++){
            for(int j=0; j<city.length; j++){
                if(activeMissile.get(i).intersects(city[j].getBounds())){
                    activeMissile.get(i).setCurColor(Color.red);
                    city[j].setCityColor(Color.BLUE);

                    // activeMissile.remove(i);
                }
            }
        }
        
       
       // Lots of option regarding the intersection, and I think 
       // personally that maybe at the end I go through and test what is the best options
       // to see if any of them actually are good are not
       for(int i =0; i<shotMissile.size(); i++)
           if(shotMissile.get(i).intersects(shotMissile.get(i).getTargetX(), shotMissile.get(i).getTargetY(), 5, 5)){
               boom.add(new Explosion(shotMissile.get(i).getTargetX()-shotMissile.get(i).width+8, shotMissile.get(i).getTargetY()-shotMissile.get(i).height-9, 10));
               shotMissile.remove(i);
           }
       
       // Drawing the explosions
       g2.setColor(new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256)));
       for(int i =0; i<boom.size(); i++)
           g2.fill(boom.get(i));
       
       
       // Destroying alien missiles if intersected by explosion
       for(int i =0; i<boom.size(); i++)
           for(int j=0; j<activeMissile.size(); j++)
               if(boom.get(i).intersects(activeMissile.get(j))){
                   boom.add(new Explosion(activeMissile.get(j).x, activeMissile.get(j).y,25));
                   activeMissile.remove(j);
               }
       
    }// End of paint component
    
    // Method that initializes the cities
    private void initializeCities(){
        // getWidth = 1584
        // getHeight = 861
        // Valley1LeftBound 324
        // Valley2LeftBound 918
        // valleyHeight 761
        int w = 1584;
        int h = 723;
        int citySize = 6;
        
        city[0] = new City((9*w)/44+2, h ,citySize);
        city[1] = new City(city[0].getRight()+15,h,citySize);
        city[2] = new City(city[1].getRight()+15,h,citySize);
        city[3] = new City(920, h, citySize);
        city[4] = new City(city[3].getRight()+15,h, citySize);
        city[5] = new City(city[4].getRight()+15,h, citySize);
        
    }
    
    // Method that gets the coordinates of a click
    public void getCoordinates(int x, int y){
        System.out.println("X: " + x + " Y: " + y);
    }
    
    
    // FYI
    // This method needs an overhaul, but for right now it works fine
    
    // Method that shoots the missiles when you click
    public void shootMissile(int x, int y){
        // shotMissile.add(new Missile(50, 500, 20,20,Math.atan2(x-50,y-500),1,1));
        
        if(x < getWidth()/3 && Ammo[0] >0){
            shotMissile.add(new Missile(190,620,20,20,Math.atan2(x-200,y-620),1,x,y));
            Ammo[0]--;
            System.out.println(Ammo[0]);
        }
        else if(x > getWidth()/3 && x<(2*getWidth())/3 && Ammo[1] > 0){
            shotMissile.add(new Missile(getWidth()/2,620,20,20,Math.atan2(x-(getWidth()/2),y-620),1,x,y));
            Ammo[1]--;
        }
        else if(x > (2*getWidth())/3 && Ammo[2] > 0){
            shotMissile.add(new Missile(1370,620,20,20,Math.atan2(x-1370,y-620),1,x,y));
            Ammo[2]--;
        }
        else if(Ammo[0] == 0 && Ammo[1] > 0){
            shotMissile.add(new Missile(getWidth()/2,620,20,20,Math.atan2(x-(getWidth()/2),y-620),1,x,y));
            Ammo[1]--;
        }
        else if(Ammo[0] == 0 && Ammo[1] == 0 && Ammo[2] > 0){
            shotMissile.add(new Missile(1370,620,20,20,Math.atan2(x-1370,y-620),1,x,y));
            Ammo[2]--;
        }
        
    }
    
   
    // Method that moves the shooter missiles
    public void updateShotMissile(){
        int deltaX = 6, deltaY = 6;
        for(int i=0; i<shotMissile.size(); i++){
            shotMissile.get(i).translate((int)(deltaX*Math.sin(shotMissile.get(i).getAngle())),
                    (int)(deltaY*Math.cos(shotMissile.get(i).getAngle())));
            shotMissile.get(i).setAngle(Math.atan2(shotMissile.get(i).getTargetX()- shotMissile.get(i).getX()-13,
                   shotMissile.get(i).getTargetY() - shotMissile.get(i).getY()-20));
            // System.out.println(shotMissile.get(i).getAngle());
        }
    
    } 
    
    // Method for reilling shooter Ammo
    public void refillAmmo(){
        for(int i =0; i<Ammo.length; i++)
            Ammo[i] = AmmoCount;
    }
    
    // Method that creates the alien missiles
    public void createMissiles(int n){
        for(int i =0; i<n; i++){
            int x = rand.nextInt(getWidth());
            int target = rand.nextInt(city.length);
            // activeMissile.add(new Missile(x, 5, 20,20,270, curMissileNum++, target));
            activeMissile.add(new Missile(x, -5, 10,15, Math.atan2(city[target].getCenterX()-x, city[target].getCenterY()), curMissileNum++, 
                    city[target].getCenterX(),city[target].getCenterY()));
            // Need to figure out equation so that angle is not messed up so as that I can use this formula to calculate angle
            // Math.atan2(city[0].getCenterX()-x+10,city[0].getBottom()+10)
        }
    
    }

    // Method that translates the alien missiles
    public void updateAlienMissile(){
        int deltaX = 3, deltaY = 3;
        for(int i=0; i<activeMissile.size(); i++){
            activeMissile.get(i).translate((int)(deltaX*Math.sin(activeMissile.get(i).getAngle())),
                    (int)(deltaY*Math.cos(activeMissile.get(i).getAngle())));
            activeMissile.get(i).setAngle(Math.atan2(activeMissile.get(i).getTargetX()- activeMissile.get(i).getX(),
                   activeMissile.get(i).getTargetY() - activeMissile.get(i).getY()));
         
            // Update the angle as the missile moves
        }
    
    }
    
    public void updateExplosion(){
        
        for(int i =0; i<boom.size(); i++){
            if(boom.get(i).sizeCount <= explosionSize)
                boom.get(i).grow();
            else
                boom.get(i).shrink();
        }
    }
    
} // End of background