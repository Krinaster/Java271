
package graphicsdemo;

import java.awt.Color;
import java.awt.Rectangle;


public class Missile extends Rectangle {
    
    private Color curColor;
    private double theta;
    private int missileNum;
    private int target;
    
    public Missile(int x, int y, int width, int length, double angle, int mNum){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = length;
        curColor = Color.WHITE;      
        theta = angle;
        missileNum = mNum;
    }
    
    public Color getCurColor(){
        return curColor;
    }
    
    public double getAngle(){
        return theta;
    }
    
    public void setAngle(double angle){
        theta = angle;
    }
    
    public void setTarget(int targetNum){
        target = targetNum;
    }
    
    public int getTarget(){
        return target;
    }

} // End of Missile Class
