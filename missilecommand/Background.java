package missilecommand;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JPanel;
import javax.swing.ImageIcon;

public class Background extends JPanel {
    
    // City[] city = new City[6];
    private ImageIcon Shooter;
    private ArrayList<Missile> activeMissile = new ArrayList<>();
    private ArrayList<Missile> shotMissile = new ArrayList<>();
    private ArrayList<Explosion> boom = new ArrayList<>();
    private ArrayList<City> city = new ArrayList<>();
    private Random rand = new Random();
    private int[] Ammo = new int[3];
    
    private int AmmoCount = 10;
    private int explosionSize = 85;
    private int alienDX = 3, alienDY = 3;
    private int shotDX = 6, shotDY = 6;
    private int shooter1X = 190, shooter2X = 792, shooter3X = 1370;
    private int shooterY = 620;
    private int score = 0;

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
        
        
        // Will Draw Cities will default to 6 cities
        for(int i =0; i<city.size(); i++){
            g2.setColor(city.get(i).getCityColor());
            g2.fill(city.get(i));
        }
        
        // This is missile is required to not crash my program
        // Dont know why, but its important
        activeMissile.add(new Missile(-1000000,-1000000, 1, 1, 1, 1, 1, 1));
        
        // Drawing missiles at top
        for(int i =0; i<activeMissile.size(); i++){
            // Rotating all the missiles around their center to angle towards their target
            // and setting the color and drawing accordingly
            g2.rotate(Math.PI * 2 -activeMissile.get(i).getAngle(),activeMissile.get(i).getCenterX(), activeMissile.get(i).getCenterY());
            g2.setColor(activeMissile.get(i).getCurColor());
            g2.draw(activeMissile.get(i));
            g2.rotate(-(Math.PI * 2 -activeMissile.get(i).getAngle()),activeMissile.get(i).getCenterX(), activeMissile.get(i).getCenterY());
            repaint();
        }
        
        // Drawing clicked missiles
        if(!shotMissile.isEmpty()){
            for(int i =0; i<shotMissile.size(); i++){
                g2.setColor(shotMissile.get(i).getCurColor());
                g2.draw(shotMissile.get(i));
                repaint();
            }
        }
        
        // Drawing Ammo
        g2.setFont(new Font("Comic Sans", Font.PLAIN,50));
        g2.setColor(Color.BLACK);
        g2.drawString(" "+Ammo[0], Base.hill1Center()-15, Base.valleyHeight());
        g2.drawString(" "+Ammo[1], Base.hill2Center()-15, Base.valleyHeight());
        g2.drawString(" "+Ammo[2], Base.hill3Center()-15, Base.valleyHeight());
        
       
       // Lots of option regarding the intersection, and I think 
       // personally that maybe at the end I go through and test what is the best options
       // to see if any of them actually are good are not
       if(!shotMissile.isEmpty()){
            for(int i =0; i<shotMissile.size(); i++)
                if(shotMissile.get(i).intersects(shotMissile.get(i).getTargetX(), shotMissile.get(i).getTargetY(), 5, 5)){
                    boom.add(new Explosion(shotMissile.get(i).getTargetX()-shotMissile.get(i).width+8, shotMissile.get(i).getTargetY()-shotMissile.get(i).height-9, 10));
                    shotMissile.remove(i);
            }
        }
       // Drawing the explosions
       g2.setColor(new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256)));
       if(!boom.isEmpty()){
            for(int i =0; i<boom.size(); i++)
                g2.fill(boom.get(i));
       }
       
       // Destroying alien missiles if intersected by explosion
       if(!boom.isEmpty()){
       for(int i =0; i<boom.size(); i++)
           for(int j=0; j<activeMissile.size(); j++)
               if(boom.get(i).intersects(activeMissile.get(j))){
                   boom.add(new Explosion(activeMissile.get(j).x, activeMissile.get(j).y,25));
                   activeMissile.remove(j);
               }
            }

        // City Destruction calling method
        cityDestruction();

        // Removes unnecessary elements from the explosion ArrayList
        explosionCleanUp();
            
      
    }// End of paint component -------------------------------------------
    
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
        
        city.add(new City((9*w)/44+2, h ,citySize));
        city.add(new City(city.get(0).getRight()+15,h,citySize));
        city.add(new City(city.get(1).getRight()+15,h,citySize));
        city.add(new City(920, h, citySize));
        city.add(new City(city.get(3).getRight()+15,h, citySize));
        city.add(new City(city.get(4).getRight()+15,h, citySize));

        // city[0] = new City((9*w)/44+2, h ,citySize);
        // city[1] = new City(city[0].getRight()+15,h,citySize);
        // city[2] = new City(city[1].getRight()+15,h,citySize);
        // city[3] = new City(920, h, citySize);
        // city[4] = new City(city[3].getRight()+15,h, citySize);
        // city[5] = new City(city[4].getRight()+15,h, citySize);
        
    }
    
    // Method that gets the coordinates of a click
    public void getCoordinates(int x, int y){
        System.out.println("X: " + x + " Y: " + y);
    }
    
    
    // Method that shoots the missiles when you click
    public void shootMissile(int x, int y){

        // Look into hashmaps and see if that is an easier solution
        // to this iterative solution

        double min, med, max;
        int closest = 0, next = 0, furtherest = 0;

        double dist1 = Math.sqrt(Math.pow(x-shooter1X, 2)+Math.pow(y-shooterY, 2));
        double dist2 = Math.sqrt(Math.pow(x-shooter2X, 2)+Math.pow(y-shooterY, 2));
        double dist3 = Math.sqrt(Math.pow(x-shooter3X, 2)+Math.pow(y-shooterY, 2));

        min = min(dist1, dist2, dist3);
        med = med(dist1, dist2, dist3);
        max = max(dist1, dist2, dist3);

        if(dist1 == min)
            closest = 1;
        else if(dist2 == min) 
            closest = 2;
        else if(dist3 == min) 
            closest = 3;

        if(dist1 == med)
            next = 1;
        else if(dist2 == med) 
            next = 2;
        else if(dist3 == med)
            next = 3;

        if(dist1 == max)
            furtherest = 1;
        else if(dist2 == max) 
            furtherest = 2;
        else if(dist3 == max)
            furtherest = 3;

        if(Ammo[--closest] > 0)
            shoot(closest,x ,y);
        else if(Ammo[--next] > 0)
            shoot(next, x, y);
        else if(Ammo[--furtherest] > 0)
            shoot(furtherest, x, y);
        


    }

    public void shoot(int shooter, int x, int y){
        // shotMissile.add(new Missile())
        shooter++;
        
        switch(shooter){
            case 1: shotMissile.add(new Missile(shooter1X, shooterY, 20, 20, Math.atan2(x-200, y-shooterY), 0, x, y)); Ammo[--shooter]--; break;
            case 2: shotMissile.add(new Missile(shooter2X, shooterY, 20, 20, Math.atan2(x-792, y-shooterY), 0, x, y)); Ammo[--shooter]--; break;
            case 3: shotMissile.add(new Missile(shooter3X, shooterY, 20, 20, Math.atan2(x-1370, y-shooterY), 0, x, y));Ammo[--shooter]--;break;
            default: System.out.println("This isnt supposed to happen");
        }

    }
    
   
    // Method that moves the shooter missiles
    public void updateShotMissile(){
        for(int i=0; i<shotMissile.size(); i++){
            shotMissile.get(i).translate((int)(shotDX*Math.sin(shotMissile.get(i).getAngle())),
                    (int)(shotDY*Math.cos(shotMissile.get(i).getAngle())));
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
            int target = rand.nextInt(city.size());
            // activeMissile.add(new Missile(x, 5, 20,20,270, curMissileNum++, target));
            activeMissile.add(new Missile(x, -5, 20,30, Math.atan2(city.get(target).getCenterX()-x, city.get(target).getCenterY()), target, 
                    city.get(target).getCenterX(),city.get(target).getCenterY()));
            // Need to figure out equation so that angle is not messed up so as that I can use this formula to calculate angle
            // Math.atan2(city[0].getCenterX()-x+10,city[0].getBottom()+10)
        }
    
    }

    // Method that translates the alien missiles
    public void updateAlienMissile(){
        for(int i=0; i<activeMissile.size(); i++){
            activeMissile.get(i).translate((int)(alienDX*Math.sin(activeMissile.get(i).getAngle())),
                    (int)(alienDY*Math.cos(activeMissile.get(i).getAngle())));
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

    public static double min(double a, double b, double c){
        return Math.min(Math.min(a,b),c);
    }

    public static double med(double a, double b, double c){
        return Math.min(Math.min(Math.max(a,b),Math.max(b,c)), Math.max(a,c));
    }

    public static double max(double a, double b, double c){
        return Math.max(Math.max(a,b),c);
    }

    public void explosionCleanUp(){
        if(!boom.isEmpty()){
            for(int i =0; i<boom.size(); i++)
                if(boom.get(i).height <= 5)
                    boom.remove(i);
        }
    }

    public void cityDestruction(){
        for(int i =0; i<activeMissile.size(); i++)
            for(int j=0; j<city.size(); j++)
                if(activeMissile.get(i).intersects(city.get(j).getBounds2D())){
                    boom.add(new Explosion(city.get(j).getCenterX(), city.get(j).getCenterY(), 15));
                    city.remove(j);
                    // activeMissile.remove(i);
                }
    }
    
} // End of background
